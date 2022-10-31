package ru.telegramBot.gm.handlers.textHandlers;

import ru.telegramBot.gm.handlers.BaseHandlersFacade;

/**
 * Фасад, который позволяет обрабатывать тексты, не содержащие команды
 */
public class TextFacade extends BaseHandlersFacade {

    /**
     * Задание списка всех обработчиков, которым даётся возможность обработать данные
     */
    public TextFacade(){
        super(new SimpleEchoHandler());
    }
}
