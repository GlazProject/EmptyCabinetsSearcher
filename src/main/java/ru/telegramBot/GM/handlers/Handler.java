package ru.telegramBot.GM.handlers;

import ru.telegramBot.GM.readers.RequestData;
import ru.telegramBot.GM.writers.ResponseData;

/**
 * Интерфейс для всех видов обработчиков
 */
public interface Handler {
    /**
     * Метод, используемый для обработки сообщения
     * @param data Данные полученные при чтении
     * @return Контейнер с обработанными данными
     */
    ResponseData handle(RequestData data);
}
