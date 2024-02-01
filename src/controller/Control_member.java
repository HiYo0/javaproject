package controller;

public class Control_member {//class start


    // 싱글톤
    private Control_member(){}
    private static Control_member control_member = new Control_member();
    public static Control_member getInstance(){return control_member;}

    // 맨위에 주석 달구 메소드 시작해주세요!!
    // ex ->
    // // 회원가입
    // public String 메소드명(){return "String";}



}//class end
