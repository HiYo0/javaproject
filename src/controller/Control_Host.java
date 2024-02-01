package controller;

import model.Dao.Host_Dao;
import model.Dto.HouseDto;
import model.Dto.Reservation_dateDto;

import java.util.ArrayList;

public class Control_Host {//class start


    // 싱글톤
    private Control_Host(){}
    private static Control_Host control_host = new Control_Host();
    public static Control_Host getInstance(){return control_host;}

    // 맨위에 주석 달구 메소드 시작해주세요!!
    // ex ->
    // // 회원가입
    // public String 메소드명(){return "String";}


    // 전승호 =======================================================
    public ArrayList<HouseDto> my_house_list (String id){
        // 로그인된 호스트 아이디 주고 유저가 등록한 숙소리스트 받기

        return Host_Dao.getInstance().my_house_list(id);
    }
    // 전승호 END ====================================================


}//class end
