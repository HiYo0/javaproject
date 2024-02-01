package view;

import java.util.Scanner;

//main page view ( host / guest / mypage / logout )
public class MainPageView {
    //싱글톤
    private MainPageView(){}
    private static MainPageView mainPageView=new MainPageView();
    public static MainPageView getInstance(){return mainPageView;}

        public void run() {
            //scanner 생성
            Scanner scanner = new Scanner(System.in);


            //안내문구
            System.out.println("== OO에 오신것을 환영합니다. 이용하실 서비스를 선택 해 주십시오. ==");
            System.out.println("1.Host | 2.Guest | 3.마이페이지 | 4.로그아웃");
            System.out.print("선택 : ");

            while (true) {
                //입력
                try {
                    int ch = scanner.nextInt();

                    if (ch == 1) {//host 페이지
                        HostPageView.getInstance().run();
                    } else if (ch == 2) {//guest 페이지
                        GuestPageView.getInstance().run();
                    } else if (ch == 3) {//마이페이지
                        MyPageView.getInstance().run();
                    } else if (ch == 4) {//로그아웃
                        break;
                    } else {
                        System.out.println("올바르지 않은 입력입니다.");
                    }
                } catch (Exception e) {
                    System.out.println("올바르지 않은 입력입니다.");
                    System.out.println(e);
                }
            }//w end
        }//m end
}//c end
