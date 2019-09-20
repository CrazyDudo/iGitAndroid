package com.cd.igitandroid.ui.news;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cd.igitandroid.R;
import com.cd.igitandroid.data.network.model.EventBean;
import com.cd.igitandroid.di.component.DaggerActivityComponent;
import com.cd.igitandroid.di.module.ActivityModule;
import com.cd.igitandroid.utils.DateUtil;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment implements NewsContract.View {

    @Inject
    NewsPresenter mPresenter;

    //    @BindView(R.id.recycler_view_news)
//    RecyclerView recyclerViewNews;
    private ArrayList<EventBean> mData;
    private RecyclerView mRecyclerViewNews;


    public NewsFragment() {
        // Required empty public constructor
        Logger.d("constructor NewsFragment");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DaggerActivityComponent.builder()
                .activityModule(new ActivityModule())
                .build()
                .inject(this);
        mPresenter.takeView(this);
        initData();

        initRecyclerView(view);
        Logger.d("onCreateView");
    }


    @Override
    public void onResume() {
        super.onResume();
        Logger.d("onResume");
    }

    private void initRecyclerView(View view) {

        mRecyclerViewNews = view.findViewById(R.id.recycler_view_news);


        //设置布局管理器
        mRecyclerViewNews.setLayoutManager(new LinearLayoutManager(getContext()));
        //设置Item增加、移除动画
        mRecyclerViewNews.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        mRecyclerViewNews.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL));

    }

    private void initData() {
        mPresenter.requestEventData(1);
    }


    @Override
    public void onLoading() {

    }

    @Override
    public void onSuccess(ArrayList<EventBean> data) {
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
    public void onFailed() {

    }
}
