package com.example.rajab.classtime;

        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteDatabase.CursorFactory;
        import android.database.sqlite.SQLiteOpenHelper;
        import java.util.ArrayList;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;



public class MySQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "database";

    //Table Definition

    private static final String TABLE_Students = "students";
    private static final String Student_ID = "studentId";
    private static final String Student_Name = "name";
    private static final String Student_Major = "major";
    private static final String Student_Department = "department";


    private static final String TABLE_Professor = "professor";
    private static final String Professor_ID = "profId";
    private static final String Professor_Name = "name";
    private static final String Professor_Department = "department";
    private static final String Professor_Faculty = "faculty";


    private static final String TABLE_Class = "class";
    private static final String Class_ID = "classId";
    private static final String Class_Section = "section";
    private static final String Class_Code = "code";
    private static final String Class_Name = "name";
    private static final String Class_ProfID = "profId";


    private static final String TABLE_ClassStudent = "classstudent";
    private static final String ClassStudent_ID = "relId";
    private static final String ClassStudent_ClassID = "classId";
    private static final String ClassStudent_StudentID = "studentId";


    // Database creation SQL statement

    private static final String CREATE_TABLE_Students = "CREATE TABLE IF NOT EXISTS " + TABLE_Students + "(" + Student_ID
            + " TEXT," + Student_Name + " TEXT," + Student_Major + " TEXT,"+ Student_Department + " TEXT);";

    private static final String CREATE_TABLE_Professor = "CREATE TABLE IF NOT EXISTS " + TABLE_Professor + "(" + Professor_ID
            + " TEXT," + Professor_Name + " TEXT," + Professor_Department + " TEXT," + Professor_Faculty +" TEXT);";

    private static final String CREATE_TABLE_Class = "CREATE TABLE IF NOT EXISTS " + TABLE_Class + "(" + Class_ID
            + " TEXT," + Class_Section + " TEXT," + Class_Code + " TEXT," + Class_Name + " TEXT,"+ Class_ProfID +" TEXT);";

    private static final String CREATE_TABLE_ClassStudent = "CREATE TABLE IF NOT EXISTS " + TABLE_ClassStudent + "(" + ClassStudent_ID
            + " TEXT," + ClassStudent_ClassID + " TEXT," + ClassStudent_StudentID  + " TEXT);";

    //Constructor

    public MySQLiteHelper(Context context) { super(context, DATABASE_NAME, null, 1); }


    public MySQLiteHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //Method Definition

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_Students);
        db.execSQL(CREATE_TABLE_Professor);
        db.execSQL(CREATE_TABLE_Class);
        db.execSQL(CREATE_TABLE_ClassStudent);


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Students);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Professor);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Class);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ClassStudent);
        onCreate(db);
    }



    //adding users
    public void addstudent(String ID, String name, String major, String dept) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();


        values.put(Student_ID, ID);
        values.put(Student_Name, name);
        values.put(Student_Major, major);
        values.put(Student_Department, dept);
        // Inserting Row
        db.insert(TABLE_Students, null, values);
        db.close(); // Closing database connection
    }


    public void addprofessor(String ID, String name, String dept, String faculty) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();


        values.put(Professor_ID, ID);
        values.put(Professor_Name, name);
        values.put(Professor_Department, dept);
        values.put(Professor_Faculty, faculty);
        // Inserting Row
        db.insert(TABLE_Professor, null, values);
        db.close(); // Closing database connection
    }

    public void addclass(String ID, String section, String code, String name, String pid) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();


        values.put(Class_ID, ID);
        values.put(Class_Section, section);
        values.put(Class_Code, code);
        values.put(Class_Name, name);
        values.put(Class_ProfID, pid);
        // Inserting Row
        db.insert(TABLE_Class, null, values);
        db.close(); // Closing database connection
    }

    public void register(String ID, String cid, String sid) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();


        values.put(ClassStudent_ID, ID);
        values.put(ClassStudent_ClassID, cid);
        values.put(ClassStudent_StudentID, sid);
        // Inserting Row
        db.insert(TABLE_ClassStudent, null, values);
        db.close(); // Closing database connection
    }






    //deleting users
    public void deletestudent(String id) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("delete from "+TABLE_Students+" where Student_ID='"+id+"'");

        db.close(); // Closing database connection
    }

    public void deleteprof(String id) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("delete from "+TABLE_Professor+" where Professor_ID='"+id+"'");

        db.close(); // Closing database connection
    }

    public void deleteClass(String id) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("delete from "+TABLE_Class+" where Class_ID='"+id+"'");

        db.close(); // Closing database connection
    }


  /*  public ArrayList<String> getTimeAcc(String time) {

        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<String> accList = new ArrayList<String>();

        String mySQLQuery = "SELECT * FROM " + TABLE_Students + "WHERE timestampAcc = " + time;

        Cursor cursor = db.rawQuery(mySQLQuery, null);

        try {
            if (cursor.moveToFirst()) {
                do {

                    accList.add(cursor.getString(0));//phone number
                    accList.add(cursor.getString(1));//measurement date
                    accList.add(cursor.getString(2));//measurement accx
                    accList.add(cursor.getString(3));//measurement accy
                    accList.add(cursor.getString(4));//measurement accz
                    accList.add(cursor.getString(5));//magnitude acceleration


                }
                while (cursor.moveToNext());
            }

            return accList;

        }
        finally {
            cursor.close();
        }
    }

    public ArrayList<String> getAcc() {

        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<String> accList = new ArrayList<String>();

        String mySQLQuery = "SELECT * FROM " + TABLE_Students;

        Cursor cursor = db.rawQuery(mySQLQuery, null);

        try {
            if (cursor.moveToFirst()) {
                do {

                    accList.add(cursor.getString(0));//phone number
                    accList.add(cursor.getString(1));//measurement date
                    accList.add(cursor.getString(2));//measurement accx
                    accList.add(cursor.getString(3));//measurement accy
                    accList.add(cursor.getString(4));//measurement accz
                    accList.add(cursor.getString(5));//magnitude acceleration


                }
                while (cursor.moveToNext());
            }

            return accList;

        }
        finally {
            cursor.close();
        }
    }

    public ArrayList<String> getLight() {

        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<String> lightList = new ArrayList<String>();

        String mySQLQuery = "SELECT "+ Class_ID +","+ Class_Section +","+ Class_Code+ " FROM " + TABLE_Class;

        Cursor cursor = db.rawQuery(mySQLQuery, null);

        try {
            if (cursor.moveToFirst()) {
                do {

                    lightList.add(cursor.getString(0));//phone number
                    lightList.add(cursor.getString(1));//measurement date
                    lightList.add(cursor.getString(2));//light intensity


                }
                while (cursor.moveToNext());
            }

            return lightList;
        }
        finally {
            cursor.close();
        }
    }

    public ArrayList<String> getMag() {

        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<String> magList = new ArrayList<String>();

        String mySQLQuery = "SELECT "+ Professor_ID +","+ Professor_Name+","+ Professor_Department+ " FROM " + TABLE_Professor;

        Cursor cursor = db.rawQuery(mySQLQuery, null);

        try {
            if (cursor.moveToFirst()) {
                do {

                    magList.add(cursor.getString(0));//phone number
                    magList.add(cursor.getString(1));//measurement date
                    magList.add(cursor.getString(2));//Professor_Department


                }
                while (cursor.moveToNext());
            }

            return magList;
        }
        finally {
            cursor.close();
        }
    }

    public ArrayList<String> getGravity() {

        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<String> gravityList = new ArrayList<String>();

        String mySQLQuery = "SELECT "+ ClassStudent_ID +"," + ClassStudent_ClassID +"," + ClassStudent_StudentID + "," + Gravity_Y + "," + Gravity_Z + " FROM " + TABLE_ClassStudent;

        Cursor cursor = db.rawQuery(mySQLQuery, null);

        try {
            if (cursor.moveToFirst()) {
                do {

                    gravityList.add(cursor.getString(0));//phone number
                    gravityList.add(cursor.getString(1));//measurement date
                    gravityList.add(cursor.getString(2));//measurement gravityx
                    gravityList.add(cursor.getString(3));//measurement gravityy
                    gravityList.add(cursor.getString(4));//measurement gravityz



                }
                while (cursor.moveToNext());
            }

            return gravityList;

        }
        finally {
            cursor.close();
        }
    }

    public ArrayList<String> getGyro() {

        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<String> gyroList = new ArrayList<String>();

        String mySQLQuery = "SELECT "+ PHONE_NUMBER_Gyro +"," + TIME_STAMP_Gyro +"," + Gyro_X + "," + Gyro_Y + "," + Gyro_Z + " FROM " + TABLE_Gyro;

        Cursor cursor = db.rawQuery(mySQLQuery, null);

        try {
            if (cursor.moveToFirst()) {
                do {

                    gyroList.add(cursor.getString(0));//phone number
                    gyroList.add(cursor.getString(1));//measurement date
                    gyroList.add(cursor.getString(2));//measurement gyrox
                    gyroList.add(cursor.getString(3));//measurement gyroy
                    gyroList.add(cursor.getString(4));//measurement gyroz



                }
                while (cursor.moveToNext());
            }

            return gyroList;

        }
        finally {
            cursor.close();
        }
    }

    public ArrayList<String> getProx() {

        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<String> proxList = new ArrayList<String>();

        String mySQLQuery = "SELECT "+ PHONE_NUMBER_Prox +","+ TIME_STAMP_Prox+","+ PROXIMITY + " FROM " + TABLE_Prox;

        Cursor cursor = db.rawQuery(mySQLQuery, null);

        try {
            if (cursor.moveToFirst()) {
                do {

                    proxList.add(cursor.getString(0));//phone number
                    proxList.add(cursor.getString(1));//measurement date
                    proxList.add(cursor.getString(2));//Proximity


                }
                while (cursor.moveToNext());
            }

            return proxList;
        }
        finally {
            cursor.close();
        }
    }

    public ArrayList<String> getUser() {

        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<String> userList = new ArrayList<String>();

        String mySQLQuery = "SELECT "+ PHONE_NUMBER_Users +","+ PERSONAL_ID +","+  FIRST_NAME +"," + LAST_NAME + " FROM " + TABLE_Users;

        Cursor cursor = db.rawQuery(mySQLQuery, null);

        try {
            if (cursor.moveToFirst()) {
                do {

                    userList.add(cursor.getString(0));//phone number
                    userList.add(cursor.getString(1));//personal id
                    userList.add(cursor.getString(2));//firstname
                    userList.add(cursor.getString(3));//lastname


                }
                while (cursor.moveToNext());
            }

            return userList;
        }
        finally {
            cursor.close();
        }
    }

    public ArrayList<String> getPhone() {

        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<String> phoneList = new ArrayList<String>();

        String mySQLQuery = "SELECT "+ PHONE_NUMBER_Phones +","+ BRAND_NAME +","+ MODEL + " FROM " + TABLE_Phones;

        Cursor cursor = db.rawQuery(mySQLQuery, null);

        try {
            if (cursor.moveToFirst()) {
                do {

                    phoneList.add(cursor.getString(0));//phone number
                    phoneList.add(cursor.getString(1));//Brand name
                    phoneList.add(cursor.getString(2));//model



                }
                while (cursor.moveToNext());
            }

            return phoneList;
        }
        finally {
            cursor.close();
        }
    }



    public ArrayList<String> getRelation() {

        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<String> relationList = new ArrayList<String>();

        String mySQLQuery = "SELECT "+ PHONE_NUMBER_Relation +","+ PERSONAL_ID_Realtion+ " FROM " + TABLE_Has;

        Cursor cursor = db.rawQuery(mySQLQuery, null);

        try {
            if (cursor.moveToFirst()) {
                do {

                    relationList.add(cursor.getString(0));//phone number
                    relationList.add(cursor.getString(1));//pid

                }
                while (cursor.moveToNext());
            }

            return relationList;
        }
        finally {
            cursor.close();
        }
    }

*/
}