package ru.telegramBot.gm.handlers;

import org.junit.jupiter.api.Test;
import ru.telegramBot.gm.dataContainer.components.TextComponent;
import ru.telegramBot.gm.handlers.commandHandlers.StartCommandHandler;
import ru.telegramBot.gm.handlers.commandHandlers.UnknownCommandHandler;
import ru.telegramBot.gm.readers.RequestData;
import ru.telegramBot.gm.writers.ResponseData;

import javax.annotation.Nullable;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

class HandlerFacadeTest {
    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("phrases");

    private RequestData createRequestDataWithText (String text){
        RequestData requestData = new RequestData();
        TextComponent textComponent = new TextComponent();
        textComponent.setText(text);
        requestData.setComponent(textComponent);
        return requestData;
    }
    @Nullable
    private String getTextFromResponseData(ResponseData responseData){
        TextComponent textComponent = responseData.getComponent(TextComponent.class);
        if (textComponent == null)
            return null;
        return textComponent.getText();
    }
    @Nullable
    private String getTextResultFromTextData(String text, Handler handler){
        RequestData requestData = createRequestDataWithText(text);
        ResponseData responseData = handler.handle(requestData);
        return getTextFromResponseData(responseData);
    }


    private void assertBundleHelper(String text, String resourceName){
        String realResult = getTextResultFromTextData(text, new HandlerFacade());
        String expectedResult = resourceBundle.getString(resourceName);
        assertEquals(expectedResult, realResult);
    }

    private void assertTextHelper(String sentText, String expectedText){
        String realResult = getTextResultFromTextData(sentText, new HandlerFacade());
        assertEquals(expectedText, realResult);
    }

    @Test
    public void helloText_ForStartCommand() {
        assertBundleHelper("/start", StartCommandHandler.HANDLER_START);
        assertBundleHelper("/start and some text", StartCommandHandler.HANDLER_START);
    }

    @Test
    public void response_ForUnknownCommand(){
        assertBundleHelper("/unknownCommand", UnknownCommandHandler.UNKNOWN_COMMAND);
        assertBundleHelper("/unknownCommand and some text", UnknownCommandHandler.UNKNOWN_COMMAND);
    }

    @Test
    public void getEchoText(){
        String englishText = "My english text";
        String russianText = "Мой русский текст";
        assertTextHelper(englishText, englishText);
        assertTextHelper(russianText, russianText);
    }

}