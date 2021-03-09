package com.jwtaut.demo.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class shopController {

    @Autowired
    private ShopRepository shopRepository;

    @PostMapping("/Shop")
    public ResponseEntity<Food> createFood(@RequestBody Food food) {
        try {
            Food food1 = shopRepository.save(new Food(food.getFoodName(), food.getFoodPrice(), false));
            return new ResponseEntity<>(food1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/foods")

    public ResponseEntity<List<Food>> getAllFoods(@RequestParam(required = false) String foodName) {
        try {
            List<Food> foods = new ArrayList<Food>();

            if (foodName == null) {
                shopRepository.findAll().forEach(foods::add);
            } else {
                shopRepository.findByFoodName(foodName).forEach(foods::add);
            }
            if (foods.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(foods, HttpStatus.OK);
        } catch (Exception err) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/foods/{id}")
    public ResponseEntity<Food> updateShop(@PathVariable("id") String id, @RequestBody Food food) {
        Optional<Food> food1 = shopRepository.findById(id);

        if (food1.isPresent()) {
            Food food2 = food1.get();
            food2.setFoodName(food.getFoodName());
            food2.setFoodPrice(food.getFoodPrice());
            return new ResponseEntity<>(shopRepository.save(food2), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteFood")

    public ResponseEntity<HttpStatus> deleteFood() {
        try {
            shopRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception err) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
