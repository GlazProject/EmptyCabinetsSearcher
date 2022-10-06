package ru.telegramBot.gm.handlers.commandHandlers;

import ru.telegramBot.gm.handlers.Handler;
import ru.telegramBot.gm.writers.ResponseData;

public class UnknownCommandHandler implements Handler<String, ResponseData> {
    @Override
    public ResponseData handle(String data) {
        return new ResponseData("Я не знаю, как реагировать на такую команду");
    }
}
