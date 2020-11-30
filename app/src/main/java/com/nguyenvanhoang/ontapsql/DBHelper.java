package com.nguyenvanhoang.ontapsql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, "OnTapSQL", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE Authors("+
                                "id integer primary key,"+
                                "name text,"+
                                "address text,"+
                                "email text)"
                                );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Authors");
            onCreate(sqLiteDatabase);
    }
    public int insertAuthor( Author author){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",author.getId());
        contentValues.put("name",author.getName());
        contentValues.put("address",author.getAddress());
        contentValues.put("email",author.getEmail());
        int result = (int) sqLiteDatabase.insert("Authors",null,contentValues);
        sqLiteDatabase.close();
        return result;
    }
    public List<Author> getAllAuthor(){
        List<Author> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from Authors",null);
        if(cursor != null){
            cursor.moveToNext();
        }
        while (!cursor.isAfterLast()){
            list.add(new Author(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3)));
            cursor.moveToNext();
        }
        cursor.close();
        sqLiteDatabase.close();
        return list;
    }
    public int updateAuthor(Author author){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",author.getName());
        contentValues.put("address",author.getAddress());
        contentValues.put("email",author.getEmail());
        int result =(int)sqLiteDatabase.update("Authors",contentValues,"id =" + "'"  + author.getId() + "'",null);
        sqLiteDatabase.close();
        return result;
    }
    public int deleteAuthor(int id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int result  = (int)sqLiteDatabase.delete("Authors","id = " + "'" + id + "'",null);
        sqLiteDatabase.close();
        return result;
    }
    public Author searchAuthorById(int id){
        Author author = new Author();
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String sql = "SELECT * FROM Authors where id =" + "'" +id + "'";
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        while (!cursor.isAfterLast()){
            author.setId(cursor.getInt(0));
            author.setName(cursor.getString(1));
            author.setAddress(cursor.getString(2));
            author.setEmail(cursor.getString(3));
            cursor.moveToNext();
        }
        cursor.close();
        sqLiteDatabase.close();
        return author;
    }
}
