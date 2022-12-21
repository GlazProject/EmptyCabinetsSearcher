package ru.telegramBot.gm.app.urfuData;

import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

record LessonBounds(Time Start, Time End, int lessonNumber){}
public class Cabinet
{
    public final String Address;
    public final String Number;

    private final Set<LessonBounds> lessons = new HashSet<>();

    public Cabinet(String address, String number){
        Number = number;
        Address = address;
    }

    public boolean isBusy(Time time){
        for(LessonBounds lessonBounds : lessons){
            if (lessonBounds.Start().before(time)
                && lessonBounds.End().after(time))
                return true;
        }
        return false;
    }

    public void occupy(Time start, Time end, int lessonNumber){
        lessons.add(new LessonBounds(start, end, lessonNumber));
    }

    public Set<LessonBounds>  getLessons(){return lessons;}

    @Override
    public String toString(){
        return String.format("%s, cabinet №%s", Address, Number);
    }

    @Override
    public boolean equals(Object other) {
        if (this.getClass() != other.getClass())
            return false;
        return ((Cabinet) other).Number.equals(this.Number) &&
                ((Cabinet) other).Address.equals(this.Address);
    }
}
