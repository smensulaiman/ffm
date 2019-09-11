package com.diufinalproject.sugarsense.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.diufinalproject.sugarsense.R;

public class SurveyWebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_web);

        WebView webView = findViewById(R.id.webView);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url)
            {
                view.loadUrl("javascript:(function() { " +
                        "var head = document.getElementsByClassName('avada-footer-scripts')[0].style.display='none'; "+
                        "var head = document.getElementsByClassName('fusion-text')[0].style.display='none'; " +
                        "})()");

            }
        });

        webView.loadUrl("https://doihaveprediabetes.org/take-the-risk-test/#/");

    }
}
