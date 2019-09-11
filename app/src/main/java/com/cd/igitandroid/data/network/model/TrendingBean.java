package com.cd.igitandroid.data.network.model;

import java.util.List;

/**
 * Created by ruandong on 2019/9/11.
 */
public class TrendingBean {

    /**
     * author : pjialin
     * name : py12306
     * avatar : https://github.com/pjialin.png
     * url : https://github.com/pjialin/py12306
     * description : 🚂 12306 购票助手，支持分布式，多账号，多任务购票以及 Web 页面管理
     * language : Python
     * languageColor : #3572A5
     * stars : 4838
     * forks : 1183
     * currentPeriodStars : 254
     * builtBy : [{"username":"pjialin","href":"https://github.com/pjialin","avatar":"https://avatars2.githubusercontent.com/u/12911871"},{"username":"kbj","href":"https://github.com/kbj","avatar":"https://avatars2.githubusercontent.com/u/12494443"},{"username":"Echowxsy","href":"https://github.com/Echowxsy","avatar":"https://avatars2.githubusercontent.com/u/12933379"},{"username":"qinglife","href":"https://github.com/qinglife","avatar":"https://avatars0.githubusercontent.com/u/52159482"},{"username":"BruceDone","href":"https://github.com/BruceDone","avatar":"https://avatars3.githubusercontent.com/u/12979090"}]
     */

    private String author;
    private String name;
    private String avatar;
    private String url;
    private String description;
    private String language;
    private String languageColor;
    private int stars;
    private int forks;
    private int currentPeriodStars;
    private List<BuiltByBean> builtBy;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguageColor() {
        return languageColor;
    }

    public void setLanguageColor(String languageColor) {
        this.languageColor = languageColor;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getForks() {
        return forks;
    }

    public void setForks(int forks) {
        this.forks = forks;
    }

    public int getCurrentPeriodStars() {
        return currentPeriodStars;
    }

    public void setCurrentPeriodStars(int currentPeriodStars) {
        this.currentPeriodStars = currentPeriodStars;
    }

    public List<BuiltByBean> getBuiltBy() {
        return builtBy;
    }

    public void setBuiltBy(List<BuiltByBean> builtBy) {
        this.builtBy = builtBy;
    }

    public static class BuiltByBean {
        /**
         * username : pjialin
         * href : https://github.com/pjialin
         * avatar : https://avatars2.githubusercontent.com/u/12911871
         */

        private String username;
        private String href;
        private String avatar;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }
}
