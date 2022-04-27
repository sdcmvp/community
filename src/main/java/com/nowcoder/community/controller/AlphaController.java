package com.nowcoder.community.controller;

import com.nowcoder.community.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@Controller
@RequestMapping("/alpha")
public class AlphaController {

    @Autowired
    private AlphaService alphaService;

    @RequestMapping("data")
    @ResponseBody
    public String getData() {
        return alphaService.find();
    }

    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response) {
        //获取请求数据
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + ":"+value);
        }
        System.out.println(request.getParameter("code"));


        //返回响应数据
        //下面的意思是返回一个网页，且这个网页支持中文
        response.setContentType("test/html;charset=utf-8");
        try (
                PrintWriter writer = response.getWriter();
        ){
            writer.write("<h1>牛客网</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //get请求看笔记

    //post请求
    @RequestMapping(path = "/student", method = RequestMethod.POST)
    @ResponseBody
    //怎么获得post请求的参数呢？
    //这个参数的名字与表达中数据的名字一致时，会自动传过来
    public String saveStudent(String name, int age) {
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    //怎么向浏览器响应数据（响应动态HTML）
    @RequestMapping(path = "/teacher", method = RequestMethod.GET)
    //ModelAndView,即下列方法返回的是Model和View的数据
    //根据SpringMVC的原理---所有的组件都是由DispatcherServlet调度的，那么DispatcherServlet会调用
    //Controller的某些方法，这些方法会返回model数据，也需要返回视图相关view的数据，然后把model和视图view提交
    //给模板引擎，由模板引擎进行渲染，生成动态的HTML
    public ModelAndView getTeacher() {
        //实例化对象
        ModelAndView modelAndView = new ModelAndView();
        //往里传动态的值，需要多少变量就addObject多少数据
        modelAndView.addObject("name","zhangsan");
        modelAndView.addObject("age",30);
        //设置模板的路径和名字
        //后缀不用写，只写文件名就可以了，如下，view其实是view.html
        modelAndView.setViewName("/demo/view");
        return modelAndView;
    }

}
