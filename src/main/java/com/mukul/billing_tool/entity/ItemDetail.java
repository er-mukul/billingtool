package com.mukul.billing_tool.entity;

import com.mukul.billing_tool.dom.common.DomainDTO;
import com.mukul.billing_tool.enums.ItemType;
import lombok.*;

import javax.persistence.*;

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
    private ItemType itemType;

    @Column
    private double itemPrice;

    @Transient
    private int quantity;
}
