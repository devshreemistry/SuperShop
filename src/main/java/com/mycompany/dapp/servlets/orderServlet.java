/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dapp.servlets;


import com.mycompany.dapp.Dao.ShopOrderDao;
import com.mycompany.dapp.entities.Shop;
import com.mycompany.dapp.entities.User;
import com.mycompany.dapp.helper.FactoryProvider;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.*;
import com.razorpay.*;
import static java.lang.System.out;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author hp
 */
public class orderServlet extends HttpServlet {

    public orderServlet(){
    super();
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
         
            
        
        
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet orderServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet orderServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
       
       // String user_phone=request.getParameter("user_phone");
        int amt = Integer.parseInt(request.getParameter("amount"));
       // String phone = request.getParameter("name");
    //int amt=30;
        RazorpayClient razorpayClient = null;
        String orderId = null;
        try {
            razorpayClient = new RazorpayClient("rzp_test_l7oRc3ZHTPmvSG", "Os3zxTaWE2eCe9vjkBGuoJD8");

            JSONObject options = new JSONObject();
            options.put("amount", amt*100);
            options.put("currency", "INR");
            options.put("receipt", "txn_123456");
            options.put("payment_capture",true);
            Order order = razorpayClient.Orders.create(options);
            orderId=order.get("id");
          
            
            
           
            
        } catch (RazorpayException ex) {
          //  Logger.getLogger(orderServlet.class.getName()).log(Level.SEVERE, null, ex);
          ex.printStackTrace();
        }
        
          response.getWriter().append(orderId); 
            
       
 
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RazorpayClient client=null;
        try {
            client = new RazorpayClient("rzp_test_l7oRc3ZHTPmvSG", "Os3zxTaWE2eCe9vjkBGuoJD8");
            JSONObject options = new JSONObject();
            options.put("razorpay_payment_id",request.getParameter("razorpay_payment_id"));
            options.put("razorpay_order_id",request.getParameter("razorpay_order_id"));
            options.put("razor_signature",request.getParameter("razor_signature")); 
            boolean SigRes = Utils.verifyPaymentSignature(options,"Os3zxTaWE2eCe9vjkBGuoJD8");
            if(SigRes){
            response.getWriter().append("payments successful and signature verified");
            }else{
             response.getWriter().append("payments failed and signature not verified");
            }
            
            
        } catch (RazorpayException ex) {
           // Logger.getLogger(orderServlet.class.getName()).log(Level.SEVERE, null, ex);
           ex.printStackTrace();
        }
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
