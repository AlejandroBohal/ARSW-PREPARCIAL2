package edu.eci.arsw.coronavirus.model;

/**
 * The type Localization.
 */
public class Localization {
    private double lng;
    private double lat;

    /**
     * Instantiates a new Localization.
     *
     * @param lat the lat
     * @param lng the lng
     */
    public Localization(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    /**
     * Gets lng.
     *
     * @return the lng
     */
    public double getLng() {
        return lng;
    }

    /**
     * Sets lng.
     *
     * @param lng the lng
     */
    public void setLng(double lng) {
        this.lng = lng;
    }

    /**
     * Gets lat.
     *
     * @return the lat
     */
    public double getLat() {
        return lat;
    }

    /**
     * Sets lat.
     *
     * @param lat the lat
     */
    public void setLat(double lat) {
        this.lat = lat;
    }
}
