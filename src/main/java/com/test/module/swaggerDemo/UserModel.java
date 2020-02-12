package com.test.module.swaggerDemo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="user对象", description="登陆用户对象")
public class UserModel {

    @ApiModelProperty(value="用户名", name="username", example="huzhenv5")
    public String username;
    @ApiModelProperty(value="状态", name="state", required=true)
    public Integer state;
    private String password;
    @ApiModelProperty(value="昵称", name="nickName", required=false)
    private String nickName;
    @ApiModelProperty(value="id数组", name="ids")
    public String[] ids;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
    }
}
