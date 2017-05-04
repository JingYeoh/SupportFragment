package com.jkb.commonlib.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Unique;

import java.io.Serializable;
import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 用户表
 * Created by yj on 2017/5/4.
 */
@Entity(nameInDb = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 8172841707427710021L;

    @Id(autoincrement = true)
    private Long id;//默认自增id

    @Property(nameInDb = "user_id")
    @NotNull
    @Unique
    private int userId;//用戶id

    @Property(nameInDb = "password")
    @NotNull
    private String password;//密码

    @Property(nameInDb = "phone")
    @NotNull
    @Unique
    private String phone;//手机号

    @Property(nameInDb = "nickname")
    @NotNull
    private String nickname;//昵称

    @Property(nameInDb = "avatar")
    @NotNull
    private String avatar;//默认头像

    @NotNull
    @Property(nameInDb = "update_time")
    private Date updateTime;//创建时间

    public User() {
    }

    @Generated(hash = 2091331386)
    public User(Long id, int userId, @NotNull String password,
            @NotNull String phone, @NotNull String nickname, @NotNull String avatar,
            @NotNull Date updateTime) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.phone = phone;
        this.nickname = nickname;
        this.avatar = avatar;
        this.updateTime = updateTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
