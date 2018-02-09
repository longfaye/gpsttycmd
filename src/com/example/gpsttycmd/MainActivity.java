package com.example.gpsttycmd;

import org.apache.http.util.ByteArrayBuffer;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
	boolean bl1, bl2, bl3;
	TextView BTRMC, BTGSA, BTGSV;
	RootShellCmd ShellCmd;
	//ifmode com1 rtcm3 off
	//log com1 gpgga ontime 1
	//log com1 gprmc ontime 1
	//log com1 gpgsv ontime 1
	//log com1 gpgsa ontime 1
	//log com1 gpnav ontime 1
	//ifmode com1 rtcm3 on	
	String ifmodecmdoff = "ifmode com1 rtcm3 off\r\n";
	String ggacmd1 = "log com1 gpgga ontime 1\r\n";
	String rmccmd1 = "log com1 gprmc ontime 1\r\n";
	String gsacmd1 = "log com1 gpgsa ontime 1\r\n";
	String gsvcmd1 = "log com1 gpgsv ontime 1\r\n";
	String navcmd1 = "log com1 gpnav ontime 1\r\n";
	String ggacmd0 = "log com1 gpgga ontime 0\r\n";
	String rmccmd0 = "log com1 gprmc ontime 0\r\n";
	String gsacmd0 = "log com1 gpgsa ontime 0\r\n";
	String gsvcmd0 = "log com1 gpgsv ontime 0\r\n";
	String navcmd0 = "log com1 gpnav ontime 0\r\n";
	String ifmodecmdon = "ifmode com1 rtcm3 on\r\n";
	
	byte[] bttest = {(byte) 0x81,(byte) 0x88,0x63,(byte) 0xfe,(byte) 0xee,0x03,0x3,0x30,0x33,(byte) 0x54};	
	
	byte[] btifmodecmdoff = ifmodecmdoff.getBytes();
	byte[] btggacmd1 = ggacmd1.getBytes();
	byte[] btrmccmd1 = rmccmd1.getBytes();
	byte[] btgsacmd1 = gsacmd1.getBytes();
	byte[] btgsvcmd1 = gsvcmd1.getBytes();
	byte[] btnavcmd1 = navcmd1.getBytes();
	byte[] btggacmd0 = ggacmd0.getBytes();
	byte[] btrmccmd0 = rmccmd0.getBytes();
	byte[] btgsacmd0 = gsacmd0.getBytes();
	byte[] btgsvcmd0 = gsvcmd0.getBytes();
	byte[] btnavcmd0 = navcmd0.getBytes();
	byte[] btifmodecmdon = ifmodecmdon.getBytes();
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BTRMC = (TextView) findViewById(R.id.tv_buttonrmc);
        BTGSA = (TextView) findViewById(R.id.tv_buttongsa);
        BTGSV = (TextView) findViewById(R.id.tv_buttongsv);
        ShellCmd = new RootShellCmd();
    }
    
    public static String BytesToStr(byte[] target)
    {
		 StringBuffer buf = new StringBuffer();
		 for (int i = 0, j = target.length; i < j; i++) {
		  buf.append((char) target[i]);
		 }
		 return buf.toString();
    }
    
    public static byte[] StrToBytes(String str) {
		 byte[] buf = new byte[str.length()];
		 for (int i = 0; i < str.length(); i++) {
		  buf[i] = (byte) str.charAt(i);
		 }
		 return buf;
    }
    
    public static String bytetoHexString(byte b){
        String s = Integer.toHexString(b & 0xFF);
        if (s.length() == 1){
            return "0" + s;
        }else{
            return s;
        }
    }
    public static String BytestoHexString(byte[] b){
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < b.length; ++i){
            buffer.append(bytetoHexString(b[i]));
        }
        return buffer.toString();
    }
    
	public void BTRMC(View v) {
		//ShellCmd = new RootShellCmd();
		if (bl1) {
			bl1 = false;
			Log.d("GPSTTYCMD", "BTRMC:" + bl1);
			/*	
			 * 		
			ShellCmd.exec("/system/bin/gpstty "+"\"ifmode com1 rtcm3 off\"");
			ShellCmd.exec("/system/bin/gpstty "+"\"log com1 gprmc ontime 0\"");
			ShellCmd.exec("/system/bin/gpstty "+"\"ifmode com1 rtcm3 on\"");
			Log.d("GPSTTYCMD", "BTRMC 11111:" + bl1);
			//ShellCmd.exec("/system/bin/gpstty "+ "\""+ifmodecmdoff+"\"");
			//ShellCmd.exec("/system/bin/gpstty "+ "\""+rmccmd0+"\"");
			//ShellCmd.exec("/system/bin/gpstty "+ "\""+ifmodecmdon+"\"");
			 * 
			 */
			
			ShellCmd.exec("/system/bin/gpstty "+BytestoHexString(btifmodecmdoff));
			ShellCmd.exec("/system/bin/gpstty "+BytestoHexString(btrmccmd0));
			ShellCmd.exec("/system/bin/gpstty "+BytestoHexString(btifmodecmdon));
			
			ShellCmd.exec("/system/bin/gpstty "+BytestoHexString(bttest));
			BTRMC.setText(0 + "");
		} else {
			bl1 = true;
			Log.d("GPSTTYCMD", "BTRMC:" + bl1);
			/*		
			 * 	
			ShellCmd.exec("/system/bin/gpstty "+"\"ifmode com1 rtcm3 off\"");
			ShellCmd.exec("/system/bin/gpstty "+"\"log com1 gprmc ontime 1\"");
			ShellCmd.exec("/system/bin/gpstty "+"\"ifmode com1 rtcm3 on\"");
			Log.d("GPSTTYCMD", "BTRMC 11111:" + bl1);
			//ShellCmd.exec("/system/bin/gpstty "+ "\""+ ifmodecmdoff+"\"");
			//ShellCmd.exec("/system/bin/gpstty "+ "\""+ rmccmd1+"\"");
			//ShellCmd.exec("/system/bin/gpstty "+ "\""+ ifmodecmdon+"\"");
			 * 
			 */
			
			ShellCmd.exec("/system/bin/gpstty "+BytestoHexString(btifmodecmdoff));
			ShellCmd.exec("/system/bin/gpstty "+BytestoHexString(btrmccmd1));
			ShellCmd.exec("/system/bin/gpstty "+BytestoHexString(btifmodecmdon));
			
			ShellCmd.exec("/system/bin/gpstty "+"\""+BytestoHexString(bttest)+"\"");
			BTRMC.setText(1 + "");
		}

	}
	
	public void BTGSA(View v) {
		//ShellCmd = new RootShellCmd();
		if (bl2) {
			bl2 = false;
			Log.d("GPSTTYCMD", "BTGSA:" + bl2);
			/*
			 * 
			ShellCmd.exec("/system/bin/gpstty "+"\"ifmode com1 rtcm3 off\"");
			ShellCmd.exec("/system/bin/gpstty "+"\"log com1 gpgsa ontime 0\"");
			ShellCmd.exec("/system/bin/gpstty "+"\"ifmode com1 rtcm3 on\"");			
			 *
			 */			
			//ShellCmd.exec("/system/bin/gpstty"+ "\""+ifmodecmdoff+"\"");
			//ShellCmd.exec("/system/bin/gpstty"+ "\""+gsacmd0+"\"");
			//ShellCmd.exec("/system/bin/gpstty"+ "\""+ifmodecmdon+"\"");
			ShellCmd.exec("/system/bin/gpstty "+BytestoHexString(btifmodecmdoff));
			ShellCmd.exec("/system/bin/gpstty "+BytestoHexString(btgsacmd0));
			ShellCmd.exec("/system/bin/gpstty "+BytestoHexString(btifmodecmdon));
			BTGSA.setText(0 + "");
		} else {
			bl2 = true;
			Log.d("GPSTTYCMD", "BTGSA:" + bl2);
			/*
			 * 
			ShellCmd.exec("/system/bin/gpstty "+"\"ifmode com1 rtcm3 off\"");
			ShellCmd.exec("/system/bin/gpstty "+"\"log com1 gpgsa ontime 1\"");
			ShellCmd.exec("/system/bin/gpstty "+"\"ifmode com1 rtcm3 on\"");

			ShellCmd.exec("/system/bin/gpstty "+ "\""+ifmodecmdoff+"\"");
			ShellCmd.exec("/system/bin/gpstty "+ "\""+gsacmd1+"\"");
			ShellCmd.exec("/system/bin/gpstty "+ "\""+ifmodecmdon+"\"");
			 *
			 */
			ShellCmd.exec("/system/bin/gpstty "+BytestoHexString(btifmodecmdoff));
			ShellCmd.exec("/system/bin/gpstty "+BytestoHexString(btgsacmd1));
			ShellCmd.exec("/system/bin/gpstty "+BytestoHexString(btifmodecmdon));
			BTGSA.setText(1 + "");
		}

	}
	
	public void BTGSV(View v) {
		//ShellCmd = new RootShellCmd();		
		if (bl3) {
			bl3 = false;
	        Log.d("GPSTTYCMD", "BTGSV:" + bl3);
	        /*
	         * 
			ShellCmd.exec("/system/bin/gpstty "+"\"ifmode com1 rtcm3 off\"");
			ShellCmd.exec("/system/bin/gpstty "+"\"log com1 gpgsv ontime 0\"");
			ShellCmd.exec("/system/bin/gpstty "+"\"ifmode com1 rtcm3 on\"");

			//ShellCmd.exec("/system/bin/gpstty "+ "\""+ifmodecmdoff+"\"");
			//ShellCmd.exec("/system/bin/gpstty "+ "\""+gsvcmd0+"\"");
			//ShellCmd.exec("/system/bin/gpstty "+ "\""+ifmodecmdon+"\"");	
			 * 
	         */
	        
			ShellCmd.exec("/system/bin/gpstty "+BytestoHexString(btifmodecmdoff));
			ShellCmd.exec("/system/bin/gpstty "+BytestoHexString(btgsvcmd0));
			ShellCmd.exec("/system/bin/gpstty "+BytestoHexString(btifmodecmdon));
			BTGSV.setText(0 + "");
		} else {
			bl3 = true;
			Log.d("GPSTTYCMD", "BTGSV:" + bl3);
	        /*
	         * 
			ShellCmd.exec("/system/bin/gpstty "+"\"ifmode com1 rtcm3 off\"");
			ShellCmd.exec("/system/bin/gpstty "+"\"log com1 gpgsv ontime 1\"");
			ShellCmd.exec("/system/bin/gpstty "+"\"ifmode com1 rtcm3 on\"");

			//ShellCmd.exec("/system/bin/gpstty "+ "\""+ifmodecmdoff+"\"");
			//ShellCmd.exec("/system/bin/gpstty "+ "\""+gsvcmd1+"\"");
			//ShellCmd.exec("/system/bin/gpstty "+ "\""+ifmodecmdon+"\"");	
			 * 
	         */
			ShellCmd.exec("/system/bin/gpstty "+BytestoHexString(btifmodecmdoff));
			ShellCmd.exec("/system/bin/gpstty "+BytestoHexString(btgsvcmd1));
			ShellCmd.exec("/system/bin/gpstty "+BytestoHexString(btifmodecmdon));
			BTGSV.setText(1 + "");
		}

	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
