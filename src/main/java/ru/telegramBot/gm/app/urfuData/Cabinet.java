package ru.telegramBot.gm.app.urfuData;

import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

record LessonBounds(Time Start, Time End, int lessonNumber){}
public class Cabinet
{
    public final String Address;
    public final int Number;

    private final Set<LessonBounds> lessonsBounds = new HashSet<>();

    public Cabinet(String address, int number){
        Number = number;
        Address = address;
    }

    public boolean isBusy(Time time){
        for(LessonBounds lessonBounds : lessonsBounds){
            if (lessonBounds.Start().before(time)
                && lessonBounds.End().after(time))
                return true;
        }
        return false;
    }

    public void occupy(Time start, Time end, int lessonNumber){
        lessonsBounds.add(new LessonBounds(start, end, lessonNumber));
    }

    @Override
    public String toString(){
        return String.format("%s, cabinet №%s", Address, Number);
    }

    @Override
    public int hashCode(){
        return (Address.hashCode() * 3 + Number) % Integer.MAX_VALUE;
    }

    @Override
    public boolean equals(Object obj){
        if (! (obj instanceof Cabinet other))
            return false;
        return other.Number == this.Number &&
                other.Address.equals(this.Address);
    }
}
