package view;

import controller.Control_Guest;
import model.Dto.Guest_ReviewDto;

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
            ArrayList<HashMap<String, String>> result = Control_Guest.getInstance().finishReservationList();

            try {
                //출력
                System.out.println("예약번호\t\t예약날짜\t\t숙소이름");
                if (result.size() == 0) {//데이터 없는경우 유효성 검사
                    System.out.println("리뷰등록 가능한 예약이 없습니다.");
                    return;
                } else {
                    for (int i = 0; i < result.size(); i++) {
                        System.out.printf("%-10s %-10s %-10s\n",
                                result.get(i).get("reservation_pk"),
                                result.get(i).get("reservation_date"),
                                result.get(i).get("houseName"));
                    }//for end
                }//if end

                //리뷰등록할 예약번호 선택
                System.out.println("리뷰를 등록하실 예약번호를 선택해 주십시오.");
                System.out.print("선택 : ");
                int reservatioPk=scanner.nextInt();
                //리뷰 가능한 예약번호인지 유효성검사
                if(!Control_Guest.getInstance().checkFinishReservationList(reservatioPk)){
                    System.out.println("유효하지 않은 번호입니다.");
                    return;
                }

                //선택한 예약번호에 리뷰내용 입력
                System.out.print("리뷰내용 작성 : ");
                scanner.nextLine();
                String rContent=scanner.nextLine();
                //선택한 예약번호에 별점 입력
                System.out.print("별점 작성(소수점은 제외됩니다.) : ");
                int score=scanner.nextByte();

                //입력한 내용 객체에 저장
                Guest_ReviewDto guestReviewDto=new Guest_ReviewDto();//리뷰 입력내용 저장 객체
                guestReviewDto.setContent(rContent);
                guestReviewDto.setScore(score);

                //DB 저장을 위한 controller에 객체 대입
                boolean inputResult= Control_Guest.getInstance().inputReview(reservatioPk, guestReviewDto);

                //결과출력
                if(inputResult){
                    System.out.println("등록성공");
                }
                else{
                    System.out.println("[등록실패] 해당하는 숙소가 존재하지 않습니다.");
                }//if end
            }//t end
            catch (Exception e) {
                System.out.println("[오류] : " + e);
            }

    }//m end
}//c end
