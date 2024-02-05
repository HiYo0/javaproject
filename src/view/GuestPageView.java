package view;

import controller.Control_Guest;
import controller.Control_member;
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
        System.out.println("1.숙소검색 | 2.예약관리 | 3.리뷰관리 | 4.돌아가기");
        System.out.print("선택 : ");
        int ch=scanner.nextInt();

        if(ch==1){

        }
        else if(ch==2){
            System.out.println("아이디(임시)test용 : ");
            String id=scanner.next();
            Control_member.getInstance().setLogin_id(id);
            reservationManagement();
        }
        else if(ch==3){

        }
        else if(ch==4){

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

            //예약결과 저장변수
            boolean deleteResult=false;
            if (ch == 1) {//예약 취소
                System.out.println("---취소하실 예약번호를 입력 해 주십시오.---");
                int reservation_pk = scanner.nextInt();

                System.out.println("정말 취소하시겠습니까? 1.예 | 2.아니오");
                int check=scanner.nextInt();
                //삭제 진행
                if(check==1){
                    //db내역 삭제 함수 호출
                    deleteResult= Control_Guest.getInstance().deleteReservation(reservation_pk);
                    //예약상태 변경 함수 호출

                    //출력
                    if(deleteResult){
                        System.out.println("예약취소가 완료되었습니다.");
                    }
                    else{
                        System.out.println("[예약취소 실패]");
                    }
                }
                else if(check==2){//삭제 취소
                    System.out.println("예약관리 페이지로 돌아갑니다.");
                }
                else{
                    System.out.println("올바르지 않은 입력입니다.");
                }//if2 end
            }
            else if (ch == 2) {//돌아가기
                System.out.println("---예약관리를 취소하셨습니다..---");
                GuestPageView.getInstance().run();
            }
            else{
                System.out.println("올바르지 않은 입력입니다.");
            }//if1 end
        }//t end
        catch(Exception e){
            System.out.println("올바르지 않은 입력입니다. : "+e);
        }

    }//m end
}//c end
