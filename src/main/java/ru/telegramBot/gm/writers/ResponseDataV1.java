package ru.telegramBot.gm.writers;

/**
 * Обёртка для данных, которая имеет только поле с текстом
 */
public class ResponseDataV1 implements ResponseData{
    protected final String text;

    public ResponseDataV1(String inputText) {
        text = inputText;
    }

    public ResponseDataV1(ResponseData responseData) {
        if (responseData instanceof ResponseDataV1)
            text = ((ResponseDataV1)responseData).getText();
        else
            throw new ClassCastException("Данные не являются ResponseDataV1");
    }

    public String getText() {
        return text;
    }
}
