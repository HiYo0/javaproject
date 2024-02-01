package model.Dto;

public class MemberDto {

    private int member_pk;
    private String mid;
    private String mpw;
    private String memail;
    private String mphone;
    private String mname;

    public MemberDto(){} // 생성자
    public MemberDto(int member_pk, String mid, String mpw, String memail, String mphone, String mname){
        // 생성자
        this.member_pk = member_pk;
        this.mid = mid;
        this.mpw = mpw;
        this.memail = memail;
        this.mphone = mphone;
        this.mname = mname;
    }

    public int getMember_pk() {
        return member_pk;
    }

    public void setMember_pk(int member_pk) {
        this.member_pk = member_pk;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getMpw() {
        return mpw;
    }

    public void setMpw(String mpw) {
        this.mpw = mpw;
    }

    public String getMemail() {
        return memail;
    }

    public void setMemail(String memail) {
        this.memail = memail;
    }

    public String getMphone() {
        return mphone;
    }

    public void setMphone(String mphone) {
        this.mphone = mphone;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    @Override
    public String toString() {
        return "MemberDto{" +
                "member_pk=" + member_pk +
                ", mid='" + mid + '\'' +
                ", mpw='" + mpw + '\'' +
                ", memail='" + memail + '\'' +
                ", mphone='" + mphone + '\'' +
                ", mname='" + mname + '\'' +
                '}';
    }
}
