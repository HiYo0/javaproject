package model.Dao;

import controller.Control_member;
import model.Dto.ReservationDto;

import java.util.ArrayList;

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
    public ArrayList<ReservationDto> reservationList(){
        System.out.println("dao호출");
        //회원번호 찾기 함수 호출
        int memberNo=findMemberPk();

        //불러온 데이터 저장할 배열
        ArrayList<ReservationDto> reservationDtos=new ArrayList<>();

        try{
            //1차 데이터 추출 sql : reservation(reservation_pk, reservation_people, reservation_status) 추출
            String sql="select reservation_pk, reservation_people, reservation_status from reservation where member_pk=?";
            //sql 기재
            ps=conn.prepareStatement(sql);
            //매개변수 대입(현재 로그인된 아이디)
            ps.setInt(1, memberNo);
            //sql 실행
            rs=ps.executeQuery();

            //불러온 데이터 reservationDto객체에 저장
            while(rs.next()){
                ReservationDto reservationDto=new ReservationDto();
                reservationDto.setReservation_pk(rs.getInt("reservation_pk"));
                reservationDto.setReservation_people(rs.getInt("reservation_people"));
                reservationDto.setReservation_status(rs.getInt("reservation_status"));
                //ArrayList<ReservationDto>타입 배열에 reservationDto 대입
                reservationDtos.add(reservationDto);
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

    //예약현황 불러오기_날짜
    public String reservationDateList(int Reservation_pk){
        //추출정보 저장 배열 선언
        ArrayList<String> dateList=new ArrayList<>();
        try {
            //2차 데이터 추출 sql : reservation_detail(reservation_date_pk) 추출
            String sql = "select reservation_date from reservation_detail where reservation_pk=?";
            //sql 기재
            ps=conn.prepareStatement(sql);
            //매개변수 대입(예약번호)
            ps.setInt(1,Reservation_pk);
            //sql 실행
            rs=ps.executeQuery();

            //출력한 날짜번호 정보 배열에 입력
            while(rs.next()){
                dateList.add(rs.getString("reservation_date_pk"));
            }
        }
        catch (Exception e){
            System.out.println("예약날짜불러오기 오류 : "+e);
        }

        return null;
    }
}//c end
