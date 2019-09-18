package com.cd.igitandroid.ui.trending;

import com.cd.igitandroid.data.network.model.TrendingBean;
import com.cd.igitandroid.ui.base.BasePresenter;
import com.cd.igitandroid.ui.base.BaseView;

import java.util.List;

/**
 * Created by ruandong on 2019/9/12.
 */
public interface TrendingContract {

    interface Presenter extends BasePresenter<View> {
        void requestData();

        void takeView(View view);
    }

    interface View extends BaseView<Presenter> {
        void onLoading();

        void onRequestSuccess(List<TrendingBean> trendingBeanList);

        void onError(String error);
    }

}
