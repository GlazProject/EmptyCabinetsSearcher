package ru.telegramBot.gm.readers;

/**
 * Контейнер для строки, полученной из Reader.
 * Служит для передачи между методами и обработчиками.
 * Желательно его не изменять, но существуют модификаторы доступа.
 */
public class RequestData<T> {
    private final T data;

    /**
     * @param inputData Данные для упаковки
     */
    public RequestData(T inputData){
        data = inputData;
    }

    /**
     * @return Значение строки из контейнера
     */
    public T getData() {
        return data;
    }
}
