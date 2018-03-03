//pattern for JSON data about Stations

package main;

public class Station {
    private int id;
    private String stationName;
    private String gegrLat;
    private String gegrLon;
    private String addressStreet;
    private StationCity city;


    public int getId() {
        return id;
    }

    public String getStationName() {
        return stationName;
    }

    public String getGegrLat() {
        return gegrLat;
    }

    public String getGegrLon() {
        return gegrLon;
    }

    public StationCity getCity() {
        return city;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    private class StationCity {
        private int id;
        private String name;
        private StationCityCommune commune;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public StationCityCommune getCommune() {
            return commune;
        }

        private class StationCityCommune {
            private String communeName;
            private String districtName;
            private String provinceName;

            public String getCommuneName() {
                return communeName;
            }

            public String getDistrictName() {
                return districtName;
            }

            public String getProvinceName() {
                return provinceName;
            }
        }
    }
}