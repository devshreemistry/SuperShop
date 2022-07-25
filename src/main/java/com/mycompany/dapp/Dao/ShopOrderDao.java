
package com.mycompany.dapp.Dao;

import com.mycompany.dapp.entities.Products;
import com.mycompany.dapp.entities.Shop;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ShopOrderDao {
  
    private SessionFactory factory;
    public ShopOrderDao(SessionFactory factory){
        this.factory=factory;
    
    
    }  
   
}
