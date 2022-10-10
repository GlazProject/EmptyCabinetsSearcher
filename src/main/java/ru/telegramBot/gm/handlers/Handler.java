package ru.telegramBot.gm.handlers;

import ru.telegramBot.gm.readers.RequestData;
import ru.telegramBot.gm.writers.ResponseData;

/**
 * Интерфейс для всех видов обработчиков
 */
public interface Handler {
    /**
     * Метод, используемый для обработки сообщения
     * @param data Данные полученные при чтении
     * @return Обёртка над обработанными данными
     */
    ResponseData handle(RequestData data);

    default boolean correctVersion(RequestData data){ return true; }
}
