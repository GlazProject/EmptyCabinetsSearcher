package ru.telegramBot.gm;


import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.telegramBot.gm.handlers.CommandsHandler;
import ru.telegramBot.gm.handlers.Handler;
import ru.telegramBot.gm.consoleBot.ConsoleReader;
import ru.telegramBot.gm.readers.Reader;
import ru.telegramBot.gm.readers.RequestDataV1;
import ru.telegramBot.gm.telegramBot.TelegramBot;
import ru.telegramBot.gm.consoleBot.ConsoleWriter;
import ru.telegramBot.gm.writers.ResponseData;
import ru.telegramBot.gm.writers.Writer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        telegramBot();
//        consoleEchoBot();
    }

    /**
     * Простой эхо ответчик для консоли.
     * Останавливает считываниие и ответ при вводе пустой строки
     */
    public static void consoleEchoBot() {
        Reader reader = new ConsoleReader();
        Writer writer = new ConsoleWriter();
        Handler handler = new CommandsHandler();

        while (true) {
            RequestDataV1 requestData = reader.read();
            if (Objects.equals(requestData.getText(), "")) break;
            ResponseData responseData = handler.handle(requestData);
            writer.write(responseData);
        }
    }

    /**
     * Подключение и запуск сессии телеграм. Данные бота из конфигурационного файла
     */
    public static void telegramBot() throws FileNotFoundException {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/config.properties"));
        } catch (IOException e) {
            throw new FileNotFoundException("Файл конфигурации не найден по адресу src/main/resources/config.properties");
        }

        String botName = Objects.requireNonNull(properties.getProperty("bot.name"), "Не найдено bot.name в файле конфигурации");
        String botToken = Objects.requireNonNull(properties.getProperty("bot.token"), "Не найдено bot.token в файле конфигурации");

        startTelegramBot(botName, botToken);
    }

    /**
     * Данный метод создаёт и регистрирует телеграм бота
     *
     * @param botName Имя бота
     * @param botToken Токен, для подключения к боту
     */
    public static void startTelegramBot(String botName, String botToken){
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new TelegramBot(botName, botToken));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
