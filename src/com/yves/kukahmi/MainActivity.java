package com.yves.kukahmi;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity {
	ImageButton iBTop, iBRight, iBLeft, iBDown;
	myOnLongClickListener listener = new myOnLongClickListener();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 用findViewById產生實體
		iBTop = (ImageButton) findViewById(R.id.imageButton2);
		iBRight = (ImageButton) findViewById(R.id.imageButton4);
		iBDown = (ImageButton) findViewById(R.id.imageButton1);
		iBLeft = (ImageButton) findViewById(R.id.imageButton3);

		// 為ImageButton註冊長按事件監聽器
		iBTop.setOnLongClickListener(listener);
		iBRight.setOnLongClickListener(listener);
		iBDown.setOnLongClickListener(listener);
		iBLeft.setOnLongClickListener(listener);
	}

	public void onTopClick(View view) {
		Toast.makeText(this, "按下TOP按鈕", Toast.LENGTH_SHORT).show();
		((ImageButton) view).setImageResource(R.drawable.arrow_up);
	}

	public void onRightClick(View view) {
		Toast.makeText(this, "按下RIGHT按鈕", Toast.LENGTH_SHORT).show();
		((ImageButton) view).setImageResource(R.drawable.arrow_right);
	}

	public void onDownClick(View view) {
		Toast.makeText(this, "按下DOWN按鈕", Toast.LENGTH_SHORT).show();
		((ImageButton) view).setImageResource(R.drawable.arrow_down);
	}

	public void onLeftClick(View view) {
		Toast.makeText(this, "按下LEFT按鈕", Toast.LENGTH_SHORT).show();
		((ImageButton) view).setImageResource(R.drawable.arrow_left);
	}

	/**
	 * 
	 * 自定義長按監聽器的類別 myOnLongClickListener
	 * 
	 */
	class myOnLongClickListener implements OnLongClickListener {

		public ConnectServerWithTCPSocket hmiConnect;

		@Override
		public boolean onLongClick(View view) {
			// TODO Auto-generated method stub
			int imageButton = view.getId();
			switch (imageButton) {

			case R.id.imageButton4: {
				Log.d("KUKA", "RIGHT");
				((ImageButton) view).setImageResource(R.drawable.redright);
				hmiConnect = new ConnectServerWithTCPSocket(2);
				new Thread(new Runnable() {
					@Override
					public void run() {
						hmiConnect.start();
					}
				}).start();
				Log.i("Thread", "RightThreadStart");
			}
				break;

			case R.id.imageButton1: {
				Log.d("KUKA", "DOWN");
				((ImageButton) view).setImageResource(R.drawable.reddown);
				hmiConnect = new ConnectServerWithTCPSocket(3);
				new Thread(new Runnable() {
					@Override
					public void run() {
						hmiConnect.start();
					}
				}).start();
				Log.i("Thread", "DownThreadStart");
			}
				break;
			case R.id.imageButton3: {
				Log.d("KUKA", "LEFT");
				((ImageButton) view).setImageResource(R.drawable.redleft);
				hmiConnect = new ConnectServerWithTCPSocket(4);
				new Thread(new Runnable() {
					@Override
					public void run() {
						hmiConnect.start();
					}
				}).start();
				Log.i("Thread", "LeftThreadStart");
			}
				break;
			case R.id.imageButton2: {
				Log.d("KUKA", "TOP");
				((ImageButton) view).setImageResource(R.drawable.redup);
				hmiConnect = new ConnectServerWithTCPSocket(1);
				new Thread(new Runnable() {
					@Override
					public void run() {
						hmiConnect.start();
					}
				}).start();
				Log.i("Thread", "TopThreadStart");
			}
				break;
			}

			return false;// false表示長按按鈕事件結束後會再執行一次onClick的事件
		}

	}
}
