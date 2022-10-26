package ru.telegramBot.gm.handlers.textHandlers;

import ru.telegramBot.gm.handlers.Handler;
import ru.telegramBot.gm.handlers.BaseHandlersFacade;

import java.util.Collections;

/**
 * Фасад, который позволяет обрабатывать тексты, не содержащие команды
 */
public class TextFacade extends BaseHandlersFacade {

    /**
     * Задание списка всех обработчиков, которым даётся возможность обработать данные
     */
    public TextFacade(){
        super();
        handlers.add(new SimpleEchoHandler());
    }

    /**
     * Задание списка всех обработчиков, которым даётся возможность обработать данные
     *
     * @param optionalHandlers Массив экземпляров обработчиков,
     *                         которым нужно дополнительно давать возможность обработать данные
     */
    public TextFacade(Handler[] optionalHandlers){
        super();
        Collections.addAll(handlers, optionalHandlers);
        handlers.add(new SimpleEchoHandler());
    }
}
