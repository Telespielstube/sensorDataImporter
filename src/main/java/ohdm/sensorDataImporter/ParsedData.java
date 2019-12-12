package ohdm.sensorDataImporter;

public class ParsedData {
    private int importedSensorId;
    private String sensorType;
    private int location;
    private float latitude;
    private float longitude;
    private String timestamp;
    private float value1;
    private float value2;
    private float value3;
    private float value4;
    private float value5;
    private float value6;
    private float value7;

    public static class Builder {
        // required data for all sensors
        private int importedSensorId;
        private String sensorType;
        private int location;
        private float latitude;
        private float longitude;
        private String timestamp;
        // optional values depending on sensor
        private float value1;
        private float value2;
        private float value3;
        private float value4;
        private float value5;
        private float value6;
        private float value7;

        public Builder(int importedSensorId, String sensorType, int location, float latitude, float longitude, String timestamp) {
            this.importedSensorId = importedSensorId;
            this.sensorType = sensorType;
            this.location = location;
            this.latitude = latitude;
            this.longitude = longitude;
            this.timestamp = timestamp;
        }

        public Builder value1(float value1) {
            this.value1 = value1;
            return this;
        }

        public Builder value2(float value2) {
            this.value2 = value2;
            return this;
        }

        public Builder value3(float value3) {
            this.value3 = value3;
            return this;
        }

        public Builder value4(float value4) {
            this.value4 = value4;
            return this;
        }

        public Builder value5(float value5) {
            this.value5 = value5;
            return this;
        }

        public Builder value6(float value6) {
            this.value6 = value6;
            return this;
        }

        public Builder value7(float value7) {
            this.value7 = value7;
            return this;
        }

        public ParsedData build() {
            return new ParsedData(this);
        }
    }

    private ParsedData(Builder builder) {
        this.importedSensorId = builder.importedSensorId;
        this.sensorType = builder.sensorType;
        this.location = builder.location;
        this.latitude = builder.latitude;
        this.longitude = builder.longitude;
        this.timestamp = builder.timestamp;
    }

    
    // Getter, setter
    public int getImportedSensorId() {
        return importedSensorId;
    }

    public void setImportedSensorId(int importedSensorId) {
        this.importedSensorId = importedSensorId;
    }

    public String getSensorType() {
        return sensorType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public float getValue1() {
        return value1;
    }

    public void setValue1(float value1) {
        this.value1 = value1;
    }

    public float getValue2() {
        return value2;
    }

    public void setValue2(float value2) {
        this.value2 = value2;
    }

    public float getValue3() {
        return value3;
    }

    public void setValue3(float value3) {
        this.value3 = value3;
    }

    public float getValue4() {
        return value4;
    }

    public void setValue4(float value4) {
        this.value4 = value4;
    }

    public float getValue5() {
        return value5;
    }

    public void setValue5(float value5) {
        this.value5 = value5;
    }

    public float getValue6() {
        return value6;
    }

    public void setValue6(float value6) {
        this.value6 = value6;
    }

    public float getValue7() {
        return value7;
    }

    public void setValue7(float value7) {
            this.value7 = value7;
    }
}
