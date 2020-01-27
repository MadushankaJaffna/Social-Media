package bussiness.custom;

import bussiness.SuperBO;
import dto.PaymentDTO;

import java.util.List;

public interface PaymentBO extends SuperBO {
   boolean save(PaymentDTO payment) throws Exception;
   List<PaymentDTO> findAll() throws Exception;
   boolean clear() throws Exception;
}
