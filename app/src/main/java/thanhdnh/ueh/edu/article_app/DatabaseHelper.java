package thanhdnh.ueh.edu.article_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "UserDatabase.db";
    private static final int DATABASE_VERSION = 2; // Nâng version để làm mới DB

    private static final String TABLE_USER = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_UNAME = "uname";
    private static final String COLUMN_PASS = "password";
    private static final String COLUMN_URL = "urlProfile";
    private static final String COLUMN_BIO = "shortBio";
    private static final String COLUMN_GENDER = "gender";
    private static final String COLUMN_AGE = "age";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_USER + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_UNAME + " TEXT, "
                + COLUMN_PASS + " TEXT, "
                + COLUMN_URL + " TEXT, "
                + COLUMN_BIO + " TEXT, "
                + COLUMN_GENDER + " TEXT, "
                + COLUMN_AGE + " INTEGER)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_UNAME, user.getUname());
        values.put(COLUMN_PASS, user.getPassword());
        values.put(COLUMN_URL, user.getUrlProfile());
        values.put(COLUMN_BIO, user.getShortBio());
        values.put(COLUMN_GENDER, user.getGender());
        values.put(COLUMN_AGE, user.getAge());

        db.insert(TABLE_USER, null, values);
        db.close();
    }

    // Lấy toàn bộ danh sách User
    public List<User> getAllUsers() {
        return getUsersFiltered(null, 0);
    }

    // Hàm Lọc User theo Giới tính và Tuổi tối thiểu
    public List<User> getUsersFiltered(String gender, int minAge) {
        List<User> userList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = null;
        List<String> selectionArgsList = new ArrayList<>();

        if (gender != null && !gender.equalsIgnoreCase("Tất cả")) {
            selection = COLUMN_GENDER + " = ?";
            selectionArgsList.add(gender);
        }

        if (minAge > 0) {
            if (selection != null) {
                selection += " AND " + COLUMN_AGE + " >= ?";
            } else {
                selection = COLUMN_AGE + " >= ?";
            }
            selectionArgsList.add(String.valueOf(minAge));
        }

        String[] selectionArgs = selectionArgsList.isEmpty() ? null : selectionArgsList.toArray(new String[0]);
        Cursor cursor = db.query(TABLE_USER, null, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
                String uname = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_UNAME));
                String pass = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PASS));
                String url = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_URL));
                String bio = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BIO));
                String g = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_GENDER));
                int age = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_AGE));

                userList.add(new User(id, uname, pass, url, bio, g, age));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return userList;
    }

    public int getUsersCount() {
        String countQuery = "SELECT * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }
}