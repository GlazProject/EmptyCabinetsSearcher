package ru.telegramBot.gm.clients.consoleBot;

import ru.telegramBot.gm.app.dataComponents.TextComponent;
import ru.telegramBot.gm.app.dataContainers.RequestData;

import java.util.Scanner;

/**
 * Класс читателя, организующий чтение данных из консоли и их упаковку в контейнер
 */
public class ConsoleReader implements Reader {
    /**
     * Метод для считывания строки с консоли
     *
     * @return Контейнер с полученной из консоли строкой в компоненте `text`
     */
    @Override
    public RequestData read() {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        RequestData requestData = new RequestData();
        requestData.setComponent(new TextComponent(text));
        return requestData;
    }
}
