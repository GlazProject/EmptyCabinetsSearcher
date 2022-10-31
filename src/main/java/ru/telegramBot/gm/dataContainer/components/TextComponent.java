package ru.telegramBot.gm.dataContainer.components;

import java.util.Objects;

/**
 * Текстовый компонент контейнера данных, который хранит данные типа String
 */
public class TextComponent implements Component {
    private final String text;

    public TextComponent(String inputText) {
        text = inputText;
    }

    /**
     * Метод, который позволяет получить текст из компонента
     * @return Текст, который содержится в компоненте
     */
    public String getText() {
        return text;
    }

    @Override
    public int hashCode() {
        return text.hashCode();
    }
    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        return Objects.equals(((TextComponent) obj).text, text);
    }
}
