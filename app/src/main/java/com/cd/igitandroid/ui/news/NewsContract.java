package com.cd.igitandroid.ui.news;

import com.cd.igitandroid.ui.base.BasePresenter;
import com.cd.igitandroid.ui.base.BaseView;

/**
 * Created by ruandong on 2019/9/12.
 */
public interface NewsContract {

    interface Presenter extends BasePresenter {
        void requestData();
    }

    interface View extends BaseView<Presenter> {
        void onLoading();

        void onSuccess();

        void onFailed();
    }

}
