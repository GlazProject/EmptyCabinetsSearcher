package ru.telegramBot.gm.telegramBot;


import org.telegram.telegrambots.meta.api.objects.Message;
import ru.telegramBot.gm.writers.ResponseData;

/*
 * Класс, предназначенный для хранения спецефических телеграм данных, в дополнение к данным ответов обработчика
 */
public class TelegramResponseData implements ResponseData{
    private final Message originalMessage;
    private final ResponseData data;

    /**
     * @param responseData Контейнер, полученный от обработчика
     * @param fromMessage Сообщение, в ответ на которое формируется ответ
     */
    public TelegramResponseData(ResponseData responseData, Message fromMessage) {
        data = responseData;
        originalMessage = fromMessage;
    }

    public Message getMessage(){
        return originalMessage;
    }
    public ResponseData getData() {
        return data;
    }
}
