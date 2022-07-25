

package com.mycompany.dapp.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Shop {
    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int myOrderId;
    private String OrderId;
     private String paymentId;
    private int totalAmount;
    private String userName;
    private String phone;
    private String shipingAddress;
   
   

    public Shop() {
        super();
    }

    public Shop(int myOrderId, String OrderId, String paymentId, int totalAmount, String userName, String phone, String shipingAddress) {
        this.myOrderId = myOrderId;
        this.OrderId = OrderId;
        this.paymentId = paymentId;
        this.totalAmount = totalAmount;
        this.userName = userName;
        this.phone = phone;
        this.shipingAddress = shipingAddress;
    }

    public int getMyOrderId() {
        return myOrderId;
    }

    public void setMyOrderId(int myOrderId) {
        this.myOrderId = myOrderId;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String OrderId) {
        this.OrderId = OrderId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getShipingAddress() {
        return shipingAddress;
    }

    public void setShipingAddress(String shipingAddress) {
        this.shipingAddress = shipingAddress;
    }

    @Override
    public String toString() {
        return "Shop{" + "myOrderId=" + myOrderId + ", OrderId=" + OrderId + ", paymentId=" + paymentId + ", totalAmount=" + totalAmount + ", userName=" + userName + ", phone=" + phone + ", shipingAddress=" + shipingAddress + '}';
    }

   
   
   
}
