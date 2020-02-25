package com.test.module.loginDemo;

import com.test.common.PJCommon;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/loginDemo")
public class LoginController {

    private static final String KEY = "huzhenv5"; // 密钥

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
            int timeoutStr = new Integer(paramMap.get("timeout").toString()); // 获取timeout参数

            // cookie超时时间
            int timeout = 0;
            // 判断有效期
            switch (timeoutStr) {
                case 0:
                    // 每次都需重新登陆
                    break;
                case 1:
                    // 关闭浏览器即失效
                    timeout = -1;
                    break;
                case 2:
                    // 30天内有效
                    timeout = 30 *24 * 60 * 60;
                    break;
                case 3:
                    // 永久有效
                    timeout = Integer.MAX_VALUE;
                    break;
            }

            // 开始进行登陆校验
            boolean islogin = false; // 是否登录
            String thisaccount = null; // 账号
            String thisssid = null; // SSID标识
            // 如果Cookie不为空，遍历Cookie
            if(request.getCookies() != null) {
                for(Cookie cookie : request.getCookies()) {
                    if(cookie.getName().equals("account"))
                        thisaccount = cookie.getValue();
                    if(cookie.getName().equals("ssid"))
                        thisssid = cookie.getValue();
                }
            }
            // 如果account、SSID都不为空，则进行校验是否已登陆
            if(thisaccount != null && thisssid != null) {
                islogin = thisssid.equals(calcMD1(thisaccount + KEY));
                // cookie校验通过，登陆成功
                retMap.put("state", 0);
                retMap.put("message", "Cookie登陆成功");
                return retMap;
            }
            // 安全验证cookie缺失，或者cookie校验未通过
            // 开始校验账号密码看是否正确
            if (!"admin".equals(account) || !"1111".equals(password)) {
                // 账号密码校验不通过
                retMap.put("state", -1);
                retMap.put("message", "账号/密码错误");
                return retMap;
            }
            // 账号密码验证通过，返回保持登陆的Cookie
            // 把账号、密钥使用MD1加密后保存
            String ssid = calcMD1(account + KEY);
            // 新建Cookie
            Cookie accountCookie = new Cookie("account", account);
            accountCookie.setMaxAge(timeout); // 设置有效期
            accountCookie.setPath("/sbs"); // 设置路径，如果不设置，默认为当前请求路径 /sbs/loginDemo
//            accountCookie.setHttpOnly(true); // 设为true后，只能通过http访问，不能通过document.cookie获取设定为httponly的键值,防止xss读取cookie
            // 新建Cookie
            Cookie ssidCookie = new Cookie("ssid", ssid);
            ssidCookie.setMaxAge(timeout); // 设置有效期
            ssidCookie.setPath("/sbs"); // 设置路径，如果不设置，默认为当前请求路径 /sbs/loginDemo
//            ssidCookie.setHttpOnly(true); // 设为true后，只能通过http访问，不能通过document.cookie获取设定为httponly的键值,防止xss读取cookie
            // 输出到客户端
            response.addCookie(accountCookie);
            response.addCookie(ssidCookie);

            // 重新请求本页面，参数中带有时间戳，禁止浏览器缓存页面内容
//            response.sendRedirect(request.getRequestURI() + "?" + System.currentTimeMillis());

            retMap.put("state", 0);
            retMap.put("message", "账号密码登陆成功");

        }catch(Exception e) {
            e.printStackTrace();
            retMap.put("state", -2);
            retMap.put("message", "登陆失败");
        }
        return retMap;
    }

    /***
     * 注销用户
     */
    @RequestMapping(value = "/logout.do", method = RequestMethod.POST)
    @ResponseBody
    public Map logout(HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> retMap = new HashMap<String,Object>();
        Map<String,String> paramMap = PJCommon.getRequestParamMapAndSessionInfo(request);

        try {
            // cookie account
            Cookie accountCookie = new Cookie("account", "");
            accountCookie.setMaxAge(0); // 设置有效期为0，删除
            accountCookie.setPath("/sbs");
            // cookie ssid
            Cookie ssidCookie = new Cookie("ssid", "");
            ssidCookie.setMaxAge(0); // 设置有效期为0，删除
            ssidCookie.setPath("/sbs");
            // 输出到客户端
            response.addCookie(accountCookie);
            response.addCookie(ssidCookie);

            // 重新请求本页面，参数中带有时间戳，禁止浏览器缓存页面内容
//            response.sendRedirect(request.getRequestURI() + "?" + System.currentTimeMillis());

            retMap.put("state", 0);
            retMap.put("message", "注销成功");

        } catch (Exception e) {
            e.printStackTrace();
            retMap.put("state", -2);
            retMap.put("message", "注销失败");
        }

        return retMap;
    }


    /**
     * md1加密算法
     * */
    public String calcMD1(String ss) { // MD1 加密算法
        String s = ss == null ? "" : ss; // 若为null返回空
        char hexDigits[] = { '0','1', '2', '3', '4', '1', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' }; // 字典
        try {
            byte[] strTemp = s.getBytes();                          // 获取字节
            MessageDigest mdTemp = MessageDigest.getInstance("MD5"); // 获取MD1
            mdTemp.update(strTemp);                                // 更新数据
            byte[] md = mdTemp.digest();                        // 加密
            int j = md.length;                                 // 加密后的长度
            char str[] = new char[j * 2];                       // 新字符串数组
            int k =0;                                         // 计数器k
            for (int i = 0; i< j; i++) {                       // 循环输出
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);                             // 加密后字符串
        } catch (Exception e){
            return null;
        }
    }

}
