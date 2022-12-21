package ru.telegramBot.gm.app.handlers.commandHandlers;

import org.jetbrains.annotations.NotNull;
import ru.telegramBot.gm.app.dataComponents.TextComponent;
import ru.telegramBot.gm.app.dataContainers.RequestData;
import ru.telegramBot.gm.app.dataContainers.ResponseData;
import ru.telegramBot.gm.app.handlers.Handler;
import ru.telegramBot.gm.app.urfuData.Cabinet;
import ru.telegramBot.gm.app.urfuData.CanNotGetScheduleException;
import ru.telegramBot.gm.app.urfuData.University;
import ru.telegramBot.gm.app.urfuData.UrfuManager;

import javax.annotation.Nullable;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Objects;

public class GetBusyCabinets implements Handler {
    private volatile University university;

    @Nullable
    @Override
    public ResponseData handle(@NotNull RequestData data) {
        ResponseData responseData = new ResponseData();
        StringBuilder sb = new StringBuilder();

        TextComponent textComponent = data.getComponent(TextComponent.class);
        if (textComponent == null)
            return null;

        CommandParser commandParser = new CommandParser(textComponent.getText());
        if (!Objects.equals(commandParser.getCommand(), "getBusyCabinets"))
            return null;

        Time time = Time.valueOf(LocalTime.now());
        try{
            time = Time.valueOf(commandParser.getText() + ":00");
        }
        catch (Exception e){
            sb.append("Can not read time. Selected current time: ");
            sb.append(time);
            sb.append("\n");
            e.printStackTrace();
        }

        TextComponent resultText;

        if (university == null) {
            try {
                university = UrfuManager.collectCabinetsInfo(62404);
            } catch (CanNotGetScheduleException e) {
                resultText = new TextComponent("Can not get schedule");
                responseData.setComponent(resultText);
                return responseData;
            }
        }


        sb.append("Busied cabinets\n");
        for (Cabinet cab : university.getCabinets()){
            if (!cab.isBusy(time) || cab.Address.startsWith("Софьи"))
                continue;
            sb.append(cab);
            sb.append('\n');
        }
        resultText = new TextComponent(sb.toString());

        responseData.setComponent(resultText);
        return responseData;
    }
}
