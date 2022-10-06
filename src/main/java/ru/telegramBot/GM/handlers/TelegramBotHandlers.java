package ru.telegramBot.GM.handlers;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.telegramBot.GM.readers.RequestData;
import ru.telegramBot.GM.writers.ResponseData;

public class TelegramBotHandlers implements Handler<Message, SendMessage> {
    @Override
    public ResponseData<SendMessage> handle(RequestData<Message> data) {
        if (data == null)
            return null;

        SendMessage sendMessage = textHandle(data.getData());
        setAttributes(sendMessage, data.getData());
        return new ResponseData<>(sendMessage);
    }

    private SendMessage textHandle(Message message) {
        SimpleEchoHandler handler = new SimpleEchoHandler();
        RequestData<String> dataFromMessage = new RequestData<>(message.getText());
        ResponseData<String> responseData = handler.handle(dataFromMessage);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(responseData.getData());
        return sendMessage;
    }

    private void setAttributes(SendMessage sendMessage, Message originalMessage){
        sendMessage.setChatId(originalMessage.getChatId());
    }

}
