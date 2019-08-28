package com.zaloni.training.controller;

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

import com.zaloni.training.entity.Product;
import com.zaloni.training.entity.User;
import com.zaloni.training.model.Login;
import com.zaloni.training.service.ProductService;
import com.zaloni.training.service.UserService;

@Controller
@SessionAttributes("currentUser")
public class ProductController {

    @Autowired
    private ProductService productService;
        
    @ModelAttribute("currentUser")
    public User setCurrentUser() {
        return null;
    }

    @RequestMapping("/product-list")
    public ModelAndView productList(HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        if (currentUser == null) {
            return new ModelAndView("redirect:/relogin");
        }
        ModelAndView mav = new ModelAndView("product/product-list");
        List<Product> productList = productService.findAll();
        mav.addObject("productList", productList);
        mav.addObject("currentUser", currentUser);
        return mav;
    }

    @RequestMapping("/product-add")
    public ModelAndView productAdd(HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        if (currentUser == null) {
            return new ModelAndView("redirect:/relogin");
        }
        ModelAndView mav = new ModelAndView("product/product-add");
        mav.addObject("product", new Product());
        mav.addObject("formSubmit", request.getContextPath() + "/product-add");
        return mav;
    }

    @RequestMapping(value = "/product-add", method = RequestMethod.POST)
    public String productAddHandler(@ModelAttribute("product")Product product, ModelMap model) {
        if (isValidProduct(product)) {
            product = productService.save(product);
            return "redirect:/product-list";
        }

        model.addAttribute("errorMessage", "some error in "+ product.getName());
        return "product/product-add";
    }

    private boolean isValidProduct(Product product) {
        return !product.getName().equals("") && product.getPrice() > 0 && product.getStock() > 0;
    }
    
    @RequestMapping("/product-edit-{id}")
    public ModelAndView productEdit(HttpServletRequest request, @PathVariable("id") int id) {
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        if (currentUser == null) {
            return new ModelAndView("redirect:/relogin");
        }

        ModelAndView mav = new ModelAndView("product/product-edit");
        Product product = productService.get(id);
        mav.addObject("product", product);
        mav.addObject("formSubmit", request.getContextPath() + "/product-edit");
        return mav;
    }

    @RequestMapping(value = "/product-edit", method = RequestMethod.POST)
    public String productEditHandler(@ModelAttribute("product")Product product, ModelMap model) {

        if (isValidProduct(product)) {
            product = productService.save(product);
            return "redirect:/product-list";
        }
        
        model.addAttribute("errorMessage", "Error editing product "+ product.getName());
        return "product-edit-"+product.getId();
    }

    @RequestMapping("/product-delete-{id}")
    public ModelAndView productDelete(HttpServletRequest request, @PathVariable("id") int id) {
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        if (currentUser == null) {
            return new ModelAndView("redirect:/relogin");
        }

        ModelAndView mav = new ModelAndView("product/product-delete");
        Product product = productService.get(id);
        mav.addObject("product", product);
        mav.addObject("formSubmit", request.getContextPath() + "/product-delete");
        return mav;
    }

    @RequestMapping(value = "/product-delete", method = RequestMethod.POST)
    public String productDeleteHandler(@ModelAttribute("product")Product product, ModelMap model, HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/relogin";
        }

        int id = product.getId().intValue();
        if (id > 0) {
            productService.delete(id);
            return "redirect:/product-list";
        }

        model.addAttribute("errorMessage", "Error deleting product with id "+ id);
        return "product-delete-"+product.getId();
    }
}
