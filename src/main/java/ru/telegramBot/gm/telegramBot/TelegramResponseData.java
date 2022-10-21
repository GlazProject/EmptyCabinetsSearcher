package ru.telegramBot.gm.telegramBot;


import org.telegram.telegrambots.meta.api.objects.Message;
import ru.telegramBot.gm.dataContainer.components.TelegramMessageComponent;
import ru.telegramBot.gm.writers.ResponseData;

import java.security.KeyException;

/*
 * Класс, предназначенный для хранения спецефических телеграм данных, в дополнение к данным ответов обработчика
 */
public class TelegramResponseData extends ResponseData{
    public TelegramResponseData(ResponseData oldData, Message message) throws KeyException {
        super(oldData);
        dataComponents.put("message", new TelegramMessageComponent());
        setToComponent("message", message);
    }
}
