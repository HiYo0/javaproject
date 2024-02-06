package view;

//마이페이지 뷰
public class MyPageView {
    //싱글톤
    private MyPageView(){}
    private static MyPageView myPageView=new MyPageView();
    public static MyPageView getInstance(){return myPageView;}

    //테스트 실행 메소드
    public static void main(String[] args) {ChangePasswordView.getInstance().ChangePasswordView();}

        public void run(){
            System.out.println("1.비밀번호 변경 | 2.회원탈퇴 | 3.돌아가기");
        }
}
