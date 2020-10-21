package edu.eci.arsw.coronavirus.model;

import java.util.Comparator;


/**
 * The type Case.
 */
public class Case implements Comparable<Case>{
    private String province;
    private String country;
    private long confirmed;
    private long deaths;
    private long recovered;
    private Localization localization;

    /**
     * Instantiates a new Case.
     */
    public Case(){
    }

    /**
     * Instantiates a new Case.
     *
     * @param province  the province
     * @param country   the country
     * @param confirmed the confirmed
     * @param deaths    the deaths
     * @param recovered the recovered
     */
    public Case(String province, String country, long confirmed, long deaths, long recovered) {
        this.province = province;
        this.country = country;
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
        this.localization = new Localization(0.0,0.0);
    }

    /**
     * Instantiates a new Case.
     *
     * @param deaths    the deaths
     * @param confirmed the confirmed
     * @param recovered the recovered
     */
    public Case(long deaths, long confirmed, long recovered) {
        this.deaths = deaths;
        this.confirmed = confirmed;
        this. recovered = recovered;
    }


    /**
     * Gets province.
     *
     * @return the province
     */
    public String getProvince() {
        return province;
    }

    /**
     * Sets province.
     *
     * @param province the province
     */
    public void setProvince(String province) {
        this.province = province==null ? "" : province;
    }

    /**
     * Gets country.
     *
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets country.
     *
     * @param country the country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets confirmed.
     *
     * @return the confirmed
     */
    public long getConfirmed() {
        return confirmed;
    }

    /**
     * Sets confirmed.
     *
     * @param confirmed the confirmed
     */
    public void setConfirmed(long confirmed) {
        this.confirmed = confirmed;
    }

    /**
     * Gets deaths.
     *
     * @return the deaths
     */
    public long getDeaths() {
        return deaths;
    }

    /**
     * Sets deaths.
     *
     * @param deaths the deaths
     */
    public void setDeaths(long deaths) {
        this.deaths = deaths;
    }

    /**
     * Gets recovered.
     *
     * @return the recovered
     */
    public long getRecovered() {
        return recovered;
    }

    /**
     * Sets recovered.
     *
     * @param recovered the recovered
     */
    public void setRecovered(long recovered) {
        this.recovered = recovered;
    }

    /**
     * Gets localization.
     *
     * @return the localization
     */
    public Localization getLocalization() {
        return localization;
    }

    /**
     * Sets localization.
     *
     * @param localization the localization
     */
    public void setLocalization(Localization localization) {
        this.localization = localization;
    }

    @Override
    public String toString() {
        return "Case{" +
                ", province='" + province + '\'' +
                ", country='" + country + '\'' +
                ", confirmed=" + confirmed +
                ", deaths=" + deaths +
                ", recovered=" + recovered +
                ", localization=" + localization +
                '}';
    }

    @Override
    public int compareTo(Case o) {
        int result = Comparator.comparing(Case::getDeaths).reversed()
                .thenComparing(Case::getConfirmed).reversed()
                .thenComparing(Case::getRecovered).reversed().compare(this,o);
        return result;
    }
}
