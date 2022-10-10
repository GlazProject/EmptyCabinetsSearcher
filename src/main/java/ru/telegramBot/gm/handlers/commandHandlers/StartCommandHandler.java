package ru.telegramBot.gm.handlers.commandHandlers;

import ru.telegramBot.gm.handlers.HandlerV1;
import ru.telegramBot.gm.readers.RequestData;
import ru.telegramBot.gm.readers.RequestDataV1;
import ru.telegramBot.gm.writers.ResponseData;
import ru.telegramBot.gm.writers.ResponseDataV1;

import java.util.Objects;

/**
 * Обработчик стартовой команды /start
 */
public class StartCommandHandler implements HandlerV1 {
    /**
     * Метод, возвращающий приветственное сообщение
     *
     * @param data Данные полученные при чтении
     * @return RD с приветственным сообщением в поле text либо null, если другая команда
     */
    @Override
    public ResponseData handle(RequestData data) {
        if (!correctVersion(data))
            return null;

        RequestDataV1 requestData = (RequestDataV1)data;
        CommandFinder commandFinder = new CommandFinder(requestData.getText());
        if (Objects.equals(commandFinder.getCommand(), "start"))
            return new ResponseDataV1("Привет, рад нашей встрече");
        return null;
    }
}
