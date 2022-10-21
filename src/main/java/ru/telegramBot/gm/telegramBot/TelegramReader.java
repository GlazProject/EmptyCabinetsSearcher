package ru.telegramBot.gm.telegramBot;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.telegramBot.gm.readers.RequestData;

import java.security.KeyException;

public class TelegramReader {
    @Nullable
    public RequestData readDataFromUpdate(Update update) {
        if (!update.hasMessage())
            return null;
        Message message = update.getMessage();
        if (!message.hasText())
            return null;

        RequestData requestData = new RequestData();
        try {
            requestData.setToComponent("text", message.getText());
        } catch (KeyException e){
            return null;
        }

        return requestData;
    }
}
