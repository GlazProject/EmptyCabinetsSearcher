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
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrFUApi {
    private static final String AllGroupsUrl = "https://urfu.ru/api/schedule/groups/";
    private static final String OneGroupSchedule = "https://urfu.ru/api/schedule/groups/lessons/";

    public static String getOneGroupSchedule(){return OneGroupSchedule;}

    public static String getAllGroupsUrl(){return AllGroupsUrl;}

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
        return new Group(id, instituteId);
    }

    public static List<Cabinet> createAllCabinets(List<Group> groups) throws IOException {
        List<Cabinet> allCabinets = new LinkedList<>();
        for (Group group : groups) {
            String groupSchedule = OneGroupSchedule + group.id();
            Document oneDayClasses = areThereClasses(groupSchedule);
            if (oneDayClasses != null){
                createOneGroupCabinets(oneDayClasses, allCabinets);
            }
        }
        return allCabinets;
    }

    private static Document areThereClasses(String groupSchedule) {
        HttpRequests requests = new HttpRequests();
        String groupScheduleStr;

        try{
            groupScheduleStr = requests.request(groupSchedule);
        } catch (Exception e){
            return null;
        }

        Document doc = Jsoup.parse(groupScheduleStr);
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
        }

        if (oneDay != null) {
            Document newDoc = Jsoup.parse(oneDay);
            Elements classes = newDoc.select("dl[class=shedule-weekday-item]");
            if (classes.size() != 0){
                return newDoc;
            }
        }
        return null;
    }

    private static void createOneGroupCabinets(Document oneDayClasses, List<Cabinet> AllCabinets){
        int numberOfClass;

        Elements lessons = oneDayClasses.select("tr[class=shedule-weekday-row]");
        for (Element lesson : lessons) {

            List<String> timeForCabs = new ArrayList<>();

            Elements num = lesson.select("dd");
            String num2 = num.text().substring(0, 1);
            numberOfClass = Integer.parseInt(num2);

            String time = Objects.requireNonNull(lesson.selectFirst("td[class=shedule-weekday-time]")).text();
            for (String s : time.split("\\s\\-\\s")){
                String[] j = s.split("\\:");
                String j2 = j[0]+ j[1];
                timeForCabs.add(j2);
            }

            ArrayList<String> certainLessons = new ArrayList<>();

            Elements classes = lesson.select("dl[class=shedule-weekday-item]");
            for (Element cl : classes){
                if (cl.select("span[class=cabinet]").hasText()){
                    certainLessons.add(cl.select("span[class=cabinet]").text());
                }
            }
            if (!(certainLessons.isEmpty())){
                for (String place : certainLessons){
                    if (!(place.equals("Teams Microsoft"))
                            && !(place.equals("Microsoft Teams"))
                            && !(place.equals("Moodle"))){
                        int index = place.lastIndexOf("(гибридный формат)");
                        if (index != -1){
                            place = place.substring(0, index) + place.substring(index + 19);
                        }
                        Pattern apprCab = Pattern.compile(".+\\s{1}[а-яА-Я-\\s]+\\,+\\s\\w+");//".+\\s{1}\\w+"    ".+\\s{1}[а-яА-Я-\\s]+"
                        Matcher match = apprCab.matcher(place);
                        if (match.find()){
                            String pr = place.substring(match.start(), match.end());

                            String[] location = pr.split(" ", 2);

                            Cabinet newCab = new Cabinet(location[1], location[0]);
                            int isExists = AllCabinets.indexOf(newCab);
                            if (isExists == -1){
                                newCab.occupy(convertStringToTime(timeForCabs.get(0)), convertStringToTime(timeForCabs.get(1)), numberOfClass);
                                AllCabinets.add(newCab);
                            }
                            else {
                                AllCabinets.get(isExists).occupy(convertStringToTime(timeForCabs.get(0)), convertStringToTime(timeForCabs.get(1)), numberOfClass);
                            }
                        }

                    }
                }
            }
        }
    }

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

        String timeValue = sb + ":00";
        return Time.valueOf(timeValue);
    }

}