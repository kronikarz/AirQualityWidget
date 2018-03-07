package main;

import java.util.List;

public class SensorData {
    String key; //"key": "PM10",
    List<Values> values;

    public String getKey() {
        return key;
    }

    public List<Values> getValues() {
        return values;
    }

    private class Values {
        String date;
        double value; //"value": 30.3018

        public String getDate() {
            return date;
        }

        public double getValue() {
            return value;
        }
    }
}
