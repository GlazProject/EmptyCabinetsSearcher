package ru.telegramBot.gm.app.dataContainers;


/**
 * Контейнер для обработанных данных, который содержит компоненты с данными. Обращение к компонентам по имени
 */
public class ResponseData extends ComponentsContainer {
    public ResponseData() {
        super();
    }

    public ResponseData(ResponseData oldData) {
        super(oldData);
    }
}
