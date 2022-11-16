package ru.telegramBot.gm.app.urfuData;

import java.util.Arrays;

public class Cabinet {
    public final String Address;
    public final int Number;

    private static final int LESSONS_COUNT = 10;
    private final boolean[] isBusy = new boolean[LESSONS_COUNT];

    public Cabinet(String address, int number, int... busyLessons){
        Number = number;
        Address = address;
        Arrays.fill(isBusy, false);
        for (int lesson: busyLessons) {
            occupy(lesson);
        }
    }

    public boolean isBusy(int lessonNumber){
        if (lessonNumber > isBusy.length || lessonNumber <= 0)
            throw new IndexOutOfBoundsException();
        return isBusy[lessonNumber-1];
    }

    public void occupy(int lessonNumber){
        setIsBusyStatus(true, lessonNumber);
    }

    public void release(int lessonNumber){
        setIsBusyStatus(false, lessonNumber);
    }

    private void setIsBusyStatus(boolean value, int number){
        if (number > isBusy.length || number <= 0)
            throw new IndexOutOfBoundsException();
        isBusy[number-1] = value;
    }

    @Override
    public String toString(){
        return String.format("%s, cabinet №%s", Address, Number);
    }
}
