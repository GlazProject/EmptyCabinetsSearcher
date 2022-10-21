package ru.telegramBot.gm.consoleBot;

import ru.telegramBot.gm.dataContainer.components.TextComponent;
import ru.telegramBot.gm.readers.Reader;
import ru.telegramBot.gm.readers.RequestData;

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
        RequestData requestData = new RequestData();
        requestData.setComponent("text", new TextComponent(){{
            set(text);
        }});
        return requestData;
    }
}
