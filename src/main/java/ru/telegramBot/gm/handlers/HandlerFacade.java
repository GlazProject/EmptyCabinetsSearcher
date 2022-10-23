package ru.telegramBot.gm.handlers;

import ru.telegramBot.gm.handlers.commandHandlers.CommandFacade;
import ru.telegramBot.gm.handlers.textHandlers.TextFacade;

import java.util.Collections;

/**
 * Фасад, который позволяет обрабатывать любые данные
 */
public class HandlerFacade extends HandlersFacade {

    /**
     * Задание списка всех обработчиков (фасадов), которым даётся возможность обработать данные
     */
    public HandlerFacade(){
        super();
        handlers.add(new CommandFacade());
        handlers.add(new TextFacade());
    }

    /**
     * Задание списка всех обработчиков (фасадов), которым даётся возможность обработать данные
     *
     * @param optionalHandlers Массив экземпляров обработчиков (фасадов),
     *                         которым нужно дополнительно давать возможность обработать данные
     */
    public HandlerFacade(Handler[] optionalHandlers){
        super();
        Collections.addAll(handlers, optionalHandlers);
        handlers.add(new CommandFacade());
        handlers.add(new TextFacade());
    }
}