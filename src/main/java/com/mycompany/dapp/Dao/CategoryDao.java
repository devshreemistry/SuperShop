/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dapp.Dao;

import com.mycompany.dapp.entities.Category;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author hp
 */
public class CategoryDao {
    
    private SessionFactory factory;
    public CategoryDao(SessionFactory factory){
        this.factory=factory;
    
    
    }
   public int saveCategory(Category category){
   
        Session session = this.factory.openSession();
        Transaction tx=session.beginTransaction();
        
       int catId= (int)session.save(category);
        tx.commit();
        session.close();
   return catId;
   } 
   
   public List<Category> getCategory(){
   
   Session session=this.factory.openSession();
     Query query=session.createQuery("from Category");
        List<Category> list = query.list();
      
     return list;
   } 
   
   public Category getCategoryById(int cid){
       Category cat=null;
   try{
   Session session=this.factory.openSession();
    cat=session.get(Category.class,cid);
   
   
   
   
   }catch(Exception e){
   
   e.printStackTrace();
   
   }
   
   return cat;
   }
   
   
    
}
