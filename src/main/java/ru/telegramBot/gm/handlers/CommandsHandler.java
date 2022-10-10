package ru.telegramBot.gm.handlers;

import ru.telegramBot.gm.handlers.commandHandlers.StartCommandHandler;
import ru.telegramBot.gm.handlers.commandHandlers.UnknownOrNonCommandHandler;
import ru.telegramBot.gm.readers.RequestData;
import ru.telegramBot.gm.writers.ResponseData;

import java.util.ArrayList;
import java.util.List;

/**
 * Основной класс обработки всех текстовых данных
 */
public class CommandsHandler implements Handler {
    // Все команды и обработчики, которые поддерживает бот
    private final List<Handler> handlers;

    public CommandsHandler(){
         handlers = new ArrayList<>();
         fillHandlers(handlers);
    }

    /**
     * Метод, выполняющий обраьотку любых текстовых данных (с командой или без)
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

    /**
     * Данный метод заполняет список обработчиков команд
     */
    private void fillHandlers(List<Handler> handlers){
        handlers.add(new StartCommandHandler());
        handlers.add(new UnknownOrNonCommandHandler());
    }
}
