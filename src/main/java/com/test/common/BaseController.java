package com.test.common;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * 基础控制器
 *
 * @author tuc
 */
public abstract class BaseController {

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