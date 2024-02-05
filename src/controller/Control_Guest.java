package controller;

import model.Dao.Guest_Dao;
import model.Dto.ReservationDto;

import java.util.ArrayList;
import java.util.HashMap;

public class Control_Guest {//class start


    // 싱글톤
    private Control_Guest(){}
    private static Control_Guest control_member = new Control_Guest();
    public static Control_Guest getInstance(){return control_member;}

    // 맨위에 주석 달구 메소드 시작해주세요!!
    // ex ->
    // // 회원가입
    // public String 메소드명(){return "String";}

    public ArrayList<HashMap<String, String>> reservationList(){
        System.out.println("control 호출");
        ArrayList<HashMap<String, String>> result=Guest_Dao.getInstance().reservationList();
        return result;
    }

    //예약 취소
    public boolean deleteReservation(int reservation_pk){
        //예약상태 반환메소드 호출(예약상태가 1:완료 / 3:완료+리뷰완료 상태이면 삭제하지 않는다.
        int reservationStatus=Guest_Dao.getInstance().findReservationStatus(reservation_pk);

        if(reservationStatus==1 || reservationStatus==3){
            System.out.println("입실 완료 된 예약입니다.");
            return false;
        }
        else if(reservationStatus==0 || reservationStatus==2){//취소 진행
            boolean result=Guest_Dao.getInstance().deleteReservation(reservation_pk);

            return result;
        } //if end
        return false;
    }//m end
    //예약상태 반환메소드 호출(예약상태가 1:완료 / 3:완료+리뷰완료 상태이면 삭제하지 않는다.


}//class end
