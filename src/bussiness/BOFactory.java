package bussiness;

import bussiness.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){

    }

    public static BOFactory getInstance(){
        return (boFactory == null)? (boFactory = new BOFactory()): boFactory;
    }

    public <T extends SuperBO> T getBO(BOType boTypes){
        switch (boTypes){
            case Register:
                return (T) new RegisterBOimpl();
            case user:
                return (T) new UserBOimpl();
            case Login:
                return (T) new LoginBOimpl();
            case photoWall:
                return (T) new PhotoWallBOimpl();
            case postWall:
                return (T) new PostWallBOimpl();
            case payment:
                return (T) new PaymentBOimpl();
            case notification:
                return (T) new NotificationBOimpl();
            case adminWork:
                return (T) new AdminWorkWorkBOimpl();
            case complain:
                return (T) new ComplainBOimpl();
            default:
                return null;
        }
    }
}
