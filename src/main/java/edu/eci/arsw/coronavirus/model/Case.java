package edu.eci.arsw.coronavirus.model;

import java.util.Date;

public class Case {
    private String city;
    private String province;
    private String country;
    private String lastUpdate;
    private String keyId;
    private long confirmed;
    private long deaths;
    private long recovered;
    private Localization localization;
    public Case(){
    }
    public Case(String city, String province, String country, String lastUpdate, String keyId, long confirmed, long deaths, long recovered) {
        this.city = city;
        this.province = province;
        this.country = country;
        this.lastUpdate = lastUpdate;
        this.keyId = keyId;
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
        this.localization = new Localization(0.0,0.0);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city==null ? "" : city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province==null ? "" : province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public long getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(long confirmed) {
        this.confirmed = confirmed;
    }

    public long getDeaths() {
        return deaths;
    }

    public void setDeaths(long deaths) {
        this.deaths = deaths;
    }

    public long getRecovered() {
        return recovered;
    }

    public void setRecovered(long recovered) {
        this.recovered = recovered;
    }

    public Localization getLocalization() {
        return localization;
    }

    public void setLocalization(Localization localization) {
        this.localization = localization;
    }
}
