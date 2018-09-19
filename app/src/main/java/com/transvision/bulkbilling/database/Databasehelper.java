package com.transvision.bulkbilling.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;

import com.transvision.bulkbilling.extra.FunctionsCall;
import com.transvision.bulkbilling.values.GetSet_MastCust;

import java.io.File;

import static com.transvision.bulkbilling.extra.Constants.DB_FILE_DELETE_SUCCESS;
import static com.transvision.bulkbilling.extra.Constants.DIR_DATABASE;
import static com.transvision.bulkbilling.extra.Constants.FILE_DATABASE;

public class Databasehelper extends SQLiteOpenHelper {

    private SQLiteDatabase myDataBase;
    private static FunctionsCall fcall = new FunctionsCall();
    private static final String DATABASE_NAME = FILE_DATABASE;
    private static String path = fcall.filepath(DIR_DATABASE);
    private final static String DATABASE_PATH = path + File.separator;
    private static final int DATABASE_VERSION = 1;

    //Constructor
    public Databasehelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //delete database
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void db_delete(Handler handler) {
        File file = new File(DATABASE_PATH + DATABASE_NAME);
        if (file.exists()) {
            file.delete();
            handler.sendEmptyMessage(DB_FILE_DELETE_SUCCESS);
        }
    }

    //Open database
    public boolean openDatabase() throws SQLException {
        String myPath = DATABASE_PATH + DATABASE_NAME;
        File file = new File(myPath);
        if (file.exists()) {
            myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
            return true;
        } else return false;
    }

