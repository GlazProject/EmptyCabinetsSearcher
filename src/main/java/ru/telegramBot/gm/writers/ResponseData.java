package ru.telegramBot.gm.writers;

import ru.telegramBot.gm.dataContainer.components.ComponentsContainer;



/**
 * Обёртка для данных, которая имеет только поле с текстом
 */
public class ResponseData extends ComponentsContainer {
    public ResponseData(ResponseData oldData){
        super(oldData);
    }

    public ResponseData() {
        super();
    }
}