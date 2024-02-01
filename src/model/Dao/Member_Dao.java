package model.Dao;

import model.Dto.MemberDto;

public class Member_Dao extends Dao {//class start
    //
    // 싱글톤 ==============================================
    private Member_Dao(){}
    private static Member_Dao member_Dao = new Member_Dao();
    public static Member_Dao getInstance() {return member_Dao;}
    // ======================================================

    // 회원가입
    public boolean join(MemberDto memberDto){
        try {
            // 1. SQL 작성
            String sql = "insert into member(mid , mpw , memail, mphone, mname ) values(?, ?, ?, ?, ? ) ";
            // 2. SQL 기재한다.
            ps = conn.prepareStatement(sql);
            // 3. SQL ? 매개변수 있을경우 ? 에 값을 대입한다.
            ps.setString(1, memberDto.getMid());
            ps.setString(2, memberDto.getMpw());
            ps.setString(3, memberDto.getMemail());
            ps.setString(4, memberDto.getMphone());
            ps.setString(5, memberDto.getMname());
            // 4. SQL 실행한다. [ SQL문이 select 이면 rs = ps.executeQuery(); , insert/update/delete 이면 int count = rs.executeUpdate() ]
            ps.executeUpdate();
            // 5. SQL 결과처리
            // if vs while   : 만약에 SELECT의 결과물이 하나의 레코드가 존재하면 로그인 성공 아니면 실패.
            //if ( rs.next() ) {  return true;   }
        }catch ( Exception e ){  System.out.println(e);  } // SQL 문제 발생.
        // 6. 함수종료
        return false;
    } // login e

    // 로그인
    public boolean login(MemberDto memberDto){
        try {
            // 1. SQL 작성
            String sql = "select * from member where mid = ? and mpw = ? ";
            // 2. SQL 기재한다.
            ps = conn.prepareStatement(sql);
            // 3. SQL ? 매개변수 있을경우 ? 에 값을 대입한다.
            ps.setString(1, memberDto.getMid()); // sql 문법내 첫번째 ? 에 매개변수 값 대입
            ps.setString(2, memberDto.getMpw()); // sql 문법내 두번째 ? 에 매개변수 값 대입
            // 4. SQL 실행한다. [ SQL문이 select 이면 rs = ps.executeQuery(); , insert/update/delete 이면 int count = rs.executeUpdate() ]
            rs = ps.executeQuery();
            // 5. SQL 결과처리
            // if vs while   : 만약에 SELECT의 결과물이 하나의 레코드가 존재하면 로그인 성공 아니면 실패.
            if ( rs.next() ) {  return true;   }
        }catch ( Exception e ){  System.out.println(e);   } // SQL 문제 발생.
        // 6. 함수종료
        return false;
    } // login e

} // c e
