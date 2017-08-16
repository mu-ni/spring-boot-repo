package com.muni.test.repo;

import org.springframework.data.repository.CrudRepository;
import com.muni.test.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
