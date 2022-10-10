package ru.telegramBot.gm.telegramBot;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.telegramBot.gm.readers.RequestDataV1;

public class TelegramReader {
    public RequestDataV1 readDataFromUpdate(Update update){
        if (!update.hasMessage())
            return null;
        Message message = update.getMessage();
        if (!message.hasText())
            return null;
        return new RequestDataV1(message.getText());
    }
}
