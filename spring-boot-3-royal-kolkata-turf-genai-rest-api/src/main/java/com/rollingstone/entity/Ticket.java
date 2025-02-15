package com.rollingstone.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "TICKETS")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TICKET_ID")
    private Integer ticketId;

    @ManyToOne
    @JoinColumn(name = "RACE_ID", nullable = false)
    private Race race;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID", nullable = false)
    private ClubMember member;

    @Enumerated(EnumType.STRING)
    @Column(name = "TICKET_TYPE", nullable = false)
    private TicketType ticketType;

    @Column(name = "PRICE", precision = 10, scale = 2, nullable = false)
    private BigDecimal price;

    // Default constructor
    public Ticket() {
    }

    // Full constructor
    public Ticket(Integer ticketId, Race race, ClubMember member, TicketType ticketType, BigDecimal price) {
        this.ticketId = ticketId;
        this.race = race;
        this.member = member;
        this.ticketType = ticketType;
        this.price = price;
    }

    // Enum for TicketType
    public enum TicketType {
        General, VIP
    }

    // Getters and setters
    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public ClubMember getMember() {
        return member;
    }

    public void setMember(ClubMember member) {
        this.member = member;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
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
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", race=" + (race != null ? race.getRaceName() : "null") +
                ", member=" + (member != null ? member.getFullName() : "null") +
                ", ticketType=" + ticketType +
                ", price=" + price +
                '}';
    }
}
