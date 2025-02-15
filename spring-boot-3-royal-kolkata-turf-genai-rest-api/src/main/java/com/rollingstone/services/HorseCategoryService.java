package com.rollingstone.services;


import com.rollingstone.entity.HorseCategory;
import com.rollingstone.repository.HorseCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HorseCategoryService {

    @Autowired
    private HorseCategoryRepository repository;

    public List<HorseCategory> getAllCategories() {
        return repository.findAll();
    }

    public Optional<HorseCategory> getCategoryById(Integer id) {
        return repository.findById(id);
    }

    public HorseCategory createCategory(HorseCategory category) {
        return repository.save(category);
    }

    public HorseCategory updateCategory(Integer id, HorseCategory category) {
        if (repository.existsById(id)) {
            category.setCategoryId(id);
            return repository.save(category);
        }
        throw new RuntimeException("Category not found with id: " + id);
    }

    public void deleteCategory(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Category not found with id: " + id);
        }
    }
}

