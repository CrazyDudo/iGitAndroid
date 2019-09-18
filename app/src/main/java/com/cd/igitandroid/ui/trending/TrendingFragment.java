package com.cd.igitandroid.ui.trending;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cd.igitandroid.R;
import com.cd.igitandroid.data.network.model.TrendingBean;
import com.cd.igitandroid.di.component.DaggerActivityComponent;
import com.cd.igitandroid.di.module.ActivityModule;

import java.util.ArrayList;
import java.util.List;

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
public class TrendingFragment extends Fragment implements TrendingContract.View {


    @Inject
    TrendingPresenter mPresenter;

    private RecyclerView recyclerView;
    private List<TrendingBean> mDatas;
    private ProgressDialog mProgressDialog;


    private TrendingAdapter mAdapter;

    public TrendingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trending, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //create presenter
        DaggerActivityComponent.builder()
                .activityModule(new ActivityModule())
                .build()
                .inject(this);
        initView(view);
        initData();
    }


    private void initData() {
        mPresenter.requestData();
    }

    private void initView(View view) {
        mPresenter.takeView(this);
        recyclerView = view.findViewById(R.id.recycler_view);
        initRecyclerView();
    }

    private void initRecyclerView() {

        //设置布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //设置Item增加、移除动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL));

    }

    @Override
    public void onLoading() {
        mProgressDialog = ProgressDialog.show(getContext(), null, "Loading...");
    }

    @Override
    public void onRequestSuccess(List<TrendingBean> trendingBeanList) {

        mProgressDialog.dismiss();
        mDatas = trendingBeanList;
        //设置adapter
        recyclerView.setAdapter(mAdapter = new TrendingAdapter());
    }


    @Override
    public void onError(String error) {
        mProgressDialog.dismiss();
        Toast.makeText(getContext(), "network error :" + error, Toast.LENGTH_SHORT).show();
    }



    class TrendingAdapter extends RecyclerView.Adapter<TrendingAdapter.MyViewHolder> {
        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(getContext())
                    .inflate(R.layout.item_trending, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            TrendingBean trendingBean = mDatas.get(position);
            holder.mTvName.setText(trendingBean.getAuthor() + "/" + trendingBean.getName());
            holder.mTvDesc.setText(trendingBean.getDescription());
            holder.mTvCurrentStars.setText(trendingBean.getCurrentPeriodStars() + "");
            holder.mTvStar.setText(trendingBean.getStars() + "");
            holder.mTvFork.setText(trendingBean.getForks() + "");
            holder.mTvLanguage.setText(trendingBean.getLanguage());

            List<TrendingBean.BuiltByBean> builtBy = trendingBean.getBuiltBy();
            List<ImageView> imageViewList = new ArrayList<>();
            imageViewList.add(holder.mIvFirst);
            imageViewList.add(holder.mIvSecond);
            imageViewList.add(holder.mIvThird);
            imageViewList.add(holder.mIvForth);
            imageViewList.add(holder.mIvFifth);
            ImageView[] imageViews = {holder.mIvFirst,holder.mIvSecond,holder.mIvThird,holder.mIvForth,holder.mIvFifth};
            for (int i = 0; i < trendingBean.getBuiltBy().size(); i++) {
//                Glide.with(holder.itemView).load(trendingBean.getBuiltBy().get(i).getAvatar()).into(imageViews[i]);
                Glide.with(holder.itemView).load(trendingBean.getBuiltBy().get(i).getAvatar()).into(imageViewList.get(i));
            }
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView mTvName;
            private final TextView mTvDesc;
            private final TextView mTvStar;
            private final TextView mTvFork;
            private final TextView mTvLanguage;
            private final TextView mTvCurrentStars;
            private final ImageView mIvFirst;
            private final ImageView mIvSecond;
            private final ImageView mIvThird;
            private final ImageView mIvForth;
            private final ImageView mIvFifth;


            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                mTvName = itemView.findViewById(R.id.tv_name);
                mTvDesc = itemView.findViewById(R.id.tv_desc);
                mTvCurrentStars = itemView.findViewById(R.id.tv_current_period_stars);
                mTvStar = itemView.findViewById(R.id.tv_star);
                mTvFork = itemView.findViewById(R.id.tv_fork);
                mTvLanguage = itemView.findViewById(R.id.tv_language);
                mIvFirst = itemView.findViewById(R.id.img_first);
                mIvSecond = itemView.findViewById(R.id.img_second);
                mIvThird = itemView.findViewById(R.id.img_third);
                mIvForth = itemView.findViewById(R.id.img_forth);
                mIvFifth = itemView.findViewById(R.id.img_fifth);

            }
        }

    }


}

