package view;

//게스트 뷰
public class GuestPageView {
    //싱글톤
    private GuestPageView(){}
    private static GuestPageView guestPageView=new GuestPageView();
    public static GuestPageView getInstance(){return guestPageView;}

    //실행 메소드
    public void run(){
        System.out.println("1.숙소검색 | 2.예약관리 | 3.리뷰관리 | 4.돌아가기");
    }
}
