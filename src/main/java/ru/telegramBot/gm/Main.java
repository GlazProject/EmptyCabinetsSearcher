package ru.telegramBot.gm;


import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.telegramBot.gm.handlers.CommandsHandler;
import ru.telegramBot.gm.handlers.Handler;
import ru.telegramBot.gm.readers.ConsoleReader;
import ru.telegramBot.gm.readers.Reader;
import ru.telegramBot.gm.readers.RequestData;
import ru.telegramBot.gm.telegramBot.Bot;
import ru.telegramBot.gm.writers.ConsoleWriter;
import ru.telegramBot.gm.writers.ResponseData;
import ru.telegramBot.gm.writers.Writer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
//        telegramEchoBot();
        consoleEchoBot();
    }

    /**
     * Простой эхо ответчик для консоли.
     * Останавливает считываниие и ответ при вводе пустой строки
     */
    public static void consoleEchoBot() {
        Reader<String> reader = new ConsoleReader();
        Writer<ResponseData> writer = new ConsoleWriter();
        Handler<String, ResponseData> handler = new CommandsHandler();

        while (true) {
            RequestData<String> requestData = reader.read();
            if (Objects.equals(requestData.getData(), "")) break;
            ResponseData responseData = handler.handle(requestData.getData());
            writer.write(responseData);
        }
    }


    public static void telegramEchoBot() throws FileNotFoundException {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/config.properties"));
        } catch (IOException e) {
            throw new FileNotFoundException("Файл конфигурации не найден по адресу ./resources/config.properties");
        }

        String botName = properties.getProperty("bot.name");
        String botToken = properties.getProperty("bot.token");
        if (botName == null) {
            throw new IllegalArgumentException("Не найдено bot.name в файле конфигурации");
        }
        if (botToken == null) {
            throw new IllegalArgumentException("Не найдено bot.token в файле конфигурации");
        }

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new Bot(botName, botToken));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
