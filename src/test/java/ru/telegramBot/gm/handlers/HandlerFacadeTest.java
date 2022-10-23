package ru.telegramBot.gm.handlers;

import org.junit.jupiter.api.Test;
import ru.telegramBot.gm.dataContainer.components.TextComponent;
import ru.telegramBot.gm.handlers.commandHandlers.CommandFacade;
import ru.telegramBot.gm.handlers.commandHandlers.StartCommandHandler;
import ru.telegramBot.gm.handlers.commandHandlers.UnknownCommandHandler;
import ru.telegramBot.gm.readers.RequestData;
import ru.telegramBot.gm.writers.ResponseData;

import javax.annotation.Nullable;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

class HandlerFacadeTest {
    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("phrases");

    private  RequestData createRequestDataWithText (String text){
        RequestData requestData = new RequestData();
        TextComponent textComponent = new TextComponent();
        textComponent.set(text);
        requestData.setComponent("text", textComponent);
        return requestData;
    }
    @Nullable
    private String getTextFromResponseData(ResponseData responseData){
        TextComponent textComponent = responseData.getComponent("text");
        if (textComponent == null)
            return null;
        return textComponent.get();
    }
    @Nullable
    private String getTextResultFromTextData(String text, Handler handler){
        RequestData requestData = createRequestDataWithText(text);
        ResponseData responseData = handler.handle(requestData);
        return getTextFromResponseData(responseData);
    }


    void assertHelper(String text, String resourceName){
        String realResult = getTextResultFromTextData(text, new CommandFacade());
        String expectedResult = resourceBundle.getString(resourceName);
        assertEquals(expectedResult, realResult);
    }

    @Test
    void helloText_ForStartCommand() {
        assertHelper("/start", StartCommandHandler.HANDLER_START);
        assertHelper("/start and some text", StartCommandHandler.HANDLER_START);
    }

    @Test
    void response_ForUnknownCommand(){
        assertHelper("/unknownCommand", UnknownCommandHandler.UNKNOWN_COMMAND);
        assertHelper("/unknownCommand and some text", UnknownCommandHandler.UNKNOWN_COMMAND);
    }
}