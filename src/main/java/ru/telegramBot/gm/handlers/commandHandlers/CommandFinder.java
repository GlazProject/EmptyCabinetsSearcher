package ru.telegramBot.gm.handlers.commandHandlers;

import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

/**
 * Класс, который позволяет осуществять поиск команды формата /command в тексте
 */
public class CommandFinder {
    private String command;
    private String text;

    public CommandFinder(String text){
        findCommand(text);
    }

    /**
     * Метод, который позволяет получить найденную команду
     * @return Команда без /, либо null, если команда не была найдена
     */
    @Nullable
    public String getCommand(){
        return command;
    }

    /**
     * Метод, который позволяет получить найденный текст
     * @return Текст из входных данных без команды, либо null, если текст не найден
     */
    @Nullable
    public String getText(){
        return text;
    }

    /**
     * Метод, который позволяет разделить полученный текст на команду и оставшийся текст
     * @param inputText Текст, который нужно разделить
     */
    private void findCommand(@NotNull String inputText){
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
