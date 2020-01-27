package bussiness.custom.impl;

import bussiness.custom.PostWallBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.custom.WallPostDAO;
import dto.PostWallDTO;
import entity.WallPost;

import java.util.ArrayList;
import java.util.List;

public class PostWallBOimpl implements PostWallBO {
  private WallPostDAO wallPostDAO = DAOFactory.getInstance().getDAO(DAOType.WallPost);
    @Override
    public List<PostWallDTO> findAll() throws Exception {
        List<PostWallDTO> post = new ArrayList<>();
        List<WallPost> all = wallPostDAO.findAll();
        for (WallPost data:all) {
            post.add(new PostWallDTO(data.getPostId(),data.getReactCount(),data.getUserId(),data.getDescriotion(),data.getDate()));
        }
    return post;
    }

    @Override
    public boolean save(PostWallDTO savePost) throws Exception {
        String lastId = getLastIdwall();
        String newlastId = null;
        if (lastId ==null) {
            newlastId="WP:001";
        } else {

            String[] split = lastId.split(":");
            int s = Integer.parseInt(split[1]);
            s++;
            if(s<10){ newlastId ="WP:00" + s; }
            else if(s<100){ newlastId = "WP:0" + s;}
            else if(s<1000){ newlastId = "WP:" + s;}
        }


        boolean save = wallPostDAO.save(new WallPost(newlastId, savePost.getReactCount(),
                savePost.getUserId(), savePost.getDescriotion(), savePost.getDate()));
        return save;
    }

    @Override
    public String getLastIdwall() throws Exception {
        String s = wallPostDAO.getlastIdwall();
        return s;
    }
}
