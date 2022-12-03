package ru.telegramBot.gm.app.urfuData;


public class Main {
    public static void main(String[] args) throws CanNotGetScheduleException {
        University matMeh = UrfuManager.collectCabinetsInfo(62404);
        for (Cabinet cab : matMeh.getCabinets()){
            System.out.println("cab " + cab.Number + " " + cab.Address + cab.getLessons());
        }
    }
}