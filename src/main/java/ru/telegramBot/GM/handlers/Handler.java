package ru.telegramBot.GM.handlers;

import ru.telegramBot.GM.readers.RequestData;
import ru.telegramBot.GM.writers.ResponseData;

/**
 * Интерфейс для всех видов обработчиков
 */
public interface Handler<T_in, T_out> {
    /**
     * Метод, используемый для обработки сообщения
     * @param data Данные полученные при чтении
     * @return Контейнер с обработанными данными
     */
    ResponseData<T_out> handle(RequestData<T_in> data);
}
