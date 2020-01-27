package bussiness.custom.impl;

import bussiness.custom.ComplainBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.custom.ComplainDAO;
import dto.ComplainDTO;
import entity.Complain;

import java.util.ArrayList;
import java.util.List;


public class ComplainBOimpl implements ComplainBO {

    private ComplainDAO complainDAO = DAOFactory.getInstance().getDAO(DAOType.Complain);

    @Override
    public boolean save(ComplainDTO complain) throws Exception {
        boolean save = complainDAO.save(new Complain(complain.getSenderId(), complain.getBlockerId()));
        return save;
    }

    @Override
    public boolean Delete(String Id) throws Exception {
        boolean delete = complainDAO.delete(Id);
        return delete;
    }

    @Override
    public List<ComplainDTO> findAll() throws Exception {
        List<Complain> all = complainDAO.findAll();
        List<ComplainDTO> datalist = new ArrayList<>();
        for (Complain data:
             all) {
            datalist.add(new ComplainDTO(data.getSenderId(),data.getBlockerId()));
        }
        return datalist;
    }
}
