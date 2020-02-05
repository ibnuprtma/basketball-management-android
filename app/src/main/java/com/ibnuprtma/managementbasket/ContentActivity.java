package com.ibnuprtma.managementbasket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class ContentActivity extends AppCompatActivity {

    WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);


        web = findViewById(R.id.web);
        web.setWebViewClient(new WebViewClient());
        web.getSettings().setJavaScriptEnabled(true);




        if (savedInstanceState != null) {
            web.restoreState(savedInstanceState);
        } else {
            web.getSettings().setJavaScriptEnabled(true);
            web.getSettings().setUseWideViewPort(true);
            web.getSettings().setLoadWithOverviewMode(true);
            web.getSettings().setSupportZoom(true);
            web.getSettings().setSupportMultipleWindows(true);
            web.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        }

        web.setWebViewClient(new MyWebViewClient());

        try {
            if(!NetworkState.connectionAvailable(ContentActivity.this)){
                Toast.makeText(ContentActivity.this, R.string.check_connection, Toast.LENGTH_SHORT).show();
            }else {

                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(web.getWindowToken(), 0);
                web.loadUrl("https://www.mainbasket.com/");
            }

        }catch (Exception e) {
            e.printStackTrace();
        }


    }
}
