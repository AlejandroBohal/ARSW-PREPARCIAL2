package edu.eci.arsw.coronavirus.controller;
import com.mashape.unirest.http.exceptions.UnirestException;
import edu.eci.arsw.coronavirus.model.Case;
import edu.eci.arsw.coronavirus.services.CoronavirusStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Coronavirus stats controller.
 */
@RestController
@RequestMapping("/api")
public class CoronavirusStatsController {
    @Autowired
    private CoronavirusStatsService coronavirusStatsService;
    /**
     * Get all cases response entity.
     *
     * @return the response entity
     */
    @GetMapping("/cases")
    public ResponseEntity<?> getAllCases(){
        List<Case> data = null;
        Map<String,String> error= new HashMap<>();
        try {
            data = coronavirusStatsService.getAllCases();
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (UnirestException e) {
            error.put("error",e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
        }
    }
    /**
     * Gets cases by country.
     *
     * @param country the country
     * @return the cases by country
     * @throws UnirestException the unirest exception
     */
    @GetMapping("/countries")
    public ResponseEntity<?> getCasesByCountry(@RequestParam String country) throws UnirestException {
        List<Case> data = null;
        Map<String,String> error= new HashMap<>();
        data = coronavirusStatsService.getCasesByCountry(country);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
