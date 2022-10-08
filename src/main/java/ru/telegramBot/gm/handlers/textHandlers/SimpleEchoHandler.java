package ru.telegramBot.gm.handlers.textHandlers;

import ru.telegramBot.gm.handlers.Handler;
import ru.telegramBot.gm.writers.ResponseData;

/**
 * Простой обработчик сообщений, возвращающий исходные
 * данные без изменения
 */
public class SimpleEchoHandler implements Handler<String, ResponseData> {

    /**
     * Этот метод используется для обработки входных данных
     * Возвращает входящие данные без изменения, просто упаковывая
     * их в новый контейнер
     * @param data Это контейнер с входными данными
     * @return Контейнер с обработанными данными, пригодными для вывода
     */
    @Override
    public ResponseData handle(String data) {
        return new ResponseData(data);
    }
}
