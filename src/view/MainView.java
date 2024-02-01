package view;

import java.util.Scanner;

public class MainView {
    // 싱글톤
    private MainView(){}
    private static MainView MainView = new MainView();
    public static MainView getInstance(){return MainView;}

    public void run(){
        while (true) {
            Scanner scanner = new Scanner(System.in);
            // 기능 선택하기
            System.out.println("======================================");
            System.out.println("1.로그인 | 2.회원가입 | 3.아이디찾기 | 4.비밀번호찾기");
            System.out.println("======================================");
            System.out.print("기능을 선택해 주세요(1~4) => ");
            int ch = scanner.nextInt();

            if(ch==1){}
            else if (ch==2) {}
            else if (ch==3) {}
            else if (ch==4) {}
        }
    }
}// c e
