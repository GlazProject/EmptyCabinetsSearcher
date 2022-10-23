package ru.telegramBot.gm.telegramBot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.telegramBot.gm.readers.RequestData;
import ru.telegramBot.gm.writers.ResponseData;
import ru.telegramBot.gm.writers.Writer;

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
        ResponseData telegramResponseData = handler.handle(requestData);
        write(telegramResponseData);
    }

    /**
     * Метод, позволяющий отправлять сообщения в телеграм
     * @param data Контейнер с обработанной информацией, являющийся {@code TelegramResponseData}
     */
    @Override
    public void write(ResponseData data) {
        try{
            SendMessage message = writer.createMessage(data);
            execute(message);
        } catch (TelegramApiException e){
            e.printStackTrace();
        }
    }
}
