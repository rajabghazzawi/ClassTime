package com.example.rajab.classtime;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mongodb.BasicDBObject;

import static com.example.rajab.classtime.DBConnector.profSignUp;
import static com.example.rajab.classtime.DBConnector.validateProfLogin;


public class SignUpActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Spinner dropdown = (Spinner)findViewById(R.id.spinner);
        String[] items = new String[]{"Select", "FAS", "FAFS", "OSB", "FM", "FEA", "FHS", "DUIP"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        dropdown.setAdapter(adapter);

        Spinner dropdown2 = (Spinner)findViewById(R.id.spinner2);
        String[] items2 = new String[]{"Select", "ECE", "CIVE", "ARCH & DES", "ME", "CE", "ENMG"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items2);
        dropdown2.setAdapter(adapter2);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_up, menu);
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

    public void submit(View view) {

        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);

        String faculty = spinner.getSelectedItem().toString();
        String Department = spinner2.getSelectedItem().toString();

        TextView nameBox = (TextView) findViewById(R.id.editText);
        TextView passBox = (TextView) findViewById(R.id.editText2);

        final String name = nameBox.getText().toString();
        String pass = passBox.getText().toString();

        final BasicDBObject prof = new BasicDBObject();
        prof.put("name", name);
        prof.put("faculty", faculty);
        prof.put("department", Department);
        prof.put("password", pass);

        if (name.equals("") || faculty.equals("") || Department.equals("") || pass.equals("")) {
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked OK button
                }
            });

            builder.setMessage("You have to fill up all the fields!").setTitle("Oops! You missed a spot!");
            AlertDialog dialog = builder.create();

            dialog.show();
        } else {
            Thread thread = new Thread(new Runnable(){
                @Override
                public void run() {
                    try {
                        profSignUp(prof);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            thread.start();



            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked OK button

                    Context context = getApplicationContext();
                    CharSequence text = "Welcome Professor " + name + "! Signing up...";
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            });

            // 2. Chain together various setter methods to set the dialog characteristics
            builder.setMessage(faculty + " " + Department + " " + name + " " + pass)
                    .setTitle("Confirm?");


            // 3. Get the AlertDialog from create()
            AlertDialog dialog = builder.create();

            dialog.show();
        }
    }

}
