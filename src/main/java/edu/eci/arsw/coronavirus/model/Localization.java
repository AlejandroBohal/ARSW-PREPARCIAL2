package edu.eci.arsw.coronavirus.model;

public class Localization {
    private double lng;
    private double lat;

    public Localization(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }
}
