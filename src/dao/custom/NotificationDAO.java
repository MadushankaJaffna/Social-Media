package dao.custom;

import dao.CrudDAO;
import entity.Notification;

import java.util.List;

public interface NotificationDAO extends CrudDAO<Notification,String> {
    Notification findallLiked(String userId) throws Exception;
    List<Notification> findall(String userId) throws Exception;

}
