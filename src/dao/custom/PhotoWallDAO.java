package dao.custom;

import dao.CrudDAO;
import entity.PhotoWall;

import java.util.List;

public interface PhotoWallDAO extends CrudDAO<PhotoWall,String> {
     String getLastId() throws Exception;
     List<PhotoWall> getUserPhoto(String userId) throws Exception;
}
