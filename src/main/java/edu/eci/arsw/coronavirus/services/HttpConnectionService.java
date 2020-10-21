package edu.eci.arsw.coronavirus.services;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * The interface Http connection service.
 */
public interface HttpConnectionService {
    /**
     * Gets all cases.
     *
     * @return the all cases
     * @throws UnirestException the unirest exception
     */
    JSONObject getAllCases() throws UnirestException;

    /**
     * Gets cases by country.
     *
     * @param country the country
     * @return the cases by country
     * @throws UnirestException the unirest exception
     */
    JSONObject getCasesByCountry(String country) throws UnirestException;

    /**
     * Gets coords by country.
     *
     * @param country the country
     * @return the coords by country
     * @throws UnirestException the unirest exception
     */
    JSONArray getCoordsByCountry(String country) throws UnirestException;
}
