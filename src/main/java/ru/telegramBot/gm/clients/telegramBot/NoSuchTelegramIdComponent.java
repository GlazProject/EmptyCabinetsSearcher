package ru.telegramBot.gm.clients.telegramBot;

/**
 * Ошибка, которая означает, что не найден компонент, содержащий ID чата для Telegram
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
