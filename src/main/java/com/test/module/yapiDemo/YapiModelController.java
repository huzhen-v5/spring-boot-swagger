package com.test.module.yapiDemo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/yapimodel")
@Api(tags ="@ApiModel注解示例类")
public class YapiModelController {

    @RequestMapping(value = "/addtion", method = RequestMethod.POST)
    @ApiOperation("新增用户")
    public UserModel addtion(
            @RequestBody @ApiParam(name="用户对象", value="请求时传入json格式", required=true) UserModel user){
        return new UserModel();
    }

}
