package ru.telegramBot.gm.handlers.commandHandlers;

import ru.telegramBot.gm.handlers.Handler;
import ru.telegramBot.gm.handlers.HandlersFacade;

import java.util.Collections;

/**
 * Фасад, который позволяет обрабатывать тексты, содержащие команды
 */
public class CommandFacade extends HandlersFacade {

    /**
     * Задание списка всех обработчиков, которым даётся возможность обработать данные
     */
    public CommandFacade(){
        super();
        handlers.add(new StartCommandHandler());
        handlers.add(new UnknownCommandHandler());
    }

    /**
     * Задание списка всех обработчиков, которым даётся возможность обработать данные
     *
     * @param optionalHandlers Массив экземпляров обработчиков,
     *                         которым нужно дополнительно давать возможность обработать данные
     */
    public CommandFacade(Handler[] optionalHandlers){
        super();
        Collections.addAll(handlers, optionalHandlers);
        handlers.add(new StartCommandHandler());
        handlers.add(new UnknownCommandHandler());
    }
}
