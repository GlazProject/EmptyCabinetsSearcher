package ru.telegramBot.gm.app.handlers;

import ru.telegramBot.gm.app.dataContainers.RequestData;
import ru.telegramBot.gm.app.dataContainers.ResponseData;

/**
 * Интерфейс для всех видов обработчиков
 */
public interface Handler {
    /**
     * Метод, используемый для обработки сообщения
     *
     * @param data Данные полученные при чтении
     * @return Обёртка над обработанными данными
     */
    ResponseData handle(RequestData data);
}