    public void onCreate(SQLiteDatabase db) {
        /*db.execSQL("CREATE TABLE MAST_CUST(MONTH TEXT, READDATE TEXT, RRNO TEXT, NAME TEXT, ADD1 TEXT, TARIFF TEXT, MF TEXT, PREVSTAT TEXT, " +
                "AVGCON TEXT, LINEMIN TEXT, SANCHP TEXT, SANCKW TEXT, PRVRED TEXT, FR TEXT, IR TEXT, DLCOUNT TEXT, ARREARS TEXT, PF_FLAG TEXT, " +
                "BILLFOR TEXT, MRCODE TEXT, LEGFOL TEXT, ODDEVEN TEXT, SSNO INTEGER, CONSNO TEXT, REBATE_FLAG TEXT, RREBATE TEXT, EXTRA1 TEXT, " +
                "DATA1 TEXT, EXTRA2 TEXT, DATA2 TEXT, PH_NO TEXT, DEPOSIT TEXT, MTRDIGIT TEXT, PFVAL TEXT, BMDVAL TEXT, ASDAMT TEXT, " +
                "IODAMT TEXT, BILL_NO TEXT, INTEREST_AMT TEXT, CAP_FLAG TEXT, TOD_FLAG TEXT, TOD_PREVIOUS1 TEXT, TOD_PREVIOUS3 TEXT, " +
                "INT_ON_DEP TEXT, SO_FEEDER_TC_POLE TEXT, TARIFF_NAME TEXT, PREV_READ_DATE TEXT, BILL_DAYS TEXT, MTR_SERIAL_NO TEXT, " +
                "CHQ_DISSHONOUR_FLAG TEXT, CHQ_DISHONOUR_DATE TEXT, FDRNAME TEXT, TCCODE TEXT, MTR_FLAG TEXT, INVENTORY_LOAD TEXT, HP TEXT, " +
                "BMDKW TEXT, CONNLDHP TEXT, CONNLDKW TEXT, CREADJ TEXT, READKVAH TEXT, GPS_LAT TEXT, GPS_LONG TEXT, AADHAAR TEXT, MAIL TEXT, " +
                "ELECTION TEXT, RATION TEXT, WATER TEXT, HOUSE_NO TEXT, FDRCODE TEXT, TCNAME TEXT, PRES_RDG TEXT, PRES_STS TEXT, " +
                "TOD_CURRENT1 TEXT, TOD_CURRENT3 TEXT, _id integer primary key);");
        db.execSQL("CREATE TABLE MAST_OUT(MONTH TEXT, READDATE TEXT, RRNO TEXT, NAME TEXT, ADD1 TEXT, TARIFF TEXT, MF TEXT, PREVSTAT TEXT, " +
                "AVGCON TEXT, LINEMIN TEXT, SANCHP TEXT, SANCKW TEXT, PRVRED TEXT, FR TEXT, IR TEXT, DLCOUNT TEXT, ARREARS TEXT, PF_FLAG TEXT, " +
                "BILLFOR TEXT, MRCODE TEXT, LEGFOL TEXT, ODDEVEN TEXT, SSNO INTEGER, CONSNO TEXT, PH_NO TEXT, REBATE_FLAG TEXT, RREBATE TEXT, " +
                "EXTRA1 TEXT, DATA1 TEXT, EXTRA2 TEXT, DATA2 TEXT, DEPOSIT TEXT, MTRDIGIT TEXT, ASDAMT TEXT, IODAMT TEXT, PFVAL TEXT, " +
                "BMDVAL TEXT, BILL_NO TEXT, INTEREST_AMT TEXT, CAP_FLAG TEXT, TOD_FLAG TEXT, TOD_PREVIOUS1 TEXT, TOD_PREVIOUS3 TEXT, " +
                "INT_ON_DEP TEXT, SO_FEEDER_TC_POLE TEXT, TARIFF_NAME TEXT, PREV_READ_DATE TEXT, BILL_DAYS TEXT, MTR_SERIAL_NO TEXT, " +
                "CHQ_DISSHONOUR_FLAG TEXT, CHQ_DISHONOUR_DATE TEXT, FDRNAME TEXT, TCCODE TEXT, MTR_FLAG TEXT, PRES_RDG TEXT, PRES_STS TEXT, " +
                "UNITS TEXT, FIX TEXT, ENGCHG TEXT, REBATE_AMOUNT TEXT, TAX_AMOUNT TEXT, BMD_PENALTY TEXT, PF_PENALTY TEXT, PAYABLE TEXT, " +
                "BILLDATE TEXT, BILLTIME TEXT, TOD_CURRENT1 TEXT, TOD_CURRENT3 TEXT, GOK_SUBSIDY TEXT, DEM_REVENUE TEXT, GPS_LAT TEXT, " +
                "GPS_LONG TEXT, ONLINE_FLAG TEXT, BATTERY_CHARGE TEXT, SIGNAL_STRENGTH TEXT, IMGADD TEXT, PAYABLE_REAL TEXT, " +
                "PAYABLE_PROFIT TEXT, PAYABLE_LOSS TEXT, BILL_PRINTED TEXT, FSLAB1 TEXT, FSLAB2 TEXT, FSLAB3 TEXT, FSLAB4 TEXT, FSLAB5 TEXT, " +
                "ESLAB1 TEXT, ESLAB2 TEXT, ESLAB3 TEXT, ESLAB4 TEXT, ESLAB5 TEXT, ESLAB6 TEXT, CHARITABLE_RBT_AMT TEXT, SOLAR_RBT_AMT TEXT, " +
                "FL_RBT_AMT TEXT, HANDICAP_RBT_AMT TEXT, PL_RBT_AMT TEXT, IPSET_RBT_AMT TEXT, REBATEFROMCCB_AMT TEXT, TOD_CHARGES TEXT, " +
                "PF_PENALITY_AMT TEXT, EXLOAD_MDPENALITY TEXT, CURR_BILL_AMOUNT TEXT, ROUNDING_AMOUNT TEXT, DUE_DATE TEXT, DISCONN_DATE TEXT, " +
                "CREADJ TEXT, PREADKVAH TEXT, AADHAAR TEXT, MAIL TEXT, MTR_DIGIT TEXT, ELECTION TEXT, RATION TEXT, WATER TEXT, HOUSE_NO TEXT, " +
                "VERSION TEXT, DL_FC TEXT, FDRCODE TEXT, TCNAME TEXT, RENT TEXT, _id integer primary key);");
        db.execSQL("CREATE TABLE MAST_OUT_SLABS(READDATE TEXT, CONSNO TEXT, FIX TEXT, FIX_NEW TEXT, ENGCHG TEXT, ENGCHG_NEW TEXT, FSLAB1 TEXT, " +
                "FSLAB2 TEXT, FSLAB3 TEXT, FSLAB4 TEXT, FSLAB5 TEXT, ESLAB1 TEXT, ESLAB2 TEXT, ESLAB3 TEXT, ESLAB4 TEXT, ESLAB5 TEXT, " +
                "ESLAB6 TEXT, BILL_DAYS TEXT, BILL_DAYS_NEW TEXT, DL_COUNT TEXT, DL_COUNT_NEW TEXT, UNITS TEXT, UNITS_NEW TEXT, FAC TEXT, " +
                "FAC_NULL TEXT, DL_FC TEXT, _id integer primary key);");*/
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public Cursor getData() {
        return myDataBase.query("MAST_CUST", null, null, null, null, null, null);
    }

    public Cursor getCustData() {
        return myDataBase.rawQuery("SELECT * FROM MAST_CUST", null);
    }

    public String get_status(String status_code) {
        Cursor data = myDataBase.rawQuery("SELECT STATUS_LABEL FROM BILLING_STATUS WHERE STATUS_CODE = '"+status_code+"'", null);
        data.moveToNext();
        return fcall.getCursorValue(data, "STATUS_LABEL");
    }

    public boolean checkinserteddata(String account_id) {
        Cursor data;
        data = myDataBase.rawQuery("SELECT * FROM MAST_OUT WHERE CONSNO ='" + account_id + "'", null);
        if (data.getCount() > 0) {
            data.close();
            return true;
        } else {
            data.close();
            return false;
        }
    }

    //Update the Billed Record
    public void updatebill(int billUpdate) {
        ContentValues cv = new ContentValues();
        cv.put("Billed_Record", billUpdate);
        myDataBase.update("SUBDIV_DETAILS", cv, "Billed_Record", null);
    }

    public Cursor getBilledRecordData() {
        return myDataBase.rawQuery("select * from mast_cust where _id = (select Billed_Record from subdiv_details)", null);
    }

    public Cursor updateDLrecord(String monthdiff) {
        return myDataBase.rawQuery("update MAST_CUST set DLCOUNT = '" + monthdiff + "' where rowid = (select billed_record from subdiv_details)", null);
    }

    public Cursor getTarrifData(String rRNO, String rRebate) {
        return myDataBase.rawQuery("select * from TARIFF_CONFIG_CURRENT where TARIFF_CODE = (select TARIFF from MAST_CUST where CONSNO = " + "'" + rRNO + "') and RUFLAG = (select RREBATE from MAST_CUST where RREBATE = " + "'" + rRebate + "')", null);
    }

    public Cursor getTarrifData_old(String rRNO, String rRebate) {
        Cursor c;
        c = myDataBase.rawQuery("select * from TARIFF_CONFIG_CURRENT_OLD", null);
        if (c.getCount() > 0) {
            c.close();
            return myDataBase.rawQuery("select * from TARIFF_CONFIG_CURRENT_OLD where TARIFF_CODE = (select TARIFF from MAST_CUST where CONSNO = " + "'" + rRNO + "') and RUFLAG = (select RREBATE from MAST_CUST where RREBATE = " + "'" + rRebate + "')", null);
        } else {
            c.close();
            return null;
        }
    }

    //Tariff = 10
    public Cursor getTarrifDataBJ(String rRebate, String Tariff) {
        return myDataBase.rawQuery("select * from TARIFF_CONFIG_CURRENT where TARIFF_CODE = " + "'" + Tariff + "'and RUFLAG = " + "'" + rRebate + "'", null);
    }

    public Cursor getTarrifDataBJ2(String rRebate, String Tariff) {
        Cursor c;
        c = myDataBase.rawQuery("select * from TARIFF_CONFIG_CURRENT_OLD", null);
        if (c.getCount() > 0) {
            c.close();
            return myDataBase.rawQuery("select * from TARIFF_CONFIG_CURRENT_OLD where TARIFF_CODE = " + "'" + Tariff + "'and RUFLAG = " + "'" + rRebate + "'", null);
        } else {
            c.close();
            return null;
        }
    }

    public Cursor billed() {
        return myDataBase.rawQuery("select * from MAST_OUT", null);
    }

    public void insertInCustTable(ContentValues cv1) {
        myDataBase.insertOrThrow("MAST_CUST", null, cv1);
    }

    public boolean insertInTable(ContentValues cv1) {
        long result = myDataBase.insertOrThrow("MAST_OUT", null, cv1);
        return result != -1;
    }

    public boolean insertInSlabsTable(ContentValues cv1) {
        long result = myDataBase.insertOrThrow("MAST_OUT_SLABS", null, cv1);
        return result != -1;
    }

    public boolean insert_Subdiv_details(ContentValues contentValues) {
        long result = myDataBase.insertOrThrow("SUBDIV_DETAILS", null, contentValues);
        return result != -1;
    }

    /*public Cursor getold_slabs(String account_id) {
        return myDataBase.rawQuery("SELECT * FROM MAST_OUT_SLABS WHERE CONSNO ='"+account_id+"'", null);
    }*/

    public Cursor addingSSNO() {
        return myDataBase.rawQuery("select count(CONSNO)+1 SSNO from MAST_OUT", null);
    }

    public Cursor report(String value) {
        return myDataBase.rawQuery("select * from MAST_OUT,SUBDIV_DETAILS where MAST_OUT.CONSNO = " + "'" + value + "'", null);
    }

    public Cursor reportbyid(String value) {
        return myDataBase.rawQuery("select *, (MAST_OUT._id)ID from MAST_OUT,SUBDIV_DETAILS where MAST_OUT.CONSNO = " + "'" + value + "'", null);
    }

    public Cursor subdivdetails() {
        return myDataBase.rawQuery("select * from SUBDIV_DETAILS", null);
    }

    public Cursor flECcount() {
        return myDataBase.rawQuery("select * from TARIFF_CONFIG_CURRENT where TARIFF_CODE = '23' and RUFLAG = '0'", null);
    }

    public Cursor flECcount_old() {
        return myDataBase.rawQuery("select * from TARIFF_CONFIG_CURRENT_OLD where TARIFF_CODE = '23' and RUFLAG = '0'", null);
    }

    public void insertInCollectionInput(ContentValues cv1) {
        myDataBase.insert("COLLECTION_INPUT", null, cv1);
    }

    public Cursor getbilledvalues() {
        return myDataBase.rawQuery("SELECT *, (MAST_OUT._id)OUT_ID FROM MAST_OUT, SUBDIV_DETAILS WHERE MAST_OUT.ONLINE_FLAG ='N'", null);
    }

    public void update_out_values(String account_id, GetSet_MastCust getSetMastCust) {
        Cursor data;
        data = myDataBase.rawQuery("UPDATE MAST_CUST SET PRES_RDG = '"+getSetMastCust.getPRES_RDG()+"', " +
                "PRES_STS = '"+getSetMastCust.getPRES_STS()+"', " +
                "TOD_CURRENT1 = '"+getSetMastCust.getTOD_CURRENT1()+"', " +
                "TOD_CURRENT3 = '"+getSetMastCust.getTOD_CURRENT3()+"', " +
                "PFVAL = '"+getSetMastCust.getPFVAL()+"', " +
                "BMDVAL = '"+getSetMastCust.getBMDVAL()+"' " +
                "WHERE CONSNO = '"+account_id+"'", null);
        data.moveToNext();
        data.close();
    }
}
