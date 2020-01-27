package bussiness.custom.impl;

import bussiness.custom.UserBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.custom.UserDAO;
import dto.SearchDTO;
import dto.UserDTO;
import entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserBOimpl implements UserBO {
    private UserDAO userDAO = DAOFactory.getInstance().getDAO(DAOType.User);


    @Override
    public List<UserDTO> findAll() throws Exception {
        return null;
    }

    @Override
    public UserDTO find(String Id) throws Exception {
        User user = userDAO.find(Id);
        return new UserDTO(user.getUserID(),user.getName(),user.getGender(),user.getEmail(),
                user.getPhoneNo(),user.getCivilStatus()
                ,user.getDescription(),user.getProPic(),user.getAddress(),user.getHomeTown(),
                user.getBirthDay(),user.getWorking(),user.getEducation());

    }

    @Override
    public boolean update(UserDTO dto) throws Exception {
        return userDAO.update(new User(dto.getUserID(),dto.getName(),dto.getGender(),dto.getEmail(),dto.getPhoneNo(),dto.getCivilStatus(),
                dto.getDescription(),dto.getProPic(),dto.getAddress(),dto.getHomeTown(),dto.getBirthDay(),
                dto.getWorking(),dto.getEducation()));
    }

    @Override
    public boolean deleteUser(String id) throws Exception {
        boolean delete = userDAO.delete(id);
        return delete;
    }

    @Override
    public List<SearchDTO> search(String text) throws Exception {
        List<User> search = userDAO.search(text);
        List<SearchDTO> data = new ArrayList<>();
        for (User all:search) {
                 data.add(new SearchDTO(all.getUserID(),all.getName()));
        }
        return data;
    }

    @Override
    public String findId(String name) throws Exception {
        String id = userDAO.findId(name);
        return id;
    }


}
