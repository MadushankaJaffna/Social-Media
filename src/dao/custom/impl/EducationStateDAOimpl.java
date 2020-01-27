package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.EducationStateDAO;
import entity.EducationState;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EducationStateDAOimpl implements EducationStateDAO {
    @Override
    public List<EducationState> findAll() throws Exception {
      ResultSet risult = CrudUtil.execute("SELECT * FROM educationState");
      List<EducationState> ED = new ArrayList<>();
      while (risult.next()){
        ED.add(new EducationState(risult.getString(1)));
      }
      return ED;
    }

    @Override
    public EducationState find(String s) throws Exception {
        return null;
    }

    @Override
    public boolean save(EducationState entity) throws Exception {
        return CrudUtil.execute("INSERT INTO educationState VALUE (?)", entity.getEducation());

    }

    @Override
    public boolean update(EducationState entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtil.execute("DELETE FROM educationState WHERE education=?",s);
    }
}
