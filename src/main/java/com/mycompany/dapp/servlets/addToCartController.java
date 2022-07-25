/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dapp.servlets;

//import com.mysql.cj.x.protobuf.MysqlxCrud.Order;
import com.google.gson.Gson;
import com.razorpay.RazorpayClient;
import com.razorpay.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author hp
 */
public class addToCartController extends HttpServlet {

   
   
    @Override
  //  @ResponseBody
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
       // processRequest(request, response);
        
      //response.getWriter().print(request.getParameter("amount"));
  int data =Integer.parseInt(request.getParameter("amount"));
        System.out.println(data);
        RazorpayClient razorpayClient = null;
       
        try {
            razorpayClient = new RazorpayClient("rzp_test_l7oRc3ZHTPmvSG", "Os3zxTaWE2eCe9vjkBGuoJD8");
        } catch (RazorpayException ex) {
            Logger.getLogger(addToCartController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
JSONObject options = new JSONObject();
options.put("amount", data*100);
options.put("currency", "INR");
options.put("receipt", "txn_123456");
        try {
            Order order = razorpayClient.Orders.create(options);
               
            // System.out.println("hello");
        } catch (RazorpayException ex) {
            Logger.getLogger(addToCartController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
  

        
        
    } 

    
 

}
