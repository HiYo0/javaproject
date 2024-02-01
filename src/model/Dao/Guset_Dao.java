package model.Dao;

public class Guset_Dao extends Dao{
    // 싱글톤 ==============================================
    private Guset_Dao(){}
    private static Guset_Dao guset_dao = new Guset_Dao();
    public static Guset_Dao getInstance() {return guset_dao;}
    // ======================================================
}
