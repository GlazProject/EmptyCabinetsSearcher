package ru.telegramBot.gm.app.urfuData;


import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.Property;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class UrFUApi {
    public static final String AllGroupsUrl = "https://urfu.ru/api/schedule/groups/";

    public static List<Group> convertStringToGroups(String groups) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(groups);
        List<Group> groupList = new ArrayList<>();

        for (Object it : jsonArray) {
            JSONObject groupInfo = (JSONObject) it;
            groupList.add(parseGroupInfo(groupInfo));
        }

        return groupList;
    }

    private static @NotNull Group parseGroupInfo(@NotNull JSONObject groupInfo) {
        int id = (int) groupInfo.get("id");
        int instituteId = (int) groupInfo.get("institute_id");
        return new Group(id, instituteId);
    }

    private static Calendar createCalendarByIcal(String icalSchedule)
            throws ParserException, IOException {
        StringReader inputStream = new StringReader(icalSchedule);
        CalendarBuilder builder = new CalendarBuilder();
        return builder.build(inputStream);
    }

    public static List<Cabinet> getSchedule(String icalSchedule) throws IOException, CanNotGetScheduleException {
        List<Cabinet> cabinets = new ArrayList<>();

        Calendar calendar;
        try {
            calendar = createCalendarByIcal(icalSchedule);
        } catch (ParserException e) {
            throw new CanNotGetScheduleException();
        }

        int lastPair = -1;
        for (final Component component : calendar.getComponents()) {
            int dStart = -1;
            String instituteAddress = null;
            for (final Property property : component.getProperties()) {
                switch (property.getName()) {
                    case ("DTSTART") -> {
                        // TODO rename dSt
                        String dSt = property.getValue().substring(9, 13);
                        // TODO rename Data
                        Data data = new Data(dSt);
                        dStart = data.getNum_of_lesson();
                    }
                    case ("LOCATION") -> {
                        instituteAddress = property.getValue();
                    }
                }
            }
            if (dStart < lastPair && dStart != -1)
                break;

            lastPair = dStart;
            if (instituteAddress != null) {
                Cabinet cabinet = new Cabinet(instituteAddress, dStart);
                cabinets.add(cabinet);
            }
        }
        return cabinets;
    }
}