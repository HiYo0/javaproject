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

    //예약내역 출력 메소드
    public ArrayList<HashMap<String, String>> reservationList(){
        System.out.println("control 호출");
        ArrayList<HashMap<String, String>> result=Guest_Dao.getInstance().reservationList();
        return result;
    }

    //예약 취소 메소드
    public int deleteReservation(int reservation_pk){
        //예약상태 반환메소드 호출(예약상태가 1:완료 / 3:완료+리뷰완료 상태이면 삭제하지 않는다.
        int reservationStatus=Guest_Dao.getInstance().findReservationStatus(reservation_pk);

        if(reservationStatus==1 || reservationStatus==3){
            return 2;
        }
        else if(reservationStatus==0){//취소 진행
            int result=Guest_Dao.getInstance().deleteReservation(reservation_pk);

            return result;
        } //if end
        else if(reservationStatus==2){//이미 취소됨
            return 3;
        }

        return 0;
    }//m end

    //존재하는 예약번호인지 유효성검사 메소드
    public boolean checkReservationPk(int reservation_pk){
        boolean result=Guest_Dao.getInstance().checkReservationPk(reservation_pk);
        return result;
    }//m end

    //리뷰 가능 내역 출력 메소드 (조건 : 예약일자지남 && 예약상태1(승인완료))
    public ArrayList<HashMap<String, String>> finishReservationList() {
        //dao결과 호출
        ArrayList<HashMap<String, String>> result=Guest_Dao.getInstance().finishReservationList();

        //GuestReviewView로 반환
        return result;
    }//m end

    //리뷰등록 메소드
    public boolean inputReview(){

        return false;
    }//m end


}//class end
