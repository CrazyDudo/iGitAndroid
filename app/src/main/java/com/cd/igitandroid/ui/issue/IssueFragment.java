package com.cd.igitandroid.ui.issue;


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
import com.cd.igitandroid.data.network.model.IssueSearchResultBean;
import com.cd.igitandroid.di.component.DaggerActivityComponent;
import com.cd.igitandroid.di.module.ActivityModule;
import com.cd.igitandroid.ui.base.BaseFragment;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * issue fragment
 */
public class IssueFragment extends BaseFragment implements IssueContract.View {


    @BindView(R.id.loading_animation)
    LottieAnimationView loadingAnimation;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.iv_empty)
    ImageView ivEmpty;
    private IssueSearchResultBean mData;

    @Inject
    IssuePresenter mPresenter;


    public IssueFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_issue;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    protected void setupFragmentComponent() {
        DaggerActivityComponent
                .builder()
                .activityModule(new ActivityModule())
                .build()
                .inject(this);
    }

    @Override
    protected void initView() {
        mPresenter.takeView(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void initData() {
        mPresenter.loadData(1);
    }

    @Override
    public void onSuccess(IssueSearchResultBean issueSearchResultBean) {
        loadingAnimation.setVisibility(View.GONE);
        mData = issueSearchResultBean;
        mRecyclerView.setAdapter(new MyAdapter());
    }

    @Override
    public void onLoading() {
        loadingAnimation.setVisibility(View.VISIBLE);
    }

    @Override
    public void onError(String error) {
        Toast.makeText(getContext(), "Empty", Toast.LENGTH_SHORT).show();
        showEmptyView();
    }

    @Override
    public void onEmpty() {
        showEmptyView();
        Toast.makeText(getContext(), "Empty", Toast.LENGTH_SHORT).show();
    }


    public void showEmptyView() {
        loadingAnimation.setVisibility(View.GONE);
        ivEmpty.setVisibility(View.VISIBLE);

    }

    @OnClick(R.id.loading_animation)
    public void onViewClicked() {
    }


    class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.item_issue, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

            holder.mTvTitle.setText(mData.getItems().get(position).getTitle());
//            "https://api.github.com/repos/CrazyDudo/fvip",
            holder.mTvRepoAndTime.setText(mData.getItems().get(position).getRepository_url().substring(29));

            Glide.with(getContext()).load(mData.getItems().get(position).getUser().getAvatar_url()).into(holder.mIvAvatar);

        }


        @Override
        public int getItemCount() {
            return mData.getTotal_count();
        }
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTvTitle;
        private final TextView mTvRepoAndTime;
        private final ImageView mIvAvatar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mTvTitle = itemView.findViewById(R.id.tv_issue_title);
            mTvRepoAndTime = itemView.findViewById(R.id.tv_issue_repo_time);

            mIvAvatar = itemView.findViewById(R.id.iv_avatar);
        }
    }

}

