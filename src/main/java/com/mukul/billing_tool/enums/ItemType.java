package com.mukul.billing_tool.enums;
/**
 *  Enum to declare all Item Types
 */
public enum ItemType {
    GROCERY("Grocery"),
    NORMAL("Normal");

    private final String itemType;

    ItemType(String itemType) {
        this.itemType = itemType;
    }
}
