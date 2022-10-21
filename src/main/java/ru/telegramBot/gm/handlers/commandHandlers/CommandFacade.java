package ru.telegramBot.gm.handlers.commandHandlers;

import ru.telegramBot.gm.handlers.Handler;
import ru.telegramBot.gm.readers.RequestData;
import ru.telegramBot.gm.writers.ResponseData;

import java.util.List;

public class CommandFacade implements Handler {

    private final List<Handler> handlers = List.of(
            new StartCommandHandler(),
            new UnknownCommandHandler()
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
