package com.example.app_lehuo;


import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


public class HomeActivity extends ActionBarActivity {
	private WebView mWebView;
	private Handler mHandler=new Handler();
	
    @SuppressLint("JavascriptInterface")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mWebView=(WebView)findViewById(R.id.webview);
        WebSettings webSettings=mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(new Object(){
        	@JavascriptInterface
			public void gotoShoplist(){
        		mHandler.post(new Runnable(){
        			public void run(){
        	    		Intent intent= new Intent();
        	    		intent.setClass(HomeActivity.this,ShoplistActivity.class);
        	    		Bundle bundle=new Bundle();
        	    		intent.putExtras(bundle);
        	    		startActivity(intent);
        			}
        		});
        	} 
        	@JavascriptInterface
        	public void gotoMyfile(){
        		mHandler.post(new Runnable(){
        			public void run(){
        	    		Intent intent= new Intent();
        	    		intent.setClass(HomeActivity.this,MyfileActivity.class);
        	    		Bundle bundle=new Bundle();
        	    		intent.putExtras(bundle);
        	    		startActivity(intent);
        			}
        		});
        	}
        	@JavascriptInterface
        	public void gotoShoplist_hospital(){
        		mHandler.post(new Runnable(){
        			public void run(){
        	    		Intent intent= new Intent();
        	    		intent.setClass(HomeActivity.this,MyQueueActivityHospital.class);
        	    		Bundle bundle=new Bundle();
        	    		intent.putExtras(bundle);
        	    		startActivity(intent);
        			}
        		});
        	}
        }, "home");
        mWebView.loadUrl("file:///android_asset/layout_html/Home.html");
    }
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.shortcut, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.myfile) {
			Intent intent = new Intent();
			intent.setClass(HomeActivity.this, MyfileActivity.class);
			Bundle bundle = new Bundle();
			intent.putExtras(bundle);
			startActivity(intent);
		} else if (id == R.id.about) {
/*			Intent intent = new Intent();
			intent.setClass(ChangePWDActivity.this, AboutActivity.class);
			Bundle bundle = new Bundle();
			intent.putExtras(bundle);
			startActivity(intent);*/
		} else {
			Intent intent = new Intent();
			intent.setClass(HomeActivity.this, HomeActivity.class);
			Bundle bundle = new Bundle();
			intent.putExtras(bundle);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}
}
