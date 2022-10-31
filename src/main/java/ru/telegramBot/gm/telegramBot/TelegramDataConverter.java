package ru.telegramBot.gm.telegramBot;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.telegramBot.gm.dataContainer.components.TextComponent;
import ru.telegramBot.gm.readers.RequestData;
import ru.telegramBot.gm.writers.ResponseData;

/**
 * Класс, который преобразует Telegram Update в контейнер с данными
 */
public class TelegramDataConverter {
    /**
     * Метод, который проверяет наличие сообщения в обновлении и преобразует его в {@code RequestData}
     *
     * @param update Обновление, которое вызвало обработку
     * @return Контейнер с данными, либо null, если в обновлении нет сообщения
     */
    @Nullable
    public RequestData readDataFromUpdate(@NotNull Update update) {
        if (!update.hasMessage())
            return null;

        RequestData requestData = new RequestData();
        Message message = update.getMessage();

        TelegramChatIDComponent telegramChatIDComponent = new TelegramChatIDComponent(message.getChatId());
        requestData.setComponent(telegramChatIDComponent);

        if (message.hasText())
            requestData.setComponent(new TextComponent(message.getText()));

        return requestData;
    }

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

        TelegramChatIDComponent chatIdComponent = responseData.getComponent(TelegramChatIDComponent.class);
        if (chatIdComponent == null)
            return null;
        return createMessageByChatId(responseData, chatIdComponent.getID());
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

        TextComponent textComponent = responseData.getComponent(TextComponent.class);
        if (textComponent == null)
            return null;
        sendMessage.setText(textComponent.getText());

        // Заменяет пустой текст на прозрачный символ, чтобы не было ошибок пустого сообщения
        if (sendMessage.getText().equals(""))
            sendMessage.setText("\uE000");

        return sendMessage;
    }
}
