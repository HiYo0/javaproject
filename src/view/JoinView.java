package view;

import controller.Control_member;
import model.Dto.MemberDto;

import java.util.Scanner;

public class JoinView {
    Scanner scanner = MainView.getInstance().scanner;

    // 싱글톤
    private JoinView(){}
    private static JoinView JoinView = new JoinView();
    public static JoinView getInstance(){return JoinView;}

    public void join(){
        // 입력
        System.out.print("아이디 : "); String mid = scanner.next();
        System.out.print("비밀번호 : "); String mpw = scanner.next();
        System.out.print("이메일 : "); String memail = scanner.next();
        System.out.print("핸드폰번호 : "); String mphone = scanner.next();
        System.out.print("이름 : "); String mname = scanner.next();

        // 객체화
        MemberDto memberDto = new MemberDto();
        memberDto.setMid(mid);
        memberDto.setMpw(mpw);
        memberDto.setMpw(memail);
        memberDto.setMpw(mphone);
        memberDto.setMpw(mname);

        // 저장 후 control에 전달
        boolean result = Control_member.getInstance().login(memberDto);

        // 결과 출력
        if(result){
            System.out.println("<안내>회원가입 성공");
        } else {
            System.out.println("<안내>회원가입 실패");
        }
    } // join e
} // c e
