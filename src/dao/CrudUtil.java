package dao;

import db.DBConnection;

import java.awt.*;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class CrudUtil {
    static int i=0;
    static Connection connection = DBConnection.getInstance().getConnection();


    public static <T>T execute(String sql,Object... params) throws Exception{
        PreparedStatement pstm  = connection.prepareStatement(sql);
        for (i=0 ; i < params.length; i++) {
            if(params[i] instanceof InputStream){
                pstm.setBinaryStream((i + 1), (InputStream) params[i]);
            }
            else{
            pstm.setObject((i + 1), params[i]);
             }
        }
        if (sql.startsWith("SELECT")){
            return (T) pstm.executeQuery();
        }
        return (T)((Boolean) (pstm.executeUpdate() > 0));
    }

}
