package com.rollingstone.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "HORSES")
public class Horse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="HORSE_ID", nullable = false)
    private Integer horseId;

    @Column(name="NAME", nullable = false)
    private String name;

    @Column(name="BIRTH_DATE", nullable = false)
    private Date birthDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name="COLOR", nullable = false)
    private String color;
    @Column(name="HEIGHT", nullable = false)
    private BigDecimal height;
    @Column(name="WEIGHT", nullable = false)
    private BigDecimal weight;
    @Column(name="BIRTHMARKS", nullable = false)
    private String birthmarks;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private HorseCategory category;

    @ManyToOne
    @JoinColumn(name = "SIRE_ID")
    @JsonIgnore
    private Horse sire;

    @ManyToOne
    @JoinColumn(name = "DAM_ID")
    @JsonIgnore
    private Horse dam;

    private BigDecimal handicapRating;

    // Default constructor
    public Horse() {
    }

    // Full constructor
    public Horse(Integer horseId, String name, Date birthDate, Gender gender, String color, BigDecimal height, BigDecimal weight,
                 String birthmarks, HorseCategory category, Horse sire, Horse dam, BigDecimal handicapRating) {
        this.horseId = horseId;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.color = color;
        this.height = height;
        this.weight = weight;
        this.birthmarks = birthmarks;
        this.category = category;
        this.sire = sire;
        this.dam = dam;
        this.handicapRating = handicapRating;
    }

    // Getters and setters
    public Integer getHorseId() {
        return horseId;
    }

    public void setHorseId(Integer horseId) {
        this.horseId = horseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getBirthmarks() {
        return birthmarks;
    }

    public void setBirthmarks(String birthmarks) {
        this.birthmarks = birthmarks;
    }

    public HorseCategory getCategory() {
        return category;
    }

    public void setCategory(HorseCategory category) {
        this.category = category;
    }

    public Horse getSire() {
        return sire;
    }

    public void setSire(Horse sire) {
        this.sire = sire;
    }

    public Horse getDam() {
        return dam;
    }

    public void setDam(Horse dam) {
        this.dam = dam;
    }

    public BigDecimal getHandicapRating() {
        return handicapRating;
    }

    public void setHandicapRating(BigDecimal handicapRating) {
        this.handicapRating = handicapRating;
    }

    // toString method
    @Override
    public String toString() {
        return "Horse{" +
                "horseId=" + horseId +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", gender=" + gender +
                ", color='" + color + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", birthmarks='" + birthmarks + '\'' +
                ", category=" + (category != null ? category.getCategoryName() : "null") +
                ", sire=" + (sire != null ? sire.getName() : "null") +
                ", dam=" + (dam != null ? dam.getName() : "null") +
                ", handicapRating=" + handicapRating +
                '}';
    }

    // Enum for Gender
    public enum Gender {
        Male, Female
    }
}
