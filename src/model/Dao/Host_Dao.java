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
            int 회원번호 = login_number(id);
            ArrayList<HouseDto> 스왑용 = new ArrayList<>();

            for (int i = 0; i < my_house_list.size(); i++) {
                if (my_house_list.get(i).getHouse_pk()==회원번호){
                    스왑용.add(my_house_list.get(i));
                }
            }//for end
            my_house_list = 스왑용;

            return my_house_list;
        }catch (Exception e){
            System.out.println("오류"+e);
        }
        return null;// 없음
    }
    // ID 받아서 회원번호로 반납해주는 메소드
    public int login_number (String id){
        try {
            // 1. sql 작성한다
            String sql = "select member_pk from member where mid = '"+id+"';";
            // 2. sql 기재한다
            ps = conn.prepareStatement(sql);
            // 3. sql 실행한다.
            rs = ps.executeQuery();
            // 4. sql 결과처리
            return rs.getInt(1);
        }catch (Exception e) {
            System.out.println("오류"+e);
        }
        return 0;
    }

    // 전승호END ================================================================

}//class end
