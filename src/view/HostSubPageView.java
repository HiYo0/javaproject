package view;

import controller.Control_Host;
import controller.Control_member;
import model.Dto.HouseDto;
import model.Dto.HouseFixDto;
import model.Dto.Reservation_dateDto;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HostSubPageView {

    private HostSubPageView(){}
    private static HostSubPageView hostSubPageView=new HostSubPageView();
    public static HostSubPageView getInstance(){return hostSubPageView;}

//    public static void main(String[] args) {
//        HostSubPageView.getInstance().run();
//    }

    public void run(){
        // 스캐너 선언
        Scanner scanner = new Scanner(System.in);

        while (true){
            try {
                System.out.println("1.숙소등록 | 2.숙소수정 | 3.숙소삭제 | 4.돌아가기");
                System.out.print("선택 > ");
                int ch = scanner.nextInt(); // 1.숙소등록 | 2.숙소수정 | 3.숙소삭제 | 4.돌아가기

                if (ch == 1) { // 숙소등록
                    insertHouse();
                } else if (ch == 2) { //숙소수정
                    // 1. 내가등록한 숙소를 DB에서 꺼내오기
                    // 2. 수정할 데이터 선택 및 입력받아서 수정 -> 성공여부 출력
                    // houseView(); // 내가 로그인한 아이디로 등록한 숙소 출력 => 반환 = 숙소리스트
                    // returnHouseNo(houseView()); // 내가 선택한 번호를 하우스번호로 반환
                    int houseNo = returnHouseNo(houseView());

                    // 하우스식별번호 주고 정보 받아오기
                    ArrayList<HouseFixDto> houseFixDtos = Control_Host.getInstance().HouseFix_View(houseNo);
                    int 수정선택번호 = 0;

                    if (!houseFixDtos.isEmpty()) {
                        System.out.println("======================= " + houseFixDtos.get(0).getHouseName() + " =======================");
                        System.out.printf(" %2s\t%-12s %-8s %-4s \t\t%2s\n", "번호", "날짜", "가격", "지역", "최대인원");
                        for (int i = 0; i < houseFixDtos.size(); i++) {
                            String 날짜 = houseFixDtos.get(i).getReservation_date();
                            int 가격 = houseFixDtos.get(i).getDay_price();
                            String 지역 = houseFixDtos.get(i).getRegion();
                            int 최대인원 = houseFixDtos.get(i).getMaxPeople();

                            System.out.printf(" %2s\t%-12s\t %-10d %-4s \t\t%2s\n", i + 1, 날짜, 가격, 지역, 최대인원);
                        }//for end
                        System.out.println("================================================================");

                        while (true) {
                            try {
                                수정선택번호 = scanner.nextInt();

                                System.out.println("\n수정하실 내용을 선택해주세요");
                                System.out.println("1.날짜 2.가격 3.지역 4.최대인원");
                                System.out.print("선택 > ");
                                int 항목선택 = scanner.nextInt();

                                if(항목선택 == 1 || 항목선택== 3) {
                                    int 수정내용 = scanner.nextInt();
                                    Control_Host.getInstance().HouseFix(항목선택, 수정선택번호 - 1, 수정내용);
                                }

                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("안내] 숫자로 입력해주세요");
                                scanner.nextLine();
                            }
                        }

                    }else {
                        System.out.println("안내] 등록된 예약일이 없습니다.");
                    }



                } else if (ch == 3) { // 숙소삭제

                } else if (ch == 4) {// 돌아가기
                    break;
                } else {
                    System.out.println("잘못 입력하셨습니다.");
                }
            }catch (InputMismatchException e){
                System.out.println("\n안내] 숫자로를 입력해주세요");
            }
        } // while end


    } // run() end
    // 전승호 ===========================================================================

    public ArrayList<HouseDto> houseView(){
        // 내가 등록한 숙소 출력하기
            // 1. 로그인한 id를 받서 -> 식별번호로 변환
            // 2. 식별번호로 등록된 HouseDto 반환받아와서 출력하기
            // 3. 하우스 리스트 반환
        String id = Control_member.getInstance().getLogin_id();
        System.out.println("======================= 내가 등록한 숙소 =======================");
        System.out.printf(" %2s\t%-12s %-15s %-4s \t\t%2s\n","번호","식별번호","이름","지역","최대인원");
        // 로그인한 id 주고 그에대한 house 정보 가져오기
        ArrayList<HouseDto> my_house_list = Control_Host.getInstance().my_house_list(id);
        for (int i = 0; i < my_house_list.size(); i++) {
            int house_pk = my_house_list.get(i).getHouse_pk();// 하우스 식별번호
            String name = my_house_list.get(i).getHouseName();// 하우스 이름
            String region = my_house_list.get(i).getRegion();//  지역
            int maxPeople = my_house_list.get(i).getMaxPeople(); // 최대인원
            System.out.printf(" %2d\t\t%-10d \t%-15s %-4s  \t\t%2d\n",i+1,house_pk,name,region,maxPeople);
        }
        System.out.println("================================================================");
        return my_house_list;
    }
    public int returnHouseNo(ArrayList<HouseDto> my_house_list){
        // 집 리스트 주고 선택받은 하우스 식별번호 를 반환해주는 메소드

        Scanner scanner = new Scanner(System.in);
        int ch = 0; // 하우스 식별번호

        if (my_house_list.size()>=1) { // 1개의 데이터도 없으면 스킵
            while (true) { // 한글 입력시 예외처리 하기위한 while
                try {
                    System.out.print("선택 > ");
                    ch = scanner.nextInt();
                    if (ch < my_house_list.size() + 1) { //있는 번호중에서만 선택할수있게 하기
                        // 선택받은 번호를 하우스 식별번호로 변경하는 코드
                        for (int i = 0; i < my_house_list.size(); i++) {
                            if ((ch - 1) == i) {
                                ch = my_house_list.get(i).getHouse_pk();

                                break;
                            }
                        }//for end
                        break;
                    } else {
                        System.out.println("해당 선택번호는 없습니다.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("숫자입력해주세요");
                    scanner.next();
                }
            }// while end
        }// if end
        return ch;
    }// returnHouseNo method end


    // 전승호End ==========================================================================

    // 오승택==============================================================================
    public void insertHouse(){
        // 스캐너 선언
        Scanner scanner = new Scanner(System.in);

        System.out.println("숙소이름 :");      String name = scanner.next();
        System.out.println("지역 :");         String region = scanner.next();
        System.out.println("시작날짜 ex)0000-00-00 :");         String date = scanner.next();
        System.out.println("며칠 등록하시겠습니까? : "); int day = scanner.nextInt();
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
    // 오승택 end============================================================================================

}
