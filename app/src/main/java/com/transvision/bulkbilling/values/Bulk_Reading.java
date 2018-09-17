package com.transvision.bulkbilling.values;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Handler;

import com.transvision.bulkbilling.database.Bulk_Database;
import com.transvision.bulkbilling.database.Databasehelper;
import com.transvision.bulkbilling.extra.FunctionsCall;

import static com.transvision.bulkbilling.extra.Constants.MAST_CUST_FILE_UPDATED;
import static com.transvision.bulkbilling.extra.Constants.MAST_OUT_FILE_UPDATED;

public class Bulk_Reading {

    public void read_cust_values(Context context, Databasehelper databasehelper, Handler handler) {
        Bulk_Database bulkDatabase = new Bulk_Database(context);
        bulkDatabase.openDatabase();
        Cursor data = bulkDatabase.getBulk_records();
        if (data.getCount() > 0) {
            while (data.moveToNext()) {
                GetSet_MastCust getSetMastCust = new GetSet_MastCust();
                new GetSet_Mast_Values(handler).set_Mast_Cust_values(data, getSetMastCust);
                insert_values(databasehelper, getSetMastCust);
            }
            data.close();
            bulkDatabase.close();
            bulkDatabase.db_delete();
            bulkDatabase.db_delete_journal();
            handler.sendEmptyMessage(MAST_CUST_FILE_UPDATED);
        }
    }

    private void insert_values(Databasehelper databasehelper, GetSet_MastCust getSetMastCust) {
        ContentValues cv = new ContentValues();
        cv.put("MONTH", getSetMastCust.getMONTH());
        cv.put("READDATE", getSetMastCust.getREADDATE());
        cv.put("RRNO", getSetMastCust.getRRNO());
        cv.put("NAME", getSetMastCust.getNAME());
        cv.put("ADD1", getSetMastCust.getADD1());
        cv.put("TARIFF", getSetMastCust.getTARIFF());
        cv.put("MF", getSetMastCust.getMF());
        cv.put("PREVSTAT", getSetMastCust.getPREVSTAT());
        cv.put("AVGCON", getSetMastCust.getAVGCON());
        cv.put("LINEMIN", getSetMastCust.getLINEMIN());
        cv.put("SANCHP", getSetMastCust.getSANCHP());
        cv.put("SANCKW", getSetMastCust.getSANCKW());
        cv.put("PRVRED", getSetMastCust.getPRVRED());
        cv.put("FR", getSetMastCust.getFR());
        cv.put("IR", getSetMastCust.getIR());
        cv.put("DLCOUNT", getSetMastCust.getDLCOUNT());
        cv.put("ARREARS", getSetMastCust.getARREARS());
        cv.put("PF_FLAG", getSetMastCust.getPF_FLAG());
        cv.put("BILLFOR", "0");
        cv.put("MRCODE", getSetMastCust.getMRCODE());
        cv.put("LEGFOL", getSetMastCust.getLEGFOL());
        cv.put("ODDEVEN", getSetMastCust.getODDEVEN());
        cv.put("SSNO", getSetMastCust.getSSNO());
        cv.put("CONSNO", getSetMastCust.getCONSNO());
        cv.put("REBATE_FLAG", getSetMastCust.getREBATE_FLAG());
        cv.put("RREBATE", getSetMastCust.getRREBATE());
        cv.put("EXTRA1", getSetMastCust.getEXTRA1());
        cv.put("DATA1", getSetMastCust.getDATA1());
        cv.put("EXTRA2", getSetMastCust.getEXTRA2());
        cv.put("DATA2", getSetMastCust.getDATA2());
        cv.put("PH_NO", getSetMastCust.getPH_NO());
        cv.put("DEPOSIT", getSetMastCust.getDEPOSIT());
        cv.put("MTRDIGIT", getSetMastCust.getMTRDIGIT());
        cv.put("PFVAL", getSetMastCust.getPFVAL());
        cv.put("BMDVAL", getSetMastCust.getBMDVAL());
        cv.put("ASDAMT", getSetMastCust.getASDAMT());
        cv.put("IODAMT", getSetMastCust.getIODAMT());
        cv.put("BILL_NO", "0");
        cv.put("INTEREST_AMT", getSetMastCust.getINTEREST_AMT());
        cv.put("CAP_FLAG", getSetMastCust.getCAP_FLAG());
        cv.put("TOD_FLAG", getSetMastCust.getTOD_FLAG());
        cv.put("TOD_PREVIOUS1", getSetMastCust.getTOD_PREVIOUS1());
        cv.put("TOD_PREVIOUS3", getSetMastCust.getTOD_PREVIOUS3());
        cv.put("INT_ON_DEP", getSetMastCust.getINT_ON_DEP());
        cv.put("SO_FEEDER_TC_POLE", getSetMastCust.getSO_FEEDER_TC_POLE());
        cv.put("TARIFF_NAME", getSetMastCust.getTARIFF_NAME());
        cv.put("PREV_READ_DATE", getSetMastCust.getPREV_READ_DATE());
        cv.put("BILL_DAYS", getSetMastCust.getBILL_DAYS());
        cv.put("MTR_SERIAL_NO", getSetMastCust.getMTR_SERIAL_NO());
        cv.put("CHQ_DISSHONOUR_FLAG", getSetMastCust.getCHQ_DISSHONOUR_FLAG());
        cv.put("CHQ_DISHONOUR_DATE", getSetMastCust.getCHQ_DISHONOUR_DATE());
        cv.put("FDRNAME", getSetMastCust.getFDRNAME());
        cv.put("TCCODE", getSetMastCust.getTCCODE());
        cv.put("MTR_FLAG", getSetMastCust.getMTR_FLAG());
        cv.put("INVENTORY_LOAD", getSetMastCust.getINVENTORY_LOAD());
        cv.put("HP", getSetMastCust.getHP());
        cv.put("BMDKW", getSetMastCust.getBMDKW());
        cv.put("CONNLDHP", getSetMastCust.getCONNLDHP());
        cv.put("CONNLDKW", getSetMastCust.getCONNLDKW());
        cv.put("CREADJ", getSetMastCust.getCREADJ());
        cv.put("READKVAH", getSetMastCust.getREADKVAH());
        cv.put("GPS_LAT", getSetMastCust.getGPS_LAT());
        cv.put("GPS_LONG", getSetMastCust.getGPS_LONG());
        cv.put("AADHAAR", getSetMastCust.getAADHAAR());
        cv.put("MAIL", getSetMastCust.getMAIL());
        cv.put("ELECTION", getSetMastCust.getELECTION());
        cv.put("RATION", getSetMastCust.getRATION());
        cv.put("WATER", getSetMastCust.getWATER());
        cv.put("HOUSE_NO", getSetMastCust.getHOUSE_NO());
        cv.put("FDRCODE", getSetMastCust.getFDRCODE());
        cv.put("TCNAME", getSetMastCust.getTCNAME());
        databasehelper.insertInCustTable(cv);
    }

