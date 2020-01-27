package bussiness.custom;

import bussiness.SuperBO;
import dto.PostWallDTO;

import java.util.List;

public interface PostWallBO extends SuperBO {
    List<PostWallDTO> findAll() throws Exception;
    boolean save(PostWallDTO savePost) throws Exception;
    String getLastIdwall() throws Exception;
}
