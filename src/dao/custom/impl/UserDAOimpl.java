package dao.custom.impl;
import dao.CrudUtil;
import dao.custom.UserDAO;
import db.DBConnection;
import entity.User;
import net.sf.jasperreports.engine.type.BreakTypeEnum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAOimpl implements UserDAO {
    @Override
    public List<User> findAll() throws Exception {
        ResultSet set = CrudUtil.execute("SELECT * FROM user");
        List<User> users = new ArrayList<>();
        while (set.next()) {
            users.add(new User(
                    set.getString(1),
                    set.getString(2),
                    set.getString(3),
                    set.getString(4),
                    set.getInt(5),
                    set.getString(6),
                    set.getString(7),
                    set.getBinaryStream(8),
                    set.getString(9),
                    set.getString(10),
                    set.getDate(11),
                    set.getString(12),
                    set.getString(13)));
        }
        return users;
    }

    @Override
    public User find(String s) throws Exception {
        ResultSet set = CrudUtil.execute("SELECT * FROM user WHERE uId=?", s);
        if (set.next()) {
            return new User(set.getString(1),
                    set.getString(2),
                    set.getString(3),
                    set.getString(4),
                    set.getInt(5),
                    set.getString(6),
                    set.getString(7),
                    set.getBinaryStream(8),
                    set.getString(9),
                    set.getString(10),
                    set.getDate(11),
                    set.getString(12),
                    set.getString(13));


        }
        return null;
    }

    @Override
    public boolean save(User entity) throws Exception {
       boolean value = CrudUtil.execute("INSERT INTO user value (?,?,?,?,?,?,?,?,?,?,?,?,?)",
                entity.getUserID(), entity.getName(), entity.getGender(), entity.getEmail(), entity.getPhoneNo(),
                entity.getCivilStatus(), entity.getDescription(), entity.getProPic(), entity.getAddress(),
                entity.getHomeTown(), entity.getBirthDay(), entity.getWorking(), entity.getEducation());
        return value;
    }

    @Override
    public boolean update(User entity) throws Exception {
       return CrudUtil.execute("UPDATE user SET name=?,gender=?,email=?,phoneNo=?,civilStatus=?,descrip=?,proPic=?,address=?,homeTown=?,birthDay=?,working=?,education=? WHERE uId=?",
                entity.getName(),entity.getGender(),entity.getEmail(),entity.getPhoneNo(),entity.getCivilStatus(),entity.getDescription(),entity.getProPic(),entity.getAddress(),
               entity.getHomeTown(),entity.getBirthDay(),entity.getWorking(),entity.getEducation(),entity.getUserID());
    }


    @Override
    public boolean delete(String s) throws Exception {
       return CrudUtil.execute("DELETE FROM user WHERE uId=?",s);
    }

    @Override
    public String getLastCustomerID() throws Exception {
        ResultSet ris = CrudUtil.execute("SELECT uId FROM user ORDER BY uId DESC LIMIT 1");
        if(ris.next()){
            return ris.getString(1);
        }
        return null;
    }

    @Override
    public List<User> search(String text) throws Exception {
        ResultSet execute = CrudUtil.execute("SELECT * FROM user WHERE name LIKE ?", text);
        List<User> data = new ArrayList<>();
        while (execute.next()){
            data.add(new User(execute.getString(1),
                    execute.getString(2),
                    execute.getString(3),
                    execute.getString(4),
                    execute.getInt(5),
                    execute.getString(6),
                    execute.getString(7),
                    execute.getBinaryStream(8),
                    execute.getString(9),
                    execute.getString(10),
                    execute.getDate(11),
                    execute.getString(12),
                    execute.getString(13)));
        }
        return data;
    }

    @Override
    public String findId(String name) throws Exception {
        ResultSet execute = CrudUtil.execute("SELECT uId FROM user WHERE name=?", name);
        if(execute.next()){
           return execute.getString(1);
        }
        return null;
    }

}
