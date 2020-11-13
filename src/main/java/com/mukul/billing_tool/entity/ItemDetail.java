package com.mukul.billing_tool.entity;

import com.mukul.billing_tool.dom.common.DomainDTO;
import com.mukul.billing_tool.enums.ItemTypeEnum;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ItemDetail implements DomainDTO {
    @Id
    private Long id;

    @Column(unique = true)
    private String itemName;

    @Column
    private ItemTypeEnum itemType;

    @Column
    private double itemPrice;

    @Transient
    private int quantity;
}
