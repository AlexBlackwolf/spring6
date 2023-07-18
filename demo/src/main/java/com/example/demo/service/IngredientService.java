package com.example.demo.service;

import com.example.demo.dao.IngredientDAO;
import com.example.demo.entity.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {
    private final IngredientDAO ingredientDAO;

    @Autowired
    public IngredientService(IngredientDAO ingredientDAO) {
        this.ingredientDAO = ingredientDAO;
    }

    public void insertMealTest() {
        ingredientDAO.save(new Ingredient("Flour", true, true, true, true));
    }
}