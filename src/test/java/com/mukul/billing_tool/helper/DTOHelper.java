package com.mukul.billing_tool.helper;

import com.mukul.billing_tool.dto.BillDetail;
import com.mukul.billing_tool.entity.Customer;
import com.mukul.billing_tool.entity.ItemDetail;
import com.mukul.billing_tool.enums.CustomerType;
import com.mukul.billing_tool.enums.ItemType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DTOHelper {
    public static Customer createCustomer(final CustomerType cType, final LocalDate date) {
        return Customer.builder().id(1l).customerName("TestName").customerType(cType).joiningDate(date).build();
    }

    public static List<ItemDetail> createList(){
        List<ItemDetail> itemList = new ArrayList<>();
        for(int i=0;i<4;i++){
            itemList.add(ItemDetail.builder().id(1l+i).itemName("Item"+i).itemType(i%2==0? ItemType.GROCERY:ItemType.NORMAL).quantity(10+i).itemPrice(100d).build());
        }
        return itemList;
    }

    public static BillDetail getBillDetail(){
        return BillDetail.builder().customerName("TestName").items(createList()).build();
    }
}
