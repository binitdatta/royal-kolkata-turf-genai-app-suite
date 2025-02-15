package com.rollingstone.services;

import com.rollingstone.entity.FoodAndDrink;
import com.rollingstone.repository.FoodAndDrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodAndDrinkService {

    @Autowired
    private FoodAndDrinkRepository repository;

    public List<FoodAndDrink> getAllItems() {
        return repository.findAll();
    }

    public Optional<FoodAndDrink> getItemById(Integer id) {
        return repository.findById(id);
    }

    public FoodAndDrink createItem(FoodAndDrink item) {
        return repository.save(item);
    }

    public FoodAndDrink updateItem(Integer id, FoodAndDrink item) {
        if (repository.existsById(id)) {
            item.setItemId(id);
            return repository.save(item);
        }
        throw new RuntimeException("Item not found with id: " + id);
    }

    public void deleteItem(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Item not found with id: " + id);
        }
    }
}
