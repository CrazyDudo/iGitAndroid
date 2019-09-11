package com.cd.igitandroid.ui.homepage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.cd.igitandroid.R;
import com.cd.igitandroid.ui.issue.IssueFragment;
import com.cd.igitandroid.ui.me.MeFragment;
import com.cd.igitandroid.ui.news.NewsFragment;
import com.cd.igitandroid.ui.pr.PullRequestFragment;
import com.cd.igitandroid.ui.trending.TrendingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView mBottomNavigationView;

    private Fragment[] mFragments;

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
        mFragments = initFragment();
        mBottomNavigationView = findViewById(R.id.navigation);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                switch (item.getItemId()) {
                    case R.id.tab_menu_news:
                        fragment = mFragments[0];
                        break;
                    case R.id.tab_menu_issue:
                        fragment = mFragments[1];
                        break;
                    case R.id.tab_menu_trending:
                        fragment = mFragments[2];
                        break;
                    case R.id.tab_menu_pr:
                        fragment = mFragments[3];
                        break;

                    case R.id.tab_menu_me:
                        fragment = mFragments[4];
                        break;

                }

                if (fragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.home_container, fragment).commit();
                }

                //这里返回true，表示事件已经被处理。如果返回false，为了达到条目选中效果，还需要下面的代码
                // item.setChecked(true);  不论点击了哪一个，都手动设置为选中状态true（该控件并没有默认实现)
                // 。如果不设置，只有第一个menu展示的时候是选中状态，其他的即便被点击选中了，图标和文字也不会做任何更改
                return true;

            }
        });

        //default
        mBottomNavigationView.getMenu().getItem(2).setChecked(true);
    }

    private Fragment[] initFragment() {

        Fragment fragments[] = new Fragment[5];
        fragments[0] = new NewsFragment();
        fragments[1] = new IssueFragment();
        fragments[2] = new TrendingFragment();
        fragments[3] = new PullRequestFragment();
        fragments[4] = new MeFragment();
        return fragments;
    }


}
