package model.Dao;

import controller.Control_member;
import model.Dto.HouseDto;
import model.Dto.ReservationDto;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Guest_Dao extends Dao{
    // 싱글톤 ==============================================
    private Guest_Dao(){}
    private static Guest_Dao guset_dao = new Guest_Dao();
    public static Guest_Dao getInstance() {return guset_dao;}
    // ======================================================
    //로그인된 아이디의 회원번호 저장
    public int findMemberPk(){
        //현재 로그인한 아이디값 받아오기
        String currentId= Control_member.getInstance().getLogin_id();
        try {
            //아이디로 회원번호 찾는 sql
            String sql = "select member_pk from member where mid=?";
            //sql 기재
            ps = conn.prepareStatement(sql);
            //매개변수 대입(현재 로그인된 아이디)
            ps.setString(1,currentId);
            //sql 실행
            rs=ps.executeQuery();
            //찾은 회원번호 저장
            rs.next();
            int memberNo=rs.getInt("member_pk");
            return memberNo;
        }//try end
        catch (Exception e){
            System.out.println("오류 : "+e);
            return 0;
        }//catch end
    }//m end

    //예약현황 불러오기_예약번호, 예약인원, 예약상태
    public ArrayList<HashMap<String, String>> reservationList(){
        System.out.println("dao호출");
        //회원번호 찾기 함수 호출
        int memberNo=findMemberPk();

        //불러온 데이터 저장할 배열
        ArrayList<HashMap<String, String>> reservationDtos=new ArrayList<>();

        try{
            //예약내역 추출
            String sql="select reservation_pk, reservation_date, houseName, reservation_people, reservation_status from (\n" +
                    "\t  select * from (select house_pk, houseName from house) as h join (\n" +
                    "\t\tselect*from reservation_date join \n" +
                    "\t\t\t( select * from reservation join reservation_detail using(reservation_pk) ) as a\n" +
                    "\t\tusing(reservation_date_pk) ) as b\n" +
                    "\t  using(house_pk) ) as c\n" +
                    "where member_pk=?;";
            //sql 기재
            ps=conn.prepareStatement(sql);
            //매개변수 대입(현재 로그인된 아이디)
            ps.setInt(1, memberNo);
            //sql 실행
            rs=ps.executeQuery();

            //불러온 데이터 저장
            while(rs.next()){
                //hashMap 객체 생성
                HashMap<String, String> reservationList=new HashMap<>();
                //hashMap에 저장
                reservationList.put("reservation_pk",String.valueOf(  rs.getInt("reservation_pk") ));
                reservationList.put("reservation_date",String.valueOf( rs.getString( "reservation_date") ));
                reservationList.put("houseName",String.valueOf( rs.getString( "houseName") ));
                reservationList.put("reservation_people",String.valueOf( rs.getInt( "reservation_people") ));
                reservationList.put("reservation_status",String.valueOf( rs.getInt("reservation_status") ));

                //ArrayList에 저장
                reservationDtos.add(reservationList);
            }
            //배열에 아무것도 저장되지 않을경우 안내문구 출력
            if (reservationDtos.size()==0){
                return null;
            }
            //저장된 배열 반환
            return reservationDtos;
        }//try end
        catch (Exception e){
            System.out.println("[오류]데이터를 불러오는데 실패했습니다 : "+e);
        }//catch end
        return null;
    }//m end

    //예약내역 삭제
    public int deleteReservation(int reservation_pk){
        try{
            String sql="update reservation set reservation_status=2 where reservation_pk=?;";
            //sql 기재
            ps=conn.prepareStatement(sql);
            //매개변수 대입
            ps.setInt(1,reservation_pk);
            //sql 실행
            int count= ps.executeUpdate();

            if(count==0){
                return 0;
            }

            //예약 취소 성공
            return 1;

        }
        catch (Exception e){
            System.out.println("[오류] : "+e);
        }

        return 0;
    }

    //예약 상태 출력 함수(매개변수 : 예약번호)
    public int findReservationStatus(int reservation_pk){
        try {
            String sql = "select reservation_status from reservation where reservation_pk=?";
            //sql 기재
            ps = conn.prepareStatement(sql);
            //매개변수 대입
            ps.setInt(1,reservation_pk);
            //sql실행
            rs=ps.executeQuery();
            //예약번호에 해당하는 정보는 하나의 레코드
            rs.next();
            int result=rs.getInt("reservation_status");

            //반환
            return result;
        }
        catch(Exception e){
            System.out.println("[오류] : "+e);
        }
        return 0;
    }//m end

    //존재하는 예약번호인지 유효성검사 메소드
    public boolean checkReservationPk(int reservation_pk){
        try {
            //sql 작성
            String sql = "select reservation_pk from reservation_detail where reservation_pk=?;";
            //sql 기재
            ps = conn.prepareStatement(sql);
            //매개변수 대입
            ps.setInt(1,reservation_pk);
            //sql 실행
            rs=ps.executeQuery();

            //만약 조회결과가 존재 할 경우 true 반환
            if(rs.next()){
                return true;
            }
        }
        catch (Exception e){
            System.out.println("[오류] : "+e);
        }
        return false;
    }//m end


//=========================================== 리뷰관리 =================================================
    //리뷰 가능 내역 출력 메소드 (조건 : 예약일자지남 && 예약상태1(승인완료))
    public ArrayList<HashMap<String, String>> finishReservationList(){
        //출력값 저장 배열 생성
        ArrayList<HashMap<String, String>> finishReservations=new ArrayList<>();

        //회원번호 저장 변수
        int member_pk=findMemberPk();
        //sql 실행
        try{
            String sql="select reservation_pk, reservation_date, houseName from house join\n" +
                    "(select * from reservation_date join\n" +
                    "(select reservation_pk, reservation_date_pk from (select * from reservation where reservation_status=0 and member_pk=?) as a \n" +
                    "join (select * from reservation_detail group by reservation_pk) as b\n" +
                    "using(reservation_pk)) as c\n" +
                    "using(reservation_date_pk)) as d\n" +
                    "using(house_pk);";
            //sql 기재
            ps=conn.prepareStatement(sql);
            //매개변수 대입
            ps.setInt(1,member_pk);
            //실행
            rs=ps.executeQuery();

            //출력값 저장
            while(rs.next()){
                //hashMap 객체 생성
                HashMap<String, String> finishRecords=new HashMap<>();

                //hashMap에 저장(예약번호, 날짜, 숙소이름)
                finishRecords.put("reservation_pk",String.valueOf(rs.getInt("reservation_pk")));
                finishRecords.put("reservation_date",rs.getString("reservation_date"));
                finishRecords.put("houseName",rs.getString("houseName"));

                //배열에 hashMap 저장
                finishReservations.add(finishRecords);
            }
            //배열 반환
            return finishReservations;
        }
        catch (Exception e){
            System.out.println("[오류] : "+e);
        }
        return null;
    }


    // 승택 ============================================================
    public ArrayList<HashMap<String,String>> searchHouse(String region){
        ArrayList<HashMap<String, String>> searchHouse = new ArrayList<>();

        try {
            String sql = "select house.house_pk, house.houseName, house.region, house.maxPeople, reservation_date.reservation_date,\n" +
                         "reservation_date.day_price from house inner join reservation_date on\n" +
                         "house.house_pk = reservation_date.house_pk where region = ? order by reservation_date.reservation_date asc";

            ps=conn.prepareStatement(sql);
            ps.setString(1, region);
            rs=ps.executeQuery();
            while(rs.next()){
                HashMap<String, String> houseList=new HashMap<>();

                houseList.put("house_pk",String.valueOf( rs.getInt("house_pk")));
                houseList.put("houseName",String.valueOf( rs.getString("houseName")));
                houseList.put("region",String.valueOf( rs.getString("region")));
                houseList.put("maxPeople",String.valueOf( rs.getInt("maxPeople")));
                houseList.put("reservation_date",String.valueOf( rs.getString("reservation_date")));
                houseList.put("day_price",String.valueOf( rs.getInt("day_price")));

                searchHouse.add(houseList);
            }
            if (searchHouse.size()==0){
                System.out.println("등록된 숙소가 없습니다.");
            }
            return searchHouse;

        }catch (Exception e){
            System.out.println(e + "오류");
        }

        return null;
    }
    // 승택 end ========================================================


    //리뷰등록 메소드
    public boolean inputReview(){

        return false;
    }//m end
}//c end
