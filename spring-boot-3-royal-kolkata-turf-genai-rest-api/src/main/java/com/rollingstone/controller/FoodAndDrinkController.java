package com.rollingstone.controller;

import com.rollingstone.entity.FoodAndDrink;
import com.rollingstone.services.FoodAndDrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/food-and-drinks")
public class FoodAndDrinkController {

    @Autowired
    private FoodAndDrinkService service;

    @GetMapping
    public ResponseEntity<List<FoodAndDrink>> getAllItems() {
        return ResponseEntity.ok(service.getAllItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodAndDrink> getItemById(@PathVariable Integer id) {
        Optional<FoodAndDrink> item = service.getItemById(id);
        if (item.isPresent()) {
            return ResponseEntity.ok(item.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<FoodAndDrink> createItem(@RequestBody FoodAndDrink item) {
        return ResponseEntity.ok(service.createItem(item));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FoodAndDrink> updateItem(@PathVariable Integer id, @RequestBody FoodAndDrink item) {
        try {
            return ResponseEntity.ok(service.updateItem(id, item));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Integer id) {
        try {
            service.deleteItem(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
