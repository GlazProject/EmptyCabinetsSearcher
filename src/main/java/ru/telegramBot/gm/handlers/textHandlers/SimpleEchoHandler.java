package ru.telegramBot.gm.handlers.textHandlers;

import org.jetbrains.annotations.NotNull;
import ru.telegramBot.gm.dataContainer.components.TextComponent;
import ru.telegramBot.gm.handlers.Handler;
import ru.telegramBot.gm.readers.RequestData;
import ru.telegramBot.gm.writers.ResponseData;

import javax.annotation.Nullable;

/**
 * Простой обработчик сообщений, возвращающий исходные
 * данные без изменения
 */
public class SimpleEchoHandler implements Handler {

    /**
     * Метод, работающий как эхо ответчик на входящую строку
     *
     * @param data Контейнер с данными в компоненте "text"
     * @return Контейнер с текстом исходной строки в поле "text", либо null, если не найден компонент
     */
    @Nullable
    @Override
    public ResponseData handle(@NotNull RequestData data) {
        TextComponent textComponent = data.getComponent("text");
        if (textComponent == null)
            return  null;
        ResponseData responseData = new ResponseData();
        responseData.setComponent("text", textComponent);
        return responseData;
    }
}
