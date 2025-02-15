package com.rollingstone.springaifunctions.model;

public class MembershipByTypePiechartData {
    private String membershipType;
    private long totalMembers;

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public long getTotalMembers() {
        return totalMembers;
    }

    public void setTotalMembers(long totalMembers) {
        this.totalMembers = totalMembers;
    }

    public MembershipByTypePiechartData() {
    }

    public MembershipByTypePiechartData(String membershipType, long totalMembers) {
        this.membershipType = membershipType;
        this.totalMembers = totalMembers;
    }


}
