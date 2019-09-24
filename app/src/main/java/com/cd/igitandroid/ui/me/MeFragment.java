package com.cd.igitandroid.ui.me;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cd.igitandroid.R;
import com.cd.igitandroid.data.network.ApiManager;
import com.cd.igitandroid.data.network.model.LoginResponseBean;
import com.orhanobut.logger.Logger;
import com.vansuita.materialabout.builder.AboutBuilder;
import com.vansuita.materialabout.views.AboutView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends Fragment {


    private LoginResponseBean mData;

    public MeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_me, container, false);
//        return getProfileView();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }



    private View getAboutView() {

        Element versionElement = new Element();
        versionElement.setTitle("Version 6.2");

        View aboutPage = new AboutPage(getContext())
                .isRTL(false)
                .setImage(R.mipmap.github)
                .addItem(versionElement)
//                .addItem(adsElement)
                .addGroup("Connect with us")
                .addEmail("elmehdi.sakout@gmail.com")
                .addWebsite("http://medyo.github.io/")
                .addFacebook("the.medy")
                .addTwitter("medyo80")
                .addYoutube("UCdPQtdWIsg7_pi4mrRu46vA")
                .addPlayStore("com.ideashower.readitlater.pro")
                .addGitHub("medyo")
                .addInstagram("medyo80")
                .create();

        return aboutPage;

    }

    public View getProfileView() {

        AboutView view = AboutBuilder.with(getContext())
                .setPhoto(R.mipmap.profile_picture)
                .setCover(R.mipmap.profile_cover)
                .setName(mData.getLogin())
                .setSubTitle("Mobile Developer")
                .setBrief("I'm warmed of mobile technologies. Ideas maker, curious and nature lover.")
                .setAppIcon(R.mipmap.ic_launcher)
                .setAppName(R.string.app_name)
                .addGooglePlayStoreLink("8002078663318221363")
                .addGitHubLink("user")
                .addFacebookLink("user")
                .addFiveStarsAction()
                .setVersionNameAsAppSubTitle()
                .addShareAction(R.string.app_name)
                .setWrapScrollView(true)
                .setLinksAnimated(true)
                .setShowAsCard(true)
                .build();


//        getActivity().addContentView(view, new ViewGroup.LayoutParams(
//                ViewGroup.LayoutParams.WRAP_CONTENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT));

        return view;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        initData();
    }

    private void initData() {
        String basic = "Y3JhenlkdWRvOmNkMTAwNDQyMjU=";
        ApiManager.getInstance()
                .getLoginService(basic)
                .login()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginResponseBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginResponseBean loginResponseBean) {
                        mData = loginResponseBean;
                        Logger.d(loginResponseBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.d(e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


}
