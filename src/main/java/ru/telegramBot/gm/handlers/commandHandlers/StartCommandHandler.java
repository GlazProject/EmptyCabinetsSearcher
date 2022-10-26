package ru.telegramBot.gm.handlers.commandHandlers;

import org.jetbrains.annotations.NotNull;
import ru.telegramBot.gm.dataContainer.components.DataWriter;
import ru.telegramBot.gm.dataContainer.components.TextComponent;
import ru.telegramBot.gm.handlers.Handler;
import ru.telegramBot.gm.readers.RequestData;
import ru.telegramBot.gm.writers.ResponseData;

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

        CommandFinder commandFinder = new CommandFinder(textComponent.getText());
        if (!Objects.equals(commandFinder.getCommand(), "start"))
            return null;

        ResponseData responseData = new ResponseData();
        DataWriter.write(resourceBundle.getString(HANDLER_START), responseData);
        return responseData;
    }
}
