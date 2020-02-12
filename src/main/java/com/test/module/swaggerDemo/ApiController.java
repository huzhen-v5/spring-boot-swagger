package com.test.module.swaggerDemo;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/apiDemo")
@Api(tags ="@Api注解示例类", value="@api注解示例")
public class ApiController {

    @RequestMapping(value = "/addtion", method = RequestMethod.GET)
    public Object addtion(HttpServletRequest request) {
        return null;
    }
}