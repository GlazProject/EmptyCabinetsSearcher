package ru.telegramBot.gm.app.urfuData;

import java.util.ArrayList;
import java.util.List;

public class University {
    private final List<Group> groups;
    private final List<Cabinet> cabinets;

    public University() {
        groups = new ArrayList<>();
        cabinets = new ArrayList<>();
    }

    public void addGroups(List<Group> newGroups){
        groups.addAll(newGroups);
    }

    public void addCabinets(List<Cabinet> newCabinets){
        cabinets.addAll(newCabinets);
    }

    public List<Group> getGroups(){
        return groups;
    }

    public List<Cabinet> getCabinets(){
        return cabinets;
    }
}
