package main;

public class Sensor {
    private int id;
    private int stationId;
    private SensorParam param;

    public int getId() {
        return id;
    }

    public SensorParam getParam() {
        return param;
    }

    public class SensorParam {
        private String paramName;
        private String paramFormula;
        private String paramCode;
        private int idParam;

        public String getParamName() {
            return paramName;
        }

        public String getParamFormula() {
            return paramFormula;
        }

        public String getParamCode() {
            return paramCode;
        }

        public int getIdParam() {
            return idParam;
        }
    }
}