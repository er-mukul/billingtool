package com.mukul.billing_tool.enums;
/**
 *  Enum to declare all Item Types
 */
public enum ItemTypeEnum {
    GROCERY("Grocery"),
    NORMAL("Normal");

    private final String itemType;

    ItemTypeEnum(String itemType) {
        this.itemType = itemType;
    }
}
