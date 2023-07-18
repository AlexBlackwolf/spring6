package com.example.demo.dao;

import com.example.demo.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientDAO extends JpaRepository <Ingredient, Long> {
}
