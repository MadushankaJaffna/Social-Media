package bussiness.custom.impl;

import bussiness.custom.AdminWorkBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.custom.AdminNoteDAO;
import dao.custom.CivilStatusDAO;
import dao.custom.EducationStateDAO;
import dto.AdminNoteDTO;
import dto.CivilStateDTO;
import dto.EducationStateDTO;
import entity.AdminNote;
import entity.CivilStatus;
import entity.EducationState;

import java.util.ArrayList;
import java.util.List;

public class AdminWorkWorkBOimpl implements AdminWorkBO {
    private AdminNoteDAO adminNoteDAO = DAOFactory.getInstance().getDAO(DAOType.adminNote);
    private CivilStatusDAO civilStatusDAO = DAOFactory.getInstance().getDAO(DAOType.CivilStatus);
    private EducationStateDAO educationStatedao = DAOFactory.getInstance().getDAO(DAOType.EducationState);


    @Override
    public List<EducationStateDTO> findAll() throws Exception {
        List<EducationStateDTO> data = new ArrayList<>();
        List<EducationState> all = educationStatedao.findAll();
        for (EducationState sate :
                all) {
            data.add(new EducationStateDTO(sate.getEducation()));
        }
        return data;
    }

    @Override
    public boolean delete(String s) throws Exception {
        boolean delete = educationStatedao.delete(s);
        return delete;
    }

    @Override
    public boolean add(EducationStateDTO s) throws Exception {
        EducationState educationState = new EducationState();
        educationState.setEducation(s.getEducation());
        boolean save = educationStatedao.save(educationState);
        return save;

    }

    @Override
    public List<CivilStateDTO> findAllC() throws Exception {
        List<CivilStateDTO> data = new ArrayList<>();
        List<CivilStatus> all = civilStatusDAO.findAll();
        for (CivilStatus sate :
                all) {
            data.add(new CivilStateDTO(sate.getCivilStatus()));
        }
        return data;
    }

    @Override
    public boolean deleteC(String s) throws Exception {
        boolean delete = civilStatusDAO.delete(s);
        return delete;
    }

    @Override
    public boolean addC(CivilStateDTO s) throws Exception {
        CivilStatus civilStatus = new CivilStatus();
        civilStatus.setCivilStatus(s.getCivilStatus());
        boolean save = civilStatusDAO.save(civilStatus);
        return save;
    }

    @Override
    public List<AdminNoteDTO> findNote() throws Exception {
        List<AdminNoteDTO> data = new ArrayList<>();
        List<AdminNote> all = adminNoteDAO.findAll();
        for (AdminNote sate : all) {
            data.add(new AdminNoteDTO(sate.getNote()));
        }
        return data;
    }

    @Override
    public boolean deleteNote(String s) throws Exception {
        boolean delete = adminNoteDAO.delete(s);
        return delete;
    }

    @Override
    public boolean addNOte(AdminNoteDTO s) throws Exception {
        AdminNote adminNote = new AdminNote();
        adminNote.setNote(s.getNote());
        boolean save = adminNoteDAO.save(adminNote);
        return save;
    }
}