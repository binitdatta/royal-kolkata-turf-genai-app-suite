package com.rollingstone.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "PARTY_BOOKINGS")
public class PartyBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOKING_ID")
    private Integer bookingId;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID", nullable = false)
    private ClubMember member;

    @Column(name = "EVENT_DATE", nullable = false)
    private LocalDate eventDate;

    @Column(name = "VENUE", nullable = false)
    private String venue;

    @Column(name = "GUEST_COUNT", nullable = false)
    private Integer guestCount;

    @Column(name = "TOTAL_COST", nullable = false)
    private BigDecimal totalCost;

    // Default constructor
    public PartyBooking() {
    }

    // Full constructor
    public PartyBooking(Integer bookingId, ClubMember member, LocalDate eventDate, String venue, Integer guestCount, BigDecimal totalCost) {
        this.bookingId = bookingId;
        this.member = member;
        this.eventDate = eventDate;
        this.venue = venue;
        this.guestCount = guestCount;
        this.totalCost = totalCost;
    }

    // Getters and setters
    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public ClubMember getMember() {
        return member;
    }

    public void setMember(ClubMember member) {
        this.member = member;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public Integer getGuestCount() {
        return guestCount;
    }

    public void setGuestCount(Integer guestCount) {
        this.guestCount = guestCount;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    // toString method
    @Override
    public String toString() {
        return "PartyBooking{" +
                "bookingId=" + bookingId +
                ", member=" + (member != null ? member.getFullName() : "null") +
                ", eventDate=" + eventDate +
                ", venue='" + venue + '\'' +
                ", guestCount=" + guestCount +
                ", totalCost=" + totalCost +
                '}';
    }
}
