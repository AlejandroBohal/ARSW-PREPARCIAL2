package edu.eci.arsw.coronavirus.services.impl;
import com.google.gson.Gson;
import com.mashape.unirest.http.exceptions.UnirestException;
import edu.eci.arsw.coronavirus.model.Case;
import edu.eci.arsw.coronavirus.model.Localization;
import edu.eci.arsw.coronavirus.services.CoronavirusStatsService;
import edu.eci.arsw.coronavirus.services.HttpConnectionService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.LinkedList;
import java.util.List;

@Service
public class CoronavirusStatsServiceImpl implements CoronavirusStatsService {
    @Autowired
    HttpConnectionService httpConnectionService;
    @Override
    public List<Case> getAllCases() throws UnirestException {
        List<Case> cases = new LinkedList<>();
        JSONArray array = httpConnectionService.getAllCases().getJSONArray("covid19Stats");
        return getCases(cases, array);
    }
    @Override
    public List<Case> getCasesByCountry(String country) throws UnirestException {
        List<Case> cases = new LinkedList<>();
        JSONArray array = httpConnectionService.getCasesByCountry(country).getJSONArray("covid19Stats");
        cases = getCases(cases, array);
        for (Case caseO:cases) {
            JSONArray coords = httpConnectionService.getCoordsByCountry(country);
            caseO.setLocalization(new Localization(coords.getDouble(0),coords.getDouble(1)));
        }
        return cases;
    }
    private List<Case> getCases(List<Case> cases, JSONArray array) {
        Gson gson = new Gson();
        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);
            Case caseO = gson.fromJson(object.toString(),Case.class);
            cases.add(caseO);
        }
        return cases;
    }

}
