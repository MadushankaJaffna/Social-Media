package bussiness.custom;

import bussiness.SuperBO;
import dto.AdminNoteDTO;
import dto.CivilStateDTO;
import dto.EducationStateDTO;

import java.util.List;

public interface AdminWorkBO extends SuperBO {
 List<EducationStateDTO> findAll() throws Exception;
 boolean delete(String s) throws Exception;
 boolean add(EducationStateDTO s) throws Exception;

 List<CivilStateDTO> findAllC() throws Exception;
 boolean deleteC(String s) throws Exception;
 boolean addC(CivilStateDTO s) throws Exception;

 List<AdminNoteDTO> findNote() throws Exception;
 boolean deleteNote(String s) throws Exception;
 boolean addNOte(AdminNoteDTO s) throws Exception;

}