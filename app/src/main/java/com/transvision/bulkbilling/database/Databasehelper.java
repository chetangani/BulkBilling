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

    public Cursor collection_input() {
        return myDataBase.rawQuery("Select * from COLLECTION_INPUT", null);
    }

    public Cursor getAccountID(String value) {
        return myDataBase.rawQuery("Select * from MAST_CUST where CONSNO = " + "'" + value + "'", null);
    }

    public Cursor getBilled_details(String value) {
        return myDataBase.rawQuery("Select * from MAST_OUT where CONSNO = " + "'" + value + "'", null);
    }

    public String get_status(String status_code) {
        Cursor data = myDataBase.rawQuery("SELECT STATUS_LABEL FROM BILLING_STATUS WHERE STATUS_CODE = '"+status_code+"'", null);
        data.moveToNext();
        return fcall.getCursorValue(data, "STATUS_LABEL");
    }

    public Cursor collects1() {
        return myDataBase.rawQuery("SELECT 'MR : ' || MRCODE || ' RDG_DATE : ' || SUBSTR(MIN(READDATE),1,2) || ' To ' || " +
                "MAX(READDATE)COL1,''COL2 FROM MAST_CUST", null);
    }

    public Cursor collects2() {
        return myDataBase.rawQuery("select 'TOTAL             'COL1, count(CONSNO)COL2 from MAST_CUST", null);
    }

    public Cursor collects3() {
        return myDataBase.rawQuery("select 'BILLED           'COL1, count(CONSNO)COL2 from MAST_OUT", null);
    }

    public Cursor collects4() {
        return myDataBase.rawQuery("select 'NOT BILLED       'COL1, ((SELECT count(CONSNO) from MAST_CUST)-(SELECT count(CONSNO) " +
                "from MAST_OUT))COL2", null);
    }

    /*public Cursor collects5() {
        return myDataBase.rawQuery("SELECT STATUS_NAME COL1, COUNT(CONSNO)COL2 FROM BILLING_STATUS LEFT OUTER JOIN " +
                "MAST_OUT ON STATUS_CODE = PRES_STS GROUP BY STATUS_CODE", null);
    }

    public Cursor transactionidbydate(String date) {
        String tariff = "select (TRANSACTION_ID)TRANSACTIONID, COUNT(COLLECTION_OUTPUT.CONSUMER_ID)RECEIPTS, SUM(AMOUNT)AMOUNT from " +
                "COLLECTION_OUTPUT where COLLECTION_OUTPUT.MODE_PAYMENT = '0' and COLLECTION_OUTPUT.RECPT_DATE = " + "'" + date + "'" + " " +
                "GROUP BY TRANSACTION_ID";
        return myDataBase.rawQuery(tariff, null);
    }*/

    public Cursor tariffwisebilling() {
        String tariff = "select (TARIFF_CONFIG_CURRENT.TARRIF)TARIFF_NAME, COUNT(MAST_OUT.CONSNO)BILLED, SUM(MAST_OUT.DEM_REVENUE)PAYABLE " +
                "from TARIFF_CONFIG_CURRENT,MAST_CUST,MAST_OUT where MAST_OUT.CONSNO = MAST_CUST.CONSNO and MAST_OUT.TARIFF = " +
                "TARIFF_CONFIG_CURRENT.TARIFF_CODE and CASE WHEN MAST_OUT.REBATE_FLAG = '4' THEN " +
                "CASE WHEN MAST_OUT.RREBATE = '0' THEN '3' ELSE '4' END ELSE MAST_OUT.RREBATE END = TARIFF_CONFIG_CURRENT.RUFLAG " +
                "GROUP BY TARIFF_CONFIG_CURRENT.TARRIF ORDER BY TARIFF_CONFIG_CURRENT.TARRIF ASC";
        return myDataBase.rawQuery(tariff, null);
    }

    public Cursor tariff_status(String tariff) {
        String status = "SELECT * FROM MAST_OUT WHERE TARIFF = (SELECT TARIFF_CODE FROM TARIFF_CONFIG_CURRENT WHERE TARRIF = '"+tariff+"') " +
                "AND RREBATE = (SELECT RUFLAG FROM TARIFF_CONFIG_CURRENT WHERE TARRIF = '"+tariff+"')";
        return myDataBase.rawQuery(status, null);
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

    public Cursor billstatus() {
        return myDataBase.rawQuery("select * from BILLSTATUS", null);
    }

    //Cursor to set data for spinner
    public Cursor spinnerData() {
        return myDataBase.rawQuery("Select STATUS_NAME from BILLING_STATUS", null);
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

    public Cursor status(String status) {
        return myDataBase.rawQuery("select * from MAST_OUT where PRES_STS = (select STATUS_CODE from BILLING_STATUS where STATUS_NAME = " + "'" + status + "')", null);
    }

    public Cursor notbilled() {
        return myDataBase.rawQuery("SELECT * from MAST_CUST where CONSNO not in (SELECT CONSNO from MAST_OUT)", null);
    }

    public Cursor searchbyrrno() {
        return myDataBase.rawQuery("select rrno from MAST_CUST", null);
    }

    public Cursor searchbyid() {
        return myDataBase.rawQuery("select CONSNO from MAST_CUST", null);
    }

    public Cursor getBilledID(String value) {
        return myDataBase.rawQuery("select * from MAST_OUT where CONSNO = " + "'" + value + "'", null);
    }

    public Cursor prvstatus(String value) {
        return myDataBase.rawQuery("select * from MAST_CUST,BILLING_STATUS where BILLING_STATUS.STATUS_CODE = MAST_CUST.PREVSTAT and MAST_CUST.CONSNO = " + "'" + value + "'", null);
    }

    public boolean insertInCustTable(ContentValues cv1) {
        long result = myDataBase.insertOrThrow("MAST_CUST", null, cv1);
        return result != -1;
    }

    public boolean insertInTable(ContentValues cv1) {
        long result = myDataBase.insertOrThrow("MAST_OUT", null, cv1);
        return result != -1;
    }

    public boolean insertInSlabsTable(ContentValues cv1) {
        long result = myDataBase.insertOrThrow("MAST_OUT_SLABS", null, cv1);
        return result != -1;
    }

    public Cursor getold_slabs(String account_id) {
        return myDataBase.rawQuery("SELECT * FROM MAST_OUT_SLABS WHERE CONSNO ='"+account_id+"'", null);
    }

    public void insertInCollectionTable(ContentValues cv1) {
        myDataBase.insert("COLLECTION_OUTPUT", null, cv1);
    }

    public Cursor getforpaymentbyid(String value) {
        return myDataBase.rawQuery("select * from COLLECTION_INPUT where CONSUMER_ID = " + "'" + value + "'", null);
    }

    public Cursor addingSSNO() {
        return myDataBase.rawQuery("select count(CONSNO)+1 SSNO from MAST_OUT", null);
    }

    public Cursor countfortransaction(String date) {
        return myDataBase.rawQuery("select count(RECPT_DATE)COUNT, TRANSACTION_ID from COLLECTION_OUTPUT where RECPT_DATE = " + "'" + date + "'", null);
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

    public Cursor counttobill() {
        return myDataBase.rawQuery("SELECT COUNT(*)CUST,MRCODE FROM MAST_CUST", null);
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

    public void delete_last_billed_row(String value) {
        Cursor billed = myDataBase.rawQuery("SELECT * FROM MAST_OUT WHERE CONSNO = '" + value + "'", null);
        if (billed.getCount() > 0) {
            Cursor data = myDataBase.rawQuery("DELETE FROM MAST_OUT WHERE CONSNO = '" + value + "'", null);
            data.moveToNext();
            data.close();
        }
        billed.close();
        Cursor slabs_data = myDataBase.rawQuery("SELECT * FROM MAST_OUT_SLABS WHERE CONSNO = '" + value + "'", null);
        if (slabs_data != null) {
            if (slabs_data.getCount() > 0) {
                slabs_data.close();
                Cursor slabs = myDataBase.rawQuery("DELETE from MAST_OUT_SLABS where CONSNO = '" + value + "'", null);
                slabs.moveToNext();
                slabs.close();
            } else slabs_data.close();
        }
    }

    public String get_RRNO_Account(String rrno) {
        Cursor data = myDataBase.rawQuery("SELECT * FROM MAST_CUST WHERE RRNO = '"+rrno+"'", null);
        data.moveToNext();
        return fcall.getCursorValue(data, "CONSNO");
    }

    public void onlinebilledrecord(String id, Handler handler) {
        Cursor data;
        data = myDataBase.rawQuery("UPDATE MAST_OUT SET ONLINE_FLAG = 'Y' WHERE _id='"+id+"'", null);
        data.moveToNext();
        data.close();
//        handler.sendEmptyMessage(ONLINE_UPDATE_FLAG_SUCCESS);
    }

    public Cursor getbilledvalues() {
        return myDataBase.rawQuery("SELECT *, (MAST_OUT._id)OUT_ID FROM MAST_OUT, SUBDIV_DETAILS WHERE MAST_OUT.ONLINE_FLAG ='N'", null);
    }

    public Cursor getdistinctvalues() {
        return myDataBase.rawQuery("SELECT READDATE, MRCODE FROM MAST_CUST GROUP BY MRCODE, READDATE", null);
    }

    public void delete_collection_record(String account_id, String receipt_date) {
        Cursor data;
        data = myDataBase.rawQuery("DELETE FROM COLLECTION_OUTPUT WHERE ACCOUNT_ID='"+account_id+"' and RECEIPT_DATE='"+receipt_date+"'", null);
        data.moveToNext();
        data.close();
    }

    public void changebtprinter(String format) {
        ContentValues cv = new ContentValues();
        cv.put("BT_PRINTER", format);
        myDataBase.update("SUBDIV_DETAILS", cv, null, null);
    }

    public void update_Extra_Billing(String ph_no, String aad_no, String mail_id, String election, String water, String ration, String house,
                                     String account_id) {
        Cursor data;
        data = myDataBase.rawQuery("UPDATE MAST_OUT SET PH_NO = '"+ph_no+"' AND AADHAAR = '"+aad_no+"' AND MAIL ='"+mail_id+"' " +
                "AND ELECTION ='"+election+"' AND RATION='"+ration+"' AND WATER='"+water+"' AND HOUSE_NO='"+house+"' " +
                "WHERE CONSNO = '"+account_id+"'", null);
        data.moveToNext();
        data.close();
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
