package com.atornel.tutemy.Activity.Torrent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.atornel.tutemy.R;

import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

public class PostActivity extends AppCompatActivity {
    public HtmlTextView post_content, post_title1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        post_content = (HtmlTextView) findViewById(R.id.post_content);
        post_title1 = (HtmlTextView) findViewById(R.id.post_title1);

        Intent intent = getIntent();
        String passedContent = intent.getStringExtra("content");
        String passedTitle = intent.getStringExtra("title");

        setTitle(passedTitle);

        post_title1.setHtml(passedTitle);
        post_content.setHtml(passedContent,new HtmlHttpImageGetter(post_content));

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
