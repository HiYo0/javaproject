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

    // 로그인된 호스트 아이디 주고 유저가 등록한 숙소리스트 받기
    public ArrayList<HouseDto> my_house_list (String id){
        return Host_Dao.getInstance().my_house_list(id);
    }



    // 오승택 =======================================================
    public boolean insertHouse(HouseDto houseDto, Reservation_dateDto reservation_dateDto, int day){
        boolean result  = false;

        // Host_Dao 에서 반환받은 결과값
        result = Host_Dao.getInstance().insertHouse(houseDto);

        // house_pk를 생성해야 하기때문에
        // house 테이블 생성완료 후 reservation_table 생성
        if(result){
            result = Host_Dao.getInstance().insertReservation_date(houseDto, reservation_dateDto, day);
        }
        return result;
    }
    // 오승택 END ====================================================


}//class end
