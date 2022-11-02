package ru.telegramBot.gm.readers;

import ru.telegramBot.gm.dataComponents.ComponentsContainer;

/**
 * Контейнер для полученных данных, который содержит компоненты с данными. Обращение к компонентам по имени
 */
public class RequestData extends ComponentsContainer {
    public RequestData(){
        super();
    }
    public RequestData(RequestData oldData){
        super(oldData);
    }
}
