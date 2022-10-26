package ru.telegramBot.gm.telegramBot;

import ru.telegramBot.gm.dataContainer.components.TelegramChatIDComponent;
import ru.telegramBot.gm.handlers.Handler;
import ru.telegramBot.gm.handlers.HandlerFacade;
import ru.telegramBot.gm.readers.RequestData;
import ru.telegramBot.gm.writers.ResponseData;

import javax.annotation.Nullable;

/**
 * Обработчик, который обрабатывает все данные, полученные через Telegram
 */
public class TelegramHandler implements Handler {

    /**
     * Данный метод реализует проверку и обработку сообщения, полученного от Telegram.
     *
     * @param data Контейнер с данными, сформированный из сообщения
     * @return Контейнер, который содержит обработанные данные,
     * либо null, если не удалось обработать данные или полученные данные не принадлежали telegram
     */
    @Nullable
    public ResponseData handle(RequestData data) {
        if (data == null || data.getComponent(TelegramChatIDComponent.class) == null)
            return null;

        HandlerFacade handler = new HandlerFacade();
        ResponseData responseData = handler.handle(data);

        if (responseData == null)
            return null;
        responseData.setComponent(data.getComponent(TelegramChatIDComponent.class));

        return responseData;
    }

}
