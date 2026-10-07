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
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_USER = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_UNAME = "uname";
    private static final String COLUMN_PASS = "password";
    private static final String COLUMN_URL = "url_profile";
    private static final String COLUMN_BIO = "short_bio";

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
                + COLUMN_BIO + " TEXT)";
        db.execSQL(CREATE_TABLE);

        // Nạp sẵn dữ liệu mẫu cho bài Lab8
        addDefaultUsers(db);
    }

    private void addDefaultUsers(SQLiteDatabase db) {
        ContentValues v1 = new ContentValues();
        v1.put(COLUMN_UNAME, "nguyen_van_a");
        v1.put(COLUMN_PASS, "123456");
        v1.put(COLUMN_URL, "https://picsum.photos/200/200?random=1");
        v1.put(COLUMN_BIO, "Lập trình viên Android yêu thích Java và Kotlin.");
        db.insert(TABLE_USER, null, v1);

        ContentValues v2 = new ContentValues();
        v2.put(COLUMN_UNAME, "tran_thi_b");
        v2.put(COLUMN_PASS, "654321");
        v2.put(COLUMN_URL, "https://picsum.photos/200/200?random=2");
        v2.put(COLUMN_BIO, "Chuyên viên thiết kế giao diện UI/UX.");
        db.insert(TABLE_USER, null, v2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
                String uname = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_UNAME));
                String pass = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PASS));
                String url = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_URL));
                String bio = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BIO));

                userList.add(new User(id, uname, pass, url, bio));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return userList;
    }
}