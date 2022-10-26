package ru.telegramBot.gm.dataContainer.components;

/**
 * Текстовый компонент контейнера данных, который хранит данные типа String
 */
public class TextComponent implements Component {
    private String text;

    /**
     * Метод, который позволяет получить текст из компонента
     * @return Текст, который содержится в компоненте
     */
    public String getText() {
        return text;
    }

    /**
     * Метод, который позволяет добавить (обновить) текст в компонент
     * @param value Значение типа `String`, которое необходимо записать в компонент
     */
    public void setText(String value){
        text = value;
    }
}
