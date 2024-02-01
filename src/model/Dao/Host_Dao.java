package model.Dao;

public class Host_Dao extends Dao{// class start

    // 싱글톤 ==============================================
    private Host_Dao(){}
    private static Host_Dao host_dao = new Host_Dao();
    public static Host_Dao getInstance() {return host_dao;}
    // ======================================================



}//class end
