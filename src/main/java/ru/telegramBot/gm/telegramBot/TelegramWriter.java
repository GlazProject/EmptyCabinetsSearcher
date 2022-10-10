package ru.telegramBot.gm.telegramBot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.telegramBot.gm.writers.ResponseDataV1;

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
    public SendMessage createMessage(TelegramResponseData responseData){
        SendMessage message = new SendMessage();
        message.setChatId(responseData.getMessage().getChatId());
        if (responseData.getData() instanceof ResponseDataV1) {
            ResponseDataV1 innerData = (ResponseDataV1)(responseData.getData());
            message.setText(innerData.getText());
        }
        // Заменяет пустой текст на прозрачный символ, чтобы не было ошибок пустого сообщения
        if (message.getText().equals(""))
            message.setText("\uE000");
        return message;
    }
}
