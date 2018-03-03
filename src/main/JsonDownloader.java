package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JsonDownloader {

    public String findAllStations() {
        String stringURL = "http://api.gios.gov.pl/pjp-api/rest/station/findAll";
        return downloadJson(stringURL);
    }

    private String downloadJson(String stringURL) {
        try {
            URL url = new URL(stringURL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setRequestMethod("GET");
            int responseCode = httpURLConnection.getResponseCode();

            System.out.println("\nSending 'GET' request to " + stringURL);
            System.out.println("Response Code: " + responseCode);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String inputLineString;
            StringBuffer inputLineBuffer = new StringBuffer();

            while ((inputLineString = bufferedReader.readLine()) != null) {
                inputLineBuffer.append(inputLineString);
            }

            bufferedReader.close();

            return inputLineBuffer.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "1";
    }


}