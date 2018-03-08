package main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.util.Collections;
import java.util.List;

public class ControllerView {

    @FXML
    private Label resultPM25Label, resultPM10Label;

    @FXML
    private ComboBox<String> cityComboBox, stationAddressComboBox;

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
        ObservableList<String> observableList = FXCollections.observableArrayList();
        String s = "";

        for (Station station : stationList) {
            String c = station.getCity().getName();
            //condition to not repeat cities in ComboBox
            if (!s.equals(c)) {
                s = c;
                observableList.add(s);
            }
        }
        cityComboBox.setItems(observableList);
    }

    public void setAddressName() {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        String i = cityComboBox.getSelectionModel().getSelectedItem();

        for (Station station : stationList) {
            String c = station.getCity().getName();
            String a = station.getAddressStreet();
            String n = station.getStationName();
            //if city address = null, set station name instead
            if (i.equals(c) && (a != null))
                observableList.add(a);
            else if (i.equals(c))
                observableList.add(n);
        }
        stationAddressComboBox.setItems(observableList);
    }
}