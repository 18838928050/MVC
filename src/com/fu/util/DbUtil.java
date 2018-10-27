/**
 * 
 */
package com.fu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

/**
 @author： fu    @time：2018年10月26日 下午11:19:47 
 @说明： 一份耕耘，一份收获
**/
public class DbUtil {
    
    private String url="jdbc:mysql://localhost:3306/study";
    private String user="root";
    private String password="root";
    private String driver="com.mysql.jdbc.Driver";
    
    public Connection getCon() throws Exception{
        Class.forName(driver);
        Connection con=DriverManager.getConnection(url, user, password);
        return con;
    }
    
    public static void getClose(Connection con) throws SQLException{
        if(con!=null){
            con.close();
        }
    }
    
    @Test
    	public void  testDbUtil() throws Exception
		{
    	DbUtil dbUtil=new DbUtil();
    	dbUtil.getCon();
    	}
}