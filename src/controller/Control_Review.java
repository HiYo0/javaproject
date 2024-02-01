package controller;

import model.Dto.ReviewDto;

import java.util.ArrayList;

public class Control_Review {//class start


    // 싱글톤
    private Control_Review(){}
    private static Control_Review control_review = new Control_Review();
    public static Control_Review getInstance(){return control_review;}

    // 맨위에 주석 달구 메소드 시작해주세요!!
    // ex ->
    // // 회원가입
    // public String 메소드명(){return "String";}

    // 전승호 start--------------------------------------
    public boolean review_write() {
        return false;
    }
//    public ArrayList<ReviewDto> my_view(){
//        return ;
//    }
    // 전승호 end------------------------------------



}//class end
