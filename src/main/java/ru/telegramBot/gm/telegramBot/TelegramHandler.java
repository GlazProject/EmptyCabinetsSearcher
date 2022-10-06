package ru.telegramBot.gm.telegramBot;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.telegramBot.gm.handlers.CommandsHandler;
import ru.telegramBot.gm.handlers.Handler;
import ru.telegramBot.gm.writers.ResponseData;

public class TelegramHandler implements Handler<Update, TelegramResponseData> {

    @Override
    public TelegramResponseData handle(Update data) {
        if (!data.hasMessage())
            return null;
        Message message = data.getMessage();
        if (!message.hasText())
            return null;

        CommandsHandler handler = new CommandsHandler();
        ResponseData responseData = handler.handle(message.getText());
        return new TelegramResponseData(responseData, message);
    }

}
