package ru.telegramBot.GM.readers;

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
    public RequestData read() {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        return new RequestData(text);
    }
}
