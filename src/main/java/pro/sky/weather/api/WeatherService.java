package pro.sky.weather.api;

import pro.sky.weather.model.Weather;

public interface WeatherService {

    Weather getWeather(String city);

}
