package bussiness.custom;

import bussiness.SuperBO;
import dto.NotificationDTO;

import java.util.List;

public interface NotificationBO extends SuperBO {
 List<NotificationDTO> find (String userId) throws Exception;
 boolean save(NotificationDTO data) throws  Exception;
 boolean delete(String s) throws Exception;
 NotificationDTO findpost(String PostId) throws Exception;
}
