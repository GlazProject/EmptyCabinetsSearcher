package ru.telegramBot.gm.handlers.commandHandlers;

public class CommandFinder {
    private String command;
    private String text;

    public CommandFinder(String text){
        findCommand(text);
    }

    public String getCommand(){
        return command;
    }

    public String getText(){
        return text;
    }

    private void findCommand(String inputText){
        if (inputText.charAt(0) != '/'){
            text = inputText;
            return;
        }

        for (int i=1;i<inputText.length();i++) {
            if (inputText.charAt(i) == ' ') {
                command = inputText.substring(1, i);
                text = inputText.substring(i + 1);
                break;
            }
            if(i == inputText.length()-1)
                command = inputText.substring(1, i+1);
        }
    }
}
