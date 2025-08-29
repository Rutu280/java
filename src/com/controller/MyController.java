package com.controller;

import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.Model.Person;
import com.bo.BO;

@Controller
public class MyController {

    @Autowired
    private BO bo;  // Spring will inject the BO bean

    @RequestMapping(value = "/")
    public String home() {
        return "Home";
    }

    @RequestMapping(value = "/index")
    public String indexPage() {
        return "Index";
    }

    @RequestMapping(value = "/loginsucess")
    public String loginSuccess() {
        return "Logginsucess";
    }

    @RequestMapping(value = "/error")
    public String errorPage() {
        return "Error";
    }

    @RequestMapping(value = "/insert")
    public String insert(HttpServletRequest request, HttpServletResponse resp, ModelMap map) {
        Person p = new Person();
        p.setName(request.getParameter("name"));
        p.setGender(request.getParameter("gender"));
        p.setCity(request.getParameter("city"));
        p.setState(request.getParameter("state"));
        p.setEmail(request.getParameter("email"));
        p.setPassword(request.getParameter("password"));

        bo.inserData(p);
        return "show";
    }

    @RequestMapping(value = "/delete")
    public String delete(HttpServletRequest request, HttpServletResponse resp, ModelMap map) {
        int id = Integer.parseInt(request.getParameter("id"));
        bo.deletedata(id);
        return "show";
    }

    @RequestMapping(value = "/edit")
    public String edit(HttpServletRequest request, HttpServletResponse resp, ModelMap map) {
        int id = Integer.parseInt(request.getParameter("id"));
        Person p = bo.getPersonById(id);
        map.addAttribute("p", p);
        return "update";
    }

    @RequestMapping(value = "/update")
    public String update(HttpServletRequest request, HttpServletResponse resp, ModelMap map) {
        Person p = new Person();
        p.setId(Integer.parseInt(request.getParameter("id")));
        p.setName(request.getParameter("name"));
        p.setGender(request.getParameter("gender"));
        p.setCity(request.getParameter("city"));
        p.setState(request.getParameter("state"));
        p.setEmail(request.getParameter("email"));
        p.setPassword(request.getParameter("password"));

        bo.updatedata(p);
        return "show";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "Login";
    }

    public boolean checkSession(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        return (httpSession.getAttribute("person") != null);
    }

    @RequestMapping(value = "/Signin", method = RequestMethod.POST)
    public String signin(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
        String username = request.getParameter("name");
        String password = request.getParameter("password");

        Person person = bo.getPersonByUsernameAndPassword(username, password);

        if (person == null) {
            System.out.println("username and password is invalid");
            return "error";
        } else {
            HttpSession httpSession = request.getSession();
            person.setPassword(Base64.getEncoder().encodeToString(person.getPassword().getBytes()));
            httpSession.setAttribute("person", person);
            System.out.println(person.getName() + " welcome to your login");
            return "loginsucess";
        }
    }
}
