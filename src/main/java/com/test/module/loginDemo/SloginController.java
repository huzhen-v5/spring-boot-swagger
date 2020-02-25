package com.test.module.loginDemo;

import com.test.common.PJCommon;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/sLoginDemo")
public class SloginController {
    /***
     * 测试登录
     * 这里假设用户（userName=admin,password=1111）
     */
    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    @ResponseBody
    public Map login(HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> retMap = new HashMap<String,Object>();
        Map<String,String> paramMap = PJCommon.getRequestParamMapAndSessionInfo(request);
        try {
            String account = paramMap.get("account").toString(); // 获取account参数
            String password = paramMap.get("password").toString(); // 获取password参数

            /*
             * 获取或者创建Session
             * ajax请求会自动带上名为"JSESSIONID"的cookie（如果客户端有这个cookie的话），该值就是服务端创建session后，返回客户端的seesionId
             * getSession会根据这个id查询服务端是否有这个session，如果有的话就会获取该session，如果没有的话就会新建一个新的session
             * Session生成后，只要用户继续访问，服务器就会更新Session的最后访问时间，并维护该Session。
             * 用户每访问服务器一次，无论是否读写Session，服务器都认为该用户的Session“活跃（active）”了一次。
             * */
            HttpSession session = request.getSession(); // 获取Session对象
            Object personObj = session.getAttribute("person");
            // 判断personObj是否存在，如果存在则不是新建的session，直接返回账户信息即可
            if (personObj != null) {
                retMap.put("state", 0);
                retMap.put("person", personObj);
                retMap.put("message", "session登陆成功");
                return retMap;
            }
            // 如果不存在，则是新建的session，需要重新验证账号密码
            if (!"admin".equals(account) || !"1111".equals(password)) {
                session.invalidate(); // 作废session
                // 账号密码校验不通过
                retMap.put("state", -1);
                retMap.put("message", "账号/密码错误");
                return retMap;
            }
            // 验证通过
            Map personInfo = new HashMap();
            personInfo.put("name", "huzhenv5");
            personInfo.put("age", "18");
            session.setAttribute("person", personInfo); // 设置Session中的属性
            // 设置Session维持时间，单位是秒（0或者负数表示session永远不超时）
            // spring boot 可以在配置文件中配置默认的session超时时间
            // 通过以下代码设置session超时时间，优先级更高
            session.setMaxInactiveInterval(10);

            retMap.put("state", 0);
            retMap.put("person", personInfo);
            retMap.put("message", "账号/密码登陆成功");

        } catch (Exception e) {
            e.printStackTrace();
            retMap.put("state", -2);
            retMap.put("message", "登陆失败");
        }
        return retMap;
    }

    /***
     * 注销登录
     */
    @RequestMapping(value = "/logout.do", method = RequestMethod.POST)
    @ResponseBody
    public Map logout(HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> retMap = new HashMap<String,Object>();
        Map<String,String> paramMap = PJCommon.getRequestParamMapAndSessionInfo(request);
        try {
            HttpSession session = request.getSession(); // 获取Session对象
            session.invalidate(); // 作废session
            // 注销成功
            retMap.put("state", 0);
            retMap.put("message", "注销成功");

        } catch (Exception e) {
            e.printStackTrace();
            retMap.put("state", -2);
            retMap.put("message", "注销失败");
        }
        return retMap;
    }

}
