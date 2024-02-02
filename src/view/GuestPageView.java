package view;

import controller.Control_Guest;
import controller.Control_member;
import model.Dto.ReservationDto;

import java.util.ArrayList;
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

    public void reservationManagement() {
        ArrayList<ReservationDto> result = Control_Guest.getInstance().reservationList();
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i).getReservation_pk());
            System.out.println(result.get(i).getReservation_people());
            System.out.println(result.get(i).getReservation_status());
        }//for end
    }//m end
}//c end
