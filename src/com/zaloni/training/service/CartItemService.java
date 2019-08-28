package com.zaloni.training.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zaloni.training.dao.CartItemRepository;
import com.zaloni.training.dao.OrderDetailRepository;
import com.zaloni.training.dao.OrderRepository;
import com.zaloni.training.entity.CartItem;
import com.zaloni.training.entity.Order;
import com.zaloni.training.entity.OrderDetail;
import com.zaloni.training.entity.Product;
import com.zaloni.training.model.CartItemDetail;

@Service
@Transactional
public class CartItemService {
    
    @Autowired
    CartItemRepository repo;
    
    @Autowired
    OrderRepository orderRepo;
    
    @Autowired
    OrderDetailRepository orderDetailRepo;

    @Autowired
    ProductService productService;
    
    public CartItem save(CartItem cartItem) {
        return repo.save(cartItem);
    }

    public int getOrderId(Long userId) {
        Order order = new Order();
        order.setUserId(userId);
        orderRepo.save(order);
        return order.getId().intValue();
    }

    public void placeOrderForCartItems(Long userId) {
        int orderId = getOrderId(userId);
        List<CartItem> list = findByUserId(userId);
        for (CartItem c:list) {
            Product p = productService.get(c.getProductId().intValue());

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(new Long(orderId));
            orderDetail.setProductId(c.getProductId());
            orderDetail.setQuantity(c.getQuantity());
            orderDetail.setPrice(new Long(p.getPrice()));
            orderDetailRepo.save(orderDetail);
            repo.delete(c);
        }
    }

    public CartItem findByUserIdAndProductId(Long userId, Long productId) {
        List<CartItem> ci = repo.searchByUserIdAndProductId(userId, productId);
        return ci.size() == 1 ? ci.get(0) : null;
    }

    public CartItem get(int id) {
        Optional<CartItem> matchedCartItem = repo.findById(new Long(id));
        return matchedCartItem.isPresent() ? matchedCartItem.get() : null;
    }

    public CartItemDetail getCartItemDetail(int id) {
        CartItem ci = get(id);
        Product product = productService.get(ci.getProductId().intValue());
        CartItemDetail cartItemDetail = new CartItemDetail(ci, product);
        return cartItemDetail;
    }

    public void delete(int id) {
        repo.deleteById(new Long(id));
    }

    public List<CartItem> findByUserId(Long userId) {
        List<CartItem> ci = repo.searchByUserId(userId);
        return ci;
    }

}
