package com.cd.igitandroid.ui.issue;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cd.igitandroid.R;
import com.cd.igitandroid.data.network.model.IssueSearchResultBean;
import com.cd.igitandroid.di.component.DaggerActivityComponent;
import com.cd.igitandroid.di.module.ActivityModule;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * issue fragment
 */
public class IssueFragment extends Fragment implements IssueContract.View {

    private IssueSearchResultBean mData;

    @Inject
    IssuePresenter mPresenter;
    private RecyclerView mRecyclerView;

    public IssueFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_issue, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DaggerActivityComponent
                .builder()
                .activityModule(new ActivityModule())
                .build()
                .inject(this);
        mPresenter.takeView(this);
        mPresenter.loadData(1);

        initView(view);
    }

    private void initView(View view) {

        mRecyclerView = view.findViewById(R.id.recycler_view);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onSuccess(IssueSearchResultBean issueSearchResultBean) {
        mData = issueSearchResultBean;
        mRecyclerView.setAdapter(new MyAdapter());
    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onError(String error) {

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

