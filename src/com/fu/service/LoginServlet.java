/**
 * 
 */
package com.fu.service;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fu.dao.UserDao;
import com.fu.model.User;
import com.fu.util.DbUtil;

/**
 @author： fu    @time：2018年10月27日 上午8:03:35 
 @说明： 一份耕耘，一份收获
**/
public class LoginServlet extends HttpServlet {

	DbUtil db=new DbUtil();
	UserDao userDao=new UserDao();
	
	/*
	 * 
	 */
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		Connection con=null;
		
		try {
			User user=new User(username,password);
			con=db.getCon();
			User currentUser=userDao.login(con, user);
			if (currentUser==null) {
				req.setAttribute("error", "用户名或者密码错误");
				req.setAttribute("username", username);
				req.setAttribute("password", password);
				req.getRequestDispatcher("login.jsp").forward(req, resp);
				
			}else {
				HttpSession session=req.getSession();
				session.setAttribute("currentUser", currentUser);
				resp.sendRedirect("success.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
