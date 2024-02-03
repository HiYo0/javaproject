package view;

import controller.Control_member;
import model.Dto.MemberDto;

import java.util.Scanner;

public class SearchId {
    Scanner scanner = MainView.getInstance().scanner;

    // 싱글톤
    private SearchId(){}
    private static SearchId SearchId = new SearchId();
    public static SearchId getInstance(){return SearchId;}


    public void SearchId(){
        // 입력
        System.out.print("이름 : "); String mname = scanner.next();
        System.out.print("핸드폰번호 : "); String mphone = scanner.next();

        // 객체화
        MemberDto memberDto = new MemberDto();
        memberDto.setMname(mname);
        memberDto.setMphone(mphone);

        // 저장 후 control에 전달
        String result = Control_member.getInstance().SearchId(memberDto);


        // 결과 출력
        System.out.println("찾는 아이디 : "+result);
    } // SearchId e
} // c e
