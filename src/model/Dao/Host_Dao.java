package model.Dao;

import controller.Control_member;
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

    // 오승택 =================================================================

    public boolean insertHouse(HouseDto houseDto, Reservation_dateDto reservation_dateDto){

        try{
            int member_pk = 0;
            String sql = "";
            String id = Control_member.getInstance().getLogin_id();

            // member 테이블에서 member_pk를 먼저 받아오기
            sql = "select member_pk from member where mid = ?;";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                member_pk = rs.getInt("member_pk");
            }

            // member_pk를 먼저 받아와서 오류검사 > house 테이블에 넣기
            sql = "insert into house(houseName, member_pk, region, maxPeople) values(?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, houseDto.getHouseName());
            ps.setInt(2, member_pk);
            ps.setString(3, houseDto.getRegion());
            ps.setInt(4, houseDto.getMaxPeople());

            // reservation_date, house_pk, day_price
            // house_pk를 먼저 받아와서 테이블에 넣어야함


            if(ps.executeUpdate() == 1){
                return true;
            }
        }catch (Exception e){
            System.out.println(e +"DB오류");
        }
        return false;
    }
    // 오승택END ================================================================

}//class end
