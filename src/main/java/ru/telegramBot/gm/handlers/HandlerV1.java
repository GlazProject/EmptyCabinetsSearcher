package ru.telegramBot.gm.handlers;


import ru.telegramBot.gm.readers.RequestData;
import ru.telegramBot.gm.readers.RequestDataV1;

/**
 * Обрабочитк, который работает с контейнерами, которые содержат только строку
 */
public interface HandlerV1 extends Handler {
    @Override
    default boolean correctVersion(RequestData data){
        return data instanceof RequestDataV1;
    }
}
