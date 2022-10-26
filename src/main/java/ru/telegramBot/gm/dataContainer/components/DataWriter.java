package ru.telegramBot.gm.dataContainer.components;


import org.jetbrains.annotations.NotNull;

/**
 * Класс, который позволяет удобно добавлять данные в контейнер.
 * Функция write перегружается для разных типов данных
 */
public class DataWriter {

    /**
     * Метод, который позволяет добавить текстовый компонент с заданным текстов в контейнер
     *
     * @param text Текст, который нужно добавить в контейнер
     * @param data Контейнер, в который происходит добавление
     */
    public static void write(
            @NotNull String text,
            @NotNull ComponentsContainer data
    ){
        TextComponent textComponent = new TextComponent();
        textComponent.setText(text);
        data.setComponent(textComponent);
    }
}
