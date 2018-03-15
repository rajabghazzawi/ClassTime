package com.example.rajab.classtime;

/**
 * Created by Rajab on 4/12/2015.
 */

import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.UnknownHostException;
import com.mongodb.*;

public class DBConnector {



    public DBConnector(){


    }

    public static void setMobileDataEnabled(Context context, boolean enabled) throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

            final ConnectivityManager conman = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            final Class conmanClass = Class.forName(conman.getClass().getName());
            final Field iConnectivityManagerField = conmanClass.getDeclaredField("mService");
            iConnectivityManagerField.setAccessible(true);
            final Object iConnectivityManager = iConnectivityManagerField.get(conman);
            final Class iConnectivityManagerClass = Class.forName(iConnectivityManager.getClass().getName());
            final Method setMobileDataEnabledMethod = iConnectivityManagerClass.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
            setMobileDataEnabledMethod.setAccessible(true);

            setMobileDataEnabledMethod.invoke(iConnectivityManager, enabled);

    }


    public static void profSignUp(BasicDBObject obj){

        MongoClientURI uri  = new MongoClientURI("mongodb://fyp:fyp@ds053139.mongolab.com:53139/appblock-fyp");
        MongoClient client = new MongoClient(uri);
        DB db = client.getDB(uri.getDatabase());

        DBCollection profs = db.getCollection("Professor");

        profs.insert(obj);

    }

    public static boolean validateProfLogin(String user, String pass){

        System.out.println("Before Connection");

        MongoClientURI uri  = new MongoClientURI("mongodb://fyp:fyp@ds053139.mongolab.com:53139/appblock-fyp");
        MongoClient client = new MongoClient(uri);
        DB db = client.getDB(uri.getDatabase());

        System.out.println("After Connection");

        DBCollection profs = db.getCollection("Professor");

        System.out.println("Got the Collection");

        //BasicDBObject findQuery = new BasicDBObject("name", user);
        BasicDBObject findQuery = new BasicDBObject("name", new BasicDBObject("$eq",user));

        DBCursor docs = profs.find(findQuery);

        System.out.println("Queried!");

        String valProfPass = "";

        while(docs.hasNext()) {

            System.out.println("Before doc!");

            DBObject doc = docs.next();

            System.out.println("After docs!" + doc.get("password"));

            valProfPass = doc.get("password").toString();
        }

        System.out.println("Acquired password");

        if(pass.equals(valProfPass)&& !(valProfPass.equals("")))
            return true;
        else
            return false;


    }

}
