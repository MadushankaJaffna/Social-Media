package dao.custom;

import dao.CrudDAO;
import entity.User;

import java.util.List;

public interface UserDAO extends CrudDAO<User,String> {
    String getLastCustomerID() throws Exception;
    List<User> search(String text) throws Exception;
    String findId(String name) throws Exception;
}
