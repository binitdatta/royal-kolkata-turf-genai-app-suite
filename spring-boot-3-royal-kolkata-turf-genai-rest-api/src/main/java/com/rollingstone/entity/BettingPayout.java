package com.rollingstone.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "BETTING_PAYOUTS")
public class BettingPayout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PAYOUT_ID")
    private Integer payoutId;

    @ManyToOne
    @JoinColumn(name = "BET_ID", nullable = false)
    private Betting bet;

    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount;

    @Column(name = "PAYMENT_DATE", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime paymentDate;

    // Default constructor
    public BettingPayout() {
    }

    // Full constructor
    public BettingPayout(Integer payoutId, Betting bet, BigDecimal amount, LocalDateTime paymentDate) {
        this.payoutId = payoutId;
        this.bet = bet;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    // Getters and setters
    public Integer getPayoutId() {
        return payoutId;
    }

    public void setPayoutId(Integer payoutId) {
        this.payoutId = payoutId;
    }

    public Betting getBet() {
        return bet;
    }

    public void setBet(Betting bet) {
        this.bet = bet;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    // toString method
    @Override
    public String toString() {
        return "BettingPayout{" +
                "payoutId=" + payoutId +
                ", bet=" + (bet != null ? bet.getBetId() : "null") +
                ", amount=" + amount +
                ", paymentDate=" + paymentDate +
                '}';
    }
}
