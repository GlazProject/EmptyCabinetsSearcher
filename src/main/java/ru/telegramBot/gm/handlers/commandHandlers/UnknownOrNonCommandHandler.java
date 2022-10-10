package ru.telegramBot.gm.handlers.commandHandlers;

import ru.telegramBot.gm.handlers.HandlerV1;
import ru.telegramBot.gm.handlers.textHandlers.TextHandler;
import ru.telegramBot.gm.readers.RequestData;
import ru.telegramBot.gm.readers.RequestDataV1;
import ru.telegramBot.gm.writers.ResponseData;
import ru.telegramBot.gm.writers.ResponseDataV1;

/**
 * Обработчик всех неизвестных команд
 */
public class UnknownOrNonCommandHandler implements HandlerV1 {
    /**
     * Метод, обрабатывабщий неизвестные/неправильные команды и текст без команд
     *
     * @param data Данные полученные при чтении
     * @return RD с сообщением о неправильной командой в поле text
     */
    @Override
    public ResponseData handle(RequestData data) {
        if (!correctVersion(data))
            return null;

        RequestDataV1 requestData = (RequestDataV1)data;
        CommandFinder commandFinder = new CommandFinder(requestData.getText());
        if (commandFinder.getCommand() == null)
            return new TextHandler().handle(data);
        return new ResponseDataV1("Я не знаю, как реагировать на такую команду");

    }
}
