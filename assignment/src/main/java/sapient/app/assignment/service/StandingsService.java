package sapient.app.assignment.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sapient.app.assignment.constants.AppConstants;
import sapient.app.assignment.dto.StandingsDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StandingsService {

    @Autowired
    private RestTemplate restTemplate;

    public List<StandingsDto> getStandings(String league_id, String country_name, String league_name, String team_name) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        String url = AppConstants.URL + AppConstants.LEAGUE_ID + league_id + AppConstants.APIKEY;
        ArrayNode responseBody = restTemplate.exchange(url, HttpMethod.GET, entity, ArrayNode.class).getBody();

        List<StandingsDto> standingsDtoList = new ArrayList<>();
        for (JsonNode node: responseBody) {
            StandingsDto standingsDto = new StandingsDto();
            standingsDto.setCountryName(node.get("country_name").toString());
            standingsDto.setLeagueId(node.get("league_id").toString());
            standingsDto.setLeagueName(node.get("league_name").toString());
            standingsDto.setTeamId(node.get("team_id").toString());
            standingsDto.setTeamName(node.get("team_name").toString());
            standingsDto.setOverallLeaguePosition(node.get("overall_league_position").toString());
            standingsDtoList.add(standingsDto);
        }
        return standingsDtoList;
    }
}
