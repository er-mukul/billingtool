package com.mukul.billing_tool.jpa;

import com.mukul.billing_tool.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    public Customer findCustomerByCustomerName(final String customerName);
}
