package com.example.cse_227etp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ProgressBar;

public class WebViewActivity extends AppCompatActivity {

    WebView webView;
    ProgressBar progressBar;
    String stringB = "";
    EditText edtUrl;
    String myurl = "";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webView = findViewById(R.id.web_view);
        progressBar = findViewById(R.id.progress_bar);
        edtUrl = findViewById(R.id.edt_url);

        findViewById(R.id.btn_load_url).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myurl = edtUrl.getText().toString();

                if(myurl.isEmpty()){
                    if (savedInstanceState == null){
                        webView.loadUrl("https://www.google.com");
                    }
                }else{
                    if (savedInstanceState == null){
                        webView.loadUrl(myurl);
                    }
                }


            }
        });

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new KhushiWebViewClient());
        webView.setWebChromeClient(new KhushiWebChromeClient());

    }

    private class KhushiWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            String url = request.getUrl().toString();
            view.loadUrl(url);
            return true;
        }
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            getSupportActionBar().setTitle(view.getTitle());
        }
    }

    private class KhushiWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            //super.onProgressChanged(view, newProgress);
            progressBar.setProgress(newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            //super.onReceivedTitle(view, title);
            stringB = title;
            getSupportActionBar().setTitle(title);

        }

        @Override
        public void onReceivedIcon(WebView view, Bitmap icon) {
            super.onReceivedIcon(view, icon);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.back:
                if(webView.canGoBack())
                    webView.goBack();
                break;
            case R.id.forward:
                if(webView.canGoForward())
                    webView.goForward();
                break;
            case R.id.reload:
                webView.reload();
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();
        }else{
            super.onBackPressed();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        webView.saveState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        webView.restoreState(savedInstanceState);
    }
}
