package edu.eci.arsw.coronavirus.services;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

public interface HttpConnectionService {
    JSONObject getAllCases() throws UnirestException;
    JSONObject getCasesByCountry(String country) throws UnirestException;
    JSONArray getCoordsByCountry(String country) throws UnirestException;
}
