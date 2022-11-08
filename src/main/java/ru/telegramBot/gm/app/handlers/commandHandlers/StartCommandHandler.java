package ru.telegramBot.gm.app.handlers.commandHandlers;

import org.jetbrains.annotations.NotNull;
import ru.telegramBot.gm.app.dataComponents.TextComponent;
import ru.telegramBot.gm.app.dataContainers.RequestData;
import ru.telegramBot.gm.app.dataContainers.ResponseData;
import ru.telegramBot.gm.app.handlers.Handler;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Обработчик стартовой команды /start
 */
public class StartCommandHandler implements Handler {
    public static final String HANDLER_START = "StartCommandHandler.start";
    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("phrases");

    /**
     * Метод, возвращающий приветственное сообщение
     *
     * @param data Контейнер с данными, содержащий строку в поле "text"
     * @return Контейнер с приветственным сообщением в поле "text", либо null, если другая команда
     */
    @Nullable
    @Override
    public ResponseData handle(@NotNull RequestData data) {
        TextComponent textComponent = data.getComponent(TextComponent.class);
        if (textComponent == null)
            return null;

        CommandParser commandParser = new CommandParser(textComponent.getText());
        if (!Objects.equals(commandParser.getCommand(), "start"))
            return null;

        ResponseData responseData = new ResponseData();
        responseData.setComponent(new TextComponent(resourceBundle.getString(HANDLER_START)));
        return responseData;
    }
}
