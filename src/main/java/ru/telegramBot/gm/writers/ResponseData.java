package ru.telegramBot.gm.writers;

/**
 * Класс контейнера для хранения и передачи строки от обработчика в метод вывода
 */
public class ResponseData {
    protected final String text;

    /**
     * @param inputText Текст, который необходимо упаковать в контейнер
     */
    public ResponseData(String inputText) {
        text = inputText;
    }

    public ResponseData(ResponseData responseData) {
        text = responseData.getText();
    }

    /**
     * @return Строка, которая упакована в контейнер
     */
    public String getText() {
        return text;
    }
}
