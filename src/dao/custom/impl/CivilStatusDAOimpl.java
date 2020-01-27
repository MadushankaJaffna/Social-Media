package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.CivilStatusDAO;
import entity.CivilStatus;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CivilStatusDAOimpl implements CivilStatusDAO {
    @Override
    public List<CivilStatus> findAll() throws Exception {
        ResultSet execute = CrudUtil.execute("SELECT * FROM civilstatus");
        List<CivilStatus> CS = new ArrayList<>();
        while (execute.next()){
            CS.add(new CivilStatus(execute.getString(1)));
        }
        return CS;
    }

    @Override
    public CivilStatus find(String s) throws Exception {
        return null;
    }

    @Override
    public boolean save(CivilStatus entity) throws Exception {
        return CrudUtil.execute("INSERT INTO civilstatus VALUE (?)",entity.getCivilStatus());
    }

    @Override
    public boolean update(CivilStatus entity) throws Exception {
        return false;
    }


    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtil.execute("DELETE FROM civilstatus WHERE civilState=?",s);
    }
}
