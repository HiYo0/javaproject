package model.Dao;

import model.Dto.Guest_ReviewDto;
import model.Dto.ReservationDto;
import model.Dto.ReviewWrite_View_Dto;


import java.util.ArrayList;

public class Review_Dao extends Dao{//class start
    // 싱글톤 ==============================================
    private Review_Dao(){}
    private static Review_Dao review_dao = new Review_Dao();
    public static Review_Dao getInstance() {return review_dao;}
    // ======================================================

    // 전승호 start--------------------------------------
    public ArrayList<Guest_ReviewDto> my_house_Review(int ch){
        ArrayList<Guest_ReviewDto> house_review_list = new ArrayList<>();// house_review_list 데이터의집
        try {
            // 1. sql 작성한다
            String sql = "select * from guest_review where target = "+ch+";";
            // 2. sql 기재한다
            ps = conn.prepareStatement(sql);
            // 3. sql 실행한다.
            rs = ps.executeQuery();
            // 4. sql 결과처리


            while (rs.next()){//DB house 데이터 다 가져오기
                Guest_ReviewDto guest_reviewDto = new Guest_ReviewDto();
                guest_reviewDto.setReview_pk(rs.getInt(1));
                guest_reviewDto.setTarget(rs.getInt(2));
                guest_reviewDto.setWriter(rs.getInt(3));
                guest_reviewDto.setContent(rs.getString(4));
                guest_reviewDto.setScore(rs.getInt(5));

                house_review_list.add(guest_reviewDto);
            }

            return house_review_list;
        }catch (Exception e){
            System.out.println("my_house_list 오류"+e);

        }
        return house_review_list;
    }
    // 하우스 식벽번호 -> 하우스 이름반환
    public String house_name (int ch){
        try {
            String sql = "select * from house where house_pk = " + ch + ";";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            // 처리
            String name = "";
            if(rs.next()){
                name=rs.getString("houseName");
            }
            return name;
        }catch (Exception e){
            System.out.println("house_name 오류"+e);
        }

        return "";
    }

    // 작성자 식별번호 -> 이름으로 반환
    public String writer_name (int writer){
        try {
            String sql = "select * from member where member_pk = "+writer+";";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            // 처리
            String name = "";
            if(rs.next()){
                name=rs.getString("mname");
            }
            return name;
        }catch (Exception e){
            System.out.println("house_name 오류"+e);
        }
        return "";
    }

    // 리뷰 작성할수 있는 거 찾아오기
    public ArrayList<ReviewWrite_View_Dto>review_write_view(){
        ArrayList<ReviewWrite_View_Dto> review_write_view = new ArrayList<>();
        ArrayList<ReservationDto> reservationDtos = new ArrayList<>();
        try {
            String sql = "select * from reservation where reservation_status = 1;";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            // 처리
            while (rs.next()){
                ReservationDto reservationDto = new ReservationDto();
                reservationDto.setReservation_pk(rs.getInt(1));
                reservationDto.setMember_pk(rs.getInt(2));//회원
                reservationDto.setReservation_people(rs.getInt(3));
                reservationDto.setReservation_status(rs.getInt(4));//상태

                reservationDtos.add(reservationDto);
            }
            // 찾은 멤버PK 배열에 등록
            int[] memberPk = new int[reservationDtos.size()];
            for (int i = 0; i < reservationDtos.size(); i++) {
                memberPk[i] = reservationDtos.get(i).getMember_pk();
            }

            String sql2 = "select * from reservation where reservation_status = 1;";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            // 맴베 pk 로 이름 구해오기
            String[] memberName = new String[reservationDtos.size()];
            for (int i = 0; i < memberName.length; i++) {
                memberName[i]=writer_name(memberPk[i]);
            }


            return review_write_view;

        }catch (Exception e){
            System.out.println("review_write_view 오류"+e);
        }

        return review_write_view;
    }




    // 전승호 end------------------------------------


}//class end
