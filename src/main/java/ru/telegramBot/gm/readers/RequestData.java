package ru.telegramBot.gm.readers;

import ru.telegramBot.gm.dataContainer.components.ComponentsContainer;

/**
 * Обёртка для данных, которая содержит только поле с текстом
 */
public class RequestData extends ComponentsContainer {
    public RequestData(){
        super();
    }

    public RequestData(RequestData oldData){
        super(oldData);
    }
}
