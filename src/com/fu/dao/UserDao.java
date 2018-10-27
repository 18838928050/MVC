/**
 * 
 */
package com.fu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fu.model.User;
import com.fu.util.DbUtil;

/**
 @author： fu    @time：2018年10月27日 上午7:22:02 
 @说明： 一份耕耘，一份收获
**/
public class UserDao extends DbUtil {

	public User	 login(Connection con,User user) throws SQLException {
		User resultUser=null;
		        String sql="select * from user where username=? and password=?";
		        PreparedStatement ps=con.prepareStatement(sql);//
		        ps.setString(1, user.getUsername());
		        ps.setString(2, user.getPassword());
		        ResultSet rs=ps.executeQuery();
		        if(rs.next()){
		            resultUser=new User();
		            resultUser.setUsername(rs.getString("username"));
		            resultUser.setPassword(rs.getString("password"));
		        }
		        return resultUser;
		    }
}
