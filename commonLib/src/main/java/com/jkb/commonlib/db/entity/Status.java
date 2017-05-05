package com.jkb.commonlib.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;

import java.io.Serializable;
import java.util.Date;

import org.greenrobot.greendao.annotation.Generated;

/**
 * 状态表
 * Created by yj on 2017/5/4.
 */
@Entity(nameInDb = "status")
public class Status implements Serializable {

    private static final long serialVersionUID = 8154581069678716043L;

    @Id(autoincrement = true)
    private Long id;//默认自增id

    @NotNull
    @Property(nameInDb = "flag_login")
    private boolean flag_login;

    @NotNull
    @Property(nameInDb = "user_id")
    private int user_id;

    @NotNull
    @Property(nameInDb = "create_time")
    private Date create_time;

    public Status() {
    }

    @Generated(hash = 1486184134)
    public Status(Long id, boolean flag_login, int user_id,
            @NotNull Date create_time) {
        this.id = id;
        this.flag_login = flag_login;
        this.user_id = user_id;
        this.create_time = create_time;
    }

    public boolean isFlag_login() {
        return flag_login;
    }

    public void setFlag_login(boolean flag_login) {
        this.flag_login = flag_login;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getFlag_login() {
        return this.flag_login;
    }
}
