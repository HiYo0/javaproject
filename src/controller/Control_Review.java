package controller;

import model.Dao.Review_Dao;
import model.Dto.Guest_ReviewDto;
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

    // 로그인된 호스트 아이디 주고 유저가 등록한 리뷰리스트 받기
    public ArrayList<Guest_ReviewDto> my_house_Review (int ch){
        return Review_Dao.getInstance().my_house_Review(ch);
    }

    // 리뷰쓰기
    public boolean review_write(){
        return false;
    }
    // 전승호 end------------------------------------



}//class end
