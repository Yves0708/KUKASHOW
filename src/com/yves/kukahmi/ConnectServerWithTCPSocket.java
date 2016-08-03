package com.yves.kukahmi;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;

import javax.net.ssl.HttpsURLConnection;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * Created by Yves on 2015/11/18.
 */
@SuppressLint("HandlerLeak")
public class ConnectServerWithTCPSocket extends Thread {
	int transferNo = 0;
	Socket socket;

	public ConnectServerWithTCPSocket(int c) {
		transferNo = c;
	}

	@Override
	public void run() {
		Log.d("Message", "Send the Message");
		OutputStream out = null;
		OutputStreamWriter osw =null;
		BufferedWriter bw = null; 
		try {
			Log.d("Data", "TransferNumber = " + Integer.toString(transferNo));
			// socket = new Socket("10.0.2.2", 65000); //和NetBeans測試程式用
			socket = new Socket("172.31.1.10", 30003);
			Log.d("Socket", "Socket is opened");
			out = socket.getOutputStream();
			osw = new OutputStreamWriter(out);
			bw = new BufferedWriter(osw);
			bw.write(Integer.toString(transferNo));
			bw.flush();
			Log.d("Data", "Transfer Number Success!!!");

		} catch (IOException e) {
			e.printStackTrace();
			Log.e("Data", "Transfer Number Failed!!!");
		}
		try {
			if (socket != null && socket.isConnected()) {
				bw.close();
				osw.close();
				out.close();
				socket.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
