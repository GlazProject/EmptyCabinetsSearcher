package ru.telegramBot.GM.readers;

/**
 * Контейнер для строки, полученной из Reader.
 * Служит для передачи между методами и обработчиками.
 * Желательно его не изменять, но существуют модификаторы доступа.
 */
public class RequestData {
    private String m_sText;

    /**
     * @param sText Данные для упаковки
     */
    public RequestData(String sText){
        m_sText = sText;
    }

    /**
     * @return Значение строки из контейнера
     */
    public String getText() {
        return m_sText;
    }

    /**
     * Не рекомендуется к использованию!
     * @param sText Текст, который необходимо упаковать в контейнер
     */
    public void setText(String sText) {
        m_sText = sText;
    }
}
