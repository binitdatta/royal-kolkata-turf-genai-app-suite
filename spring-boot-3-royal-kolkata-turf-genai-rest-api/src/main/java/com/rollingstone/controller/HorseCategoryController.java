package com.rollingstone.controller;


import com.rollingstone.entity.HorseCategory;
import com.rollingstone.services.HorseCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/horse-categories")
public class HorseCategoryController {

    @Autowired
    private HorseCategoryService service;

    @GetMapping
    public ResponseEntity<List<HorseCategory>> getAllCategories() {
        return ResponseEntity.ok(service.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HorseCategory> getCategoryById(@PathVariable Integer id) {
        Optional<HorseCategory> category = service.getCategoryById(id);
        if (category.isPresent()) {
            return ResponseEntity.ok(category.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<HorseCategory> createCategory(@RequestBody HorseCategory category) {
        return ResponseEntity.ok(service.createCategory(category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HorseCategory> updateCategory(@PathVariable Integer id, @RequestBody HorseCategory category) {
        try {
            return ResponseEntity.ok(service.updateCategory(id, category));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
        try {
            service.deleteCategory(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

