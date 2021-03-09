package com.jwtaut.demo;


import com.jwtaut.demo.models.Food;
import com.jwtaut.demo.models.ShopRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataMongoTest
public class test {

    @Autowired
    private ShopRepository shopRepository;

    @Test
    @Rollback(value = false)

    public void testCreateTests() {
        Food savedFood = (Food) this.shopRepository.save(new Food("Ice cream", 2.2, true));
        Assertions.assertThat(savedFood.getId());
    }

    @Test
    public void testFindAll() {
        List<Food> food = this.shopRepository.findAll();
        Assertions.assertThat(food).size().isGreaterThan(0);
    }
}
