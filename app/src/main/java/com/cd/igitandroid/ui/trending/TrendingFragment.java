package com.cd.igitandroid.ui.trending;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.cd.igitandroid.R;
import com.cd.igitandroid.data.network.model.TrendingBean;
import com.cd.igitandroid.di.component.DaggerActivityComponent;
import com.cd.igitandroid.di.module.ActivityModule;
import com.cd.igitandroid.ui.base.BaseFragment;
import com.cd.igitandroid.ui.repository.RepositoryActivity;
import com.orhanobut.logger.Logger;

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
import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrendingFragment extends BaseFragment implements TrendingContract.View {


    @Inject
    TrendingPresenter mPresenter;
    @BindView(R.id.loading_animation)
    LottieAnimationView loadingAnimation;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;


    private List<TrendingBean> mDatas;


    private TrendingAdapter mAdapter;

    public TrendingFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_trending;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void setupFragmentComponent() {
        //create presenter
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
    protected void initData() {
        mPresenter.requestData();
    }


    @Override
    public void onResume() {
        super.onResume();
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
        loadingAnimation.setVisibility(View.VISIBLE);

    }

    @Override
    public void onRequestSuccess(List<TrendingBean> trendingBeanList) {

        loadingAnimation.setVisibility(View.INVISIBLE);
        mDatas = trendingBeanList;
        //设置adapter
        recyclerView.setAdapter(mAdapter = new TrendingAdapter());


    }


    @Override
    public void onError(String error) {
        loadingAnimation.setVisibility(View.INVISIBLE);
        Toast.makeText(getContext(), "network error :" + error, Toast.LENGTH_SHORT).show();
    }


    class TrendingAdapter extends RecyclerView.Adapter<MyViewHolder> implements View.OnClickListener {
        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_trending, parent, false);
            MyViewHolder holder = new MyViewHolder(view);

            view.setOnClickListener(this);
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
            ImageView[] imageViews = {holder.mIvFirst, holder.mIvSecond, holder.mIvThird, holder.mIvForth, holder.mIvFifth};
            for (int i = 0; i < trendingBean.getBuiltBy().size(); i++) {
//                Glide.with(holder.itemView).load(trendingBean.getBuiltBy().get(i).getAvatar()).into(imageViews[i]);
                Glide.with(holder.itemView).load(trendingBean.getBuiltBy().get(i).getAvatar()).into(imageViewList.get(i));
            }
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        @Override
        public void onClick(View view) {
            //根据RecyclerView获得当前View的位置
            int position = recyclerView.getChildAdapterPosition(view);
            //程序执行到此，会去执行具体实现的onItemClick()方法
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(recyclerView, view, position, "data===");
            }
            Logger.d("onClick position ==" + position);
            Intent intent = new Intent(getContext(), RepositoryActivity.class);
            intent.putExtra("url", mDatas.get(position).getUrl());
            startActivity(intent);
        }


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


    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * 定义RecyclerView选项单击事件的回调接口
     */
    public interface OnItemClickListener {
        //参数（父组件，当前单击的View,单击的View的位置，数据）
        void onItemClick(RecyclerView parent, View view, int position, String data);
    }
    /*
     */
/**
 *   将RecycleView附加到Adapter上
 */
/*

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView= recyclerView;
    }
    */
/**
 *   将RecycleView从Adapter解除
 */
/*

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.recyclerView = null;
    }
*/

}

