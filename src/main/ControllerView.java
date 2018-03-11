package main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ControllerView {

    @FXML
    private Label datePM25Label, resultPM25Label, datePM10Label, resultPM10Label;

    @FXML
    private ComboBox<String> cityComboBox, stationAddressComboBox;

    private List<Station> stationList;
    private List<Sensor> sensorList;
    private SensorData sensorData;

    private List<Integer> stationID;

    @FXML
    private void initialize() {
        stationList = new ModelView().getStations();
        Collections.sort(stationList);
        setStationCity();
        setStationName();
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
        cityComboBox.getSelectionModel().selectFirst();
    }

    public void setStationName() {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        String c, a, n, s = cityComboBox.getSelectionModel().getSelectedItem();
        int i = 0;
        stationID = new ArrayList<Integer>();

        for (Station station : stationList) {
            c = station.getCity().getName();
            a = station.getAddressStreet();
            n = station.getStationName();
            //if city address = null, set station name instead
            if (s.equals(c) && (a != null)) {
                observableList.add(a);
                stationID.add(i, station.getId());
                i++;
            } else if (s.equals(c)) {
                observableList.add(n);
                stationID.add(i, station.getId());
                i++;
            }
        }
        if (!stationAddressComboBox.getItems().isEmpty()) {
            stationAddressComboBox.getItems().clear();
        }

        stationAddressComboBox.setItems(observableList);
        stationAddressComboBox.getSelectionModel().selectFirst();

        setSensorData();
    }

    public void setSensorData() {
        if (!stationAddressComboBox.getItems().isEmpty()) {
            int idPM25 = 0, idPM10 = 0, i = stationAddressComboBox.getSelectionModel().getSelectedIndex();
            sensorList = new ModelView().getSensors(stationID.get(i).toString());

            //for PM 2.5 & PM 10
            for (Sensor s : sensorList) {
                if (s.getParam().getParamCode().equals("PM2.5")) {
                    idPM25 = s.getId();
                }
                if (s.getParam().getParamCode().equals("PM10")) {
                    idPM10 = s.getId();
                }
            }

            if (idPM25 != 0) {
                sensorData = new ModelView().getSensorData(String.valueOf(idPM25));

                for (SensorData.Values sd : sensorData.getValues()) {
                    if (sd.getValue() != null) {
                        resultPM25Label.setText(String.valueOf(sd.getValue()));
                        datePM25Label.setText(sd.getDate());
                        break;
                    }
                }
            } else {
                datePM25Label.setText("Sensor not installed.");
                resultPM25Label.setText("0");
            }

            if (idPM10 != 0) {
                sensorData = new ModelView().getSensorData(String.valueOf(idPM10));

                for (SensorData.Values sd : sensorData.getValues()) {
                    if (sd.getValue() != null) {
                        resultPM10Label.setText(String.valueOf(sd.getValue()));
                        datePM10Label.setText(sd.getDate());
                        break;
                    }
                }
            } else {
                datePM10Label.setText("Sensor not installed.");
                resultPM10Label.setText("0");
            }
        }
    }
}