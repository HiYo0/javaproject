package view;

import controller.Control_Host;
import model.Dto.HouseDto;
import model.Dto.Reservation_dateDto;

import java.util.Scanner;

public class HostSubPageView {

    private HostSubPageView(){}
    private static HostSubPageView hostSubPageView=new HostSubPageView();
    public static HostSubPageView getInstance(){return hostSubPageView;}

    public void run(){
        // 스캐너 선언
        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.println("1.숙소등록 | 2.숙소수정 | 3.숙소삭제 | 4.돌아가기");
            int ch = scanner.nextInt(); // 1.숙소등록 | 2.숙소수정 | 3.숙소삭제 | 4.돌아가기

            if(ch == 1){ // 숙소등록
                insertHouse();
            }
            else if(ch == 2){

            }
            else if(ch == 3){

            }
            else if(ch == 4){
                break;
            }
            else{
                System.out.println("잘못 입력하셨습니다.");
            }
        } // while end


    } // run() end

    public void insertHouse(){
        // 스캐너 선언
        Scanner scanner = new Scanner(System.in);

        System.out.println("숙소이름 :");      String name = scanner.next();
        System.out.println("지역 :");         String region = scanner.next();
        System.out.println("시작날짜 ex)0000-00-00 :");         String date = scanner.next();
        System.out.println("몇박 하시겠습니까? : "); int day = scanner.nextInt();
        System.out.println("최대인원 :");       int people = scanner.nextInt();
        System.out.println("1박당 가격 :");     int price = scanner.nextInt();

        // house table DB 추가용 객체
        HouseDto houseDto = new HouseDto( 0,name, 0, region, people);

        // reservation_data DB 추가용 객체
        Reservation_dateDto reservation_dateDto = new Reservation_dateDto(0, date, 0, price);

        boolean result = Control_Host.getInstance().insertHouse(houseDto, reservation_dateDto, day);

        if(result){
            System.out.println("숙소 등록이 완료되었습니다.");
        }
    }

}
