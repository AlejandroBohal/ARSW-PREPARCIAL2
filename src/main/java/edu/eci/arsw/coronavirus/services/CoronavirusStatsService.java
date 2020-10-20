package edu.eci.arsw.coronavirus.services;

import com.mashape.unirest.http.exceptions.UnirestException;
import edu.eci.arsw.coronavirus.model.Case;

import java.util.List;

public interface CoronavirusStatsService {
    List<Case> getAllCases() throws UnirestException;
    List<Case> getCasesByCountry(String country) throws UnirestException;
}
