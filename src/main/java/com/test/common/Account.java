package com.test.common;

/**
 * Created by Administrator on 2020/2/14.
 */
public class Account {
    private Integer id;
    /**
     * 联系人名称
     */
    private String userName;
    /**
     * 手机
     */
    private String phone;
    /**
     * 公司名称
     */
    private String company;

    public Account() {
    }

    public Account(Integer id, String userName, String phone, String company) {
        this.id = id;
        this.userName = userName;
        this.phone = phone;
        this.company = company;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
