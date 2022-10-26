package ru.telegramBot.gm.handlers.commandHandlers;

import org.jetbrains.annotations.NotNull;
import ru.telegramBot.gm.dataContainer.components.DataWriter;
import ru.telegramBot.gm.dataContainer.components.TextComponent;
import ru.telegramBot.gm.handlers.Handler;
import ru.telegramBot.gm.readers.RequestData;
import ru.telegramBot.gm.writers.ResponseData;

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

        CommandFinder commandFinder = new CommandFinder(textComponent.getText());
        if (commandFinder.getCommand() == null)
            return null;

        ResponseData responseData = new ResponseData();
        DataWriter.write(resourceBundle.getString(UNKNOWN_COMMAND), responseData);
        return responseData;

    }
}
