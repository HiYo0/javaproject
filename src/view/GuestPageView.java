package view;

import controller.Control_Guest;
import controller.Control_member;
import model.Dto.HouseDto;
import model.Dto.ReservationDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;



//게스트 뷰
public class GuestPageView {
    //싱글톤
    private GuestPageView(){}
    private static GuestPageView guestPageView=new GuestPageView();
    public static GuestPageView getInstance(){return guestPageView;}

    //scanner 객체 생성
    Scanner scanner=new Scanner(System.in);

    //test용 main메소드
    public static void main(String[] args) {
        GuestPageView.getInstance().run();
    }

    //실행 메소드
    public void run(){
        while (true) {
            System.out.println("1.숙소검색 | 2.예약관리 | 3.리뷰관리 | 4.돌아가기");
            System.out.print("선택 : ");
            int ch = scanner.nextInt();

            if (ch == 1) {
                searchHouse();
            }
            else if (ch == 2) {
                System.out.println("아이디(임시)test용 : ");
                String id = scanner.next();
                Control_member.getInstance().setLogin_id(id);
                reservationManagement();
            }
            else if (ch == 3) {

            }
            else if (ch == 4) {

            }
        }
    }//m end

    //예약관리 메소드
    public void reservationManagement() {
        ArrayList<HashMap<String, String>> result = Control_Guest.getInstance().reservationList();

        //예약내역 출력
        System.out.println("============= 예약내역 ===============");
        System.out.println("예약번호\t\t예약날짜\t\t\t\t숙소이름\t\t예약인원\t\t예약상태");
        for (int i = 0; i < result.size(); i++) {
            System.out.printf("%-10s %-10s %-10s %-10s %-10s\n",
                    result.get(i).get("reservation_pk"),
                    result.get(i).get("reservation_date"),
                    result.get(i).get("houseName"),
                    result.get(i).get("reservation_people"),
                    result.get(i).get("reservation_status"));
        }//for end
        System.out.println("=====================================");

        try {
            //예약 관리
            System.out.println("--- 1.예약취소 | 2.돌아가기");
            //콘솔 입력
            int ch = scanner.nextInt();
            if (ch == 1) {//예약 취소
                System.out.println("---취소하실 예약번호를 입력 해 주십시오.---");
                int reservation_pk = scanner.nextInt();
                //db내역 삭제 함수 호출
                //예약상태 변경 함수 호출
            } else if (ch == 2) {//돌아가기
                System.out.println("---예약관리를 취소하셨습니다..---");
                GuestPageView.getInstance().run();
            }
            else{
                System.out.println("올바르지 않은 입력입니다.");
            }
        }
        catch(Exception e){
            System.out.println("올바르지 않은 입력입니다. : "+e);
        }//t end

    }//m end


    // 승택 ================================================================
    public void searchHouse(){
        System.out.println("1.지역만 선택하기 | 2.상세 조건 선택하기(지역, 날짜, 인원, 가격) : ");
        int ch = scanner.nextInt();

        if(ch == 1){
            System.out.println("지역 선택 : ");     String region = scanner.next();
            ArrayList<HashMap<String, String>> searchHouse = Control_Guest.getInstance().searchHouse(region);
            outputHouse(region, searchHouse);
        }
        else if(ch == 2){
            System.out.println("입실 날짜 : ");
            System.out.println("몇박 하시겠습니까?");
        }
        else{
            System.out.println("잘못 입력하셨습니다.");
        }
    }

    public void outputHouse(String rigion, ArrayList<HashMap<String, String>> searchHouse){
        System.out.println("============= 숙소내역 ===============");
        System.out.println("no\t\t숙소이름\t\t지역\t\t최대인원\t\t\t\t날짜\t\t\t1박당가격");
        for (int i = 0; i < searchHouse.size(); i++) {
            System.out.printf("%-7s %-10s %-10s %-10s %10s %13s\n",
                    (i+1),
                    searchHouse.get(i).get("houseName"),
                    searchHouse.get(i).get("region"),
                    searchHouse.get(i).get("maxPeople"),
                    searchHouse.get(i).get("reservation_date"),
                    searchHouse.get(i).get("day_price"));
        }//for end
        System.out.println("=====================================");
    }
    // 승택 end ============================================================
}//c end
