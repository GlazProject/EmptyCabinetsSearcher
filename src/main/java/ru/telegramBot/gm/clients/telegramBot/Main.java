package ru.telegramBot.gm.clients.telegramBot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class Main {
    /**
     * �����, ��������� � ����������� Telegram ���� � ����� ������
     *
     * @param args �� ������������
     */
    public static void main(String[] args) {
        try {
            createTelegramBot();
            System.out.println("[INFO] Telegram bot successfully started");
        } catch (IOException e) {
            System.out.println("[ERROR] Couldn't connect to Telegram");
            throw new RuntimeException(e);
        }
    }

    /**
     * ����������� � ������ ������ ��������. ������ ���� �� ����������������� �����
     *
     * @throws IOException ���� �� ������� �������� ������ ������������
     */
    private static void createTelegramBot() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/config.properties"));

        String botName = Objects.requireNonNull(properties.getProperty("bot.name"), "Not found bot.name in config file");
        String botToken = Objects.requireNonNull(properties.getProperty("bot.token"), "Not fount bot.token in config file");

        startTelegramBot(botName, botToken);
    }

    /**
     * ������ ����� ������ � ������������ �������� ����
     *
     * @param botName  ��� ����
     * @param botToken �����, ��� ����������� � ����
     */
    private static void startTelegramBot(String botName, String botToken) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new TelegramBot(botName, botToken));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
