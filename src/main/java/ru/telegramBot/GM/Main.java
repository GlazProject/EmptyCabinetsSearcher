package ru.telegramBot.GM;


import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.telegramBot.GM.handlers.Handler;
import ru.telegramBot.GM.handlers.SimpleEchoHandler;
import ru.telegramBot.GM.readers.ConsoleReader;
import ru.telegramBot.GM.readers.Reader;
import ru.telegramBot.GM.readers.RequestData;
import ru.telegramBot.GM.telegramBot.Bot;
import ru.telegramBot.GM.writers.ConsoleWriter;
import ru.telegramBot.GM.writers.ResponseData;
import ru.telegramBot.GM.writers.Writer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        telegramEchoBot();
    }

    /**
     * Простой эхо ответчик для консоли.
     * Останавливает считываниие и ответ при вводе пустой строки
     */
    public static void consoleEchoBot() {
        Reader<String> reader = new ConsoleReader();
        Writer<String> writer = new ConsoleWriter();
        Handler<String, String> handler = new SimpleEchoHandler();

        while (true) {
            RequestData<String> requestData = reader.read();
            ResponseData<String> responseData = handler.handle(requestData);
            if (Objects.equals(responseData.getData(), "")) break;
            writer.write(responseData);
        }
    }


    public static void telegramEchoBot() throws FileNotFoundException {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/config.properties"));
        } catch (IOException e) {
            throw new FileNotFoundException("Файл конфигурации не найден по адресу ./resources/config.properties");
//            System.out.println("Файл конфигурации не найден по адресу ./resources/config.properties");
//            return;
        }

        String botName = properties.getProperty("bot.name");
        String botToken = properties.getProperty("bot.token");
        if (botName == null){
            throw new IllegalArgumentException("Не найдено bot.name в файле конфигурации");
//            System.out.println("Не найдено bot.name в файле конфигурации");
//            return;
        }
        if (botToken == null){
            throw new IllegalArgumentException("Не найдено bot.token в файле конфигурации");
//            System.out.println("Не найдено bot.token в файле конфигурации");
//            return;
        }

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new Bot(botName, botToken));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
