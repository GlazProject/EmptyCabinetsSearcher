package ru.telegramBot.gm.clients;

import ru.telegramBot.gm.app.dataContainers.ResponseData;

/**
 * Интерфейс для вывода обработанных данных в какую-либо среду
 */
public interface Writer {
    /**
     * Главный метод писателя, который выводит данный контейнера в какую-либо среду
     *
     * @param data Контейнер с обработанной информацией
     */
    void write(ResponseData data);
}
