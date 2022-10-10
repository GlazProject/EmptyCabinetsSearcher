package ru.telegramBot.gm.handlers.textHandlers;

import ru.telegramBot.gm.handlers.HandlerV1;
import ru.telegramBot.gm.readers.RequestData;
import ru.telegramBot.gm.readers.RequestDataV1;
import ru.telegramBot.gm.writers.ResponseData;
import ru.telegramBot.gm.writers.ResponseDataV1;

/**
 * Простой обработчик сообщений, возвращающий исходные
 * данные без изменения
 */
public class SimpleEchoHandler implements HandlerV1 {

    /**
     * Метод, возвращающий исходную строку
     *
     * @param data Это строка с исходным текстом
     * @return RD с текстом исходной строки в поле text
     */
    @Override
    public ResponseData handle(RequestData data) {
        if (!correctVersion(data))
            return null;
        RequestDataV1 requestData = (RequestDataV1)data;
        return new ResponseDataV1(requestData.getText());
    }
}
