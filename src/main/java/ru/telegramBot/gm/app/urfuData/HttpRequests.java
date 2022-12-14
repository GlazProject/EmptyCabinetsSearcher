package ru.telegramBot.gm.app.urfuData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;


public class HttpRequests {

    public String request(String urlPath){
        HttpURLConnection connection = null;
        String inputLine;
        StringBuilder resultData = new StringBuilder();
        InputStream is = null;
        try {
            URL url = new URL(urlPath);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Chrome/81.0.4044.138");
            connection.setConnectTimeout(500);
            connection.setReadTimeout(3000);
            is = connection.getInputStream();
            BufferedReader bufferReader  = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            while ((inputLine = bufferReader.readLine()) != null){
                resultData.append(inputLine).append("\n");
            }
        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (is != null){
                try{
                    is.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
            if (connection != null){
                connection.disconnect();
            }
        }
        return resultData.toString();
    }

}