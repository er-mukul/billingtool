package com.mukul.billing_tool.services;

import com.mukul.billing_tool.entity.Customer;
import com.mukul.billing_tool.entity.ItemDetail;

import java.util.List;

public interface BillingService {
    double calculateBillAmount(Customer customer, List<ItemDetail> itemDetailList);
}
