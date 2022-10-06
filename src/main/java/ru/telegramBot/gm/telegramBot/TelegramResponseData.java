package ru.telegramBot.gm.telegramBot;


import org.telegram.telegrambots.meta.api.objects.Message;
import ru.telegramBot.gm.writers.ResponseData;

public class TelegramResponseData extends ResponseData {
    private final Message originalMessage;

    /**
     * @param inputText Текст, который нужно сохранить в ответе
     * @param fromMessage Сообщение, в ответ на которое создаём ответ
     */
    public TelegramResponseData(String inputText, Message fromMessage) {
        super(inputText);
        originalMessage = fromMessage;
    }

    public TelegramResponseData(ResponseData responseData, Message fromMessage) {
        super(responseData);
        originalMessage = fromMessage;
    }

    public Message getMessage(){
        return originalMessage;
    }
}
