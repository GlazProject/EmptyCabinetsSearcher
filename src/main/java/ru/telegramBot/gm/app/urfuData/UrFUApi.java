package ru.telegramBot.gm.app.urfuData;


import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrFUApi {
    //TODO переехать на парсинг html страницы (html текста), которые будут получаться с сайта
    // https://urfu.ru/api/schedule/groups/lessons/<group_id>
    // Лучше всего использовать для этого BeautifulSoup
    // Если сможешь написать парсинг такой страницы, чтобы получить аналог метода getSchedule(string htmlPage),
    // то это будет потрясающе. К сожалению через Ical мы не можем получить достаточно информации :(

    private static final String AllGroupsUrl = "https://urfu.ru/api/schedule/groups/";

    private static final String OneGroupSchedule = "https://urfu.ru/api/schedule/groups/lessons/";

    public static final String getOneGroupSchedule(){return OneGroupSchedule;}

    public static final String getAllGroupsUrl(){return AllGroupsUrl;}

    public static List<Group> convertStringToGroups(String groups) throws ParseException {
       JSONParser parser = new JSONParser();
       JSONArray jsonArray = (JSONArray) parser.parse(groups);
        List<Group> groupList = new ArrayList<>();

        for (Object it : jsonArray) {
            org.json.simple.JSONObject groupInfo = (org.json.simple.JSONObject) it;
            groupList.add(parseGroupInfo(groupInfo));
        }

        return groupList;
   }

    private static @NotNull Group parseGroupInfo(@NotNull JSONObject groupInfo) {
        long id = (long) groupInfo.get("id");
        long instituteId = (long) groupInfo.get("institute_id");
        return new Group((int)id, (int)instituteId);
    }

    public static List<Cabinet> CreateAllCabinets(List<Group> groups) throws IOException {
        List<Cabinet> AllCabinets = new ArrayList<>();
        for (Group group : groups) {

            //System.out.println("group.id() - " + group.id());

            String GroupSchedule = OneGroupSchedule + group.id();
            Document oneDayClasses = AreThereClasses(GroupSchedule);
            if (oneDayClasses != null){
                CreateOneGroupCabinets(oneDayClasses, AllCabinets);

            }
        }
        return AllCabinets;
    }

    private static Document AreThereClasses(String groupShedule) throws IOException {
        Document doc = Jsoup.connect(groupShedule).get();
        String StringDocument = doc.toString();
        Pattern newDay = Pattern.compile(">\\d{2}\\s\\W+<");
        Matcher matcher = newDay.matcher(StringDocument);
        String oneDay = null;
        int c = 0;

        while (matcher.find()) {
            c += 1;
            if (c == 2) {
                break;
            }
            oneDay = StringDocument.substring(0, matcher.find() ? matcher.start() : -1);
            //System.out.println(oneDay);
        }
        //System.out.println(oneDay);
        if (oneDay != null) {
            //System.out.println(oneDay);
            Document newDoc = Jsoup.parse(oneDay);
            Elements classes = newDoc.select("dl[class=shedule-weekday-item]");
            if (classes.size() != 0){
                return newDoc;
            }
        }
        return null;
    }

    private static void CreateOneGroupCabinets(Document oneDayClasses, List<Cabinet> AllCabinets){
        List<Cabinet> cabs = new ArrayList<>();

        //List<String> timeForCabs = new ArrayList<>();
        int numberOfClass;

        Elements lessons = oneDayClasses.select("tr[class=shedule-weekday-row]");
        for (Element lesson : lessons) {

            List<String> timeForCabs = new ArrayList<>();

            Elements num = lesson.select("dd");
            String num2 = num.text().substring(0, 1);
            numberOfClass = Integer.parseInt(num2);

            String time = lesson.selectFirst("td[class=shedule-weekday-time]").text();
            for (String s : time.split("\\s\\-\\s")){
                String j[] = s.split("\\:");
                String j2 = j[0]+ j[1];
                timeForCabs.add(j2);
            }

            ArrayList<String> certainLessons = new ArrayList();

            Elements classes = lesson.select("dl[class=shedule-weekday-item]");
            for (Element cl : classes){
                if (cl.select("span[class=cabinet]").hasText()){
                    certainLessons.add(cl.select("span[class=cabinet]").text());
                }
            }
            if (!(certainLessons.isEmpty())){
                for (String place : certainLessons){
                    if (!(place.equals("Teams Microsoft")) && !(place.equals("Microsoft Teams")) && !(place.equals("Moodle"))){
                        int index = place.lastIndexOf("(гибридный формат)");
                        if (index != -1){
                            place = place.substring(0, index) + place.substring(index + 19, place.length());
                        }
                        Pattern apprCab = Pattern.compile(".+\\s{1}[а-яА-Я-\\s]+\\,+\\s\\w+");//".+\\s{1}\\w+"    ".+\\s{1}[а-яА-Я-\\s]+"
                        Matcher match = apprCab.matcher(place);
                        if (match.find()){
                            String pr = place.substring(match.start(), match.end());

                            String[] location = pr.split(" ", 2);

                            Cabinet newCab = new Cabinet(location[0].toString(), location[1].toString());
                            int isExists = AllCabinets.indexOf(newCab);
                            if (isExists == -1){
                                newCab.occupy(convertStringToTime(timeForCabs.get(0)), convertStringToTime(timeForCabs.get(1)), numberOfClass);
                                AllCabinets.add(newCab);
                            }
                            else {
                                AllCabinets.get(isExists).occupy(convertStringToTime(timeForCabs.get(0)), convertStringToTime(timeForCabs.get(1)), numberOfClass);
                            }

                            //System.out.println("cab " + newCab.Number + " " + newCab.Address + newCab.getLessons());

                        }

                    }
                }
            }
        }
    }

    /*private static int Isnew(Cabinet other){

    }*/

    @Contract("null -> fail")
    private static @NotNull Time convertStringToTime(String time){
        if (time == null || time.length() % 2 != 0)
            throw new IllegalArgumentException("Time format should be HHMM or HHMMSS");


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < time.length()/2; i++){
            if (i > 0)
                sb.append(":");
            sb.append(time.charAt(i*2));
            sb.append(time.charAt(i*2 + 1));
        }

        String timeValue = sb.toString() + ":00";
        return Time.valueOf(timeValue);
    }

}