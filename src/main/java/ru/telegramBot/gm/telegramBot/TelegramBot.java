package ru.telegramBot.gm.telegramBot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.telegramBot.gm.readers.RequestData;
import ru.telegramBot.gm.writers.ResponseData;
import ru.telegramBot.gm.writers.Writer;

import java.security.KeyException;

/**
 * Класс, позволяющий создать подключение к TelegramApi
 */
public class TelegramBot extends TelegramLongPollingBot
    implements Writer
{
    private final String botName;
    private final String botToken;
    private final TelegramHandler handler;
    private final TelegramWriter writer;
    private final TelegramReader reader;

    public TelegramBot(String bot_name, String bot_token) {
        super();
        botName = bot_name;
        botToken = bot_token;
        handler = new TelegramHandler();
        writer = new TelegramWriter();
        reader = new TelegramReader();
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        RequestData requestData = reader.readDataFromUpdate(update);
        TelegramResponseData telegramResponseData = handler.handle(requestData, update);
        write(telegramResponseData);
    }

    @Override
    public void write(ResponseData data) {
        if (data instanceof TelegramResponseData) {
            try{
                SendMessage message = writer.createMessage((TelegramResponseData)data);
                execute(message);
            } catch (KeyException e){
                System.out.println("Возникли проблемы при формировании сообщения");
            } catch (TelegramApiException e){
                e.printStackTrace();
            }
        }
        throw new ClassCastException("Контейнер не является TelegramResponseData");
    }
}
