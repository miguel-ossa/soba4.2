package com.perfmath.spring.soba.model.dao;

import java.util.List;

import com.perfmath.spring.soba.model.domain.BillPayment;

public interface BillPaymentDao {
    public void store(BillPayment billPayment);

    public void delete(String id);

    public BillPayment findById(String id);

    public List<BillPayment> findAll();
}
