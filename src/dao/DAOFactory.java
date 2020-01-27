package dao;

import dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){

    }
    public static DAOFactory getInstance(){

        return (daoFactory==null)?(daoFactory=new DAOFactory()):daoFactory;
    }


    public <T extends SuperDAO> T getDAO(DAOType daoType){
        switch (daoType){
            case User:
                return (T) new UserDAOimpl();
            case Login:
                return (T) new LoginDAOimpl();
            case Payment:
                return (T) new PaymentDAOimpl();
            case Complain:
                return (T) new ComplainDAOimpl();
            case WallPost:
                return (T) new WallPostDAOimpl();
            case AdminNote:
                return (T) new AdminNoteDAOimpl();
            case PhotoWall:
                return (T) new PhotoWallDAOimpl();
            case CivilStatus:
                return (T) new CivilStatusDAOimpl();
            case Notification:
                return (T) new NotificationDAOimpl();
            case EducationState:
                return (T) new EducationStateDAOimpl();
            case adminNote:
                return (T) new AdminNoteDAOimpl();
            default:
                return null;
        }
    }
}
