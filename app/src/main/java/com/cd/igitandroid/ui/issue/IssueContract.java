package com.cd.igitandroid.ui.issue;

import com.cd.igitandroid.data.network.model.IssueSearchResultBean;
import com.cd.igitandroid.ui.base.BasePresenter;
import com.cd.igitandroid.ui.base.BaseView;

/**
 * Created by ruandong on 2019/9/16.
 */
public class IssueContract {

    interface Presenter extends BasePresenter<View> {
        void loadData(int page);
        void takeView(IssueContract.View view);
    }
    interface View extends BaseView<Presenter> {
        void onSuccess(IssueSearchResultBean issueSearchResultBean);
        void onLoading();
        void onError(String error);
    }


}
