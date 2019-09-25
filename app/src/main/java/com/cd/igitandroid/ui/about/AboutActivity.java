package com.cd.igitandroid.ui.about;

import android.os.Bundle;
import android.view.View;

import com.cd.igitandroid.R;

import androidx.appcompat.app.AppCompatActivity;
import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_about);
        Element adsElement = new Element();
        adsElement.setTitle("Advertise with us");
        View aboutPage = new AboutPage(this)
                .isRTL(false)
//                .setDescription("this is a github android app")
                .setImage(R.mipmap.github_logo)
                .addItem(new Element().setTitle("Version 0.7"))
//                .addItem(adsElement)
                .addGroup("Connect with us")
                .addEmail("crazydudo@gmail.com")
                .addWebsite("http://crazydudo.github.io/")
//                .addFacebook("the.medy")
                .addTwitter("@nullpoint@@")
//                .addYoutube("UCdPQtdWIsg7_pi4mrRu46vA")
//                .addPlayStore("com.ideashower.readitlater.pro")
                .addGitHub("crazydudo")
                .addInstagram("crazydudo")
                .create();

        setContentView(aboutPage);

//                addContentView(getAboutView(), new ViewGroup.LayoutParams(
//                ViewGroup.LayoutParams.WRAP_CONTENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT));
    }


}
