package ru.telegramBot.gm.handlers.textHandlers;

import ru.telegramBot.gm.handlers.HandlerV1;
import ru.telegramBot.gm.readers.RequestData;
import ru.telegramBot.gm.writers.ResponseData;

/**
 * Главный обработчик всех некомандных текстов
 */
public class TextHandler implements HandlerV1 {
    private final HandlerV1 echoHandler;

    public TextHandler() {
        echoHandler = new SimpleEchoHandler();
    }

    /**
     * Выполняет обработку любого текстового запроса, не сожержащего кооманды
     *
     * @param data Данные полученные при чтении
     * @return RD с информацией, полученной от конкретного обработчика
     */
    @Override
    public ResponseData handle(RequestData data) {
        if (!correctVersion(data))
            return null;
        return echoHandler.handle(data);
    }
}
