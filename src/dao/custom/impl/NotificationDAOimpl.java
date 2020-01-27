package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.NotificationDAO;
import entity.Notification;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NotificationDAOimpl implements NotificationDAO {


    @Override
    public List<Notification> findAll() throws Exception {
        return null;
    }

    @Override
    public Notification find(String s) throws Exception {
        return null;
    }

    @Override
    public boolean save(Notification entity) throws Exception {
        return   CrudUtil.execute("INSERT INTO notification VALUES (?,?,?,?)",
                entity.getSenderId(),
                entity.getUserId(),
                entity.getDate(),
                entity.getPostId());

    }

    @Override
    public boolean update(Notification entity) throws Exception {
        return false;
    }


    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtil.execute("DELETE FROM notification where postId=?", s);
    }

    @Override
    public List<Notification> findall(String userId) throws Exception {
        ResultSet execute = CrudUtil.execute("SELECT * FROM notification WHERE userID=?", userId);
        List<Notification> send = new ArrayList<>();
        while (execute.next()){
            send.add(new Notification(execute.getString(1),
                    execute.getString(2),
                    execute.getDate(3),
                    execute.getString(4)));
        }
        return send;
    }

    @Override
    public Notification findallLiked(String postId) throws Exception {
        ResultSet risult = CrudUtil.execute("SELECT senderId FROM notification WHERE postId=?", postId);
        if (risult.next()){
            return new Notification(risult.getString(1));
        }
        return null;

    }
}
