package ru.telegramBot.GM.handlers;

import ru.telegramBot.GM.readers.RequestData;
import ru.telegramBot.GM.writers.ResponseData;

/**
 * Простой обработчик сообщений, возвращающий исходные
 * данные без изменения
 */
public class SimpleEchoHandler implements Handler<String, String> {

    /**
     * Этот метод используется для обработки входных данных
     * Возвращает входящие данные без изменения, просто упаковывая
     * их в новый контейнер
     * @param data Это контейнер с входными данными
     * @return Контейнер с обработанными данными, пригодными для вывода
     */
    @Override
    public ResponseData<String> handle(RequestData<String> data) {
        return new ResponseData<>(data.getData());
    }
}
