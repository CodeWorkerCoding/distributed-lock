package com.edu.nchu.distributed.domain;

import com.edu.nchu.distributed.enums.OrderStatusEnum;

//import javax.persistence.Entity;
//import javax.persistence.Table;

/**
 * Created by Alen on 2016/10/30.
 */
//@Entity
//@Table(name = "sm_dist_order")
public class Order {

    private String id;
    private String orderNo;

    private OrderStatusEnum status;

    private String amount;
    private String createTime;
    private String modifedTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getAmount() {
        return amount;
    }

    public OrderStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OrderStatusEnum status) {
        this.status = status;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifedTime() {
        return modifedTime;
    }

    public void setModifedTime(String modifedTime) {
        this.modifedTime = modifedTime;
    }
}
