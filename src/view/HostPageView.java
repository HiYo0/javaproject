package view;

//호스트 뷰
public class HostPageView {
    //싱글톤
    private HostPageView(){}
    private static HostPageView hostPageView=new HostPageView();
    public static HostPageView getInstance(){return hostPageView;}

    //실행 메소드
    public void run(){
        System.out.println("1.숙소관리 | 2.리뷰관리 | 3.돌아가기");

    }
}
