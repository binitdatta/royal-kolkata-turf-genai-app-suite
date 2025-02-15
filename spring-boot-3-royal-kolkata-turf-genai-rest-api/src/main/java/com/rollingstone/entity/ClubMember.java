package com.rollingstone.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "CLUB_MEMBERS")
public class ClubMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Integer memberId;

    @Column(name = "FULL_NAME", nullable = false)
    private String fullName;

    @Column(name = "DOB")
    private LocalDate dob;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "MEMBERSHIP_START_DATE", nullable = false)
    private LocalDate membershipStartDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "MEMBERSHIP_TYPE")
    private MembershipType membershipType;

    @Column(name = "SUBSCRIPTION_FEE", precision = 10, scale = 2)
    private BigDecimal subscriptionFee;

    // Default constructor
    public ClubMember() {
    }

    // Full constructor
    public ClubMember(Integer memberId, String fullName, LocalDate dob, String address, String email, String phone,
                      LocalDate membershipStartDate, MembershipType membershipType, BigDecimal subscriptionFee) {
        this.memberId = memberId;
        this.fullName = fullName;
        this.dob = dob;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.membershipStartDate = membershipStartDate;
        this.membershipType = membershipType;
        this.subscriptionFee = subscriptionFee;
    }

    // Getters and setters
    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getMembershipStartDate() {
        return membershipStartDate;
    }

    public void setMembershipStartDate(LocalDate membershipStartDate) {
        this.membershipStartDate = membershipStartDate;
    }

    public MembershipType getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(MembershipType membershipType) {
        this.membershipType = membershipType;
    }

    public BigDecimal getSubscriptionFee() {
        return subscriptionFee;
    }

    public void setSubscriptionFee(BigDecimal subscriptionFee) {
        this.subscriptionFee = subscriptionFee;
    }

    // toString method
    @Override
    public String toString() {
        return "ClubMember{" +
                "memberId=" + memberId +
                ", fullName='" + fullName + '\'' +
                ", dob=" + dob +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", membershipStartDate=" + membershipStartDate +
                ", membershipType=" + membershipType +
                ", subscriptionFee=" + subscriptionFee +
                '}';
    }

    // Enum for MembershipType
    public enum MembershipType {
        Regular, VIP
    }
}
