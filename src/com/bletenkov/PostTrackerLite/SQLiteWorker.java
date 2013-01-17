package com.bletenkov.PostTrackerLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteWorker extends SQLiteOpenHelper {

    public SQLiteWorker(Context context){
        super(context, "Options", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //папки
        sqLiteDatabase.execSQL("CREATE TABLE [folders] (\n" +
                "[id] VARCHAR(5) NOT NULL PRIMARY KEY,\n" +
                "[name] NVARCHAR(20)  NULL,\n" +
                "[check] BOOLEAN DEFAULT 'FALSE' NULL\n" +
                ")");
        //создадим папки по умолчанию
        sqLiteDatabase.execSQL("INSERT INTO folders VALUES('mytrc', 'My track', 'true');");
        sqLiteDatabase.execSQL("INSERT INTO folders VALUES('archv', 'Archive', 'true');");

        //состояния
        sqLiteDatabase.execSQL("CREATE TABLE [states] (\n" +
                "[id] INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "[trucknumber] INTEGER NULL,\n" +
                "[date] INTEGER  NOT NULL,\n" +
                "[state] NVARCHAR(75)  NULL\n" +
                ")");

        //трэк номера
        sqLiteDatabase.execSQL("CREATE TABLE [tracknumbers] (\n" +
                "[id] INTEGER  PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                "[tracknumber] NVARCHAR(20)  NOT NULL,\n" +
                "[lastcheck] INTEGER  NULL,\n" +
                "[folder] VARCHAR(5) NOT NULL,\n" +
                "[outcountry] NVARCHAR(30)  NULL,\n" +
                "[incountry] NVARCHAR(30)  NULL,\n" +
                "[comment] NVARCHAR(500)  NULL,\n" +
                "[dateadd] INTEGER  NOT NULL\n" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
