package ru.telegramBot.gm.handlers;

/**
 * Интерфейс для всех видов обработчиков
 */
public interface Handler<T_in, T_out> {
    /**
     * Метод, используемый для обработки сообщения
     * @param data Данные полученные при чтении
     * @return Контейнер с обработанными данными
     */
    T_out handle(T_in data);
}
