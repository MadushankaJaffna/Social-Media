package bussiness.custom;

import bussiness.SuperBO;
import dto.FindRollDTO;
import dto.LoginDTO;

public interface LoginBO extends SuperBO {
    boolean compare(String email,String password) throws Exception;
    boolean save(LoginDTO log)throws Exception;
    String getId() throws Exception;
    boolean getRoll() throws Exception;
    FindRollDTO find(String id) throws Exception;
    boolean update(FindRollDTO logdetails) throws Exception;
}
