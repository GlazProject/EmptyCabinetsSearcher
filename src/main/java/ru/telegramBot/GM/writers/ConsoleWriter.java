package ru.telegramBot.GM.writers;

/**
 * Простой класс для вывода данных из контейнера в консоль
 */
public class ConsoleWriter implements Writer<String> {

    /**
     * Основной метод вывода данных в консоль
     * @param data Контейнер с входными данными
     */
    @Override
    public void write(ResponseData<String> data) {
        System.out.println(data.getData());
    }
}
