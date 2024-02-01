package model.Dao;

public class Member_Dao extends Dao {//class start
    //
    // 싱글톤 ==============================================
    private Member_Dao(){}
    private static Member_Dao member_Dao = new Member_Dao();
    public static Member_Dao getInstance() {return member_Dao;}
    // ======================================================

}
