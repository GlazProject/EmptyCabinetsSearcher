package ru.telegramBot.gm.consoleBot;

import ru.telegramBot.gm.dataContainer.components.TextComponent;
import ru.telegramBot.gm.writers.ResponseData;
import ru.telegramBot.gm.writers.Writer;

/**
 * Простой класс для вывода данных из контейнера в консоль
 */
public class ConsoleWriter implements Writer {

    /**
     * Основной метод вывода данных в консоль
     * @param data Контейнер с входными данными
     * @exception IllegalArgumentException, если в контекнере не существует компонента с именем "text"
     */
    @Override
    public void write(ResponseData data) {
        TextComponent textComponent = data.getComponent(TextComponent.class);
        if (textComponent == null){
            throw new IllegalArgumentException("Не найдено поле с текстом");
        }
        System.out.println(textComponent.getText());

    }
}
