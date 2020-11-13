package com.mukul.billing_tool.jpa;

import com.mukul.billing_tool.entity.ItemDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<ItemDetail, Long> {
}
