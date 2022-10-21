package ru.telegramBot.gm.telegramBot;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.telegramBot.gm.handlers.HandlerFacade;
import ru.telegramBot.gm.readers.RequestData;
import ru.telegramBot.gm.writers.ResponseData;

import java.security.KeyException;

/**
 * Обработчик данных, полученных из телеграм.
 */

/*
 * Через этот обработчик происходит вся обработка данных, полученных в Update.
 * Из сообщения выделяется текст и передаётся в обработчика команд
 */
public class TelegramHandler {

    /**
     * Данный метод реализует проверку и обработку сообщения, полученного от Telegram.
     *
     * @param data Контейнер с данными, сформированный из сообщения
     * @param update Обновление, которое вызвало обработку
     * @return TRD, который содержит сообщение, на которое отвечали, и все данные, сформированные обработчиком.
     */
    public TelegramResponseData handle(RequestData data, Update update) {
        if (data == null)
            return null;
        HandlerFacade handler = new HandlerFacade();
        ResponseData responseData = handler.handle(data);
        try {
            return new TelegramResponseData(responseData, update.getMessage());
        } catch (KeyException e){
            return null;
        }

    }

}
