package dao.custom;

import dao.CrudDAO;
import entity.AdminNote;
import entity.WallPost;

public interface WallPostDAO extends CrudDAO<WallPost,String> {
    String getlastIdwall() throws Exception;
}
