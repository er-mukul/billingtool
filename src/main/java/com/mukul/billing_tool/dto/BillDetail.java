package com.mukul.billing_tool.dto;

import com.mukul.billing_tool.dom.common.DomainDTO;
import com.mukul.billing_tool.entity.ItemDetail;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class BillDetail implements DomainDTO {
    @NotNull
    @NotEmpty
    private String customerName;

    @NotNull
    private List<ItemDetail> items;

}
