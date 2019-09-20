package com.cd.igitandroid.ui.homepage;

import android.os.Bundle;
import android.view.MenuItem;

import com.cd.igitandroid.R;
import com.cd.igitandroid.ui.issue.IssueFragment;
import com.cd.igitandroid.ui.me.MeFragment;
import com.cd.igitandroid.ui.news.NewsFragment;
import com.cd.igitandroid.ui.pr.PullRequestFragment;
import com.cd.igitandroid.ui.trending.TrendingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class HomeActivity extends AppCompatActivity {


    private BottomNavigationView mBottomNavigationView;

    Fragment mNewsFragment, mIssueFragment, mTrendingFragment, mPullRequestFragment, mMeFragment;

    private List<Fragment> mFragments = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {

        initView();
    }

    private void initView() {
        initFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.home_container, mNewsFragment).commitAllowingStateLoss();
        mBottomNavigationView = findViewById(R.id.navigation);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                switch (item.getItemId()) {
                    case R.id.tab_menu_news:
                        fragment = mNewsFragment;
                        break;
                    case R.id.tab_menu_issue:
                        fragment = mIssueFragment;
                        break;
                    case R.id.tab_menu_trending:
                        fragment = mTrendingFragment;

                        break;
                    case R.id.tab_menu_pr:
                        fragment = mPullRequestFragment;
                        break;

                    case R.id.tab_menu_me:
                        fragment = mMeFragment;
                        break;

                }

                hideAllFragment();

                if (fragment != null) {
                    if (!fragment.isAdded()) {
                        fragmentTransaction.add(R.id.home_container, fragment);
                        fragmentTransaction.show(fragment);
                    } else {
                        fragmentTransaction.show(fragment);
                    }
                    fragmentTransaction.commit();
                }

                //这里返回true，表示事件已经被处理。如果返回false，为了达到条目选中效果，还需要下面的代码
                // item.setChecked(true);  不论点击了哪一个，都手动设置为选中状态true（该控件并没有默认实现)
                // 。如果不设置，只有第一个menu展示的时候是选中状态，其他的即便被点击选中了，图标和文字也不会做任何更改
                return true;
            }
        });

        //default
        mBottomNavigationView.getMenu().getItem(0).setChecked(true);
    }


    private void initFragment() {
        mNewsFragment = new NewsFragment();
        mIssueFragment = new IssueFragment();
        mTrendingFragment = new TrendingFragment();
        mPullRequestFragment = new PullRequestFragment();
        mMeFragment = new MeFragment();

        mFragments.add(mNewsFragment);
        mFragments.add(mIssueFragment);
        mFragments.add(mTrendingFragment);
        mFragments.add(mPullRequestFragment);
        mFragments.add(mMeFragment);
    }

    private void hideAllFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if ((mNewsFragment != null) && !mNewsFragment.isHidden()) {
            ft.hide(mNewsFragment);
        }
        if ((mIssueFragment != null) && !mIssueFragment.isHidden()) {
            ft.hide(mIssueFragment);
        }
        if ((mTrendingFragment != null) && !mTrendingFragment.isHidden()) {
            ft.hide(mTrendingFragment);
        }
        if ((mPullRequestFragment != null) && !mPullRequestFragment.isHidden()) {
            ft.hide(mPullRequestFragment);
        }
        if ((mMeFragment != null) && !mMeFragment.isHidden()) {
            ft.hide(mMeFragment);
        }

        ft.commit();
    }
}