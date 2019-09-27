package com.cd.igitandroid.data.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by ruandong on 2019/9/27.
 */
@Entity()
public class LocalUser {

    @Id(autoincrement = true)
    private Long id;
    private String login;
    private String name;
    private String avatarUrl;
    private Integer followers;
    private Integer following;
    private Integer repos;
    private String email;
    private String blog;
    private String location;
    @Generated(hash = 1239113844)
    public LocalUser(Long id, String login, String name, String avatarUrl,
            Integer followers, Integer following, Integer repos, String email,
            String blog, String location) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.followers = followers;
        this.following = following;
        this.repos = repos;
        this.email = email;
        this.blog = blog;
        this.location = location;
    }
    @Generated(hash = 173344742)
    public LocalUser() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getLogin() {
        return this.login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAvatarUrl() {
        return this.avatarUrl;
    }
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
    public Integer getFollowers() {
        return this.followers;
    }
    public void setFollowers(Integer followers) {
        this.followers = followers;
    }
    public Integer getFollowing() {
        return this.following;
    }
    public void setFollowing(Integer following) {
        this.following = following;
    }
    public Integer getRepos() {
        return this.repos;
    }
    public void setRepos(Integer repos) {
        this.repos = repos;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getBlog() {
        return this.blog;
    }
    public void setBlog(String blog) {
        this.blog = blog;
    }
    public String getLocation() {
        return this.location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

}
