package ru.telegramBot.GM.telegramBot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.telegramBot.GM.handlers.Handler;
import ru.telegramBot.GM.handlers.TelegramBotHandlers;
import ru.telegramBot.GM.readers.RequestData;
import ru.telegramBot.GM.readers.TelegramReader;
import ru.telegramBot.GM.writers.ResponseData;

public class Bot extends TelegramLongPollingBot {
    private final String BOT_NAME;
    private final String BOT_TOKEN;

    public Bot(String bot_name, String bot_token) {
        super();
        BOT_NAME = bot_name;
        BOT_TOKEN = bot_token;
    }


    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }


    @Override
    public void onUpdateReceived(Update update) {
        TelegramReader reader = new TelegramReader();
        Handler<Message, SendMessage> handler = new TelegramBotHandlers();
        RequestData<Message> messageRequestData = reader.readFrom(update);
        ResponseData<SendMessage> responseMessage = handler.handle(messageRequestData);
        try {
            execute(responseMessage.getData());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
