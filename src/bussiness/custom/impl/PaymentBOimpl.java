package bussiness.custom.impl;

import bussiness.custom.PaymentBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.custom.PaymentDAO;
import dto.PaymentDTO;
import entity.Payment;

import java.util.ArrayList;
import java.util.List;

public class PaymentBOimpl implements PaymentBO {

    private PaymentDAO paymentDAO = DAOFactory.getInstance().getDAO(DAOType.Payment);

    @Override
    public boolean save(PaymentDTO payment) throws Exception {
       return paymentDAO.save(new Payment(payment.getUserId(),payment.getName(),payment.getCardno(),payment.getDate(),
                payment.getPrice()));
    }

    @Override
    public List<PaymentDTO> findAll() throws Exception {
        List<Payment> all = paymentDAO.findAll();
        List<PaymentDTO> paymentlist = new ArrayList<>();
        for (Payment data:
             all) {
            paymentlist.add(new PaymentDTO(data.getUserId(),data.getName(),data.getCardno(),
                    data.getDate(),data.getPrice()));
        }
        return paymentlist;
    }

    @Override
    public boolean clear() throws Exception {
        boolean delete = paymentDAO.clear();
        return delete;
    }
}
