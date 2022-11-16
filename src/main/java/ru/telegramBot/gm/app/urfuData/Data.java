package ru.telegramBot.gm.app.urfuData;

public class Data {
    // TODO понять, как вообще работает и дать правильное название
    private int num_of_lesson;
    public Data(String lesson){
        for (int i = 0; i < time_1.length; i++){
            if (lesson.equals(time_1[i])){
                num_of_lesson = i;
            }
        }
        for (int i = 0; i < time_2.length; i++){
            if (lesson.equals(time_2[i])){
                num_of_lesson = i;
            }
        }
    }
    public static String START1_1 = "0900";
    public static String START1_2 = "1040";
    public static String START1_3 = "1250";
    public static String START1_4 = "1430";
    public static String START1_5 = "1610";
    public static String START1_6 = "1750";
    public static String START1_7 = "1930";
    public String[] time_1 = {START1_1, START1_2, START1_3, START1_4, START1_5, START1_6, START1_7};

    public static String START2_1 = "0830";
    public static String START2_2 = "1015";
    public static String START2_3 = "1200";
    public static String START2_4 = "1415";
    public static String START2_5 = "1600";
    public static String START2_6 = "1740";
    public static String START2_7 = "1915";
    public String[] time_2 = {START2_1, START2_2, START2_3, START2_4, START2_5, START2_6, START2_7};

    public int getNum_of_lesson(){
        return num_of_lesson;
    }
}
