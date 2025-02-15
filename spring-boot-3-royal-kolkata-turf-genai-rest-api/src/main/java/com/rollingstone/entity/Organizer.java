package com.rollingstone.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ORGANIZERS")
public class Organizer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ORGANIZER_ID", nullable = false)
    private Integer organizerId;

    @Column(name="FULL_NAME", nullable = false)
    private String fullName;

    @Column(name="ROLE", nullable = false)
    private String role;

    @Column(name="CONTACT", nullable = false)
    private String contact;

    // Default constructor
    public Organizer() {
    }

    // Full constructor
    public Organizer(Integer organizerId, String fullName, String role, String contact) {
        this.organizerId = organizerId;
        this.fullName = fullName;
        this.role = role;
        this.contact = contact;
    }

    // Getters and setters
    public Integer getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(Integer organizerId) {
        this.organizerId = organizerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    // toString method
    @Override
    public String toString() {
        return "Organizer{" +
                "organizerId=" + organizerId +
                ", fullName='" + fullName + '\'' +
                ", role='" + role + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
