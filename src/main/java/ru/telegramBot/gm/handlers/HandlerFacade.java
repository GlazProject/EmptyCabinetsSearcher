package ru.telegramBot.gm.handlers;

import ru.telegramBot.gm.handlers.commandHandlers.CommandFacade;
import ru.telegramBot.gm.handlers.textHandlers.TextFacade;

/**
 * Фасад, который позволяет обрабатывать любые данные
 */
public class HandlerFacade extends BaseHandlersFacade {

    /**
     * Задание списка всех обработчиков (фасадов), которым даётся возможность обработать данные
     */
    public HandlerFacade(){
        super(new CommandFacade(), new TextFacade());
    }
}