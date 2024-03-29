package com.cd.igitandroid.di.component;

import com.cd.igitandroid.di.module.ActivityModule;
import com.cd.igitandroid.ui.issue.IssueFragment;
import com.cd.igitandroid.ui.login.LoginActivity;
import com.cd.igitandroid.ui.me.MeFragment;
import com.cd.igitandroid.ui.news.NewsFragment;
import com.cd.igitandroid.ui.trending.TrendingFragment;

import dagger.Component;

/**
 * Created by ruandong on 2019/9/18.
 */

@Component(modules = {ActivityModule.class})
public interface ActivityComponent {

    //    void inject(HomeActivity activity);
    void inject(LoginActivity activity);

    void inject(TrendingFragment fragment);

    void inject(NewsFragment fragment);


    void inject(IssueFragment fragment);


    void inject(MeFragment fragment);

}
