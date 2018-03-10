package main;

import java.util.List;

public class SensorData {
    private String key; //"key": "PM10",
    private List<Values> values;

    public String getKey() {
        return key;
    }

    public List<Values> getValues() {
        return values;
    }

    public class Values {
        private String date;
        private Double value; //"value": 30.3018

        public String getDate() {
            return date;
        }

        public Double getValue() {
            return value;
        }
    }
}
