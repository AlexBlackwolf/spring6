package com.example.demo.controller;

import com.example.demo.entity.Ingredient;
import com.example.demo.entity.Meal;
import com.example.demo.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class MealController {

    private final MealService mealService;

    @Autowired
    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping("/insert-meal-test")
    public ResponseEntity<Void> insertMealTest() {
        mealService.insertMealTest();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/meal-many-to-one")
    public ResponseEntity<Void> mealManyToOne() {
        Meal meal = new Meal("focaccia", "verygood", 5.3, true, false);
        Ingredient ingredient = new Ingredient("Flour", true, true, true, true);

        ingredient.setMeal(meal);
        meal.setIngredients(Arrays.asList(ingredient));
        mealService.addMeal(meal);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/summer/meals")
    public ResponseEntity<List<Meal>> getSummerMeals() {
        return ResponseEntity.ok(mealService.getSummerMeals());
    }
    @GetMapping("/winter-meals")
    public ResponseEntity<List<Meal>> getWinterMeals(){
        return ResponseEntity.ok(mealService.getWinterMeal());
    }
}