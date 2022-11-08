package ru.telegramBot.gm.clients.consoleBot;

import ru.telegramBot.gm.app.dataComponents.TextComponent;
import ru.telegramBot.gm.app.dataContainers.RequestData;
import ru.telegramBot.gm.app.dataContainers.ResponseData;
import ru.telegramBot.gm.app.handlers.Handler;
import ru.telegramBot.gm.app.handlers.HandlerFacade;
import ru.telegramBot.gm.clients.Writer;

import java.util.Objects;

public class Main {
    /**
     * Метод, создающий и запускающий консольного бота
     *
     * @param args Не используется
     */
    public static void main(String[] args) {
        System.out.println("[INFO] Console bot successfully started\n\n");
        startConsoleBot();
    }

    /**
     * Простой эхо ответчик для консоли.
     * Останавливает считывание и ответ при вводе пустой строки
     */
    private static void startConsoleBot() {
        Reader reader = new ConsoleReader();
        Writer writer = new ConsoleWriter();
        Handler handler = new HandlerFacade();

        while (true) {
            RequestData requestData = reader.read();
            TextComponent textComponent = requestData.getComponent(TextComponent.class);
            if (textComponent == null)
                break;
            if (Objects.equals(textComponent.getText(), ""))
                break;
            ResponseData responseData = handler.handle(requestData);
            writer.write(responseData);
        }
    }
}
