package model.Dao;

import model.Dto.HouseDto;
import model.Dto.Reservation_dateDto;

import java.util.ArrayList;

public class Host_Dao extends Dao{// class start

    // 싱글톤 ==============================================
    private Host_Dao(){}
    private static Host_Dao host_dao = new Host_Dao();
    public static Host_Dao getInstance() {return host_dao;}
    // ======================================================


    // 전승호 =================================================================
    public ArrayList<HouseDto> my_house_list (String id) {
        try {
            // 1. sql 작성한다
            String sql = "select * from House;";
            // 2. sql 기재한다
            ps = conn.prepareStatement(sql);
            // 3. sql 실행한다.
            rs = ps.executeQuery();
            // 4. sql 결과처리
            ArrayList<HouseDto> my_house_list = new ArrayList<>();// house 데이터의집

            while (rs.next()){//DB house 데이터 다 가져오기
                HouseDto houseDto = new HouseDto();
                houseDto.setHouse_pk(rs.getInt(1));
                houseDto.setHouseName(rs.getString(2));
                houseDto.setMember_pk(rs.getInt(3));
                houseDto.setRegion(rs.getString(4));
                houseDto.setMaxPeople(rs.getInt(5));

                my_house_list.add(houseDto);
            }
            for (int i = 0; i < my_house_list.size(); i++) {

            }

            return my_house_list;
        }catch (Exception e){
            System.out.println("오류"+e);
        }
        return null;// 없음
    }
    public int login_number (String id){ // 이것은 아이디를 받아서 아이디에 해당하는 회원번호를 찾는 메소드
        try {
            // 1. sql 작성한다
            String sql = "select * from member where mid = '아이디넣는곳';";
            // 2. sql 기재한다
            ps = conn.prepareStatement(sql);
            // 3. sql 실행한다.
            rs = ps.executeQuery();
            // 4. sql 결과처리
            return 3;
        }catch (Exception e) {
            System.out.println("오류"+e);
        }
        return 0;
    }

    // 전승호END ================================================================

}//class end
