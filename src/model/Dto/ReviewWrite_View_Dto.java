package model.Dto;

public class ReviewWrite_View_Dto {//class start
    // 전승호 -----------------------------
    private String houseName;       // 숙소이름
    private String memberName;      // 사용인 이름
    private String date;            // 사용 일자
    private String region;          // 지역
    private int price;              // 가격


    public ReviewWrite_View_Dto(){}
    public ReviewWrite_View_Dto(String houseName, String memberName, String date, String region, int price) {
        this.houseName = houseName;
        this.memberName = memberName;
        this.date = date;
        this.region = region;
        this.price = price;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    // 전승호 end -------------------------------------

}//class end
