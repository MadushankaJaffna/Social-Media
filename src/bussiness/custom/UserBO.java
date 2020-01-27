package bussiness.custom;

import bussiness.SuperBO;
import dto.SearchDTO;
import dto.UserDTO;

import java.util.List;

public interface UserBO extends SuperBO {
    List<UserDTO> findAll() throws Exception ;
    UserDTO find(String Id) throws  Exception;
    boolean update(UserDTO dto) throws Exception;
    boolean deleteUser(String id) throws Exception;
    List<SearchDTO> search(String text) throws Exception;
    String findId(String name) throws Exception;
}
