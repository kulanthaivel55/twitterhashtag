package com.example.twitterhashtagsearch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class TwitterSearchDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter_search_details);

        WebView aWebView = (WebView) findViewById(R.id.webView);
        String aURL="";

        if (getIntent().getExtras()!=null) {
            aURL=getIntent().getStringExtra("post_url");

            aWebView.loadUrl(aURL);
            aWebView.clearCache(true);
            aWebView.clearHistory();
            aWebView.getSettings().setJavaScriptEnabled(true);
            aWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            aWebView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;

                }
            });
        }


    }
}
