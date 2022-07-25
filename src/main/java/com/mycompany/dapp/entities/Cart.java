

package com.mycompany.dapp.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderno;
   private String productId;
   private String productQuantity;

    public Cart() {
    }

    public Cart(int orderno, String productId, String productQuantity) {
        this.orderno = orderno;
        this.productId = productId;
        this.productQuantity = productQuantity;
    }

    public int getOrderno() {
        return orderno;
    }

    public void setOrderno(int orderno) {
        this.orderno = orderno;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        this.productQuantity = productQuantity;
    }

    @Override
    public String toString() {
        return "Cart{" + "orderno=" + orderno + ", productId=" + productId + ", productQuantity=" + productQuantity + '}';
    }

   
}
