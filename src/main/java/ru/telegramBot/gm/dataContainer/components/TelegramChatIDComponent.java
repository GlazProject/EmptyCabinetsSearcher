package ru.telegramBot.gm.dataContainer.components;

/**
 * Компонент контейнера данных, который хранит одно сообщение из Telegram
 */
public class TelegramChatIDComponent implements Component{
    private Long id;

    public TelegramChatIDComponent(){}
    public TelegramChatIDComponent(Long chatID){
        setID(chatID);
    }

    /**
     * Метод, который позволяет получить id чата Telegram из компонента
     * @return ID чата, сохраненный в компонент
     */
    public Long getID() {
        return id;
    }

    /**
     * Метод, который позволяет добавить id чата Telegram в компонент
     * @param value id чата в Telegram
     */
    public void setID(Long value) {
        id = value;
    }
}
