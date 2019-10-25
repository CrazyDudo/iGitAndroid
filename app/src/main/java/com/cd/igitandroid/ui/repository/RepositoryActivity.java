package com.cd.igitandroid.ui.repository;

import android.os.Bundle;

import com.cd.igitandroid.R;
import com.mukesh.MarkdownView;

import androidx.appcompat.app.AppCompatActivity;

public class RepositoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository);
        initData();
    }

    private void initData() {
        String url = getIntent().getStringExtra("url");
        MarkdownView markdownView = findViewById(R.id.markdown_view_readme);
        markdownView.loadUrl(url);
    }
}
