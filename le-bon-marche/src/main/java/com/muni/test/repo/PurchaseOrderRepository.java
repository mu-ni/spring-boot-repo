package com.muni.test.repo;

import org.springframework.data.repository.CrudRepository;

import com.muni.test.entity.PurchaseOrder;

public interface PurchaseOrderRepository extends CrudRepository<PurchaseOrder, Long> {

}
