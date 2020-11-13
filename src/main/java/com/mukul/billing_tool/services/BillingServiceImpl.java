package com.mukul.billing_tool.services;

import com.mukul.billing_tool.entity.Customer;
import com.mukul.billing_tool.entity.ItemDetail;
import com.mukul.billing_tool.enums.CustomerTypeEnum;
import com.mukul.billing_tool.enums.ItemTypeEnum;
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

        groceryAmount = itemDetailList.stream().filter(itemDetail -> ItemTypeEnum.GROCERY.equals(itemDetail.getItemType())).mapToDouble(itemDetail -> itemDetail.getItemPrice() * itemDetail.getQuantity()).sum();
        nonGroceryAmount = itemDetailList.stream().filter(itemDetail -> !ItemTypeEnum.GROCERY.equals(itemDetail.getItemType())).mapToDouble(itemDetail -> itemDetail.getItemPrice() * itemDetail.getQuantity()).sum();

        double amountAfterPercentageDiscount = groceryAmount + nonGroceryAmount - nonGroceryAmount*discountPercentage;

        int cashDiscountFactor = (int)amountAfterPercentageDiscount/100;

        return amountAfterPercentageDiscount - cashDiscountFactor*5;
    }

    private double findDiscountPercentage(Customer customer) {
        double discountPercentage = 0d;
        Period loyaltyPeriod = Period.between(customer.getJoiningDate(), LocalDate.now());

        if(CustomerTypeEnum.EMPLOYEE.equals(customer.getCustomerType())){
            discountPercentage = CustomerTypeEnum.EMPLOYEE.getDiscount();
        } else if(CustomerTypeEnum.AFFILIATE.equals(customer.getCustomerType())){
            discountPercentage = CustomerTypeEnum.AFFILIATE.getDiscount();
        } else if(loyaltyPeriod.getYears() > 2){
            discountPercentage = CustomerTypeEnum.LOYALTY.getDiscount();
        }

        return discountPercentage;
    }
}
