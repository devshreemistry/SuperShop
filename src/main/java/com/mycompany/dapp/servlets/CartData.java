/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dapp.servlets;

import com.mycompany.dapp.entities.Cart;
import com.mycompany.dapp.helper.FactoryProvider;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author hp
 */
public class CartData extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
          Cart cart =new Cart();
            
        Session hibernateSession = FactoryProvider.getFactory().openSession() ;
         Transaction tx= hibernateSession.beginTransaction();
        String jasondata = request.getParameter("para");
        
        
        
    try{
             JSONParser jasonparser = new JSONParser();
   
            Object obj= jasonparser.parse(jasondata);
            JSONArray cartarray=(JSONArray)obj;
            for(int i=0;i<cartarray.length();i++){
      
      JSONObject product=(JSONObject)cartarray.get(i);
      String productId=(String)product.get("productId");
       String productQuantity=(String)product.get("productQuantity");
      
     // System.out.println("productId="+productId);
       
     cart.setProductId(productId);
     cart.setProductQuantity(productQuantity);
        hibernateSession.save(cart);
           
        } 
         tx.commit();
          hibernateSession.close();
        
        
    }catch(ParseException ex){
    ex.printStackTrace();
    }
       
         
        
        
            
        
        
        
        
        
        
        
        
        
        
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CartData</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CartData at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
