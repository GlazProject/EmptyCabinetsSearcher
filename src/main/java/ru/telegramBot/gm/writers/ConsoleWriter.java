package ru.telegramBot.gm.writers;

/**
 * Простой класс для вывода данных из контейнера в консоль
 */
public class ConsoleWriter implements Writer<ResponseData> {

    /**
     * Основной метод вывода данных в консоль
     * @param data Контейнер с входными данными
     */
    @Override
    public void write(ResponseData data) {
        System.out.println(data.getText());
    }
}
