package ru.telegramBot.gm.app.urfuData;

public class Cabinet {
    public Cabinet(String number, int lesson){
        this.number = number;
        this.status[lesson] = 1;
    }

    private String address;
    private final String number;
    private int[] status = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    public String getNumber(){
        return this.number;
    }
    public void print() {System.out.println("print");};
}
