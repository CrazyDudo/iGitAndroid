package com.cd.igitandroid.di.module;

import com.cd.igitandroid.ui.issue.IssuePresenter;
import com.cd.igitandroid.ui.login.LoginPresenter;
import com.cd.igitandroid.ui.news.NewsPresenter;
import com.cd.igitandroid.ui.trending.TrendingPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ruandong on 2019/9/18.
 */

@Module
public class ActivityModule {

    public ActivityModule() {
    }

    @Provides
    LoginPresenter provideLoginPresenter() {
        return new LoginPresenter();
    }

    @Provides
    TrendingPresenter provideTrendingPresenter() {
        return new TrendingPresenter();
    }


    @Provides
    NewsPresenter provideNewsPresenter() {
        return new NewsPresenter();
    }

    @Provides
    IssuePresenter provideIssuePresenter() {
        return new IssuePresenter();
    }
}
