package ru.telegramBot.gm.telegramBot;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.jetbrains.annotations.NotNull;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.telegramBot.gm.dataContainer.components.DataWriter;
import ru.telegramBot.gm.readers.RequestData;
/**
 * Класс, который преобразует Telegram Update в контейнер с данными
 */
public class TelegramReader {
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
        DataWriter.write(message, requestData);
        if (message.hasText())
            DataWriter.write(message.getText(), requestData);

        return requestData;
    }
}
