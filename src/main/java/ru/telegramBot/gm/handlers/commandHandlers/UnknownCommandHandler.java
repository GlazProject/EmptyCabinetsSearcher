package ru.telegramBot.gm.handlers.commandHandlers;

import ru.telegramBot.gm.dataContainer.components.TextComponent;
import ru.telegramBot.gm.handlers.Handler;
import ru.telegramBot.gm.readers.RequestData;
import ru.telegramBot.gm.writers.ResponseData;

/**
 * Обработчик всех неизвестных команд
 */
public class UnknownCommandHandler implements Handler {
    /**
     * Метод, обрабатывабщий неизвестные/неправильные команды и текст без команд
     *
     * @param data Данные полученные при чтении
     * @return RD с сообщением о неправильной командой в поле text
     */
    @Override
    public ResponseData handle(RequestData data) {
        TextComponent textComponent = data.getComponent("text");
        if (textComponent == null)
            return null;

        CommandFinder commandFinder = new CommandFinder(textComponent.get());
        if (commandFinder.getCommand() == null)
            return null;

        ResponseData responseData = new ResponseData();
        responseData.setComponent("text", new TextComponent(){{
            set("Я не знаю, как реагировать на такую команду (\nПопробуй ещё раз");
        }});
        return responseData;

    }
}
