package com.mukul.billing_tool.enums;

/**
 *  Enum to declare all Customer Types
 */
public enum CustomerTypeEnum {
    EMPLOYEE(0.3d),
    AFFILIATE(0.1d),
    LOYALTY(0.05d),
    GENERAL(0d);

    private final double discount;

    CustomerTypeEnum(double discount) {
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }
}
