package ru.telegramBot.gm.readers;

/**
 * Обёртка для данных, которая содержит только поле с текстом
 */
public class RequestDataV1 implements RequestData{
    private final String text;

    /**
     * @param inputText Данные для упаковки
     */
    public RequestDataV1(String inputText){
        text = inputText;
    }

    public String getText() {
        return text;
    }
}
