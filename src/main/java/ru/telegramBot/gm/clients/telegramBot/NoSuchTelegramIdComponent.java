package ru.telegramBot.gm.clients.telegramBot;

/**
 * ������, ������� ��������, ��� �� ������ ���������, ���������� ID ���� ��� Telegram
 */
public class NoSuchTelegramIdComponent extends Exception {
    /**
     * Constructor.
     */
    public NoSuchTelegramIdComponent() {
        super();
    }

    /**
     * Constructor with a detail message.
     *
     * @param s the detail message
     */
    public NoSuchTelegramIdComponent(String s) {
        super(s);
    }
}
