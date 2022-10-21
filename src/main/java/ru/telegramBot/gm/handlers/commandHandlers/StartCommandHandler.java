package ru.telegramBot.gm.handlers.commandHandlers;

import ru.telegramBot.gm.dataContainer.components.TextComponent;
import ru.telegramBot.gm.handlers.Handler;
import ru.telegramBot.gm.readers.RequestData;
import ru.telegramBot.gm.writers.ResponseData;

import javax.annotation.Nullable;
import java.util.Objects;

/**
 * Обработчик стартовой команды /start
 */
public class StartCommandHandler implements Handler {
    /**
     * Метод, возвращающий приветственное сообщение
     *
     * @param data Данные полученные при чтении
     * @return RD с приветственным сообщением в поле text либо null, если другая команда
     */
    @Nullable
    @Override
    public ResponseData handle(RequestData data) {
        TextComponent textComponent = data.getComponent("text");
        if (textComponent == null)
            return null;

        CommandFinder commandFinder = new CommandFinder(textComponent.get());
        if (!Objects.equals(commandFinder.getCommand(), "start"))
            return null;
        ResponseData responseData = new ResponseData();
        responseData.setComponent("text", new TextComponent(){{
            set("привет, рад нашей встрече");
        }});
        return responseData;
    }
}
