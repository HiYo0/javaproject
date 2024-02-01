package controller;

import model.Dao.Member_Dao;
import model.Dto.MemberDto;

public class Control_member {//class start


    // 싱글톤
    private Control_member(){}
    private static Control_member control_member = new Control_member();
    public static Control_member getInstance(){return control_member;}

    // 맨위에 주석 달구 메소드 시작해주세요!!
    // ex ->
    // // 회원가입
    // public String 메소드명(){return "String";}

    // 회원가입
    public boolean join(MemberDto memberDto){

        // 저장 후 dao에 전달
        boolean result = Member_Dao.getInstance().join(memberDto);

        // 반환
        return true;
    }

    // 로그인
    public boolean login(MemberDto memberDto){

        // // 저장 후 dao에 전달
        boolean result = Member_Dao.getInstance().login(memberDto);

        // 반환
        return true;
    }




}//class end
