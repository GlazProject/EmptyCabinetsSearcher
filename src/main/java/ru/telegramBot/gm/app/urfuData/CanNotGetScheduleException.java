package ru.telegramBot.gm.app.urfuData;

public class CanNotGetScheduleException extends Exception {
    /**
     * Constructor.
     */
    public CanNotGetScheduleException() {
        super();
    }

    /**
     * Constructor with a detail message.
     *
     * @param s the detail message
     */
    public CanNotGetScheduleException(String s) {
        super(s);
    }
}
