package ru.telegramBot.gm.app.urfuData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.ParseException;


public class Main {
    public static void main(String[] args) throws ParseException, IOException, CanNotGetScheduleException {
        University urfu = new University();

        HttpRequests requests = new HttpRequests();

        List<Group> urfuGroups = UrFUApi.convertStringToGroups(requests.request(UrFUApi.getAllGroupsUrl()));
        urfu.addGroups(urfuGroups);

        List<Cabinet> cabinets= UrFUApi.CreateAllCabinets(urfu.getGroups());
        urfu.addCabinets(cabinets);

        for (Cabinet cab : urfu.getCabinets()){
            System.out.println("cab " + cab.Number + " " + cab.Address + cab.getLessons());
        }



    }
}