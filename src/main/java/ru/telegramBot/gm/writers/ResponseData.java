package ru.telegramBot.gm.writers;

import ru.telegramBot.gm.dataContainer.components.ComponentsContainer;



/**
 * Контейнер для обработанных данных, который содержит компоненты с данными. Обращение к компонентам по имени
 */
public class ResponseData extends ComponentsContainer {
    public ResponseData() {
        super();
    }
    public ResponseData(ResponseData oldData){
        super(oldData);
    }
}