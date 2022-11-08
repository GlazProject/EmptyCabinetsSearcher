package ru.telegramBot.gm.clients.consoleBot;

import ru.telegramBot.gm.app.dataComponents.TextComponent;
import ru.telegramBot.gm.app.dataContainers.ResponseData;
import ru.telegramBot.gm.clients.Writer;

/**
 * Простой класс для вывода данных из контейнера в консоль
 */
public class ConsoleWriter implements Writer {
    /**
     * Основной метод вывода данных в консоль
     *
     * @param data Контейнер с входными данными
     * @throws IllegalArgumentException, если в контейнере не существует компонента с именем "text"
     */
    @Override
    public void write(ResponseData data) {
        TextComponent textComponent = data.getComponent(TextComponent.class);
        if (textComponent == null) {
            throw new IllegalArgumentException("Не найдено поле с текстом");
        }
        System.out.println(textComponent.getText());

    }
}
