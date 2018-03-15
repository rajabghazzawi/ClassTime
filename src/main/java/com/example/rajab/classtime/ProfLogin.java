package com.example.rajab.classtime;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.rajab.classtime.DBConnector.validateProfLogin;


public class ProfLogin extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof_login);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_prof_login, menu);
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

    public void PfSinUp(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    public void PfLogin(View view) {


        Context context = getApplicationContext();
        CharSequence text2 = "Login Successful";
        CharSequence text3 = "Login Failed! Incorrect Name or Password!";
        int duration = Toast.LENGTH_SHORT;
        int duration2 = Toast.LENGTH_LONG;

        final Toast toast2 = Toast.makeText(context, text2, duration);
        final Toast toast3 = Toast.makeText(context, text3, duration2);

        TextView nameBox = (TextView) findViewById(R.id.editText8);
        TextView passBox = (TextView) findViewById(R.id.editText9);

        final String name = nameBox.getText().toString();
        final String pass = passBox.getText().toString();

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final Intent intent = new Intent(this, MasterMain.class);


        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    boolean val = validateProfLogin(name, pass);
                   if(val){

                       toast2.show();

                       startActivity(intent);
                       finish();

                   }
                   else{

                      toast3.show();
                   }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();


        /*if( validateProfLogin(name, pass)){



            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked OK button
                    startActivity(intent);
                }
            });

            // 2. Chain together various setter methods to set the dialog characteristics
            builder.setMessage("Login Successful")
                    .setTitle("Confirm?");


        }
        else{

            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked OK button
                }
            });

            // 2. Chain together various setter methods to set the dialog characteristics
            builder.setMessage("Login Failed")
                    .setTitle("Confirm?");
        }
*/
    }
}
