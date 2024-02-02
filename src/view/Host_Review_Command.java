package view;


import controller.Control_Host;
import controller.Control_Review;
import model.Dto.Guest_ReviewDto;
import model.Dto.HouseDto;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Host_Review_Command {//class start
    // 싱글톤
    private Host_Review_Command(){}
    private static Host_Review_Command review_command = new Host_Review_Command();
    public static Host_Review_Command getInstance(){return review_command;}


    public void run(){
        Scanner scanner = new Scanner(System.in);
        String id = "noname1";/*Control_member.getInstance().getLogin_id();*/
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
        int ch = 0;

        while (true) { // 한글 입력시 예외처리 하기위한 while
            try {
                System.out.print("선택 > ");
                ch = scanner.nextInt();
                // 선택받은 번호를 하우스 식별번호로 변경하는 코드
                for (int i = 0; i < my_house_list.size(); i++) {
                    if ((ch - 1) == i) {
                        ch = my_house_list.get(i).getHouse_pk();
                        break;
                    }
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("숫자입력해주세요");
                scanner.next();
            }
        }// while end

        // 내가 등록한 house 에 대한 리뷰 들고오기
        ArrayList<Guest_ReviewDto> my_house_Review = Control_Review.getInstance().my_house_Review(ch);//리뷰출력할 객체받아오기
        // 매개변수 = 하우스 식별번호(ch)




        while (true) { // while start
            System.out.println("\n\n============== 기능을 선택해주세요 ==============");
            System.out.println("1.리뷰등록 | 2.리뷰수정 | 3.리뷰삭제 | 4.돌아가기");
            System.out.print("선택(1~4) > ");
            try {
                int choice = scanner.nextInt();


                if (choice == 1) {//리뷰등록

                    if (Control_Review.getInstance().review_write()) {
                        System.out.println("안내]리뷰등록에 성공했습니다.");
                    } else {
                        System.out.println("안내]리뷰등록에 실패했습니다.");
                    }

                } else if (choice == 2) {//리뷰수정

                } else if (choice == 3) {//리뷰삭제

                } else if (choice == 4) {//돌아가기
                    break;
                }//IF END
            }catch (InputMismatchException e) {System.out.println(e+"\n숫자로 입력해주세요");}
        }//while end
    }// run method end

}//class end
