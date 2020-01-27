package bussiness.custom;

import bussiness.SuperBO;
import dto.CivilStateDTO;
import dto.EducationStateDTO;
import dto.UserDTO;

import java.util.List;

public interface RegisterBO extends SuperBO {
   boolean saveUser(UserDTO userDTO) throws Exception;
   List<CivilStateDTO> civilState()throws Exception;
   List<EducationStateDTO> educationState() throws Exception;
   String getLastUid() throws Exception;
}
