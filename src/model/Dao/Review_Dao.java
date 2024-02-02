package model.Dao;

import model.Dto.Guest_ReviewDto;
import model.Dto.HouseDto;
import model.Dto.Reservation_dateDto;

import java.util.ArrayList;

public class Review_Dao extends Dao{//class start
    // 싱글톤 ==============================================
    private Review_Dao(){}
    private static Review_Dao review_dao = new Review_Dao();
    public static Review_Dao getInstance() {return review_dao;}
    // ======================================================

    // 전승호 start--------------------------------------
    public ArrayList<Guest_ReviewDto> my_house_Review(int ch){
        try {
            // 1. sql 작성한다
            String sql = "";
            // 2. sql 기재한다
            ps = conn.prepareStatement(sql);
            // 3. sql 실행한다.
            rs = ps.executeQuery();
            // 4. sql 결과처리
            ArrayList<Guest_ReviewDto> house_review_list = new ArrayList<>();// house_review_list 데이터의집

            while (rs.next()){//DB house 데이터 다 가져오기
                Guest_ReviewDto guest_reviewDto = new Guest_ReviewDto();
//                member_reviewDto.setHouse_pk(rs.getInt(7));
//                member_reviewDto.setHouseName(rs.getString(8));
//                member_reviewDto.setMember_pk(rs.getInt(1));
//                member_reviewDto.setRegion(rs.getString(10));
//                member_reviewDto.setMaxPeople(rs.getInt(11));

                house_review_list.add(guest_reviewDto);
            }

            return house_review_list;
        }catch (Exception e){
            System.out.println("my_house_list 오류"+e);
        }
        return null;
    }


    // 전승호 end------------------------------------


}//class end
