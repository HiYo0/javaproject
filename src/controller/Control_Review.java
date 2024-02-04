package controller;

import model.Dao.Review_Dao;
import model.Dto.Guest_ReviewDto;
import model.Dto.ReviewWrite_View_Dto;

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


    // 하우스 식별번호로 하우스이름 받아오기
    public String house_name (int ch){
        return Review_Dao.getInstance().house_name(ch);
    }
    // 작성자 식별번호로 작성자이름 받아오기
    public String writer_name(int writer){
        return  Review_Dao.getInstance().writer_name(writer);
    }

    // 리뷰 상태가 3인 리뷰 불러오기
    // 1. reservation_date 에서 내 house가 포함된 거 찾기
    // 2. house 중 reservation_status 가 1인거 찾아오기
    // 3. reservation_status 가 1인거중 member / 내숙소이름 /이용날짜 가져오기
    public ArrayList<ReviewWrite_View_Dto> review_write_view(){

        return Review_Dao.getInstance().review_write_view();

    }

    // 리뷰쓰기
    public boolean review_write(){
        return false;
    }


    // 전승호 end------------------------------------



}//class end
