package ru.telegramBot.gm.app.urfuData;

import java.io.IOException;
import java.util.List;
import net.fortuna.ical4j.data.ParserException;
import org.json.simple.parser.ParseException;


public class main {
    public static void main(String[] args) throws ParseException, ParserException, IOException {
        UrFUApi urfu = new UrFUApi();
        HttpRequests newRequest = new HttpRequests();
        urfu.UrFUGroups = urfu.convertStringToGroups(newRequest.request(urfu.getStringOfGroups()));
        for (Group it : urfu.UrFUGroups)
        {
            String url = "https://urfu.ru/api/schedule/groups/calendar/" + it.getId();
            System.out.println("group " + it.getId());
            List<Cabinet> cabinets = urfu.getSchedule(newRequest.request(url));
            System.out.println("new add:");
            for (Cabinet cab: cabinets){
                System.out.println(cab.getNumber() + " hi");
            }
            if (cabinets != null){
                urfu.UrFUCabinets.addAll(cabinets);
            }
            //urfu.UrFUCabinets.addAll(cabinets);
        }
    }
}