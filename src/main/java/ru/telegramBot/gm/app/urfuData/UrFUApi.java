package ru.telegramBot.gm.app.urfuData;


import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.Property;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class UrFUApi{

    List<Group> UrFUGroups;
    List <Cabinet> UrFUCabinets;

    public List<Cabinet> getSchedule(String innerNumber) throws IOException, ParserException {
        List<Cabinet> cabinets = new ArrayList<>();
        String myCalendarString = innerNumber;
        StringReader sin = new StringReader(myCalendarString);
        CalendarBuilder builder = new CalendarBuilder();
        Calendar calendar = builder.build(sin);
        //CalendarBuilder builder = new CalendarBuilder();
        //final UnfoldingReader ufrdr = new UnfoldingReader(new FileReader(ics), true);
        //Calendar calendar = builder.build(ufrdr);
        int last_pair = -1;
        oneDay: {
            for (final Object o : calendar.getComponents()) {
                Component component = (Component) o;
                //System.out.println("new");
                int dStart = -1;
                String locationNum = "-1";
                for (final Property property : component.getProperties()) {
                    switch (property.getName()){
                        case ("DTSTART") -> {
                            String dSt = property.getValue().substring(9, 13);
                            Data data = new Data(dSt);
                            dStart = data.getNum_of_lesson();
                        }
                        case ("LOCATION") -> {
                            locationNum = property.getValue();
                        }
                    }
                }
                if (dStart < last_pair & dStart != -1){
                    break oneDay;
                }
                last_pair = dStart;
                //int num_locationNum = Integer.parseInt(locationNum);
                if (locationNum != "-1"){
                    Cabinet cabinet = new Cabinet(locationNum, dStart);
                    cabinets.add(cabinet);
                    //System.out.println("cabinet " + cabinet.getNumber() + " " + dStart);
                }
            }
        }
        return cabinets;
    }

    public String getStringOfGroups(){
        // https://urfu.ru/api/schedule/groups/ позволяет получить все группы УрФУ
        String url = "https://urfu.ru/api/schedule/groups/";
        return url;
    }

    public static List<Group> convertStringToGroups(String groups) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(groups);
        List<Group> groupList = new ArrayList<>();
        for (Object it : jsonArray) {
            //System.out.println(it);
            JSONObject jsonGroup = (JSONObject) it;

            long id = (long)jsonGroup.get("id");
            long institute_id = (long)jsonGroup.get("institute_id");
            Group group = new Group((int)id, (int)institute_id);
            groupList.add(group);
        }
        return groupList;
    }




}