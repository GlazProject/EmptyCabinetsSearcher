package ru.telegramBot.gm.app.urfuData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class HttpRequests {

    public String request(String urlPath){
        HttpURLConnection connection = null;
        String inputLine = "";
        String resultData = "";
        InputStream is = null;
        try {
            URL url = new URL(urlPath);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(300);
            connection.setReadTimeout(3000);
            is = connection.getInputStream();
            BufferedReader bufferReader  = new BufferedReader(new InputStreamReader(is));
            while ((inputLine = bufferReader.readLine()) != null ){
                resultData +=inputLine + "\n";
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
        return resultData;
    }

}