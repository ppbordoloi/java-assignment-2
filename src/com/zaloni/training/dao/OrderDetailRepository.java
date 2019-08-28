package com.zaloni.training.dao;

import org.springframework.data.repository.CrudRepository;

import com.zaloni.training.entity.OrderDetail;

public interface OrderDetailRepository extends CrudRepository<OrderDetail, Long> {}
