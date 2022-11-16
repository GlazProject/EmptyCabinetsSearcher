package ru.telegramBot.gm.app.urfuData;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;


public class Main {
    public static void main(String[] args) throws ParseException, IOException, CanNotGetScheduleException {
        University urfu = new University();
        HttpRequests requests = new HttpRequests();

        List<Group> urfuGroups = UrFUApi.convertStringToGroups(requests.request(UrFUApi.AllGroupsUrl));
        urfu.addGroups(urfuGroups);

        for (Group it : urfu.getGroups())
        {
            String url = "https://urfu.ru/api/schedule/groups/calendar/" + it.id();
            System.out.println("group " + it.id());
            List<Cabinet> cabinets = UrFUApi.getSchedule(requests.request(url));
            System.out.println("new add:");
            for (Cabinet cabinet: cabinets){
                System.out.println(cabinet.Number + " hi");
            }
            urfu.addCabinets(cabinets);
        }
    }
}