package edu.manuel.android.login.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import edu.manuel.android.login.model.User;

/**
 * Created by postgres on 21/9/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "UserManager.db";

    private static final String TABLE_USER = "user";

    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";

    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT," + COLUMN_USER_PASSWORD + " TEXT" + ")";

    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;

    public DatabaseHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
      sqLiteDatabase.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(DROP_USER_TABLE);
        onCreate(sqLiteDatabase);
    }

    public void addUsuario(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getNombre());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getContraseña());

        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public boolean checkUsuario(String email, String password) {
        String[] columns = {COLUMN_USER_ID};
        SQLiteDatabase db = this.getWritableDatabase();
        String seleccion = COLUMN_USER_EMAIL + " = ?";
        String[] seleccionArgs = {email};

        Cursor cursor = db.query(TABLE_USER,
                columns,
                seleccion,
                seleccionArgs,
                null,
                null,
                null);

        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount >0) {
            return true;
        }
        return false;
    }

    public boolean checkUsuario(String email) {
        String[] columns = {COLUMN_USER_ID};
        SQLiteDatabase db = this.getWritableDatabase();
        String seleccion = COLUMN_USER_EMAIL + " = ?";
        String[] seleccionArgs = {email};

        Cursor cursor = db.query(TABLE_USER,
                columns,
                seleccion,
                seleccionArgs,
                null,
                null,
                null);

        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount >0) {
            return true;
        }
        return false;
    }
}
