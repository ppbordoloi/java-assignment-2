package com.zaloni.training.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zaloni.training.dao.ProductRepository;
import com.zaloni.training.entity.Product;

@Service
@Transactional
public class ProductService {

    @Autowired
    ProductRepository repo;
    
    public List<Product> findAll() {
        return (List<Product>) repo.findAll();
    }
    
    public Product save(Product product) {
        return repo.save(product);
    }

    public Product get(int id) {
        Optional<Product> optProduct = repo.findById(new Long(id));
        return optProduct.isPresent() ? optProduct.get() : null;
    }
    
    public void delete(int id) {
        repo.deleteById(new Long(id));
    }

}
