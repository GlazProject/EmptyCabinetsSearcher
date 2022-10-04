package ru.telegramBot.GM.writers;

/**
 * Класс контейнера для хранения и передачи строки от обработчика в метод вывода
 */
public class ResponseData {
    private String dataText;

    /**
     * @param text Текст, который необходимо упаковать в контейнер
     */
    public ResponseData(String text) {
        dataText = text;
    }

    /**
     * @return Строка, которая упакована в контейнер
     */
    public String getText() {
        return dataText;
    }

    /**
     * Не рекомендуется к использованию!
     * @param text Текст, который необходимо упаковать в контейнер
     */
    public void setText(String text) {
        dataText = text;
    }
}
