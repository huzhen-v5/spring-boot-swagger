package com.test.module.swaggerDemo;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/ignore")
@Api(tags ="@ApiIgnore注解示例类", value="测试api注解")
public class IgnoreController {

    @RequestMapping(value = "/addtion", method = RequestMethod.GET)
    public Object addtion(HttpServletRequest request) {
        return null;
    }

    @ApiIgnore
    @RequestMapping(value = "/deletion", method = RequestMethod.GET)
    public Object deletion(HttpServletRequest request) {
        return null;
    }

}
