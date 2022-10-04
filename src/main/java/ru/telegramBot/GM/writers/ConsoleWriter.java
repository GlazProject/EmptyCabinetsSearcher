package ru.telegramBot.GM.writers;

/**
 * Простой класс для вывода данных из контейнера в консоль
 */
public class ConsoleWriter implements Writer {

    /**
     * Основной метод вывода данных в консоль
     * @param data Контейнер с входными данными
     * @return Код результата вывода. 0 - всё хорошо
     */
    @Override
    public int write(ResponseData data) {
        System.out.println(data.getText());
        return 0;
    }
}
