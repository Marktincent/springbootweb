package com.uaes.esw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author zhenghuan.wang
 */
@Controller
public class SpringSessionController {

//    @RequestMapping("/putsession.html")
//    public @ResponseBody
//    String putSession(HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        System.out.println("session.getClass:" + session.getClass() + "----session.getId:" + session.getId());
//        String name = "hhh";
//        session.setAttribute("user", name);
//        return "hey," + name;
//    }
}
