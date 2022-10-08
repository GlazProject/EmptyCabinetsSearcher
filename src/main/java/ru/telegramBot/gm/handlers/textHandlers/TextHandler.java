package ru.telegramBot.gm.handlers.textHandlers;

import ru.telegramBot.gm.handlers.Handler;
import ru.telegramBot.gm.writers.ResponseData;

public class TextHandler implements Handler<String, ResponseData> {

    @Override
    public ResponseData handle(String data) {
        return new SimpleEchoHandler().handle(data);
    }
}
