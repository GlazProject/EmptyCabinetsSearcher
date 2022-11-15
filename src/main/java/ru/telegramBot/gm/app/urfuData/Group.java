package ru.telegramBot.gm.app.urfuData;

public class Group {
    private final int institute_id;
    private final int id;

    public Group(int m_id, int m_institute_id){
        id = m_id;
        institute_id = m_institute_id;
    }
    public String getId(){
        String stringId = Integer.toString(id);
        return stringId;
        //return id;
    }
    public int getInstitute_id() {return institute_id;}
}
