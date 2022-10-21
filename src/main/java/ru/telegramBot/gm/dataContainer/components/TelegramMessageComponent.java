package ru.telegramBot.gm.dataContainer.components;

import org.telegram.telegrambots.meta.api.objects.Message;

public class TelegramMessageComponent extends Component{
    private Message message;

    @Override
    public Message get() {
        return message;
    }

    @Override
    public void set(Object value) {
        if (!(value instanceof Message)){
            throw new ClassCastException();
        }
        message = (Message) value;
    }
}
