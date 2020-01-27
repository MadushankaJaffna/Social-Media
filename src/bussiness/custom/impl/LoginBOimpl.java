package bussiness.custom.impl;

import bussiness.custom.LoginBO;
import cript.LikerCript;
import dao.DAOFactory;
import dao.DAOType;
import dao.custom.LoginDAO;
import dto.FindRollDTO;
import dto.LoginDTO;
import entity.Login;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class LoginBOimpl implements LoginBO {

   private LoginDAO loginDAO = DAOFactory.getInstance().getDAO(DAOType.Login);
    private static String userId;
    private static boolean roll;
    @Override
    public boolean compare(String email,String password) {
        // System.out.println(password);
        // String encode = LikerCript.encode(password, "123");
        // System.out.println(encode);
        try {
            Login login = loginDAO.find(email);
             userId=login.getUid();
             roll = login.isRoll();
            //  System.out.println(login.getPassword());
            String decode = LikerCript.decode(login.getPassword(), "123");
            //  System.out.println(decode);
            if (!decode.equals(password) || !login.getEmail().equals(email)) {

                if (!login.getEmail().equals(email)) {
                    new Alert(Alert.AlertType.ERROR, "Your email not Correct", ButtonType.OK).show();
                } else if (!decode.equals(password)) {
                    new Alert(Alert.AlertType.ERROR, "Your Password is Incorrect", ButtonType.OK).show();
                }
                return false;
            } else {
                return true;
            }
        }catch (NullPointerException e){
            new Alert(Alert.AlertType.WARNING,"You entered email is not Valid please try again",ButtonType.OK).show();
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean save(LoginDTO log) throws Exception {
        String encode = LikerCript.encode(log.getPassword(), "123");
       return loginDAO.save(new Login(log.getUserId(),log.getEmail(),encode,false));
    }

    @Override
    public String getId() throws Exception {
        return userId;
    }

    @Override
    public boolean getRoll() throws Exception {
        return roll;
    }

    @Override
    public FindRollDTO find(String id) throws Exception {
        Login roll = loginDAO.findRoll(id);
        return new FindRollDTO(roll.getUid(),roll.isRoll());
    }

    @Override
    public boolean update(FindRollDTO logdetails) throws Exception {
        boolean update = loginDAO.update(new Login(logdetails.getUserId(), logdetails.getRoll()));
        return update;
    }
}
