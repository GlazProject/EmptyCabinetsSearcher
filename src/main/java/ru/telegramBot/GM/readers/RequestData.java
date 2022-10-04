package ru.telegramBot.GM.readers;

/**
 * Контейнер для строки, полученной из Reader.
 * Служит для передачи между методами и обработчиками.
 * Желательно его не изменять, но существуют модификаторы доступа.
 */
public class RequestData {
    private String dataText;

    /**
     * @param text Данные для упаковки
     */
    public RequestData(String text){
        this.dataText = text;
    }

    /**
     * @return Значение строки из контейнера
     */
    public String getText() {
        return dataText;
    }
}
