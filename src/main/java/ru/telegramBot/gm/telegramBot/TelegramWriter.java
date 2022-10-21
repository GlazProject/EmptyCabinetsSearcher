package ru.telegramBot.gm.telegramBot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.security.KeyException;

/**
 * Класс, предоставляющий методы для создания сообщений ответа Telegram
 */
public class TelegramWriter {
    /**
     * Метод, который создаёт сообщение по полученному контейнеру TRD
     *
     * @param responseData Контейнер с данными, полученный от обработчика
     * @return Готовое соообщение ответа, пригодное для отпарвки
     */
    public SendMessage createMessage(TelegramResponseData responseData) throws KeyException {
        SendMessage message = new SendMessage();
        Message originalMessage = responseData.getFromComponent("message");
        if (originalMessage == null)
            return null;
        message.setChatId(originalMessage.getChatId());

        String text = responseData.getFromComponent("text");
        if (text == null)
            return null;
        message.setText(text);

        // Заменяет пустой текст на прозрачный символ, чтобы не было ошибок пустого сообщения
        if (message.getText().equals(""))
            message.setText("\uE000");

        return message;
    }
}
