package main;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class ModelView {

    public List<Station> getStations() {
        String stringURL = "http://api.gios.gov.pl/pjp-api/rest/station/findAll";
        stringURL = downloadJson(stringURL);

        //Needed to parse JSON to List<Station>
        Type foundListType = new TypeToken<ArrayList<Station>>() {
        }.getType();

        if (stringURL == "1") {
            stringURL = "[{'id': 1,'stationName':'Error','gegrLat':'Error','gegrLon':'Error','city':{'id': 1,'name':'Error','commune':{'communeName':'Error','districtName':'Error','provinceName':'Error'}},'addressStreet':'Error'}]";
            return new Gson().fromJson(stringURL, foundListType);
        } else {
            return new Gson().fromJson(stringURL, foundListType);
        }
    }

    public List<Sensor> getSensors(String stationID) {
        String stringURL = "http://api.gios.gov.pl/pjp-api/rest/station/sensors/" + stationID;
        stringURL = downloadJson(stringURL);

        //Needed to parse JSON to List<Sensor>
        Type foundListType = new TypeToken<ArrayList<Sensor>>() {
        }.getType();

        if (stringURL == "1") {
            stringURL = "[{'id': 1,'stationId': 1,'param':{'paramName':'Error','paramFormula':'Error','paramCode':'Error','idParam': 1}}]";
            return new Gson().fromJson(stringURL, foundListType);
        } else {
            return new Gson().fromJson(stringURL, foundListType);
        }
    }

    public SensorData getSensorData(String sensorID) {
        String stringURL = "http://api.gios.gov.pl/pjp-api/rest/data/getData/" + sensorID;

        stringURL = downloadJson(stringURL);

        if (stringURL == "1") {
            stringURL = "{'key':'Error','values':[{'date':'Error','value': 1}]}";
            return new Gson().fromJson(stringURL, SensorData.class);
        } else {
            return new Gson().fromJson(stringURL, SensorData.class);
        }
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