package com.test.module.session;

import com.test.common.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/testSession")
public class TestController {

    /***
     * 测试登录
     * 这里假设用户（userName=admin,password=123456）
     */
    @RequestMapping(value = "/login3", method = RequestMethod.GET)
    @ResponseBody
    public Map login3(HttpServletRequest request) {
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

    /***
     * 测试登录
     * 这里假设用户（userName=admin,password=123456）
     */
    @RequestMapping(value = "/login2", method = RequestMethod.GET)
    @ResponseBody
    public Map login2(HttpServletRequest request) {
        Map<String,Object> retMap = new HashMap<String,Object>();
        Map<String,String> paramMap = PJCommon.getRequestParamMapAndSessionInfo(request);
        HttpSession session = getRequest().getSession();
        try{
            retMap.put("list", 1);
            retMap.put("state", 0);
        }catch(Exception e) {
            e.printStackTrace();
            retMap.put("state", 1);
        }
        return retMap;
    }
    /***
     * 测试登录
     * 这里假设用户（userName=admin,password=123456）
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResultModel login(String userName, String password) {
        try {
            Map<String, Object> map = new HashMap<>(1);
            if ("admin".equals(userName) && "123456".equals(password)) {
                //登录成功，开始缓用户信息(当前我使用假数据，真实情况是查询数据库获取)
                Account account = new Account();
                account.setId(1);
                account.setUserName("张三");
                account.setPhone("15388888888");
                account.setCompany("xx投资公司");
                setAccount(account);
            } else {
                return ResultTools.result(1003, "", map);
            }
            return ResultTools.result(0, "", map);
        } catch (Exception e) {
            return ResultTools.result(404, e.getMessage(), null);
        }
    }


    /***
     * 获取用户
     */
    @RequestMapping(value = "/getUser", method = RequestMethod.POST)
    @ResponseBody
    public ResultModel getUser() {
        try {
            Map<String, Object> map = new HashMap<>(1);
            Account account = getAccount();
            if (account == null) {
                return ResultTools.result(1002, "", map);
            }
            map.put("account", account);
            return ResultTools.result(0, "", map);
        } catch (Exception e) {
            return ResultTools.result(404, e.getMessage(), null);
        }
    }

    /**
     * 当前账号常量
     */
    private static final String ACCOUNT = "account";


    private HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    public Account getAccount() {
        HttpSession session = getRequest().getSession();
        return (Account) session.getAttribute(ACCOUNT);
    }

    public void setAccount(Account account) {
        HttpSession session = getRequest().getSession();
        if (account != null) {
            session.setAttribute(ACCOUNT, account);
            //session过期时间设置，以秒为单位，即在没有活动30分钟后，session将失效
            session.setMaxInactiveInterval(30 * 60);
        }
    }
}
