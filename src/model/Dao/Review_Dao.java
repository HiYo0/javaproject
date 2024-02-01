package model.Dao;

import model.Dto.Reservation_dateDto;

import java.util.ArrayList;

public class Review_Dao extends Dao{//class start
    // 싱글톤 ==============================================
    private Review_Dao(){}
    private static Review_Dao review_dao = new Review_Dao();
    public static Review_Dao getInstance() {return review_dao;}
    // ======================================================

    // 전승호 start--------------------------------------
    public boolean review_write() {//리뷰작성
        return false;
    }


    // 전승호 end------------------------------------


}//class end
