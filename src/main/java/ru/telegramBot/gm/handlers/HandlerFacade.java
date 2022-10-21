package ru.telegramBot.gm.handlers;

import ru.telegramBot.gm.handlers.commandHandlers.CommandFacade;
import ru.telegramBot.gm.handlers.textHandlers.TextFacade;
import ru.telegramBot.gm.readers.RequestData;
import ru.telegramBot.gm.writers.ResponseData;

import java.util.List;

/**
 * Основной класс обработки всех текстовых данных
 */
public class HandlerFacade implements Handler {

    // Все команды и обработчики, которые поддерживает бот
    private final List<Handler> handlers = List.of(
            new CommandFacade(),
            new TextFacade()
    );

    /**
     * Метод, выполняющий обраьотку любых данных
     *
     * @param data Данные полученные при чтении
     * @return RD, которую формирует конкретный обработчик
     */
    @Override
    public ResponseData handle(RequestData data) {
        for (Handler handler : handlers){
            ResponseData responseData = handler.handle(data);
            if (responseData != null)
                return responseData;
        }
        System.out.println("Не найден обработчик для данных");
        return null;
    }
}
