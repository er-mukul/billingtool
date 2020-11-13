package com.mukul.billing_tool.services;

import com.mukul.billing_tool.entity.Customer;
import com.mukul.billing_tool.entity.ItemDetail;
import com.mukul.billing_tool.enums.CustomerType;
import com.mukul.billing_tool.enums.ItemType;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Component
public class BillingServiceImpl implements BillingService {

    @Override
    public double calculateBillAmount(final Customer customer, final List<ItemDetail> itemDetailList) {
        double discountPercentage = findDiscountPercentage(customer);
        double groceryAmount;
        double nonGroceryAmount;

        groceryAmount = itemDetailList.stream().filter(itemDetail -> ItemType.GROCERY.equals(itemDetail.getItemType())).mapToDouble(itemDetail -> itemDetail.getItemPrice() * itemDetail.getQuantity()).sum();
        nonGroceryAmount = itemDetailList.stream().filter(itemDetail -> !ItemType.GROCERY.equals(itemDetail.getItemType())).mapToDouble(itemDetail -> itemDetail.getItemPrice() * itemDetail.getQuantity()).sum();

        double amountAfterPercentageDiscount = groceryAmount + nonGroceryAmount - nonGroceryAmount*discountPercentage;

        int cashDiscountFactor = (int)amountAfterPercentageDiscount/100;

        return amountAfterPercentageDiscount - cashDiscountFactor*5;
    }

    private double findDiscountPercentage(Customer customer) {
        double discountPercentage = 0d;
        Period loyaltyPeriod = Period.between(customer.getJoiningDate(), LocalDate.now());

        if(CustomerType.Employee.equals(customer.getCustomerType())){
            discountPercentage = CustomerType.Employee.getDiscount();
        } else if(CustomerType.Affiliate.equals(customer.getCustomerType())){
            discountPercentage = CustomerType.Affiliate.getDiscount();
        } else if(loyaltyPeriod.getYears() > 2){
            discountPercentage = CustomerType.LOYALTY.getDiscount();
        }

        return discountPercentage;
    }
}
