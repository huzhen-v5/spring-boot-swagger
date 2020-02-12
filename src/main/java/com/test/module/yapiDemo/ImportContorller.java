package com.test.module.yapiDemo;

import com.test.common.PJCommon;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/import")
@Api(tags ="yapi导入测试类")
public class ImportContorller {

    private static Log log = LogFactory.getLog(ImportContorller.class);

    // 无参数的接口
    @RequestMapping(value = "/getDemo1", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "返回1接口",notes = "返回一个固定值1")
    public Object getDemo1(HttpServletRequest request) {
        Map<String,Object> retMap = new HashMap<String,Object>();
        Map<String,String> paramMap = PJCommon.getRequestParamMapAndSessionInfo(request);
        try{
            retMap.put("list", 1);
            retMap.put("state", 0);
        }catch(Exception e) {
            e.printStackTrace();
            retMap.put("state", 1);
        }
        return retMap;
    }

    // 一个参数的接口
    @RequestMapping(value = "/getDemo10", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "乘以10接口",notes = "根据传的参数，返回乘以10的数字")
    @ApiImplicitParam(name = "number", value = "被乘数", required = true, paramType = "query", dataType = "int")

    public Object getDemo10(HttpServletRequest request) {
        Map<String,Object> retMap = new HashMap<String,Object>();
        Map<String,String> paramMap = PJCommon.getRequestParamMapAndSessionInfo(request);
        try{
            int value = 10 * Integer.parseInt(paramMap.get("number").toString());
            retMap.put("result", value);
            retMap.put("state", 0);
        }catch(Exception e) {
            e.printStackTrace();
            retMap.put("state", 1);
        }
        return retMap;
    }

    // 多个参数的接口
    @RequestMapping(value = "/getDemoAdd", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "相加接口",notes = "根据传的参数，返回两个数的和")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "val1", value = "相加数1", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "val2", value = "相加数2", required = true, paramType = "query", dataType = "int")
    })
    public Object getDemoAdd(HttpServletRequest request) {
        Map<String,Object> retMap = new HashMap<String,Object>();
        Map<String,String> paramMap = PJCommon.getRequestParamMapAndSessionInfo(request);
        try{
            int value1 = Integer.parseInt(paramMap.get("val1").toString());
            int value2 = Integer.parseInt(paramMap.get("val2").toString());
            retMap.put("result", value1 + value2);
            retMap.put("state", 0);
        }catch(Exception e) {
            e.printStackTrace();
            retMap.put("state", 1);
        }
        return retMap;
    }
}
