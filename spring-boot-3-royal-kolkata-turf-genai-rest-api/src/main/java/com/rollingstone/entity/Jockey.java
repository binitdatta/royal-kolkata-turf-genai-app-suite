package com.rollingstone.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "JOCKEYS")
public class Jockey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="JOCKEY_ID", nullable = false)
    private Integer jockeyId;

    @Column(name="FULL_NAME", nullable = false)
    private String fullName;

    @Column(name="AGE", nullable = false)
    private Integer age;
    @Column(name="EXPERIENCE_YEARS", nullable = false)
    private Integer experienceYears;
    @Column(name="WEIGHT", nullable = false)
    private Double weight;
    @Column(name="HEIGHT", nullable = false)
    private Double height;

    // Default constructor
    public Jockey() {
    }

    // Full constructor
    public Jockey(Integer jockeyId, String fullName, Integer age, Integer experienceYears, Double weight, Double height) {
        this.jockeyId = jockeyId;
        this.fullName = fullName;
        this.age = age;
        this.experienceYears = experienceYears;
        this.weight = weight;
        this.height = height;
    }

    // Getters and setters
    public Integer getJockeyId() {
        return jockeyId;
    }

    public void setJockeyId(Integer jockeyId) {
        this.jockeyId = jockeyId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(Integer experienceYears) {
        this.experienceYears = experienceYears;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    // toString method
    @Override
    public String toString() {
        return "Jockey{" +
                "jockeyId=" + jockeyId +
                ", fullName='" + fullName + '\'' +
                ", age=" + age +
                ", experienceYears=" + experienceYears +
                ", weight=" + weight +
                ", height=" + height +
                '}';
    }
}
