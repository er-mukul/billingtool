package com.mukul.billing_tool.services;

import com.mukul.billing_tool.entity.Customer;
import com.mukul.billing_tool.entity.ItemDetail;
import com.mukul.billing_tool.enums.CustomerTypeEnum;
import com.mukul.billing_tool.helper.DTOHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
@DisplayName("Unit Tests- BillingServiceImpl Test")
class BillingServiceImplTest {
    @InjectMocks
    private BillingServiceImpl billingService;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void calculateBillAmountForEmployee() {
        Customer customer = DTOHelper.createCustomer(CustomerTypeEnum.EMPLOYEE, LocalDate.now());
        List<ItemDetail> itemDetailList = DTOHelper.createList();

        double billAmount = billingService.calculateBillAmount(customer,itemDetailList);
        Assertions.assertEquals(3690d,billAmount);
    }

    @Test
    void calculateBillAmountForAffiliate() {
        Customer customer = DTOHelper.createCustomer(CustomerTypeEnum.AFFILIATE, LocalDate.now());
        List<ItemDetail> itemDetailList = DTOHelper.createList();

        double billAmount = billingService.calculateBillAmount(customer,itemDetailList);
        Assertions.assertEquals(4145d,billAmount);
    }

    @Test
    void calculateBillAmountForLoyalty() {
        Customer customer = DTOHelper.createCustomer(CustomerTypeEnum.LOYALTY, LocalDate.of(2016,11,21));
        List<ItemDetail> itemDetailList = DTOHelper.createList();

        double billAmount = billingService.calculateBillAmount(customer,itemDetailList);
        Assertions.assertEquals(4260d,billAmount);
    }

    @Test
    void calculateBillAmountForGeneral() {
        Customer customer = DTOHelper.createCustomer(CustomerTypeEnum.GENERAL, LocalDate.now());
        List<ItemDetail> itemDetailList = DTOHelper.createList();

        double billAmount = billingService.calculateBillAmount(customer,itemDetailList);
        Assertions.assertEquals(4370d,billAmount);
    }

}