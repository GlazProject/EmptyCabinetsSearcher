package ru.telegramBot.gm.app.handlers.textHandlers;

import org.jetbrains.annotations.NotNull;
import ru.telegramBot.gm.app.dataComponents.TextComponent;
import ru.telegramBot.gm.app.dataContainers.RequestData;
import ru.telegramBot.gm.app.dataContainers.ResponseData;
import ru.telegramBot.gm.app.handlers.Handler;

import javax.annotation.Nullable;

/**
 * Простой обработчик сообщений, возвращающий исходные
 * данные без изменения
 */
public class EchoHandler implements Handler {
    /**
     * Метод, работающий как эхо ответчик на входящую строку
     *
     * @param data Контейнер с данными в компоненте "text"
     * @return Контейнер с текстом исходной строки в поле "text", либо null, если не найден компонент
     */
    @Nullable
    @Override
    public ResponseData handle(@NotNull RequestData data) {
        TextComponent textComponent = data.getComponent(TextComponent.class);
        if (textComponent == null)
            return null;
        ResponseData responseData = new ResponseData();
        responseData.setComponent(textComponent);
        return responseData;
    }
}
