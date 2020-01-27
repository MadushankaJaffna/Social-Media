package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.ComplainDAO;
import entity.Complain;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ComplainDAOimpl implements ComplainDAO {
    @Override
    public List<Complain> findAll() throws Exception {
        ResultSet execute = CrudUtil.execute("SELECT * FROM complain");
        List<Complain> datalist = new ArrayList<>();
        while(execute.next()){
            datalist.add(new Complain(execute.getString(1),
                    execute.getString(2)));
        }

        return datalist;
    }

    @Override
    public Complain find(String s) throws Exception {
        return null;
    }

    @Override
    public boolean save(Complain entity) throws Exception {
         boolean b =CrudUtil.execute("INSERT INTO complain VALUES(?,?)", entity.getSenderId(),entity.getBlockerId());
         return b;
    }

    @Override
    public boolean update(Complain entity) throws Exception {
        return false;
    }


    @Override
    public boolean delete(String s) throws Exception {
        boolean b = CrudUtil.execute("DELETE FROM complain WHERE blockerId = ?", s);
        return b;
    }
}
