package com.jwtaut.demo.models;

import com.jwtaut.demo.models.Food;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface ShopRepository extends MongoRepository<Food, String> {
    List<Food> findByFoodName(String foodName);
}
