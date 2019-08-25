package sapient.app.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sapient.app.assignment.dto.StandingsDto;
import sapient.app.assignment.service.StandingsService;

import java.util.List;

@RestController
public class StandingsController {

    @Autowired
    private StandingsService standingsService;

    @GetMapping(name = "/standing")
    public ResponseEntity<List<StandingsDto>> getStandings(@RequestParam("league_id") String league_id,
                                               @RequestParam("country_name") String country_name,
                                               @RequestParam("league_name") String league_name,
                                               @RequestParam("team_name") String team_name) {
        List<StandingsDto> responseObj = standingsService.getStandings(league_id, country_name, league_name, team_name);
        return new ResponseEntity(responseObj, HttpStatus.OK);
    }
}
