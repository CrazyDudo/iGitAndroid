package com.cd.igitandroid.ui.news;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.cd.igitandroid.R;
import com.cd.igitandroid.data.network.model.EventBean;
import com.cd.igitandroid.di.component.DaggerActivityComponent;
import com.cd.igitandroid.di.module.ActivityModule;
import com.cd.igitandroid.ui.base.BaseFragment;
import com.cd.igitandroid.utils.DateUtil;

import java.util.ArrayList;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends BaseFragment implements NewsContract.View {

    @Inject
    NewsPresenter mPresenter;
    @BindView(R.id.loading_animation)
    LottieAnimationView loadingAnimation;
    @BindView(R.id.recycler_view_news)
    RecyclerView mRecyclerViewNews;
    @BindView(R.id.iv_empty)
    ImageView ivEmpty;

    private ArrayList<EventBean> mData;

    public NewsFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news;
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
        mPresenter.takeView(this);
        initRecyclerView();
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    private void initRecyclerView() {
        //设置布局管理器
        mRecyclerViewNews.setLayoutManager(new LinearLayoutManager(getContext()));
        //设置Item增加、移除动画
        mRecyclerViewNews.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        mRecyclerViewNews.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL));

    }


    protected void initData() {
        mPresenter.requestEventData(1);
    }


    @Override
    public void onLoading() {
        loadingAnimation.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSuccess(ArrayList<EventBean> data) {
        loadingAnimation.setVisibility(View.GONE);
        mData = data;
        mRecyclerViewNews.setAdapter(new NewsAdapter());
    }

    class NewsAdapter extends RecyclerView.Adapter<MyViewHolder> {

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.item_news, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            EventBean eventBean = mData.get(position);
            holder.mTvMsg.setText(eventBean.getActor().getDisplay_login() + " " + eventBean.getPayload().getAction() + " " + eventBean.getRepo().getName());
            holder.mTvTime.setText(DateUtil.getTimeGap(getContext(), DateUtil.StringToDate(DateUtil.getDate(eventBean.getCreated_at()))));

            Glide.with(getContext()).load(eventBean.getActor().getAvatar_url()).into(holder.mIvAvatar);
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
    }


    class MyViewHolder extends RecyclerView.ViewHolder {


        private final TextView mTvMsg;
        private final TextView mTvTime;
        private final ImageView mIvAvatar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mIvAvatar = itemView.findViewById(R.id.iv_avatar);
            mTvMsg = itemView.findViewById(R.id.tv_news_msg);
            mTvTime = itemView.findViewById(R.id.tv_news_time);

        }
    }


    @Override
    public void onError(String e) {
        loadingAnimation.setVisibility(View.GONE);
        Toast.makeText(getContext(), "" + e, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEmpty() {
        loadingAnimation.setVisibility(View.GONE);
        ivEmpty.setVisibility(View.VISIBLE);
        Toast.makeText(getContext(), "Empty", Toast.LENGTH_SHORT).show();
    }
}
