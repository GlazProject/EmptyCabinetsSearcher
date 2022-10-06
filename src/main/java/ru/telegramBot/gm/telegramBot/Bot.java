package ru.telegramBot.gm.telegramBot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

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
        TelegramHandler handler = new TelegramHandler();
        TelegramWriter writer = new TelegramWriter();
        TelegramResponseData telegramRequestData = handler.handle(update);
        SendMessage message = writer.createMessage(telegramRequestData);
        try{
            execute(message);
        }
        catch (TelegramApiException e){
            e.printStackTrace();
        }
    }
}
