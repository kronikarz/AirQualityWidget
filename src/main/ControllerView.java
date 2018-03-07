package main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ControllerView {

    @FXML
    private Label resultPM25Label;

    @FXML
    private Label resultPM10Label;

    @FXML
    private ComboBox<String> stationNameComboBox, cityComboBox;

    private ObservableList<String> observableList = FXCollections.observableArrayList();

    private List<Station> stationList;
    private List<Sensor> sensorList;
    private List<SensorData> sensorDataList;

    @FXML
    private void initialize() {
        stationList = new ModelView().getStations();
        Collections.sort(stationList);
        setStationCity();
    }

    public void setStationCity() {
        observableList.clear();
        String string = "";

        for(Station station : stationList) {
            //condition to not repeat cities
            if (!station.getCity().getName().equals(string)) {
                string = station.getCity().getName();
                observableList.add(string);
            }
        }
        cityComboBox.setItems(observableList);
    }

    public void setStationName() {
        observableList.clear();

        for (Station station : stationList) {
            observableList.add(station.getStationName());
        }
        stationNameComboBox.setItems(observableList);
    }
}