
package com.mycompany.dapp.Dao;

import com.mycompany.dapp.entities.Category;
import com.mycompany.dapp.entities.Products;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


public class ProductDao {
  
   
    
    private SessionFactory factory;
    public ProductDao(SessionFactory factory){
        this.factory=factory;
    
    
    }
   public boolean saveProduct(Products product){
   boolean f=false;
   try{
        Session session = this.factory.openSession();
        Transaction tx=session.beginTransaction();
        
      session.save(product);
        tx.commit();
        session.close();
        f=true;
   }
   catch(Exception e){
   e.printStackTrace();
   f=false;
   }
   return f;
   }
   
 public List<Products> getAllProduct(){
        Session session1 = this.factory.openSession();
        Query query = session1.createQuery("from Products");
        List<Products> list = query.list();
        return list;
 
 
 
 
 } 
    
 
  public List<Products> getAllProductsById(int cid){
        Session session1 = this.factory.openSession();
        Query query = session1.createQuery("from Products as p where p.category.categoryId=: id");
        query.setParameter("id", cid);
        List<Products> list = query.list();
        return list;
 
 
 
 
 } 
    
 
 
    
}
