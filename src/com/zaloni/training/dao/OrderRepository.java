package com.zaloni.training.dao;

import org.springframework.data.repository.CrudRepository;

import com.zaloni.training.entity.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {}
