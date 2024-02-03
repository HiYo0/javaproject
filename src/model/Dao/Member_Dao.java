package model.Dao;

import jdk.jfr.Category;
import model.Dto.MemberDto;

public class Member_Dao extends Dao {//class start
    //
    // 싱글톤 ==============================================
    private Member_Dao(){}
    private static Member_Dao member_Dao = new Member_Dao();
    public static Member_Dao getInstance() {return member_Dao;}
    // ======================================================


    // ================================ 회원가입 ================================ //
    public int join(MemberDto memberDto){
        try {
            // SQL 작성
            String sql = "insert into member(mid , mpw , memail, mphone, mname ) values(?, ?, ?, ?, ? ) ";
            // SQL 기재
            ps = conn.prepareStatement(sql);
            // SQL 대입(? 매개변수 대입)
            ps.setString(1, memberDto.getMid());
            ps.setString(2, memberDto.getMpw());
            ps.setString(3, memberDto.getMemail());
            ps.setString(4, memberDto.getMphone());
            ps.setString(5, memberDto.getMname());
            // SQL 실행 [ SQL문이 select 이면 rs = ps.executeQuery(); , insert/update/delete 이면 int count = rs.executeUpdate() ]
            int count = ps.executeUpdate();
            if (count == 1) {   return 0; }
            System.out.println("count = " + count);
        }catch ( Exception e ){  System.out.println(e);  } // SQL 문제 발생.
        // 종료
        return 2;
    } // login e


    // ================================ 아이디 중복검사 ================================ //
    public boolean idCheck( String mid ){
        try {
            // 1. SQL 작성한다.
            String sql = "select mid from member where mid = ? ";
            // 2. SQL 기재한다.
            ps = conn.prepareStatement(sql);
            // SQL 대입(? 매개변수 대입)
            ps.setString(1, mid); // sql문법내 첫번째 ?에 mid 변수 값 대입 ;
            // 3. SQL 실행한다.
            rs = ps.executeQuery(); // 질의/검색 (select) 결과를 rs 인터페이스 대입한다.
            // 4. SQL 결과처리
            if (rs.next()) {        // rs.next() : 검색 결과 테이블에서 다음레코드 이동. [ 다음레코드 이동후 존재하면 true , 존재하지 않으면 false ]
                return true; // 중복 있음.
            }
        }catch ( Exception e ){  System.out.println(e);   }
        // 5. 함수종료
        return false; // 중복 없음
    } // e end


    // ================================ 로그인 ================================ //
    public boolean login(MemberDto memberDto){
        try {
            // SQL 작성
            String sql = "select * from member where mid = ? and mpw = ? "; // 아이디와 패스워드 행 가져오기
            // SQL 기재
            ps = conn.prepareStatement(sql); // db 연결
            // SQL 대입(? 매개변수 대입)
            ps.setString(1, memberDto.getMid()); // 첫번째 행에 id대입
            ps.setString(2, memberDto.getMpw()); // 두번째 행에 pw대입
            // SQL 실행
            rs = ps.executeQuery(); // 실행
            while(rs.next()){
                return true;
            }
            // SQL 결과
        }catch ( Exception e ){  System.out.println(e);   } // SQL 문제 발생.
        // 6. 함수종료
        return false;
    } // login e


    // ================================ 아이디를 이용한 회원번호 찾기 ================================ //
    // 왜 ?? 회원번호 찾지  // 로그인 상태 회원번호로 식별
    public int findMno( String mid ){
        try {
            // SQL 작성
            String sql = "select mno from member where mid = ?";
            // SQL 기재
            ps = conn.prepareStatement(sql);
            // SQL 대입(? 매개변수 대입)
            ps.setString(1, mid);
            // SQL 실행.
            rs = ps.executeQuery();
            // SQL 결과처리
            if (rs.next()) {
                // rs.next() : 다음 레코드 이동
                // rs.get타입( 호출할 필드번호 or 필드이름 ) : 현재 레코드의 필드 값 호출
                return rs.getInt("mno");
            }
        }catch ( Exception e ){  System.out.println(e);   }
        // 5. 함수종료
        return 0;
    }

    // ================================ 아이디 찾기 ================================ //
    /*
         아이디찾기 > 이름, 전화번호 받아서 일치하면 아아디 호출
         결과값 뭘로 줄건데? 불리언 트루 펄스
    */
    public String SearchId (MemberDto memberDto){
        try {
            // SQL 작성
            String sql = "select mid from member where mname = ? and mphone = ?";
            // SQL 기재
            ps = conn.prepareStatement(sql);
            // SQL 대입
            ps.setString(1, memberDto.getMname());
            ps.setString(2, memberDto.getMphone());
            // SQL 실행
            rs = ps.executeQuery(); // 실행
            while(rs.next()){
                return rs.getString("mid"); //get타입.필드명
            }
            // SQL 결과
        }catch ( Exception e ){  System.out.println(e);   } // SQL 문제 발생.
        // 6. 함수종료
        return null;
    }


} // c e
