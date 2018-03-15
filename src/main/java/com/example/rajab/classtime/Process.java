package com.example.rajab.classtime;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class Process extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_process, menu);
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

    public void getApps(View view){

      /*  ActivityManager am = (ActivityManager) mContext
                .getSystemService(Activity.ACTIVITY_SERVICE);
        String packageName = am.getRunningTasks(1).get(0).topActivity
                .getPackageName();
    */

      /*  ActivityManager actvityManager = (ActivityManager)
                this.getSystemService( ACTIVITY_SERVICE );
        List<ActivityManager.RunningAppProcessInfo> procInfos = actvityManager.getRunningAppProcesses();

        String packageName = actvityManager.getRunningTasks(1).get(0).topActivity
                .getPackageName();'

       */

        boolean prof = false;
        TextView proclist = (TextView) findViewById(R.id.TextViewProc);
        String APstr = "";

        System.out.println(prof);
       WifiManager wifiManager =(WifiManager)this.getSystemService(Context.WIFI_SERVICE);
        List<ScanResult> apList = wifiManager.getScanResults();

        for (ScanResult result : apList) {
            APstr += result.SSID + "/n";
            if(result.SSID.equals("Reshiram")){
                prof = true;
            }
        }

        System.out.println(prof);
        proclist.setText(APstr);
        if(prof) {
            wifiManager.setWifiEnabled(false);

            Context context = getApplicationContext();
            CharSequence text = "Turning WiFi OFF";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            String str = "";

            List<ApplicationInfo> packages;
            PackageManager pm;
            pm = getPackageManager();
            //get a list of installed apps.
            packages = pm.getInstalledApplications(0);

            ActivityManager mActivityManager = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);

            for (ApplicationInfo packageInfo : packages) {
                if ((packageInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 1) continue;
                if (packageInfo.packageName.equals("com.example.rajab.classtime")) continue;
                str += packageInfo.packageName + " /n";
                // mActivityManager.killBackgroundProcesses(packageInfo.packageName);
            }

            proclist.setText(str);
            prof = false;
        }else{
            proclist.setText("The Professor Signal is Not Available");
        }

    }

}
