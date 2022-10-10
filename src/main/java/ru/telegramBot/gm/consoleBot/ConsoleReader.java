package ru.telegramBot.gm.consoleBot;

import ru.telegramBot.gm.readers.Reader;
import ru.telegramBot.gm.readers.RequestDataV1;

import java.util.Scanner;

/**
 * Класс читателя, организующий чтение данных из консоли и их упаковку в контейнер
 */
public class ConsoleReader implements Reader {

    /**
     * Метод для считывания строки с консили
     * @return Контейнер с полученной из консоли строкой
     */
    @Override
    public RequestDataV1 read() {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        return new RequestDataV1(text);
    }
}
