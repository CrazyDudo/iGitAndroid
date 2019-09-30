package com.cd.igitandroid.ui.test;

import android.os.Bundle;

import com.cd.igitandroid.R;
import com.mukesh.MarkdownView;

import androidx.appcompat.app.AppCompatActivity;

public class TestingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        MarkdownView markdownView = (MarkdownView) findViewById(R.id.markdown_view);
        markdownView.loadMarkdownFromAssets("README.md");
   /* markdownView.setMarkDownText("# Hello World\nThis is a simple markdown\n"
                + "https://github.com/mukeshsolanki/MarkdownView-Android/");*/

    }
}