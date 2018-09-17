package com.transvision.bulkbilling.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.transvision.bulkbilling.extra.FunctionsCall;

import java.io.File;

import static com.transvision.bulkbilling.extra.Constants.DIR_DATABASE;
import static com.transvision.bulkbilling.extra.Constants.FILE_BULK_DATABASE;
import static com.transvision.bulkbilling.extra.Constants.FILE_BULK_JOURNAL;

public class Bulk_Database extends SQLiteOpenHelper {
    private SQLiteDatabase myDataBase;
    private static FunctionsCall fcall = new FunctionsCall();
    private static final String DATABASE_NAME = FILE_BULK_DATABASE;
    private static String path = fcall.filepath(DIR_DATABASE);
    private final static String DATABASE_PATH = path + File.separator;
    private static final int DATABASE_VERSION = 1;

    public Bulk_Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Open database
    public void openDatabase() throws SQLException {
        String myPath = DATABASE_PATH + DATABASE_NAME;
        File file = new File(myPath);
        if (file.exists()) {
            myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
        }
    }

    //delete database
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void db_delete() {
        File file = new File(DATABASE_PATH + DATABASE_NAME);//FILE_BULK_JOURNAL
        if (file.exists()) {
            file.delete();
        }
    }

    //delete database journal
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void db_delete_journal() {
        File file = new File(DATABASE_PATH + FILE_BULK_JOURNAL);
        if (file.exists()) {
            file.delete();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public Cursor getBulk_records() {
        return myDataBase.rawQuery("SELECT * FROM MAST_CUST", null);
    }

    public Cursor getBulk_outRecords(String acct_id) {
        return myDataBase.rawQuery("SELECT * FROM MAST_OUT WHERE CONSNO ='"+acct_id+"'", null);
    }
}
