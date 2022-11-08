package ru.telegramBot.gm.app.dataContainers;

/**
 * Контейнер для полученных данных, который содержит компоненты с данными. Обращение к компонентам по имени
 */
public class RequestData extends ComponentsContainer {
    public RequestData() {
        super();
    }

    public RequestData(RequestData oldData) {
        super(oldData);
    }
}
