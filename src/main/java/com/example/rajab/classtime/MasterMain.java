package com.example.rajab.classtime;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static com.example.rajab.classtime.DBConnector.setMobileDataEnabled;


public class MasterMain extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_master_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void start(View view) throws InterruptedException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {

        WifiApManager apman = new WifiApManager(this);

        WifiManager wifiManager = (WifiManager) this.getSystemService(this.WIFI_SERVICE);
        Method getConfigMethod = wifiManager.getClass().getMethod("getWifiApConfiguration");
        WifiConfiguration conf = (WifiConfiguration) getConfigMethod.invoke(wifiManager);
        conf.SSID = "PROF_CLASSTIME";

        apman.setWifiApEnabled(conf, true);

        //TODO: TURN OFF PROFS MOBILE DATA
        //TODO: OR PUT PASSWORD ON PROFESSORS NEW SSID
    }

}
