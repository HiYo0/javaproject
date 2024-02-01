package model.Dto;

public class HouseDto {
    private int house_pk;
    private String houseName;
    private int member_pk;
    private String region;
    private int maxPeople;

    public HouseDto(){}
    public HouseDto(int house_pk, String houseName, int member_pk, String region, int maxPeople) {
        this.house_pk = house_pk;
        this.houseName = houseName;
        this.member_pk = member_pk;
        this.region = region;
        this.maxPeople = maxPeople;
    }

    public int getHouse_pk() {
        return house_pk;
    }

    public void setHouse_pk(int house_pk) {
        this.house_pk = house_pk;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public int getMember_pk() {
        return member_pk;
    }

    public void setMember_pk(int member_pk) {
        this.member_pk = member_pk;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(int maxPeople) {
        this.maxPeople = maxPeople;
    }

    @Override
    public String toString() {
        return "HouseDto{" +
                "house_pk=" + house_pk +
                ", houseName='" + houseName + '\'' +
                ", member_pk=" + member_pk +
                ", region='" + region + '\'' +
                ", maxPeople=" + maxPeople +
                '}';
    }
}
