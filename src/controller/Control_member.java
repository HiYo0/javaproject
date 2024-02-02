package controller;

import model.Dao.Member_Dao;
import model.Dto.MemberDto;

public class Control_member {//class start
    private String login_id = "";

    // 싱글톤
    private Control_member(){}
    private static Control_member control_member = new Control_member();
    public static Control_member getInstance(){return control_member;}
    // 맨위에 주석 달구 메소드 시작해주세요!!
    // ex ->
    // // 회원가입
    // public String 메소드명(){return "String";}

    // ================================ 회원가입 ================================ //

    public int join(MemberDto memberDto){
        // 1. 반환 변수.
        int result = 0;
        // 저장 후 dao에 전달

        // 반환
        if( Member_Dao.getInstance().idCheck( memberDto.getMid() ) ) {
            return 1;
        }
        // 3. 회원가입 요청
        result = Member_Dao.getInstance().join( memberDto ); // 샘플
        // 2. 반환
        return result;
    }

    //  로그인 상태 필드
    int loginMno = 0;

    // ================================ 로그인 ================================ //
    public boolean login(MemberDto memberDto){

        // // 저장 후 dao에 전달
        boolean result = Member_Dao.getInstance().login(memberDto);
        if( result ){
            // 3. login 성공한 회원번호 dao 요청.
            loginMno = Member_Dao.getInstance().findMno(  memberDto.getMid()  );
        }
        // 반환
        return result;
    }

    public String getLogin_id() {
        return login_id;
    }

    public void setLogin_id(String login_id) {
        this.login_id = login_id;
    }
}//class end
