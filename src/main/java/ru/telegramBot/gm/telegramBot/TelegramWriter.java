package ru.telegramBot.gm.telegramBot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.telegramBot.gm.writers.ResponseData;

import javax.annotation.Nullable;

/**
 * Класс, предоставляющий методы для создания сообщений ответа Telegram
 */
public class TelegramWriter {
    /**
     * Метод, который создаёт сообщение по полученному контейнеру с сообщением Telegram
     *
     * @param responseData Контейнер с данными, полученный от обработчика
     * @return Готовое сообщение ответа, пригодное для отправки,
     * либо null, если нет данных для создания сообщения или нет сообщения отправителя
     */
    @Nullable
    public SendMessage createMessage(ResponseData responseData){
        if (responseData == null)
            return null;

        Message originalMessage = responseData.getFromComponent("telegramMessage");
        if (originalMessage == null)
            return null;
        return createMessageByChatId(responseData, originalMessage.getChatId());
    }

    /**
     * Метод, который создаёт сообщение по полученному контейнеру с обработанными данными и id чата получателя
     *
     * @param responseData Контейнер с данными, полученный от обработчика
     * @param chatID ID чата получателя
     * @return Готовое сообщение ответа, пригодное для отправки,
     * либо null, если нет данных для создания сообщения или нет сообщения отправителя
     */
    @Nullable
    public SendMessage createMessageByChatId(ResponseData responseData, Long chatID){
        if (responseData == null)
            return null;

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatID);

        String text = responseData.getFromComponent("text");
        if (text == null)
            return null;
        sendMessage.setText(text);

        // Заменяет пустой текст на прозрачный символ, чтобы не было ошибок пустого сообщения
        if (sendMessage.getText().equals(""))
            sendMessage.setText("\uE000");

        return sendMessage;
    }
}
