package com.rollingstone.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "HORSE_CATEGORIES")
public class HorseCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CATEGORY_ID", nullable = false)
    private Integer categoryId;

    @Column(name="CATEGORY_NAME", nullable = false)
    private String categoryName;

    // Getters and Setters
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public HorseCategory(Integer categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public HorseCategory() {
    }

    @Override
    public String toString() {
        return "HorseCategory{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
