package com.zaloni.training.dao;

import org.springframework.data.repository.CrudRepository;

import com.zaloni.training.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {}
