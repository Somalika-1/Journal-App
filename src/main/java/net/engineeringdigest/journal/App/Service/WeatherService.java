package net.engineeringdigest.journal.App.Service;

import net.engineeringdigest.journal.App.api.response.WeatherResponse;
import net.engineeringdigest.journal.App.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    public WeatherResponse getWeather(String city){
        String finalAPI=appCache.APP_CACHE.get("weather_api").replace("<city>",city).replace("<api_key>",apiKey);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET,null, WeatherResponse.class);

        WeatherResponse body=response.getBody();
        return body;
    }
}
