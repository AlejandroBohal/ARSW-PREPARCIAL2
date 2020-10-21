package edu.eci.arsw.coronavirus.services;

import com.mashape.unirest.http.exceptions.UnirestException;
import edu.eci.arsw.coronavirus.model.Case;

import java.util.List;

/**
 * The interface Coronavirus stats service.
 */
public interface CoronavirusStatsService {
    /**
     * Gets all cases.
     *
     * @return the all cases
     * @throws UnirestException the unirest exception
     */
    List<Case> getAllCases() throws UnirestException;

    /**
     * Gets cases by country.
     *
     * @param country the country
     * @return the cases by country
     * @throws UnirestException the unirest exception
     */
    List<Case> getCasesByCountry(String country) throws UnirestException;
}
