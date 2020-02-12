package com.test.module.swaggerDemo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/operation")
@Api(tags ="@ApiOperation注解示例类")
public class OperationController {

    @RequestMapping(value = "/addtion", method = RequestMethod.GET)
    public Object addtion(HttpServletRequest request) {
        return null;
    }

    @RequestMapping(value = "/deletion", method = RequestMethod.GET)
    @ApiOperation(value = "测试ApiOperation注解",notes = "swagger注解测试")
    public Object deletion(HttpServletRequest request) {
        return null;
    }
}
