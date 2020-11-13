package com.mukul.billing_tool.enums;

/**
 *  Enum to declare all Customer Types
 */
public enum CustomerType {
    Employee(0.3d),
    Affiliate(0.1d),
    LOYALTY(0.05d),
    General(0d);

    private final double discount;

    CustomerType(double discount) {
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }
}
