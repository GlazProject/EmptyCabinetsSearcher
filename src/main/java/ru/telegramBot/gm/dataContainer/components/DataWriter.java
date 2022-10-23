package ru.telegramBot.gm.dataContainer.components;


import org.jetbrains.annotations.NotNull;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * Класс, который позволяет удобно добавлять данные в контейнер.
 * Функция write перегружается для разных типов данных
 * <p>
 * Таблица ассоциации имени компонента и его типа:
 * <li>text : {@code TextComponent}</li>
 * <li>telegramMessage : {@code TelegramMessageComponent}</li>
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
        textComponent.set(text);
        data.setComponent("text", textComponent);
    }

    /**
     * Метод, который позволяет добавить сообщение Telegram в контейнер
     *
     * @param message Сообщение, который нужно добавить в контейнер
     * @param data Контейнер, в который происходит добавление
     */
    public static void write(
            @NotNull Message message,
            @NotNull ComponentsContainer data
    ){
        TelegramMessageComponent telegramMessageComponent = new TelegramMessageComponent();
        telegramMessageComponent.set(message);
        data.setComponent("telegramMessage", telegramMessageComponent);
    }
}
