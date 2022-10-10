package ru.telegramBot.gm.consoleBot;

import ru.telegramBot.gm.writers.ResponseData;
import ru.telegramBot.gm.writers.ResponseDataV1;
import ru.telegramBot.gm.writers.Writer;

/**
 * Простой класс для вывода данных из контейнера в консоль
 */
public class ConsoleWriter implements Writer {

    /**
     * Основной метод вывода данных в консоль
     * @param data Контейнер с входными данными
     */
    @Override
    public void write(ResponseData data) {
        if (data instanceof ResponseDataV1)
            System.out.println(((ResponseDataV1)data).getText());
        else
            throw new ClassCastException("Контейнер с данными не является ResponseDataV1 и не содержит метода getText()");
    }
}
