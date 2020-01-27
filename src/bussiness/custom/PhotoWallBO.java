package bussiness.custom;

import bussiness.SuperBO;
import dto.PhotoWallDTO;

import java.util.List;


public interface PhotoWallBO extends SuperBO {
    boolean savePhoto(PhotoWallDTO save) throws Exception;
    String getLastId() throws Exception;
    List<PhotoWallDTO> findAll() throws Exception;
    List<PhotoWallDTO> findUserPhoto(String userId) throws Exception;
    boolean delete(String id) throws Exception;
}
