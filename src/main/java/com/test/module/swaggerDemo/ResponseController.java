package com.test.module.swaggerDemo;

import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/response")
@Api(tags ="@ApiResponse注解示例类")
public class ResponseController {

    // 一个参数的接口
    @RequestMapping(value = "/addtion", method = RequestMethod.POST)
    @ApiResponses({
            @ApiResponse(code=400, message="请求参数没填好"),
            @ApiResponse(code=404, message="请求路径没有或页面跳转路径不对")
    })
    public Object addtion(HttpServletRequest request) {
        return null;
    }

}
