package com.zaloni.training.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.zaloni.training.entity.User;
import com.zaloni.training.model.Login;
import com.zaloni.training.service.LoginService;
import com.zaloni.training.service.UserService;

@Controller
@SessionAttributes("currentUser")
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    private LoginService loginService;

    @ModelAttribute("currentUser")
    public User setCurrentUser() {
        return null;
    }
    
    @RequestMapping("/")
    public String root() {
        return "redirect:/login";
    }

    @RequestMapping("/logout")
    public String logout(@ModelAttribute("currentUser") User currentUser, SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:/login";
    }
    
    @RequestMapping("/relogin")
    public ModelAndView relogin(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("auth/relogin");
        mav.addObject("loginUrl", request.getContextPath() + "/login");
        return mav;
    }

    @RequestMapping("/login")
    public ModelAndView loginForm(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("auth/login", "login", new Login());
        mav.addObject("loginTarget", request.getContextPath() + "/loginSubmit");
        return mav;
    }

    @RequestMapping(value = "/loginSubmit", method = RequestMethod.POST)
    public String submit(@ModelAttribute("login")Login login, ModelMap model) {
        if (loginService.isValidCredential(login.getEmail(), login.getPassword())) {
            User currentUser = userService.findByEmail(login.getEmail());
            model.addAttribute("currentUser", currentUser);
            return "redirect:/product-list";
        }
        else {
            String errorMessage = "Invalid email " + login.getEmail() + " or password " + login.getPassword().replaceAll(".", "*");
            model.addAttribute("errorMessage", errorMessage);
            return "auth/login";
        }
    }

    @RequestMapping("/css/style.css")
    public ModelAndView css() {
        return new ModelAndView("css");
    }
}
