package com.test.module.swaggerDemo;

import com.test.module.yapiDemo.UserModel;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/model")
@Api(tags ="@ApiModel注解示例类")
public class ModelController {

    @RequestMapping(value = "/addtion", method = RequestMethod.POST)
    @ApiOperation("新增用户")
    public UserModel addtion(
            @RequestBody @ApiParam(name="用户对象", value="请求时传入json格式", required=true) UserModel user){
        return new UserModel();
    }

}
