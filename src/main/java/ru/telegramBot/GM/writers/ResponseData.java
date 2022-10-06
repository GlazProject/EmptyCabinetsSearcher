package ru.telegramBot.GM.writers;

/**
 * Класс контейнера для хранения и передачи строки от обработчика в метод вывода
 */
public class ResponseData<T> {
    private final T data;

    /**
     * @param inputData Текст, который необходимо упаковать в контейнер
     */
    public ResponseData(T inputData) {
        data = inputData;
    }

    /**
     * @return Строка, которая упакована в контейнер
     */
    public T getData() {
        return data;
    }
}
