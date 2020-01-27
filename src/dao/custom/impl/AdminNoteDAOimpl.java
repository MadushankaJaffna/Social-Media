package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.AdminNoteDAO;
import entity.AdminNote;
import entity.CivilStatus;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdminNoteDAOimpl implements AdminNoteDAO {
    @Override
    public List<AdminNote> findAll() throws Exception {
        ResultSet execute = CrudUtil.execute("SELECT * FROM adminNote");
        List<AdminNote> CS = new ArrayList<>();
        while (execute.next()){
            CS.add(new AdminNote(execute.getString(1)));
        }
        return CS;
    }

    @Override
    public AdminNote find(String s) throws Exception {
        return null;
    }

    @Override
    public boolean save(AdminNote entity) throws Exception {
        Boolean b= CrudUtil.execute("INSERT INTO adminNote VALUES (?)",entity.getNote());
        return b;
    }

    @Override
    public boolean update(AdminNote entity) throws Exception {
        return false;
    }


    @Override
    public boolean delete(String s) throws Exception {
        boolean b = CrudUtil.execute("DELETE FROM adminNote WHERE note=?",s);
        return b;
    }
}
