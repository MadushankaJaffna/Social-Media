package bussiness.custom.impl;

import bussiness.custom.NotificationBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.custom.AdminNoteDAO;
import dao.custom.NotificationDAO;
import dto.NotificationDTO;
import entity.Notification;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NotificationBOimpl implements NotificationBO {
    private NotificationDAO notificationDAO = DAOFactory.getInstance().getDAO(DAOType.Notification);

    @Override
    public List<NotificationDTO> find(String userId) throws Exception {
        List<Notification> findall = notificationDAO.findall(userId);
        List<NotificationDTO> data = new ArrayList<>();
        for (Notification send: findall) {
            data.add(new NotificationDTO(send.getSenderId(),send.getUserId(),send.getPostId()));
        }
        return data;
    }


    @Override
    public boolean save(NotificationDTO data) throws Exception {

        boolean save = notificationDAO.save(new Notification(data.getSenderId(), data.getUserId(),Date.valueOf(LocalDate.now()),data.getPostId()));
        return save;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return notificationDAO.delete(s);
    }

    @Override
    public NotificationDTO findpost(String postId){
        Notification notifications = null;
        try {
            notifications = notificationDAO.findallLiked(postId);
        }catch (NullPointerException e){

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new NotificationDTO(notifications.getSenderId());
    }
}
