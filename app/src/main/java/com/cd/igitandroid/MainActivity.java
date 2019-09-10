package com.cd.igitandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView bnv_001 = findViewById(R.id.navigation);

        //为底部导航设置条目选中监听
        bnv_001.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(
                    @NonNull
                            MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.tab_menu_home:
//                        tv_whichItemSelected.setText(item.getTitle());
                        break;
                    case R.id.tab_menu_discovery:
//                        tv_whichItemSelected.setText(item.getTitle());
                        break;
                    case R.id.tab_menu_attention:
//                        tv_whichItemSelected.setText(item.getTitle());
                        break;
                    case R.id.tab_menu_profile:
//                        tv_whichItemSelected.setText(item.getTitle());
                        break;

                }

                return true;    //这里返回true，表示事件已经被处理。如果返回false，为了达到条目选中效果，还需要下面的代码
                // item.setChecked(true);  不论点击了哪一个，都手动设置为选中状态true（该控件并没有默认实现)
                // 。如果不设置，只有第一个menu展示的时候是选中状态，其他的即便被点击选中了，图标和文字也不会做任何更改

            }
        });

        //默认选中底部导航栏中的第三个
        bnv_001.getMenu().getItem(2).setChecked(true);
    }
}
