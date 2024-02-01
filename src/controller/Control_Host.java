package controller;

public class Control_Host {//class start


    // 싱글톤
    private Control_Host(){}
    private static Control_Host control_host = new Control_Host();
    public static Control_Host getInstance(){return control_host;}

    // 맨위에 주석 달구 메소드 시작해주세요!!
    // ex ->
    // // 회원가입
    // public String 메소드명(){return "String";}



}//class end
