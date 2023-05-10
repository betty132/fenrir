package com.example.fenrir;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    //データベース名
    private static final String DATABASE_NAME = "my_database";

    //テーブル名
    private static final String TABLE_NAME = "favoriteShops";

    //カラム名
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_GENRE = "genre";
    private static final String COLUMN_ACCESS = "access";
    private static final String COLUMN_IMAGE_URL_SMALL = "image_url_small";
    private static final String COLUMN_IMAGE_URL_LARGE = "image_url_large";
    private static final String COLUMN_CATCH_PHRASE = "catch_phrase";
    private static final String COLUMN_OPEN = "open";
    private static final String COLUMN_ADDRESS = "address";

    // データベースバージョン
    private static final int DATABASE_VERSION = 1;

    // テーブル作成用のSQL文
    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " ("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NAME + " TEXT,"
                    + COLUMN_GENRE + " TEXT,"
                    + COLUMN_ACCESS + " TEXT,"
                    + COLUMN_IMAGE_URL_SMALL + " TEXT,"
                    + COLUMN_IMAGE_URL_LARGE + " TEXT,"
                    + COLUMN_CATCH_PHRASE + " TEXT,"
                    + COLUMN_OPEN + " TEXT,"
                    + COLUMN_ADDRESS + " TEXT)";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // テーブルを削除して再作成する
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void deleteShop(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_NAME + "=?", new String[]{name});
        db.close();
    }

    // データベースに店舗情報を追加する
    public void addShop(Shop shop) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, shop.getName());
        values.put(COLUMN_GENRE, shop.getGenre());
        values.put(COLUMN_ACCESS, shop.getAccess());
        values.put(COLUMN_IMAGE_URL_SMALL, shop.getImageUrlSmall());
        values.put(COLUMN_IMAGE_URL_LARGE, shop.getImageUrlLarge());
        values.put(COLUMN_CATCH_PHRASE, shop.getCatchPhrase());
        values.put(COLUMN_OPEN, shop.getOpen());
        values.put(COLUMN_ADDRESS, shop.getAddress());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    // 一致するデータを出力
    public Cursor rawQuery(String sql, String[] selectionArgs) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(sql, selectionArgs);
    }

}