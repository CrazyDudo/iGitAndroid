package com.cd.igitandroid.data.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by ruandong on 2019/9/19.
 */
@Entity(nameInDb = "AUTH_USER")
public class AuthUser {
    @Id(autoincrement = true)
    private Long id;
    //basic auth
    @Property(nameInDb = "accessToken")
    private String accessToken;
    @Property(nameInDb = "login_id")
    private String loginId;
    @Property(nameInDb = "auth_time")
    private String authTime;
    @Property(nameInDb = "avatar")
    private String avatar;
    @Generated(hash = 164532682)
    public AuthUser(Long id, String accessToken, String loginId, String authTime,
            String avatar) {
        this.id = id;
        this.accessToken = accessToken;
        this.loginId = loginId;
        this.authTime = authTime;
        this.avatar = avatar;
    }
    @Generated(hash = 1740224645)
    public AuthUser() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getAccessToken() {
        return this.accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    public String getLoginId() {
        return this.loginId;
    }
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
    public String getAuthTime() {
        return this.authTime;
    }
    public void setAuthTime(String authTime) {
        this.authTime = authTime;
    }
    public String getAvatar() {
        return this.avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

 
}
