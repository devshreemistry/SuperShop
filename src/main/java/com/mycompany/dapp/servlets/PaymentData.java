
package com.mycompany.dapp.servlets;

import com.mycompany.dapp.Dao.ProductDao;
import com.mycompany.dapp.Dao.ShopOrderDao;
import com.mycompany.dapp.entities.Cart;
import com.mycompany.dapp.entities.Products;
import com.mycompany.dapp.entities.Shop;
import com.mycompany.dapp.entities.User;
import com.mycompany.dapp.helper.FactoryProvider;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class PaymentData extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
          
        response.setContentType("text/html;charset=UTF-8");
      
    //  String jasondata = request.getParameter("para");
      
     HttpSession httpSession=request.getSession();
        User user = (User) httpSession.getAttribute("current_user");
        String userPhone = user.getUserPhone();
      
     int amount = Integer.parseInt(request.getParameter("amount"));
     String id =request.getParameter("orderId");
        String paymentId=  request.getParameter("paymentId");
          String shippingAddress=  user.getUserAddress();
         
   
        
         Shop shopOrder=new Shop();
           shopOrder.setOrderId(id);
            shopOrder.setTotalAmount(amount);
            shopOrder.setPhone( userPhone);
            shopOrder.setPaymentId(paymentId);
            shopOrder.setShipingAddress(shippingAddress);
       
           Session hibernateSession = FactoryProvider.getFactory().openSession() ;
         Transaction tx= hibernateSession.beginTransaction();
        hibernateSession.save(shopOrder);
        
             tx.commit();
          hibernateSession.close();
//        
//        Cart cart =new Cart();
//             
//      try{
//      JSONParser jasonparser = new JSONParser();
//            Object obj= jasonparser.parse(jasondata);
//            //JSONArray array = new JSONArray();
//          // JSONObject d=(JSONObject)obj;
//      JSONArray cartarray=(JSONArray)obj;
//   
//    
//    
//      for(int i=0;i<cartarray.length();i++){
//      
//      JSONObject product=(JSONObject)cartarray.get(i);
//      String productId=(String)product.get("productId");
//       String productQuantity=(String)product.get("productQuantity");
//      
//     // System.out.println("productId="+productId);
//       
//     cart.setProductId(productId);
//     cart.setProductQuantity(productQuantity);
//     hibernateSession.save(cart);
//      }
//      
     
//     // 
//        } catch (ParseException ex) {
//            Logger.getLogger(PaymentData.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        
             
         
         
          

        
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PaymentData</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PaymentData at " + request.getContextPath() + "</h1>");
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
