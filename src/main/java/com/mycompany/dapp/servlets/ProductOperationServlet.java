

package com.mycompany.dapp.servlets;

import com.mycompany.dapp.Dao.CategoryDao;
import com.mycompany.dapp.Dao.ProductDao;
import com.mycompany.dapp.entities.Category;
import com.mycompany.dapp.entities.Products;
import com.mycompany.dapp.helper.FactoryProvider;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import static org.graalvm.compiler.debug.DebugOptions.PrintGraphTarget.File;

@MultipartConfig
public class ProductOperationServlet extends HttpServlet {

    private String $;

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            String op=request.getParameter("operation");
            if(op.trim().equals("addcategory")){
             String title=request.getParameter("catTitle");
             String description= request.getParameter("catDescription");
             
             Category category=new Category();
             category.setCategoryTitle(title);
             category.setCategoryDiscription(description);
             
             CategoryDao categoryDao=new CategoryDao(FactoryProvider.getFactory());
             int catId=categoryDao.saveCategory(category);
             
            HttpSession httpsession =request.getSession();
            httpsession.setAttribute("message","Category added successfuly" );
            response.sendRedirect("admin.jsp");
             return;
            }else if(op.trim().equals("addproduct")){
            String pTitle= request.getParameter("pTitle");
            String pDecription=request.getParameter("pDescription");
            int pPrice= Integer.parseInt(request.getParameter("pPrice"));
            int PDiscount=Integer.parseInt(request.getParameter("pDiscount"));
            int pQuantity=Integer.parseInt(request.getParameter("pQuantity"));
            int catId=Integer.parseInt(request.getParameter("catId"));
            Part part=request.getPart("ppic");
            Products p= new Products();
            
            p.setProductName(pTitle);
            p.setProductDescription(pDecription);
            p.setProductPrice(pPrice);
            p.setProductDiscount(PDiscount);
            p.setProductQuantity(pQuantity);
          
            p.setProductPhoto(part.getSubmittedFileName());
            
            
             CategoryDao catDao=new CategoryDao(FactoryProvider.getFactory());
                Category category = catDao.getCategoryById(catId);
              p.setCategory(category);
              
             ProductDao productDao =new ProductDao(FactoryProvider.getFactory());
                productDao.saveProduct(p);
              
                
                
                  
      //find out path to upload photo
         try{    
      String path=request.getRealPath("images/product/"+ part.getSubmittedFileName());
      // String path=request.getRealPath("images")+File.separator+ "product" +File.separator +  part.getSubmittedFileName());
      System.out.println(path);
              //uploding the code
                FileOutputStream fos = new FileOutputStream(path);
                
                InputStream is=part.getInputStream();
                //reading data from file which is in database
                byte [] data= new byte[is.available()];
                is.read(data);
                //writing data in product folder
                fos.write(data);
                
                fos.close();
                
                
         }catch(Exception e){
         e.printStackTrace();
         
         }
      
      
              HttpSession httpsession =request.getSession();
            httpsession.setAttribute("message","Product added successfuly" );
            response.sendRedirect("admin.jsp");
             return;
             
             
             
              
            
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
        
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
