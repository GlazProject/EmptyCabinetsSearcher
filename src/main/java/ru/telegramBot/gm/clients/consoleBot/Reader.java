package ru.telegramBot.gm.clients.consoleBot;

import ru.telegramBot.gm.app.dataContainers.RequestData;

/**
 * Интерфейс обеспечивающий чтение данных
 * Всегда возвращает упакованный контейнер
 */
public interface Reader {
    /**
     * Метод, используемый для чтения данных из какого-либо источника
     *
     * @return Упакованный контейнер с прочтёнными данными
     */
    RequestData read();
}
