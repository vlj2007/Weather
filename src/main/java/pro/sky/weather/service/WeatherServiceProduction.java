package pro.sky.weather.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pro.sky.weather.api.WeatherService;
import pro.sky.weather.model.Weather;

@Service
@Profile("production")
public class WeatherServiceProduction implements WeatherService {

//    private static String WEATHER_SERVICE_URL = "https://api.openweathermap.org/data/3.0/weather?q={city}&units=metric&appid=8fa2b5af8278b530be6502d9cb2716e8";
    Logger logger = LoggerFactory.getLogger(WeatherServiceProduction.class);


    @Value("${weather.url}")
    private String  weatherUrl;

    @Value("${weather.api-key}")
    private String  weatherApiKey;


    @Autowired
    private RestTemplate restTemplate;

    public Weather getWeather(String city){
        logger.debug("Requesting weather for city: {}", city);
        Weather weather = restTemplate.exchange(
                weatherUrl, HttpMethod.GET,
                new HttpEntity<>(HttpHeaders.EMPTY),
                Weather.class,
                city,
                weatherApiKey
        ).getBody();
        logger.debug("The weather for {} is {}", city, weather);
        return weather;
    }



}
