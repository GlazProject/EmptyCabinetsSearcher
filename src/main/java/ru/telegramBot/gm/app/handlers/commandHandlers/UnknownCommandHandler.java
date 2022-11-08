package ru.telegramBot.gm.app.handlers.commandHandlers;

import org.jetbrains.annotations.NotNull;
import ru.telegramBot.gm.app.dataComponents.TextComponent;
import ru.telegramBot.gm.app.dataContainers.RequestData;
import ru.telegramBot.gm.app.dataContainers.ResponseData;
import ru.telegramBot.gm.app.handlers.Handler;

import javax.annotation.Nullable;
import java.util.ResourceBundle;

/**
 * Обработчик всех неизвестных команд
 */
public class UnknownCommandHandler implements Handler {
    public static final String UNKNOWN_COMMAND = "UnknownCommandHandler.unknownCommand";
    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("phrases");

    /**
     * Метод, обрабатывающий неизвестные/неправильные команды
     *
     * @param data Контейнер с данными, содержащий строку в поле "text"
     * @return Контейнер с сообщением о неправильной командой в поле "text", либо null, если не содержит команду
     */
    @Nullable
    @Override
    public ResponseData handle(@NotNull RequestData data) {
        TextComponent textComponent = data.getComponent(TextComponent.class);
        if (textComponent == null)
            return null;

        CommandParser commandParser = new CommandParser(textComponent.getText());
        if (commandParser.getCommand() == null)
            return null;

        ResponseData responseData = new ResponseData();
        responseData.setComponent(new TextComponent(resourceBundle.getString(UNKNOWN_COMMAND)));
        return responseData;
    }
}
