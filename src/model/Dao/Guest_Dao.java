package model.Dao;

import controller.Control_member;
import model.Dto.ReservationDto;

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
                System.out.println("[안내]예약 내역이 없습니다.");
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
    public boolean deleteReservation(int reservation_pk){
        try{
            String sql="delete from reservation where reservation_pk=?";
            //sql 기재
            ps=conn.prepareStatement(sql);
            //매개변수 대입
            ps.setInt(1,reservation_pk);
            //sql 실행
            int count= ps.executeUpdate();

        }
        catch (Exception e){
            System.out.println("[오류] : "+e);
        }

        return false;
    }

    //예약내역 상태 출력 함수(매개변수 : 예약번호)
    public int findReservationStatus(int reservation_pk){
        try {
            String sql = "select reservation_status from reservation where reservation_pk=?";
            //sql 기재
            ps = conn.prepareStatement(sql);
        }
        catch(Exception e){
            System.out.println("[오류] : "+e);
        }
        return 0;
    }
}//c end
