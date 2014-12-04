package com.example.app_lehuo;


import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MyfileActivity extends ActionBarActivity {
	private WebView mWebView;
	private Handler mHandler=new Handler();
	@SuppressLint("JavascriptInterface") @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myfile);
        mWebView=(WebView)findViewById(R.id.webview);
        WebSettings webSettings=mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(new Object(){
        	//订单列表页
        	@JavascriptInterface
        	public void gotoOrderlist(){
        		mHandler.post(new Runnable(){
        			public void run(){
        	    		Intent intent= new Intent();
        	    		intent.setClass(MyfileActivity.this,OrderlistActivity.class);
        	    		Bundle bundle=new Bundle();
        	    		intent.putExtras(bundle);
        	    		startActivity(intent);
        			}
        		});
        	}
        	//我的预约页
        	@JavascriptInterface
        	public void gotoMyQueue(){
        		mHandler.post(new Runnable(){
        			public void run(){
        	    		Intent intent= new Intent();
        	    		intent.setClass(MyfileActivity.this,MyQueueActivity.class);
        	    		Bundle bundle=new Bundle();
        	    		intent.putExtras(bundle);
        	    		startActivity(intent);
        			}
        		});
        	}
        	//修改密码页
        	@JavascriptInterface
        	public void gotoChangePWD(){
        		mHandler.post(new Runnable(){
        			public void run(){
        	    		Intent intent= new Intent();
        	    		intent.setClass(MyfileActivity.this,ChangePWDActivity.class);
        	    		Bundle bundle=new Bundle();
        	    		intent.putExtras(bundle);
        	    		startActivity(intent);
        			}
        		});
        	}
        }, "myfile");
        mWebView.loadUrl("file:///android_asset/layout_html/Myfile.html");
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
			intent.setClass(MyfileActivity.this, MyfileActivity.class);
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
			intent.setClass(MyfileActivity.this, HomeActivity.class);
			Bundle bundle = new Bundle();
			intent.putExtras(bundle);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}
}
