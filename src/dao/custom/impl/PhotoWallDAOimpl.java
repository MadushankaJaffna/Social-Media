package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.PhotoWallDAO;
import entity.PhotoWall;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PhotoWallDAOimpl implements PhotoWallDAO {
    @Override
    public List<PhotoWall> findAll() throws Exception {
        ResultSet execute = CrudUtil.execute("SELECT * FROM photoWall");
        List<PhotoWall> photos = new ArrayList<>();
        while (execute.next()){
            photos.add(new PhotoWall(execute.getString(1),
                    execute.getString(2),
                    execute.getBinaryStream(3),
                    execute.getInt(4),
                    execute.getDate(5)));
        }
        return photos;
    }

    @Override
    public PhotoWall find(String s) throws Exception {
        return null;
    }

    @Override
    public boolean save(PhotoWall entity) throws Exception {
       return CrudUtil.execute("INSERT INTO photoWall VALUES (?,?,?,?,?)",entity.getUserId(),
                entity.getPostId(),entity.getImage(),entity.getReact(),entity.getDate());
    }

    @Override
    public boolean update(PhotoWall entity) throws Exception {
        return false;
    }


    @Override
    public boolean delete(String postId) throws Exception {
        return CrudUtil.execute("DELETE FROM photoWall WHERE postID=?", postId);
    }

    @Override
    public String getLastId() throws Exception {
        ResultSet ris = CrudUtil.execute("SELECT postID FROM photoWall ORDER BY postID DESC LIMIT 1 ");
        if(ris.next()){
            return ris.getString(1);
        }
        return null;
    }

    @Override
    public List<PhotoWall> getUserPhoto(String userId) throws Exception {
        ResultSet execute = CrudUtil.execute("SELECT * FROM photoWall WHERE userID=?", userId);
        List<PhotoWall> send =  new ArrayList<>();
        while (execute.next()){
            send.add(new PhotoWall(execute.getString(1),
                    execute.getString(2),
                    execute.getBinaryStream(3),
                    execute.getInt(4),
                    execute.getDate(5)));
        }
    return send;
    }


}
