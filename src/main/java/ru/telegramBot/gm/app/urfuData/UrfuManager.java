package ru.telegramBot.gm.app.urfuData;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class UrfuManager {

    public static University collectCabinetsInfo(long... institutesIds)
            throws CanNotGetScheduleException
    {
        University urfu = new University();

        HttpRequests requests = new HttpRequests();
        String groupsJson = requests.request(UrFUApi.getAllGroupsUrl());

        List<Group> urfuGroups;
        try {
            urfuGroups = UrFUApi.convertStringToGroups(groupsJson);
        } catch (ParseException e) {
            throw new CanNotGetScheduleException(e);
        }
        List<Group> selectedGroups = new LinkedList<>();
        if(institutesIds.length > 0){
            for (long id : institutesIds)
                selectedGroups.addAll(getOnlyOneInstituteIdGroups(urfuGroups, id));
            urfu.addGroups(selectedGroups);
        }
        else
            urfu.addGroups(urfuGroups);

        List<Cabinet> cabinets;
        try {
            cabinets = UrFUApi.createAllCabinets(urfu.getGroups());
        } catch (IOException e) {
            throw new CanNotGetScheduleException(e);
        }
        urfu.addCabinets(cabinets);

        return urfu;
    }

    private static List<Group> getOnlyOneInstituteIdGroups(List<Group> groups, long instituteId){
        List<Group> selectedGroups = new LinkedList<>();
        for (Group group : groups) {
            if (group.instituteId() == instituteId)
                selectedGroups.add(group);
        }
        return selectedGroups;
    }
}
