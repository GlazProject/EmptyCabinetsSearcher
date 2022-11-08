package ru.telegramBot.gm.app.handlers;

import ru.telegramBot.gm.app.handlers.commandHandlers.CommandFacade;
import ru.telegramBot.gm.app.handlers.textHandlers.EchoHandler;

/**
 * Фасад, который позволяет обрабатывать любые данные
 */
public class HandlerFacade extends BaseHandlersFacade {
    /**
     * Задание списка всех обработчиков (фасадов), которым даётся возможность обработать данные
     */
    public HandlerFacade() {
        super(new CommandFacade(), new EchoHandler());
    }

    //TODO Добавить сервисы, которые выполняют цельный кусочек логики:
    // * сервис, который делает запрос к урфу
    // * сервис, который делает запрос http
    // * сервис, который парсит данные

    //TODO Получение данных с сайта УрФУ можно упаковать в DAO, обращение к БД отдельный DAO
}
