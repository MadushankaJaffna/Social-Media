package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.PaymentDAO;
import entity.Payment;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAOimpl implements PaymentDAO {
    @Override
    public List<Payment> findAll() throws Exception {
        ResultSet execute = CrudUtil.execute("SELECT * FROM payment");
        List<Payment> pay = new ArrayList<>();
        while (execute.next()){
            pay.add(new Payment(execute.getString(1),
                    execute.getString(2),
                    execute.getLong(3),
                    execute.getDate(4),
                    execute.getDouble(5)));
        }
        return pay;
    }

    @Override
    public Payment find(String s) throws Exception {
        return null;
    }

    @Override
    public boolean save(Payment entity) throws Exception {
       return CrudUtil.execute("INSERT INTO payment VALUES (?,?,?,?,?)",entity.getUserId(),
               entity.getName(),entity.getCardno(),entity.getDate(),entity.getPrice());
    }

    @Override
    public boolean update(Payment entity) throws Exception {
        return false;
    }


    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public boolean clear() throws Exception {
        boolean x= CrudUtil.execute("DELETE FROM payment ");
        return x;
    }
}
