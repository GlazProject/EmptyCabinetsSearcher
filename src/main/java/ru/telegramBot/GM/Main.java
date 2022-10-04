package ru.telegramBot.GM;


import ru.telegramBot.GM.handlers.Handler;
import ru.telegramBot.GM.handlers.SimpleEchoHandler;
import ru.telegramBot.GM.readers.ConsoleReader;
import ru.telegramBot.GM.readers.Reader;
import ru.telegramBot.GM.readers.RequestData;
import ru.telegramBot.GM.writers.ConsoleWriter;
import ru.telegramBot.GM.writers.ResponseData;
import ru.telegramBot.GM.writers.Writer;

import java.util.Objects;

public class Main {

    public static void main(String[] args) {
        readAndDoWhileNotEmptyString();
    }

    /**
     * Простой эхо ответчик для консоли.
     * Останавливает считываниие и ответ при вводе пустой строки
     */
    public static void readAndDoWhileNotEmptyString() {
        Reader reader = new ConsoleReader();
        Writer writer = new ConsoleWriter();
        Handler handler = new SimpleEchoHandler();

        while (true) {
            RequestData requestData = reader.read();
            ResponseData responseData = handler.handle(requestData);
            if (Objects.equals(responseData.getText(), "")) break;
            writer.write(responseData);
        }
    }
}
