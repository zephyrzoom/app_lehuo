package com.example.app_lehuo;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class QueueActivityYPX extends ActionBarActivity {
	private WebView mWebView;
	private Handler mHandler = new Handler();
	
	public static String table_queue2 = "19";
	public static String table_queue4 = "19";
	public static String table_queue6 = "19";
	public static String table_queue8 = "19";
	public static String table_queue10 = "19";


	@SuppressLint("JavascriptInterface")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_queue_ypx);

		mWebView = (WebView) findViewById(R.id.webview);
		WebSettings webSettings = mWebView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		mWebView.addJavascriptInterface(new Object() {
			@JavascriptInterface
			public void gotoMyQueue() {
				mHandler.post(new Runnable() {
					public void run() {
						Intent intent = new Intent();
						intent.setClass(QueueActivityYPX.this,
								MyQueueActivity.class);
						Bundle bundle = new Bundle();
						intent.putExtras(bundle);
						startActivity(intent);
					}
				});
			}

			@JavascriptInterface
			public void alert(final int table) {
				mHandler.post(new Runnable() {
					public void run() {

						new AlertDialog.Builder(QueueActivityYPX.this)
								.setTitle("提示")
								.setMessage("确认排队?")
								.setPositiveButton("确定",
										new DialogInterface.OnClickListener() {
											public void onClick(
													DialogInterface dialog,
													int whichButton) {
												switch (table) {
													case 2:
														Integer t2 = Integer.parseInt(table_queue2);
														t2++;
														table_queue2 = t2.toString();
														break;
													case 4:
														Integer t4 = Integer.parseInt(table_queue4);
														t4++;
														table_queue4 = t4.toString();
														break;
													case 6:
														Integer t6 = Integer.parseInt(table_queue6);
														t6++;
														table_queue6 = t6.toString();
														break;
													case 8:
														Integer t8 = Integer.parseInt(table_queue8);
														t8++;
														table_queue8 = t8.toString();
														break;
													case 10:
														Integer t10 = Integer.parseInt(table_queue10);
														t10++;
														table_queue10 = t10.toString();
														break;
												}
												
												loadDb();
												
												startActivity(new Intent(
														QueueActivityYPX.this,
														MyQueueActivity.class));

											}
										})
								.setNegativeButton("取消",
										new DialogInterface.OnClickListener() {
											public void onClick(
													DialogInterface dialog,
													int whichButton) {
												
											}
										}).show();

					}
				});
			}
			
			@JavascriptInterface
			public void loadDb() {
				runOnUiThread(new Runnable() {
					public void run() {
						mWebView.loadUrl("javascript:showDb("+ table_queue2 + "," 
								+ table_queue4 + "," 
								+ table_queue6 + ","
								+ table_queue8 + ","
								+ table_queue10 + ")");
					}
				});
			}

		}, "queue");
		mWebView.loadUrl("file:///android_asset/layout_html/Queue_YPX.html");
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
			intent.setClass(QueueActivityYPX.this, MyfileActivity.class);
			Bundle bundle = new Bundle();
			intent.putExtras(bundle);
			startActivity(intent);
		} else if (id == R.id.about) {
			/*
			 * Intent intent = new Intent();
			 * intent.setClass(ChangePWDActivity.this, AboutActivity.class);
			 * Bundle bundle = new Bundle(); intent.putExtras(bundle);
			 * startActivity(intent);
			 */
		} else {
			Intent intent = new Intent();
			intent.setClass(QueueActivityYPX.this, HomeActivity.class);
			Bundle bundle = new Bundle();
			intent.putExtras(bundle);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}
}
