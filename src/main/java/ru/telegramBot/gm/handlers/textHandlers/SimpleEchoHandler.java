package ru.telegramBot.gm.handlers.textHandlers;

import ru.telegramBot.gm.dataContainer.components.TextComponent;
import ru.telegramBot.gm.handlers.Handler;
import ru.telegramBot.gm.readers.RequestData;
import ru.telegramBot.gm.writers.ResponseData;

/**
 * Простой обработчик сообщений, возвращающий исходные
 * данные без изменения
 */
public class SimpleEchoHandler implements Handler {

    /**
     * Метод, возвращающий исходную строку
     *
     * @param data Это строка с исходным текстом
     * @return RD с текстом исходной строки в поле text
     */
    @Override
    public ResponseData handle(RequestData data) {
        TextComponent textComponent = data.getComponent("text");
        if (textComponent == null)
            return  null;
        ResponseData responseData = new ResponseData();
        responseData.setComponent("text", textComponent);
        return responseData;
    }
}
