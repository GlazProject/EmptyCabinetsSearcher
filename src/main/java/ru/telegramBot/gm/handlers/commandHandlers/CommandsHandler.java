package ru.telegramBot.gm.handlers.commandHandlers;

import ru.telegramBot.gm.handlers.Handler;
import ru.telegramBot.gm.handlers.textHandlers.TextHandler;
import ru.telegramBot.gm.writers.ResponseData;

import java.util.HashMap;
import java.util.Map;

public class CommandsHandler implements Handler<String, ResponseData> {
    private static class Command{
        public String command = "";
        public String text = "";
    }

    private final Map<String, Handler<String,ResponseData>> handlers;

    public CommandsHandler(){
        handlers = new HashMap<>();
        fillHandlers();
    }

    @Override
    public ResponseData handle(String data) {
        Command command = findCommand(data);
        if (handlers.containsKey(command.command))
            return handlers.get(command.command).handle(command.text);
        return new UnknownCommandHandler().handle(command.text);
    }

    private void fillHandlers(){
        handlers.put("", new TextHandler());
        handlers.put("start", new StartCommandHandler());
    }

    private Command findCommand(String inputText){
        Command command = new Command();
        if (inputText.charAt(0) != '/'){
            command.text = inputText;
            return command;
        }

        for (int i=1;i<inputText.length();i++) {
            if (inputText.charAt(i) == ' ') {
                command.command = inputText.substring(1, i);
                command.text = inputText.substring(i + 1);
                break;
            }
            if(i == inputText.length()-1)
                command.command = inputText.substring(1, i+1);
        }
        return command;
    }

}
