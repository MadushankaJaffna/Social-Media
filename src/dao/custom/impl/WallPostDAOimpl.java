package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.WallPostDAO;
import entity.AdminNote;
import entity.WallPost;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class WallPostDAOimpl implements WallPostDAO {

    @Override
    public List<WallPost> findAll() throws Exception {
        ResultSet execute = CrudUtil.execute("SELECT * FROM wallPost");
        List<WallPost> post = new ArrayList<>();
        while (execute.next()){
            post.add(new WallPost(execute.getString(1),
                    execute.getInt(2),
                    execute.getString(3),
                    execute.getString(4),
                    execute.getDate(5)));
        }
        return post;
    }

    @Override
    public WallPost find(String s) throws Exception {
        return null;
    }

    @Override
    public boolean save(WallPost entity) throws Exception {
       return CrudUtil.execute("INSERT INTO wallPost VALUES (?,?,?,?,?)",entity.getPostId()
        ,entity.getReactCount(),entity.getUserId(),entity.getDescriotion(),entity.getDate());

    }

    @Override
    public boolean update(WallPost entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public String getlastIdwall() throws Exception {
        ResultSet execute = CrudUtil.execute("SELECT postID FROM wallPost ORDER BY postId DESC LIMIT 1");
        if(execute.next()){
            return execute.getString(1);
        }
        return null;
    }
}
