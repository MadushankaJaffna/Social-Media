package bussiness.custom.impl;

import bussiness.custom.RegisterBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.custom.CivilStatusDAO;
import dao.custom.EducationStateDAO;
import dao.custom.UserDAO;
import dto.CivilStateDTO;
import dto.EducationStateDTO;
import dto.UserDTO;
import entity.CivilStatus;
import entity.EducationState;
import entity.User;
import java.util.ArrayList;
import java.util.List;

public class RegisterBOimpl implements RegisterBO {
    private CivilStatusDAO civilStatusDAO = DAOFactory.getInstance().getDAO(DAOType.CivilStatus);
    private EducationStateDAO educationStateDAO = DAOFactory.getInstance().getDAO(DAOType.EducationState);
    private UserDAO userDAO = DAOFactory.getInstance().getDAO(DAOType.User);


    @Override
    public boolean saveUser(UserDTO userDTO) throws Exception {
      return userDAO.save(new User(userDTO.getUserID(),userDTO.getName(),userDTO.getGender(),userDTO.getEmail()
              ,userDTO.getPhoneNo(),userDTO.getCivilStatus(),userDTO.getDescription()
              ,userDTO.getProPic(),userDTO.getAddress(),userDTO.getHomeTown(),userDTO.getBirthDay(),
               userDTO.getWorking(),userDTO.getEducation()));
    }

    @Override
    public List<CivilStateDTO> civilState() throws Exception {
        List<CivilStatus> all = civilStatusDAO.findAll();
        List<CivilStateDTO> sendAll = new ArrayList<>();
        for(CivilStatus civS : all){
            sendAll.add(new CivilStateDTO(
                    civS.getCivilStatus()
            ));
        }
        return sendAll;
    }

    @Override
    public List<EducationStateDTO> educationState() throws Exception {
        List<EducationState> all = educationStateDAO.findAll();
        List<EducationStateDTO> sendAll = new ArrayList<>();
        for(EducationState eduS :all){
            sendAll.add(new EducationStateDTO(eduS.getEducation()));
        }
        return sendAll;
    }

    @Override
    public String getLastUid() throws Exception {
        return userDAO.getLastCustomerID();
    }
}
