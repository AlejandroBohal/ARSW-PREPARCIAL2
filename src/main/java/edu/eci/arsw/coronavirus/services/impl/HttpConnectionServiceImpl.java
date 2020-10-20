package edu.eci.arsw.coronavirus.services.impl;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import edu.eci.arsw.coronavirus.services.HttpConnectionService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class HttpConnectionServiceImpl implements HttpConnectionService {
    public JSONObject getAllCases() throws UnirestException {
        HttpResponse<String> response = Unirest
                .get("https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/stats")
                .header("x-rapidapi-host", "covid-19-coronavirus-statistics.p.rapidapi.com")
                .header("x-rapidapi-key", "34f05cff54msh30ba6f36c91c183p166499jsn555917ef62b8")
                .asString();
        return new JSONObject(response.getBody()).getJSONObject("data");
    }
    public JSONObject getCasesByCountry(String country) throws UnirestException {
        HttpResponse<String> response = Unirest
                .get("https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/stats?country=" + country)
                .header("x-rapidapi-host", "covid-19-coronavirus-statistics.p.rapidapi.com")
                .header("x-rapidapi-key", "34f05cff54msh30ba6f36c91c183p166499jsn555917ef62b8")
                .asString();

        return new JSONObject(response.getBody()).getJSONObject("data");
    }
    public JSONArray getCoordsByCountry(String country) throws UnirestException {
        HttpResponse<String> response = Unirest.get("https://rapidapi.p.rapidapi.com/name/" + country)
                .header("x-rapidapi-host", "restcountries-v1.p.rapidapi.com")
                .header("x-rapidapi-key", "e06d3cb55dmshaba3bacba6d96f0p1fa961jsnbf6e2afb02ab")
                .asString();
        return new JSONArray(response.getBody()).getJSONObject(0).getJSONArray("latlng");
    }
}
