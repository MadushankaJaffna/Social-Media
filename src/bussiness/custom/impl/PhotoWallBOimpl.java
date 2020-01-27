package bussiness.custom.impl;

import bussiness.custom.PhotoWallBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.custom.PhotoWallDAO;
import dto.PhotoWallDTO;
import entity.PhotoWall;

import java.util.ArrayList;
import java.util.List;



public class PhotoWallBOimpl implements PhotoWallBO {
    private PhotoWallDAO photoWallDAO = DAOFactory.getInstance().getDAO(DAOType.PhotoWall);

    @Override
    public boolean savePhoto(PhotoWallDTO save) throws Exception {
        String lastId = getLastId();
        String newlastId = null;
        if (lastId ==null) {
            newlastId="P:001";
        } else {
            String[] split = lastId.split(":");
            int s = Integer.parseInt(split[1]);
            s++;
            if(s<10){ newlastId ="P:00" + s; }
            else if(s<100){ newlastId = "P:0" + s;}
            else if(s<1000){ newlastId = "P:" + s;}
        }

        boolean save1 = photoWallDAO.save(new PhotoWall(save.getUserId(),newlastId, save.getImage(), save.getReact(), save.getDate()));
    return save1;
    }

    @Override
    public String getLastId() throws Exception {
        return photoWallDAO.getLastId();
    }

    @Override
    public List<PhotoWallDTO> findAll() throws Exception {
        List<PhotoWall> all = photoWallDAO.findAll();
        List<PhotoWallDTO> photo = new ArrayList<>();
        for (PhotoWall pho:all) {
            photo.add(new PhotoWallDTO(pho.getUserId(),pho.getPostId(),pho.getImage(),
                    pho.getReact(),pho.getDate()));
        }
        return photo;
    }

    @Override
    public List<PhotoWallDTO> findUserPhoto(String userId) throws Exception {
        List<PhotoWall> userPhoto = photoWallDAO.getUserPhoto(userId);
        List<PhotoWallDTO> write = new ArrayList<>();
        for (PhotoWall photo:userPhoto) {
            write.add(new PhotoWallDTO(photo.getPostId(),photo.getUserId(),photo.getImage(),photo.getReact(),
                    photo.getDate()));
        }
        return write;
    }

    @Override
    public boolean delete(String id) throws Exception {
        boolean delete = photoWallDAO.delete(id);
        return delete;
    }
}
