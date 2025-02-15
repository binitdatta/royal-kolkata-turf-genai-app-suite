package com.rollingstone.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "FOOD_AND_DRINKS")
public class FoodAndDrink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_ID")
    private Integer itemId;

    @Column(name = "ITEM_NAME", nullable = false)
    private String itemName;

    @Enumerated(EnumType.STRING)
    @Column(name = "CATEGORY", nullable = false)
    private Category category;

    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;

    // Default constructor
    public FoodAndDrink() {
    }

    // Full constructor
    public FoodAndDrink(Integer itemId, String itemName, Category category, BigDecimal price) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.category = category;
        this.price = price;
    }

    // Getters and setters
    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    // toString method
    @Override
    public String toString() {
        return "FoodAndDrink{" +
                "itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", category=" + category +
                ", price=" + price +
                '}';
    }

    // Enum for Category
    public enum Category {
        Food, Drink
    }
}
