package com.rollingstone.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "BETTING")
public class Betting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BET_ID")
    private Integer betId;

    @ManyToOne
    @JoinColumn(name = "RACE_ID", nullable = false)
    private Race race;

    @ManyToOne
    @JoinColumn(name = "HORSE_ID", nullable = false)
    private Horse horse;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID", nullable = false)
    private ClubMember member;

    @Column(name = "BET_AMOUNT", nullable = false)
    private BigDecimal betAmount;

    @Column(name = "ODDS", nullable = false)
    private BigDecimal odds;

    @Enumerated(EnumType.STRING)
    @Column(name = "BET_OUTCOME", nullable = false)
    private BetOutcome betOutcome;

    @Column(name = "PAYOUT")
    private BigDecimal payout;

    // Default constructor
    public Betting() {
    }

    // Full constructor
    public Betting(Integer betId, Race race, Horse horse, ClubMember member, BigDecimal betAmount, BigDecimal odds, BetOutcome betOutcome, BigDecimal payout) {
        this.betId = betId;
        this.race = race;
        this.horse = horse;
        this.member = member;
        this.betAmount = betAmount;
        this.odds = odds;
        this.betOutcome = betOutcome;
        this.payout = payout;
    }

    // Enum for BetOutcome
    public enum BetOutcome {
        Win, Loss
    }

    // Getters and setters
    public Integer getBetId() {
        return betId;
    }

    public void setBetId(Integer betId) {
        this.betId = betId;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Horse getHorse() {
        return horse;
    }

    public void setHorse(Horse horse) {
        this.horse = horse;
    }

    public ClubMember getMember() {
        return member;
    }

    public void setMember(ClubMember member) {
        this.member = member;
    }

    public BigDecimal getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(BigDecimal betAmount) {
        this.betAmount = betAmount;
    }

    public BigDecimal getOdds() {
        return odds;
    }

    public void setOdds(BigDecimal odds) {
        this.odds = odds;
    }

    public BetOutcome getBetOutcome() {
        return betOutcome;
    }

    public void setBetOutcome(BetOutcome betOutcome) {
        this.betOutcome = betOutcome;
    }

    public BigDecimal getPayout() {
        return payout;
    }

    public void setPayout(BigDecimal payout) {
        this.payout = payout;
    }

    // toString method
    @Override
    public String toString() {
        return "Betting{" +
                "betId=" + betId +
                ", race=" + (race != null ? race.getRaceName() : "null") +
                ", horse=" + (horse != null ? horse.getName() : "null") +
                ", member=" + (member != null ? member.getFullName() : "null") +
                ", betAmount=" + betAmount +
                ", odds=" + odds +
                ", betOutcome=" + betOutcome +
                ", payout=" + payout +
                '}';
    }
}
