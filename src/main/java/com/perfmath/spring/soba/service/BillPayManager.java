package com.perfmath.spring.soba.service;

import java.io.Serializable;
import java.util.List;

import com.perfmath.spring.soba.model.domain.BillPayment;


public interface BillPayManager extends Serializable{
    public List<BillPayment> getBillPayments();
    public void storeBillPayment(BillPayment billPayment);
    public void deleteBillPayment(String id);
    public BillPayment findById(String id);
}
