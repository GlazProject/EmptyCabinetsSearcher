package ru.telegramBot.gm.dataContainer.components;

import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * Компонент контейнера данных, который хранит одно сообщение из Telegram
 */
public class TelegramMessageComponent extends Component{
    private Message message;

    /**
     * Метод, который позволяет получить сообщение Telegram из компонента
     * @return Соообщение, сохраненное в компонент
     */
    @Override
    public Message get() {
        return message;
    }

    /**
     * Метод, который позволяет добавить соообщение Telegram в компонент
     * @param value Сообщение, полученное в Telegram
     * @exception ClassCastException, если переданный объект не является TelegramMessage
     */
    @Override
    public void set(Object value) {
        if (!(value instanceof Message)){
            throw new ClassCastException();
        }
        message = (Message) value;
    }
}
