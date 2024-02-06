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
    // id에 해당하는 houst 리스트 가져오기
    public ArrayList<HouseDto> my_house_list (String id) {
        ArrayList<HouseDto> my_house_list = new ArrayList<>();// house 데이터의집
        try {
            int id_no = login_number(id);
            // 1. sql 작성한다
            String sql = "select * from member inner join house on member.member_pk = house.member_pk && member.member_pk ="+id_no+";";
            // 2. sql 기재한다
            ps = conn.prepareStatement(sql);
            // 3. sql 실행한다.
            rs = ps.executeQuery();
            // 4. sql 결과처리


            while (rs.next()){//DB house 데이터 다 가져오기
                HouseDto houseDto = new HouseDto();
                houseDto.setHouse_pk(rs.getInt(7));
                houseDto.setHouseName(rs.getString(8));
                houseDto.setMember_pk(rs.getInt(1));
                houseDto.setRegion(rs.getString(10));
                houseDto.setMaxPeople(rs.getInt(11));

                my_house_list.add(houseDto);
            }

            return my_house_list;
        }catch (Exception e){
            System.out.println("my_house_list 오류"+e);
        }
        return my_house_list;// 없음
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
            if(rs.next())
                return rs.getInt("member_pk");
        }catch (Exception e) {
            System.out.println("login_number 오류"+e);
        }
        return 0;
    }

    // 전승호END ================================================================

    // 오승택 =================================================================

    public boolean insertHouse(HouseDto houseDto){

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

            member_pk = 1;

            // member_pk를 먼저 받아와서 오류검사 > house 테이블에 넣기
            sql = "insert into house(houseName, member_pk, region, maxPeople) values(?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, houseDto.getHouseName());
            ps.setInt(2, member_pk);
            ps.setString(3, houseDto.getRegion());
            ps.setInt(4, houseDto.getMaxPeople());

            if(ps.executeUpdate() == 1){
                return true;
            }

        }catch (Exception e){
            System.out.println(e +"DB오류");
        }
        return false;
    }

    public boolean insertReservation_date(HouseDto houseDto, Reservation_dateDto reservation_dateDto, int day){
        try{
            int house_pk = 0;
            String sql = "";
            int result = 0;

            // house_pk를 먼저 받아오기
            sql = "select house_pk from house where houseName = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, houseDto.getHouseName());
            rs = ps.executeQuery();
            if(rs.next()){
                house_pk = rs.getInt("house_pk");
            }

            // 날짜 테이블에 데이터 넣기
            for(int i=1; i<=day; i++) {
                sql = "insert into reservation_date(reservation_date, house_pk, day_price) values(?, ?, ?)";
                ps = conn.prepareStatement(sql);
                ps.setString(1, reservation_dateDto.getReservation_date());
                ps.setInt(2, house_pk);
                ps.setInt(3, reservation_dateDto.getDay_price());
                if(ps.executeUpdate() == 1){
                    result++;
                }
            }
            if(day >= 2) { // 2박 이상일때 날짜 변경 함수를 탄다
                changeReservation_date(house_pk, reservation_dateDto);
            }
           if (result == day) {
                return true;
            }
        }catch(Exception e){
            System.out.println(e +"DB오류");
        }
        return false;
    }

    public void changeReservation_date(int house_pk, Reservation_dateDto reservation_dateDto){

        String sql = "";
        int[] reservation_date_pk = new int[14]; // 최대 2주 예약한다고 가정
        int index = 0;

        try{
            // reservation_date_pk를 먼저 가져오고
            sql = "select reservation_date_pk from reservation_date where reservation_date = ? and house_pk = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, reservation_dateDto.getReservation_date());
            ps.setInt(2, house_pk);
            rs = ps.executeQuery();
            while(rs.next()){
                reservation_date_pk[index] = rs.getInt("reservation_date_pk");
                index++;
            }

            // reservation_date 테이블의 reservation_date 칼럼을 바꿔준다
            for(int i=1; i<index; i++) {
                sql = "update reservation_date set reservation_date = date_add(reservation_date, interval ? day) where reservation_date_pk = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, i);
                ps.setInt(2, reservation_date_pk[i]);
                ps.executeUpdate();
            }
        }catch (Exception e){
            System.out.println(e + "오류");
        }
    }
    // 오승택END ================================================================


}//class end
