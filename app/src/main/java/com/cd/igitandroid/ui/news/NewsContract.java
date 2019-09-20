package com.cd.igitandroid.ui.news;

import com.cd.igitandroid.data.network.model.EventBean;
import com.cd.igitandroid.ui.base.BasePresenter;
import com.cd.igitandroid.ui.base.BaseView;

import java.util.ArrayList;

/**
 * Created by ruandong on 2019/9/12.
 */
public interface NewsContract {

    interface Presenter extends BasePresenter<View> {
        void requestEventData(int page);

        void takeView(View view);
    }

    interface View extends BaseView<Presenter> {
        void onLoading();

        void onSuccess(ArrayList<EventBean> data);

        void onFailed();
    }

}
