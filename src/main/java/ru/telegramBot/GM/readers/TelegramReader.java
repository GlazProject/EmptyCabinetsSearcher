package ru.telegramBot.GM.readers;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TelegramReader {

    public RequestData<Message> readFrom(Update update){
        if (update.hasMessage() && update.getMessage().hasText()){
            return new RequestData<>(update.getMessage());
        }
        return null;
    }
}
