package edu.iu.habahram.weathermonitoring.model;

import java.util.ArrayList;
import java.util.List;

public class StatisticsDisplay implements WeatherObserver {
    private WeatherData weatherData;
    private List<Float> temperatures;

    public StatisticsDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        this.temperatures = new ArrayList<>();
        this.weatherData.registerObserver(this);
    }

    public void subscribeToWeatherData(WeatherData weatherData) {
        this.weatherData = weatherData;
        this.weatherData.registerObserver(this);
    }

    public void unsubscribeFromWeatherData() {
        if (this.weatherData != null) {
            this.weatherData.removeObserver(this);
            this.weatherData = null;
        }
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        temperatures.add(temperature);
        display();
    }

    private float calculateAverage() {
        if (temperatures.isEmpty()) {
            return 0;
        }
        float sum = 0;
        for (float temperature : temperatures) {
            sum += temperature;
        }
        return sum / temperatures.size();
    }

    private float findMinTemperature() {
        if (temperatures.isEmpty()) {
            return 0;
        }
        float min = temperatures.get(0);
        for (float temperature : temperatures) {
            if (temperature < min) {
                min = temperature;
            }
        }
        return min;
    }

    private float findMaxTemperature() {
        if (temperatures.isEmpty()) {
            return 0;
        }
        float max = temperatures.get(0);
        for (float temperature : temperatures) {
            if (temperature > max) {
                max = temperature;
            }
        }
        return max;
    }

    public void display() {
        System.out.println("Average Temperature: " + calculateAverage() + "°C");
        System.out.println("Minimum Temperature: " + findMinTemperature() + "°C");
        System.out.println("Maximum Temperature: " + findMaxTemperature() + "°C");
    }
}
