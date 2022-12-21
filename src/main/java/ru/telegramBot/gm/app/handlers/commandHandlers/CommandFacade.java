package ru.telegramBot.gm.app.handlers.commandHandlers;

import ru.telegramBot.gm.app.handlers.BaseHandlersFacade;


/**
 * Фасад, который позволяет обрабатывать тексты, содержащие команды
 */
public class CommandFacade extends BaseHandlersFacade {

    /**
     * Задание списка всех обработчиков, которым даётся возможность обработать данные
     */
    public CommandFacade() {
        super(
                new StartCommandHandler(),
                new GetBusyCabinets(),
                new UnknownCommandHandler()
        );
    }
}
