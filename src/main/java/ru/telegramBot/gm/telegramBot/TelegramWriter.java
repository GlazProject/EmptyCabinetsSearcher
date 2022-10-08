package ru.telegramBot.gm.telegramBot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class TelegramWriter {
    public SendMessage createMessage(TelegramResponseData responseData){
        SendMessage message = new SendMessage();
        message.setChatId(responseData.getMessage().getChatId());
        message.setText(responseData.getText());
        if (message.getText().equals(""))
            message.setText("\uE000");
        return message;
    }
}
