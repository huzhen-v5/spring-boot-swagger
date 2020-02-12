package com.test.module.swaggerDemo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/param")
@Api(tags ="@ApiParam注解示例类")
public class ParamController {

    @RequestMapping(value = "/addtion", method = RequestMethod.POST)
    @ApiOperation(value = "测试ApiParam注解",notes = "swagger注解测试")
    public Object addtion(
            @ApiParam(name="account", value="账户名", example="huzhenv5", required=true) String account,
            @ApiParam(name="pwd", value="密码", example="1111", required=true) String  pwd)
    {
        return null;
    }
}
