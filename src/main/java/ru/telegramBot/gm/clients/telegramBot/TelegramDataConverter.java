package ru.telegramBot.gm.clients.telegramBot;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.telegramBot.gm.app.dataComponents.TextComponent;
import ru.telegramBot.gm.app.dataContainers.RequestData;
import ru.telegramBot.gm.app.dataContainers.ResponseData;

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
     * @param responseData Контейнер с данными, полученный от обработчика, который обязательно должен содержать компонент
     *                     с id чата получателя
     * @return Готовое сообщение ответа, пригодное для отправки, либо null, если не обнаружено текста
     * @throws NoSuchTelegramIdComponent Если в {@code responseData} не обнаружен {@code TelegramChatIDComponent}
     */
    @Nullable
    public SendMessage createMessage(ResponseData responseData) throws NoSuchTelegramIdComponent {
        if (responseData == null)
            return null;

        TelegramChatIDComponent chatIdComponent = responseData.getComponent(TelegramChatIDComponent.class);
        if (chatIdComponent == null)
            throw new NoSuchTelegramIdComponent();
        return createMessageByChatId(responseData, chatIdComponent.getID());
    }

    /**
     * Метод, который создаёт сообщение по полученному контейнеру с обработанными данными и id чата получателя
     *
     * @param responseData Контейнер с данными, полученный от обработчика
     * @param chatID       ID чата получателя
     * @return Готовое сообщение ответа, пригодное для отправки, либо null, если не обнаружено текста
     */
    @Nullable
    private SendMessage createMessageByChatId(ResponseData responseData, Long chatID) {
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
