package com.zaloni.training.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.zaloni.training.entity.CartItem;
import com.zaloni.training.entity.Product;
import com.zaloni.training.entity.User;
import com.zaloni.training.model.CartItemDetail;
import com.zaloni.training.service.CartItemService;
import com.zaloni.training.service.ProductService;

@Controller
@SessionAttributes("currentUser")
public class CartController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CartItemService cartItemService;

    @ModelAttribute("currentUser")
    public User setCurrentUser() {
        return null;
    }

    @RequestMapping("/my-cart")
    public ModelAndView myCart(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("cart/my-cart");
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        if (currentUser == null) {
            return new ModelAndView("redirect:/relogin");
        }

        List<CartItemDetail> cartItemList = getCartItemDetailList(currentUser.getId());

        mav.addObject("cartItemList", cartItemList);
        return mav;
    }

    private List<CartItemDetail> getCartItemDetailList(Long userId) {
        List<CartItem> list = cartItemService.findByUserId(userId);
        List<CartItemDetail> cartItemList = new ArrayList<CartItemDetail>();

        for(CartItem c: list) {
            Product product = productService.get(c.getProductId().intValue());
            CartItemDetail item = new CartItemDetail(c, product);
            cartItemList.add(item);
        }
        return cartItemList;
    }
    
    @RequestMapping("/cart-add-item-{id}")
    public ModelAndView addToCart(HttpServletRequest request, @PathVariable("id") int productId) {
        ModelAndView mav = new ModelAndView("cart/cart-add");

        User currentUser = (User) request.getSession().getAttribute("currentUser");
        if (currentUser == null) {
            return new ModelAndView("redirect:/relogin");
        }

        Product product = productService.get(productId);
        Long userId = currentUser.getId();
        CartItem cartItem = cartItemService.findByUserIdAndProductId(userId, new Long(productId));
        if (cartItem == null) {
            cartItem = new CartItem();
            cartItem.setUserId(currentUser.getId());
            cartItem.setProductId(new Long(productId));
            cartItem.setQuantity(new Long(1));
        }

        mav.addObject("cartItem", cartItem);
        mav.addObject("productName", product.getName());
        System.out.println(product.getStock());
        mav.addObject("stock", product.getStock());
        mav.addObject("formSubmit", request.getContextPath() + "/cart-add-item");

        return mav;
    }

    @RequestMapping(value = "/cart-add-item", method = RequestMethod.POST)
    public String addToCartHandler(@ModelAttribute("cartItem")CartItem cartItem, ModelMap model) {
        cartItemService.save(cartItem);
        return "redirect:/my-cart";
    }

    @RequestMapping("/cart-delete-item-{id}")
    public ModelAndView deleteFromCart(HttpServletRequest request, @PathVariable("id") int id) {
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        if (currentUser == null) {
            return new ModelAndView("redirect:/relogin");
        }

        ModelAndView mav = new ModelAndView("cart/cart-item-delete");
        CartItemDetail cartItemDetail = cartItemService.getCartItemDetail(id);
        mav.addObject("cartItemDetail", cartItemDetail);
        mav.addObject("formSubmit", request.getContextPath() + "/cart-item-delete");
        return mav;
    }
    
    @RequestMapping(value = "/cart-billing-confirm", method = RequestMethod.POST)
    private ModelAndView cartProcessConfirm(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("cart/cart-billing-confirm");
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        if (currentUser == null) {
            return new ModelAndView("redirect:/relogin");
        }
        
        List<CartItemDetail> cartItemList = getCartItemDetailList(currentUser.getId());
        mav.addObject("cartItemList", cartItemList);
        mav.addObject("formSubmit", request.getContextPath() + "/cart-billing-process");
        return mav;
    }

    @RequestMapping(value = "/cart-billing-done", method = RequestMethod.POST)
    private ModelAndView cartProcessDone(HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        if (currentUser == null) {
            return new ModelAndView("redirect:/relogin");
        }

        cartItemService.placeOrderForCartItems(currentUser.getId());
        return new ModelAndView("redirect:/product-list");
    }

    private ModelAndView getFormForCartItemRemove(HttpServletRequest request, int id) {
        ModelAndView mav = new ModelAndView("cart/cart-item-delete");
        CartItemDetail cartItemDetail = cartItemService.getCartItemDetail(id);
        mav.addObject("cartItemDetail", cartItemDetail);
        mav.addObject("formSubmit", request.getContextPath() + "/cart-item-delete");
        return mav;
    }

    @RequestMapping(value = "/cart-item-delete", method = RequestMethod.POST)
    public ModelAndView deleteFromCartHandler(@ModelAttribute("cartItemDetail")CartItemDetail cartItemDetail, ModelMap model, HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        if (currentUser == null) {
            return new ModelAndView("redirect:/relogin");
        }

        int id = cartItemDetail.getId().intValue();
        if (id > 0) {
            cartItemService.delete(id);
            return new ModelAndView("redirect:/product-list");
        }

        model.addAttribute("errorMessage", "Error removing product from the cart");
        return getFormForCartItemRemove(request, id);
    }

}
