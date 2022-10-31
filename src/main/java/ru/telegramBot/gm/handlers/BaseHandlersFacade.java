package ru.telegramBot.gm.handlers;

import org.jetbrains.annotations.NotNull;
import ru.telegramBot.gm.readers.RequestData;
import ru.telegramBot.gm.writers.ResponseData;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Фасад, который позволяет обрабатывать любые данные
 */
public abstract class BaseHandlersFacade implements Handler{

    /**
     * Список всех обработчиков (фасадов), которым даётся возможность обработать данные
     */
    private final List<Handler> handlers = new ArrayList<>();

    public BaseHandlersFacade(Handler... optionalHandlers){
        Collections.addAll(handlers, optionalHandlers);
    }

    /**
     * Метод, который выполняет обработку полученных данных
     *
     * @param data Контейнер с данными, которые получили при чтении
     * @return Контейнер с обработанными данными, либо null, если ни один обработчик не смог обработать данные
     */
    @Nullable
    @Override
    public ResponseData handle(@NotNull RequestData data) {
        for (Handler handler : handlers){
            ResponseData responseData = handler.handle(data);
            if (responseData != null)
                return responseData;
        }
        return null;
    }
}
