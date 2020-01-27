package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.LoginDAO;
import entity.Login;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LoginDAOimpl implements LoginDAO {
    @Override
    public List<Login> findAll() throws Exception {
        ResultSet execute = CrudUtil.execute("SELECT * FROM login");
        List<Login> logindata = new ArrayList<>();
        while (execute.next()) {
            logindata.add(new Login(execute.getString(1),
                    execute.getString(2),
                    execute.getString(3),
                    execute.getBoolean(4)));
        }
        return logindata;
    }


    @Override
    public Login find(String s) throws Exception {
         ResultSet rstm=CrudUtil.execute("SELECT * FROM login WHERE email=?",s);

         if(rstm.next()){
            return new Login(rstm.getString(1),
                    rstm.getString(2),
                    rstm.getString(3),
                    rstm.getBoolean(4));
        }
         return null;
    }

    @Override
    public boolean save(Login entity) throws Exception {
    return CrudUtil.execute("INSERT INTO login VALUE (?,?,?,?)",entity.getUid(),entity.getEmail(),entity.getPassword(),entity.isRoll());
    }

    @Override
    public boolean update(Login entity) throws Exception {
        boolean execute = CrudUtil.execute("UPDATE login SET roll=? where uid=?", entity.isRoll(), entity.getUid());
        return execute;

    }


    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public Login findRoll(String uid) throws Exception {
        ResultSet execute = CrudUtil.execute("SELECT * FROM login WHERE uid=?", uid);
        if(execute.next()){
            return new Login(execute.getString(1),
                    execute.getString(2),
                    execute.getString(3),
                    execute.getBoolean(4));
        }
        return null;
    }
}
