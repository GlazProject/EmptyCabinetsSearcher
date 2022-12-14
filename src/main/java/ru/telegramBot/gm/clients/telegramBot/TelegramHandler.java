package ru.telegramBot.gm.clients.telegramBot;

import ru.telegramBot.gm.app.dataContainers.RequestData;
import ru.telegramBot.gm.app.dataContainers.ResponseData;
import ru.telegramBot.gm.app.handlers.Handler;
import ru.telegramBot.gm.app.handlers.HandlerFacade;

import javax.annotation.Nullable;

/**
 * Обработчик, который обрабатывает все данные, полученные через Telegram
 */
public class TelegramHandler implements Handler {
    private final HandlerFacade handler = new HandlerFacade();

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

        ResponseData responseData = handler.handle(data);

        if (responseData == null)
            return null;
        responseData.setComponent(data.getComponent(TelegramChatIDComponent.class));

        return responseData;
    }
}
