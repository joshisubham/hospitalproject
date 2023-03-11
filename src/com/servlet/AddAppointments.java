/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlet;

import com.bean.AllAppointments;
import com.dao.AdminDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author user
 */
public class AddAppointments extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            AllAppointments appoint=new AllAppointments();
            appoint.setDate(Date.valueOf(request.getParameter("appdate")));
            appoint.setPid(Integer.parseInt(request.getParameter("pid")));
            appoint.setDeptid(Integer.parseInt(request.getParameter("deptid")));
            AdminDao dao=new AdminDao();
            int status=dao.insertApp(appoint);
            out.println("appointment inserted:"+status);
            String url="/HospitalRecode/authentication/admin/allappointment.jsp";
            if(status>0){
            	HttpSession session=request.getSession(false);
	            if((session==null) | session.getAttribute("a")==null){
	        		url="/HospitalRecode/authentication/adminlogin.jsp";
	        		System.out.println("you are not logged in");
	        	}else{
	        		System.out.println("you are logged in");
	        	}
            }
            response.sendRedirect(url);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AddAppointments.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
    }

    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
