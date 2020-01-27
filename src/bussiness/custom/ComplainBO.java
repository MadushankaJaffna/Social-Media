package bussiness.custom;

import bussiness.SuperBO;
import dto.ComplainDTO;

import java.util.List;

public interface ComplainBO extends SuperBO {
    boolean save(ComplainDTO complain) throws Exception;
    boolean Delete(String senderId) throws Exception;
    List<ComplainDTO> findAll() throws Exception;
}
