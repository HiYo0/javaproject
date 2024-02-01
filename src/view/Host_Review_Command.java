package view;


import controller.Control_Host;
import controller.Control_Review;
import controller.Control_member;
import model.Dto.HouseDto;
import model.Dto.Reservation_dateDto;
import model.Dto.ReviewDto;

import java.util.ArrayList;
import java.util.Scanner;

public class Host_Review_Command {//class start
    // 싱글톤
    private Host_Review_Command(){}
    private static Host_Review_Command review_command = new Host_Review_Command();
    public static Host_Review_Command getInstance(){return review_command;}

    public void run(){
        Scanner scanner = new Scanner(System.in);
        String id = Control_member.getInstance().getLogin_id();

        ArrayList<HouseDto> my_house_list = Control_Host.getInstance().my_house_list(id);
//        ArrayList<ReviewDto> my_review = Control_Review.getInstance().my_view();리뷰출력 객체받아오기



        while (true) { // while start
            System.out.println("\n\n============== 기능을 선택해주세요 ==============");
            System.out.println("1.리뷰등록 | 2.리뷰수정 | 3.리뷰삭제 | 4.돌아가기");
            System.out.print("선택(1~4) > ");
            int choice = scanner.nextInt();
            if (choice == 1) {//리뷰등록

                if(Control_Review.getInstance().review_write()){
                    System.out.println("안내]리뷰등록에 성공했습니다.");
                }else {
                    System.out.println("안내]리뷰등록에 실패했습니다.");
                }

            } else if (choice == 2) {//리뷰수정

            } else if (choice == 3) {//리뷰삭제

            } else if (choice == 4) {//돌아가기
                break;
            }//IF END
        }//while end
    }// run method end

}//class end
