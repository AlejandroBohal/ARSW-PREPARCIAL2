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
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service
public class CoronavirusStatsServiceImpl implements CoronavirusStatsService {
    @Autowired
    HttpConnectionService httpConnectionService;
    @Override
    public List<Case> getAllCases() throws UnirestException {
        List<Case> unsortedCases = new LinkedList<>();
        List<Case> sortedCases = new LinkedList<>();
        JSONArray array = httpConnectionService.getAllCases().getJSONArray("covid19Stats");
        getCases(unsortedCases, array);
        filterCasesByCountry(unsortedCases,sortedCases);
        Collections.sort(sortedCases);
        return sortedCases;
    }
    @Override
    public List<Case> getCasesByCountry(String country) throws UnirestException {
        List<Case> unsortedCases = new LinkedList<>();
        List<Case> sortedCases = new LinkedList<>();
        JSONArray array = httpConnectionService.getCasesByCountry(country).getJSONArray("covid19Stats");
        getCases(unsortedCases, array);
        filterCasesByProvince(unsortedCases,sortedCases);
        setLocalization(sortedCases,country);
        Collections.sort(sortedCases);
        return sortedCases;
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
    private void setLocalization(List<Case> sortedCases, String country) throws UnirestException {
        for (Case caseO:sortedCases) {
            if(caseO.getCountry().equals(country)){
                JSONArray coords = httpConnectionService.getCoordsByCountry(country);
                caseO.setLocalization(new Localization(coords.getDouble(0),coords.getDouble(1)));
            }
        }
    }
    private void filterCasesByProvince(List<Case> allCases, List<Case> sortedCases){
        for(Case caseToMap : allCases){
            Case auxCase = new Case();
            if(sortedCases.stream().noneMatch(o -> o.getProvince().equals(caseToMap.getProvince()))){
                long deaths = 0;
                long confirmed = 0;
                long recovered = 0;
                auxCase.setCountry(caseToMap.getCountry());
                auxCase.setProvince(caseToMap.getProvince());
                for (Case caseToCollect : allCases){
                    if(caseToCollect.getProvince().equals(caseToMap.getProvince())){
                        deaths += caseToCollect.getDeaths();
                        confirmed += caseToCollect.getConfirmed();
                        recovered += caseToCollect.getRecovered();
                    }
                }
                formatCase(auxCase,new Case(deaths, confirmed, recovered),sortedCases);
            }
        }
    }
    private void filterCasesByCountry(List<Case> allCases, List<Case> sortedCases){
        for(Case caseToMap : allCases){
            Case auxCase = new Case();
            if(sortedCases.stream().noneMatch(o -> o.getCountry().equals(caseToMap.getCountry()))){
                long deaths = 0;
                long confirmed = 0;
                long recovered = 0;
                auxCase.setCountry(caseToMap.getCountry());
                for (Case caseToCollect : allCases){
                    if(caseToCollect.getCountry().equals(caseToMap.getCountry())){
                        deaths += caseToCollect.getDeaths();
                        confirmed += caseToCollect.getConfirmed();
                        recovered += caseToCollect.getRecovered();
                    }
                }
                formatCase(auxCase,new Case(deaths, confirmed, recovered),sortedCases);
            }
        }
    }
    private void formatCase(Case auxCase, Case changeCase, List<Case> sortedCases){
        auxCase.setConfirmed(changeCase.getConfirmed());
        auxCase.setDeaths(changeCase.getDeaths());
        auxCase.setRecovered(changeCase.getRecovered());
        sortedCases.add(auxCase);
    }
}
