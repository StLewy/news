package pl.api.news.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
@Service
public class ApiConnection {


    public String getConnection(String country, String category) {
        String API_KEY = "46228c81f72641f0a276ebe91297f747";
        String url = "https://newsapi.org/v2/top-headlines?country=" + country + "&apiKey=" + API_KEY + "&category=" + category;

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> result;
        try {
            result = restTemplate.getForEntity(url, String.class);
        } catch (HttpClientErrorException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(e.getRawStatusCode()), e.getMessage());
        }
        return result.getBody();
    }
}
