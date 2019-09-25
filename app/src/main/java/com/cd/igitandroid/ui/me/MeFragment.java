package com.cd.igitandroid.ui.me;


import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.cd.igitandroid.R;
import com.cd.igitandroid.data.network.model.UserBean;
import com.cd.igitandroid.di.component.DaggerActivityComponent;
import com.cd.igitandroid.di.module.ActivityModule;
import com.cd.igitandroid.ui.about.AboutActivity;
import com.cd.igitandroid.ui.base.BaseFragment;

import javax.inject.Inject;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends BaseFragment implements MeContract.View {

    @Inject
    MePresenter mMePresenter;
    @BindView(R.id.iv_profile_photo)
    CircleImageView ivProfilePhoto;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_followers_num)
    TextView tvFollowersNum;
    @BindView(R.id.tv_following_num)
    TextView tvFollowingNum;
    @BindView(R.id.tv_repos_num)
    TextView tvReposNum;
    @BindView(R.id.card)
    CardView card;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.rl_email)
    RelativeLayout rlEmail;
    @BindView(R.id.tv_blog)
    TextView tvBlog;
    @BindView(R.id.rl_blog)
    RelativeLayout rlBlog;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.rl_location)
    RelativeLayout rlLocation;
    @BindView(R.id.card_user_info)
    CardView cardUserInfo;
    @BindView(R.id.rl_feedback)
    RelativeLayout rlFeedback;
    @BindView(R.id.rl_about)
    RelativeLayout rlAbout;
    @BindView(R.id.card_user_about)
    CardView cardUserAbout;
    @BindView(R.id.animation_view3)
    LottieAnimationView animationView3;

    private UserBean mData;

    public MeFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void setupFragmentComponent() {

        DaggerActivityComponent.builder()
                .activityModule(new ActivityModule())
                .build()
                .inject(this);
    }

    @Override
    protected void initView() {
        mMePresenter.takeView(this);

    }





    protected void initData() {
        mMePresenter.loadData();
    }


    @OnClick({R.id.iv_profile_photo, R.id.rl_feedback, R.id.rl_about})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_profile_photo:
                Toast.makeText(getContext(), "profile photo", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_feedback:
                Toast.makeText(getContext(), "rl_feedback", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_about:
                Toast.makeText(getContext(), "rl_about", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getContext(), AboutActivity.class));
                break;
        }
    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onSuccess(UserBean userBean) {
        mData = userBean;
        updateUerInfo(userBean);
    }

    private void updateUerInfo(UserBean userBean) {

        Glide.with(getContext()).load(mData.getAvatar_url()).into(ivProfilePhoto);
        tvUserName.setText(mData.getLogin());
    }

    @Override
    public void onError(String error) {
        Toast.makeText(getContext(), "" + error, Toast.LENGTH_SHORT).show();
    }
}
