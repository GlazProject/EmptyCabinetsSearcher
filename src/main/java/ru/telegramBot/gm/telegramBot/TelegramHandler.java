package ru.telegramBot.gm.telegramBot;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.telegramBot.gm.handlers.CommandsHandler;
import ru.telegramBot.gm.readers.RequestData;
import ru.telegramBot.gm.writers.ResponseData;

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
        CommandsHandler handler = new CommandsHandler();
        ResponseData responseData = handler.handle(data);
        return new TelegramResponseData(responseData, update.getMessage());
    }

}
