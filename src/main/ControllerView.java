package main;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ControllerView {

    @FXML
    private Label labelResultPM25;

    @FXML
    private Label labelResultPM10;

    private JsonDownloader jsonDownloader = new JsonDownloader();
    private String allStations;

    @FXML
    private void initialize() {
        allStations = jsonDownloader.findAllStations();

        if (allStations == "1") {
            System.out.println("Error. No data downloaded from server...");
        } else {
            Type foundListType = new TypeToken<ArrayList<Station>>() {}.getType();
            List<Station> stationsList = new Gson().fromJson(allStations, foundListType);

            //test
            System.out.println(stationsList.get(1).getId());
        }
    }

    public void refreshData() {

    }

    public void downloadCities() {

    }
}