    public void read_out_values(Context context, Databasehelper databasehelper, Handler handler) {
        FunctionsCall functionsCall = new FunctionsCall();
        Bulk_Database bulkDatabase = new Bulk_Database(context);
        bulkDatabase.openDatabase();
        Cursor data = databasehelper.getCustData();
        if (data.getCount() > 0) {
            while (data.moveToNext()) {
                GetSet_MastCust getSetMastCust = new GetSet_MastCust();
                String account_id = functionsCall.getCursorValue(data, "CONSNO");
                Cursor bulk_data = bulkDatabase.getBulk_outRecords(account_id);
                if (bulk_data.getCount() > 0) {
                    bulk_data.moveToNext();
                    getSetMastCust.setPRES_RDG(functionsCall.getCursorValue(bulk_data, "PRES_RDG"));
                    getSetMastCust.setPRES_STS(functionsCall.getCursorValue(bulk_data, "PRES_STS"));
                    getSetMastCust.setTOD_CURRENT1(functionsCall.getCursorValue(bulk_data, "TOD_CURRENT1"));
                    getSetMastCust.setTOD_CURRENT3(functionsCall.getCursorValue(bulk_data, "TOD_CURRENT3"));
                    getSetMastCust.setPFVAL(functionsCall.getCursorValue(bulk_data, "PFVAL"));
                    getSetMastCust.setBMDVAL(functionsCall.getCursorValue(bulk_data, "BMDVAL"));
                    databasehelper.update_out_values(account_id, getSetMastCust);
                }
                bulk_data.close();
            }
            data.close();
            bulkDatabase.close();
            bulkDatabase.db_delete();
            bulkDatabase.db_delete_journal();
            handler.sendEmptyMessage(MAST_OUT_FILE_UPDATED);
        }
    }


}