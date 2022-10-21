package ru.telegramBot.gm.handlers.textHandlers;

import ru.telegramBot.gm.handlers.Handler;
import ru.telegramBot.gm.readers.RequestData;
import ru.telegramBot.gm.writers.ResponseData;

import java.util.List;

public class TextFacade implements Handler {

    private final List<Handler> handlers = List.of(
            new SimpleEchoHandler()
    );

    @Override
    public ResponseData handle(RequestData data) {
        for (Handler handler : handlers){
            ResponseData responseData = handler.handle(data);
            if (responseData != null)
                return responseData;
        }
        return null;
    }
}
