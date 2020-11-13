package com.mukul.billing_tool.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mukul.billing_tool.dom.common.DomainDTO;
import com.mukul.billing_tool.enums.CustomerType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer implements DomainDTO {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotEmpty
    @JsonProperty("customerName")
    @Column(unique = true)
    private String customerName;

    @NotNull
    @Column
    private CustomerType customerType;

    @NotNull
    @Column
    private LocalDate joiningDate;

}
