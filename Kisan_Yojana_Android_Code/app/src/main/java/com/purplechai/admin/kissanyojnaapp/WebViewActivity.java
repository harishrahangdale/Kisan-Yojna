package com.purplechai.admin.kissanyojnaapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;

/**
 * Created by Harish on 27-05-2018.
 */

public class WebViewActivity extends AppCompatActivity {
    /** Called when the activity is first created. */

    WebView web;
    ProgressBar progressBar;
    String text;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        //Retrieve the url that you put into your intent
        String url = getIntent().getStringExtra("URL_Main");

        // Receiving side
        byte[] data = Base64.decode(url, Base64.DEFAULT);
        try {
            text = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        web = (WebView) findViewById(R.id.webview01);
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);

        web.setWebViewClient(new myWebClient());
        web.getSettings().setUseWideViewPort(true);
        web.getSettings().setLoadWithOverviewMode(true);
        web.getSettings().setSupportZoom(true);
        web.getSettings().setBuiltInZoomControls(true);
        web.getSettings().setDisplayZoomControls(false);
        web.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                getWindow().setTitle(title); //Set Activity tile to page title.
            }
            @Override
            public boolean onJsAlert(WebView view, final String url, String message,
                                     JsResult result) {

                AlertDialog.Builder builder = new AlertDialog.Builder(WebViewActivity.this);
                builder.setMessage(message)
                        .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                arg0.dismiss();
                            }
                        }).show();
                result.cancel();
                return true;
            }
        });
        if (!DetectConnection.checkInternetConnection(this)) {
            Toast.makeText(getApplicationContext(), "No Internet Connection!", Toast.LENGTH_LONG).show();
        } else
            web.loadDataWithBaseURL("", text, "text/html", "UTF-8", "");
    }

    public class myWebClient extends WebViewClient
    {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
            progressBar.setVisibility(View.VISIBLE);
            if (url.endsWith(".pdf")||url.endsWith(".zip")) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                // if want to download pdf manually create AsyncTask here
                // and download file
                return true;
            }
            return false;

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);

            progressBar.setVisibility(View.GONE);
        }
        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            web.loadUrl("file:///android_assets/error.html");

        }
    }

    // To handle "Back" key press event for WebView to go back to previous screen.
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && web.canGoBack()) {
            web.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
