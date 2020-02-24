package com.test.module.cookie;

import com.test.common.PJCommon;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/cookie")
public class CookieController {

    /***
     * 测试设置cookie有效期 test
     */
    @RequestMapping(value = "/cookieAge", method = RequestMethod.POST)
    @ResponseBody
    public Map login3(HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> retMap = new HashMap<String,Object>();
        try{
            retMap.put("list", 0);
            retMap.put("data", "huzhenv5");
        }catch(Exception e) {
            e.printStackTrace();
            retMap.put("state", 1);
        }
//        Cookie cookie = new Cookie("username2","helloweenvsfei"); // 新建Cookie
//        cookie.setMaxAge(Integer.MAX_VALUE); // 设置生命周期为MAX_VALUE
//        response.addCookie(cookie); // 输出到客户端
        HttpSession session = request.getSession();
        return retMap;
    }
}
