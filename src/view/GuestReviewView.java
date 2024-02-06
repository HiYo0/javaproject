package view;

import controller.Control_Guest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class GuestReviewView {
    //싱글톤
    private GuestReviewView(){}
    private static GuestReviewView guestReviewView=new GuestReviewView();
    public static GuestReviewView getInstance(){return guestReviewView;}

    //Scanner 객체
    Scanner scanner=new Scanner(System.in);

    //main view
    public void run(){
        while(true){
            System.out.println("============ 리뷰페이지 =============");
            System.out.println("1.리뷰등록 | 2.리뷰수정 | 3.리뷰삭제 | 4.돌아가기");
            System.out.print("선택 : ");

            //입력
            int ch = scanner.nextInt();

            //실행
            try {
                if (ch == 1) {  //리뷰등록
                    inputReview();
                } else if (ch == 2) {   //리뷰수정

                } else if (ch == 3) {   //리뷰삭제

                } else if (ch == 4) {   //돌아가기

                } else {
                    System.out.println("올바르지 않은 입력입니다.");
                }//if1 end
            }//t end
            catch (Exception e) {
                System.out.println("올바르지 않은 입력입니다. : " + e);
            }
        }//w end
    }//m end

    //리뷰등록 메소드
    public void inputReview(){
    //리뷰가능 예약내역 출력
        //control 호출
        ArrayList<HashMap<String, String>> result= Control_Guest.getInstance().finishReservationList();
        //출력
        if(result==null){//데이터 없는경우 유효성 검사
            System.out.println("리뷰등록이 가능한 예약번호가 없습니다. 이전페이지로 돌아갑니다.");
            return;
        }

        System.out.println("예약번호\t\t예약날짜\t\t숙소이름");
        for(int i=0; i<result.size(); i++) {
            System.out.printf("%-10s %-10s %-10s\n",
                    result.get(i).get("reservation_pk"),
                    result.get(i).get("reservation_date"),
                    result.get(i).get("houseName"));
        }//for end

    }//m end
}//c end
