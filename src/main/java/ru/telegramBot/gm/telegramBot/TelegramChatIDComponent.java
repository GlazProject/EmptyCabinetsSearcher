package ru.telegramBot.gm.telegramBot;

import ru.telegramBot.gm.dataComponents.Component;

import java.util.Objects;

/**
 * Компонент контейнера данных, который хранит одно сообщение из Telegram
 */
public class TelegramChatIDComponent implements Component {
    private final Long id;

    public TelegramChatIDComponent(Long chatID){
        id = chatID;
    }

    /**
     * Метод, который позволяет получить id чата Telegram из компонента
     * @return ID чата, сохраненный в компонент
     */
    public Long getID() {
        return id;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        return Objects.equals(((TelegramChatIDComponent) obj).id, id);
    }
}
