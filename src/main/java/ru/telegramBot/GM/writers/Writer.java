package ru.telegramBot.GM.writers;

/**
 * Интерфейс для вывода обработанных данных в какую-либо среду
 */
public interface Writer {
    /**
     * Главный метод писателя, который выводит данный контейнера в какую-либо среду
     * @param data Контейнер с обработанной информацией
     * @return Код статуса вывода информации
     */
    int write(ResponseData data);
}
