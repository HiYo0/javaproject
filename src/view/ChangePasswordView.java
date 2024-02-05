package view;

import controller.Control_member;
import model.Dto.MemberDto;

import java.util.Scanner;

public class ChangePasswordView {
    Scanner scanner = MainView.getInstance().scanner;

    // 싱글톤
    private ChangePasswordView(){}
    private static ChangePasswordView ChangePasswordView = new ChangePasswordView();
    public static ChangePasswordView getInstance(){return ChangePasswordView;}
    public void ChangePasswordView(){
//        // 입력
//        System.out.print("아이디 : "); String mid = scanner.next();
//        System.out.print("기존 비밀번호 : "); String memail = scanner.next();
//        System.out.print("새로운 비밀번호 : "); String memail = scanner.next();
//
//        // 객체화
//        MemberDto memberDto = new MemberDto();
//        memberDto.setMid(mid);
//        memberDto.setMemail(memail);
//
//        // 저장 후 control에 전달
//        String result = Control_member.getInstance().SearchPw(memberDto);
//
//        // 결과 출력
//        if (result!=null){
//            System.out.println("<안내>비밀번호 : "+result);
//        }else {
//            System.out.println("<안내> 오류 해당 정보에 맞는 비밀번호를 찾을 수 없음");
//        }
    } // ChangePasswordView e
}
