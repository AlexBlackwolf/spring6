package com.example.demo.service;

import com.example.demo.dao.IngredientDAO;
import com.example.demo.dao.MealDAO;
import com.example.demo.entity.Ingredient;
import com.example.demo.entity.Meal;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MealService {
    private final MealDAO mealDAO;
    private final IngredientDAO ingredientDAO;
    private Double MIN_SUMMER_TEMP = 20.0;
    public Double MAX_WINTER_TEMP = 15.0;

    @Autowired
    public MealService(MealDAO mealDAO, IngredientDAO ingredientDAO) {
        this.mealDAO = mealDAO;
        this.ingredientDAO = ingredientDAO;
    }

    public void insertMealTest() {
        mealDAO.save(new Meal("Pizza", "Good", 4.8, true, false));
    }

    public void addMeal(Meal meal) {
        mealDAO.save(meal);
        for (Ingredient ingredient : meal.getIngredients()) {
            ingredient.setMeal(meal);
            ingredientDAO.save(ingredient);
        }
    }

    public List<Meal> getSummerMeals() {
        Double currentTemperatureInCentigradeSummer = getCurrentTemperatureInCentigradeSummer();

        if (currentTemperatureInCentigradeSummer < MIN_SUMMER_TEMP) return new ArrayList<>();

        return mealDAO.findByIsSummerMeal(true);
    }


    public List<Meal> getWinterMeal() {
        Double currentTemperatureInCentigradeWinter = getCurrentTemperatureInCentigradeWinter();

        if (currentTemperatureInCentigradeWinter > MAX_WINTER_TEMP) return new ArrayList<>();

        return mealDAO.findByIsWinterMeal(true);

    }

        private Double getCurrentTemperatureInCentigradeSummer(){
        try {
            JSONObject response = Unirest.get("https://api.open-meteo.com/v1/forecast?latitude=37.4922&longitude=15.0704&current_weather=true").asJson().getBody().getObject();
            return response.getJSONObject("current_weather").getDouble("temperature");
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
    }
        private Double getCurrentTemperatureInCentigradeWinter(){
        try {
            JSONObject response = Unirest.get("https://api.open-meteo.com/v1/forecast?latitude=75.0808&longitude=18.1276&current_weather=true")
                    .asJson().getBody().getObject();
            return response.getJSONObject("current_weather").getDouble("temperature");
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
    }
}