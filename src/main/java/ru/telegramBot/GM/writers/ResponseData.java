package ru.telegramBot.GM.writers;

/**
 * Класс контейнера для хранения и передачи строки от обработчика в метод вывода
 */
public class ResponseData {
    private String m_sText;

    /**
     * @param sText Текст, который необходимо упаковать в контейнер
     */
    public ResponseData(String sText) {
        m_sText = sText;
    }

    /**
     * @return Строка, которая упакована в контейнер
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
