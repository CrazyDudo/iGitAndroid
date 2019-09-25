package com.cd.igitandroid.ui.me;

import com.cd.igitandroid.data.network.model.UserBean;
import com.cd.igitandroid.ui.base.BasePresenter;
import com.cd.igitandroid.ui.base.BaseView;

/**
 * Created by ruandong on 2019/9/25.
 */
public interface MeContract {

    interface Presenter extends BasePresenter<View> {
        void loadData();

        void takeView(View view);
    }

    interface View extends BaseView<Presenter> {

        void onLoading();

        void onSuccess(UserBean userBean);

        void onError(String error);
    }


}
