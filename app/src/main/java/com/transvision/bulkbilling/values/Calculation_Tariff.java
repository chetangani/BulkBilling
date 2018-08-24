package com.transvision.bulkbilling.values;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.transvision.bulkbilling.database.Databasehelper;
import com.transvision.bulkbilling.extra.FunctionsCall;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Calculation_Tariff {

    public void tariff_calculation(String tariff, String rrebate, String rebate_flag, String account_id, String units,
                                   Databasehelper database, GetSet_MastCust getSetValues) {
        switch (tariff) {
            case "10":
                int consUnits = Integer.parseInt(units);
                if (consUnits > 40) {
                    getSetValues.setCurrent_data(database.getTarrifDataBJ("1", "20"));
                    getSetValues.setOld_data(database.getTarrifDataBJ2("1", "20"));
                } else {
                    getSetValues.setCurrent_data(database.getTarrifDataBJ("1", "10"));
                    getSetValues.setOld_data(database.getTarrifDataBJ2("1", "10"));
                }
                break;

            case "23":
                if (rrebate.equals("0")) {
                    getSetValues.setCurrent_data(database.getTarrifDataBJ("0", "20"));
                    getSetValues.setOld_data(database.getTarrifDataBJ2("0", "20"));
                } else {
                    getSetValues.setCurrent_data(database.getTarrifDataBJ("1", "20"));
                    getSetValues.setOld_data(database.getTarrifDataBJ2("1", "20"));
                }
                break;

            case "30":
                if (rebate_flag.equals("4")) {
                    if (rrebate.equals("0")) {
                        getSetValues.setCurrent_data(database.getTarrifDataBJ("3", tariff));
                        getSetValues.setOld_data(database.getTarrifDataBJ2("3", tariff));
                    } else {
                        getSetValues.setCurrent_data(database.getTarrifDataBJ("4", tariff));
                        getSetValues.setOld_data(database.getTarrifDataBJ2("4", tariff));
                    }
                } else {
                    getSetValues.setCurrent_data(database.getTarrifData(account_id, rrebate));
                    getSetValues.setOld_data(database.getTarrifData_old(account_id, rrebate));
                }
                break;

            case "50":
                if (rebate_flag.equals("4")) {
                    if (rrebate.equals("0")) {
                        getSetValues.setCurrent_data(database.getTarrifDataBJ("3", tariff));
                        getSetValues.setOld_data(database.getTarrifDataBJ2("3", tariff));
                    } else {
                        getSetValues.setCurrent_data(database.getTarrifDataBJ("4", tariff));
                        getSetValues.setOld_data(database.getTarrifDataBJ2("4", tariff));
                    }
                } else {
                    getSetValues.setCurrent_data(database.getTarrifDataBJ("0", tariff));
                    getSetValues.setOld_data(database.getTarrifDataBJ2("0", tariff));
                }
                break;

            case "51":
                if (rebate_flag.equals("4")) {
                    if (rrebate.equals("0")) {
                        getSetValues.setCurrent_data(database.getTarrifDataBJ("3", tariff));
                        getSetValues.setOld_data(database.getTarrifDataBJ2("3", tariff));
                    } else {
                        getSetValues.setCurrent_data(database.getTarrifDataBJ("4", tariff));
                        getSetValues.setOld_data(database.getTarrifDataBJ2("4", tariff));
                    }
                } else {
                    getSetValues.setCurrent_data(database.getTarrifDataBJ("0", tariff));
                    getSetValues.setOld_data(database.getTarrifDataBJ2("0", tariff));
                }
                break;

            case "52":
                if (rebate_flag.equals("4")) {
                    if (rrebate.equals("0")) {
                        getSetValues.setCurrent_data(database.getTarrifDataBJ("3", tariff));
                        getSetValues.setOld_data(database.getTarrifDataBJ2("3", tariff));
                    } else {
                        getSetValues.setCurrent_data(database.getTarrifDataBJ("4", tariff));
                        getSetValues.setOld_data(database.getTarrifDataBJ2("4", tariff));
                    }
                } else {
                    getSetValues.setCurrent_data(database.getTarrifDataBJ("0", tariff));
                    getSetValues.setOld_data(database.getTarrifDataBJ2("0", tariff));
                }
                break;

            case "53":
                if (rebate_flag.equals("4")) {
                    if (rrebate.equals("0")) {
                        getSetValues.setCurrent_data(database.getTarrifDataBJ("3", tariff));
                        getSetValues.setOld_data(database.getTarrifDataBJ2("3", tariff));
                    } else {
                        getSetValues.setCurrent_data(database.getTarrifDataBJ("4", tariff));
                        getSetValues.setOld_data(database.getTarrifDataBJ2("4", tariff));
                    }
                } else {
                    getSetValues.setCurrent_data(database.getTarrifDataBJ("0", tariff));
                    getSetValues.setOld_data(database.getTarrifDataBJ2("0", tariff));
                }
                break;

            case "60":
                getSetValues.setCurrent_data(database.getTarrifDataBJ("0", tariff));
                getSetValues.setOld_data(database.getTarrifDataBJ2("0", tariff));
                break;

            case "61":
                getSetValues.setCurrent_data(database.getTarrifDataBJ("0", tariff));
                getSetValues.setOld_data(database.getTarrifDataBJ2("0", tariff));
                break;

            case "70":
                getSetValues.setCurrent_data(database.getTarrifDataBJ("0", tariff));
                getSetValues.setOld_data(database.getTarrifDataBJ2("0", tariff));
                break;

            case "40":
                getSetValues.setCurrent_data(database.getTarrifDataBJ("0", tariff));
                getSetValues.setOld_data(database.getTarrifDataBJ2("0", tariff));
                break;

            default:
                getSetValues.setCurrent_data(database.getTarrifData(account_id, rrebate));
                getSetValues.setOld_data(database.getTarrifData_old(account_id, rrebate));
                break;
        }
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void insertintomastoutandtextfile(Context context, String consid) {
        FunctionsCall functionsCall = new FunctionsCall();
        Databasehelper databasehelper = new Databasehelper(context);
        databasehelper.openDatabase();
        Cursor data = databasehelper.reportbyid(consid);
        data.moveToNext();
        String MONTH = functionsCall.getCursorValue(data, "MONTH");
        String READDATE = functionsCall.getCursorValue(data, "READDATE");
        String RRNO = functionsCall.getCursorValue(data, "RRNO");
        String NAME = functionsCall.getCursorValue(data, "NAME");
        String ADD1 = functionsCall.getCursorValue(data, "ADD1");
        String TARIFF = functionsCall.getCursorValue(data, "TARIFF");
        String MF = functionsCall.getCursorValue(data, "MF");
        String PREVSTAT = functionsCall.getCursorValue(data, "PREVSTAT");
        String AVGCON = functionsCall.getCursorValue(data, "AVGCON");
        String LINEMIN = functionsCall.getCursorValue(data, "LINEMIN");
        String SANCHP = functionsCall.getCursorValue(data, "SANCHP");
        String SANCKW = functionsCall.getCursorValue(data, "SANCKW");
        String PRVRED = functionsCall.getCursorValue(data, "PRVRED");
        String FR = functionsCall.getCursorValue(data, "FR");
        String IR = functionsCall.getCursorValue(data, "IR");
        String DLCOUNT = functionsCall.getCursorValue(data, "DLCOUNT");
        String ARREARS = functionsCall.getCursorValue(data, "ARREARS");
        String PF_FLAG = functionsCall.getCursorValue(data, "PF_FLAG");
        String BILLFOR = functionsCall.getCursorValue(data, "BILLFOR");
        String MRCODE = functionsCall.getCursorValue(data, "MRCODE");
        String LEGFOL = functionsCall.getCursorValue(data, "LEGFOL");
        String ODDEVEN = functionsCall.getCursorValue(data, "ODDEVEN");
        String SSNO = functionsCall.getCursorValue(data, "SSNO");
        String CONSNO = functionsCall.getCursorValue(data, "CONSNO");
        String REBATE_FLAG = functionsCall.getCursorValue(data, "REBATE_FLAG");
        String RREBATE = functionsCall.getCursorValue(data, "RREBATE");
        String EXTRA1 = functionsCall.getCursorValue(data, "EXTRA1");
        String DATA1 = functionsCall.getCursorValue(data, "DATA1");
        String EXTRA2 = functionsCall.getCursorValue(data, "EXTRA2");
        String DATA2 = functionsCall.getCursorValue(data, "DATA2");
        String PH_NO;
        try {
            PH_NO = functionsCall.getCursorValue(data, "PH_NO");
        } catch (NullPointerException e) {
            e.printStackTrace();
            PH_NO = "0";
        }
        String DEPOSIT = functionsCall.getCursorValue(data, "DEPOSIT");
        String MTRDIGIT = functionsCall.getCursorValue(data, "MTRDIGIT");
        String ASDAMT = functionsCall.getCursorValue(data, "ASDAMT");
        String IODAMT = functionsCall.getCursorValue(data, "IODAMT");
        String PFVAL = functionsCall.getCursorValue(data, "PFVAL");
        String BMDVAL = functionsCall.getCursorValue(data, "BMDVAL");
        String BILL_NO;
        try {
            BILL_NO = functionsCall.getCursorValue(data, "BILL_NO");
        } catch (NullPointerException e) {
            e.printStackTrace();
            BILL_NO = "0";
        }
        String INTEREST_AMT = functionsCall.getCursorValue(data, "INTEREST_AMT");
        String CAP_FLAG = functionsCall.getCursorValue(data, "CAP_FLAG");
        String TOD_FLAG = functionsCall.getCursorValue(data, "TOD_FLAG");
        String TOD_PREVIOUS1 = functionsCall.getCursorValue(data, "TOD_PREVIOUS1");
        String TOD_PREVIOUS3 = functionsCall.getCursorValue(data, "TOD_PREVIOUS3");
        String INT_ON_DEP = functionsCall.getCursorValue(data, "INT_ON_DEP");
        String SO_FEEDER_TC_POLE = functionsCall.getCursorValue(data, "SO_FEEDER_TC_POLE");
        String TARIFF_NAME = functionsCall.getCursorValue(data, "TARIFF_NAME");
        String PREV_READ_DATE = functionsCall.getCursorValue(data, "PREV_READ_DATE");
        String BILL_DAYS = functionsCall.getCursorValue(data, "BILL_DAYS");
        String MTR_SERIAL_NO = functionsCall.getCursorValue(data, "MTR_SERIAL_NO");
        String FDRNAME = functionsCall.getCursorValue(data, "FDRNAME");
        String TCCODE = functionsCall.getCursorValue(data, "TCCODE");
        String MTR_FLAG = functionsCall.getCursorValue(data, "MTR_FLAG");
        String PRES_RDG = functionsCall.getCursorValue(data, "PRES_RDG");
        String PRES_STS = functionsCall.getCursorValue(data, "PRES_STS");
        String UNITS = functionsCall.getCursorValue(data, "UNITS");
        String FIX = functionsCall.getCursorValue(data, "FIX");
        String ENGCHG = functionsCall.getCursorValue(data, "ENGCHG");
        String REBATE_AMOUNT = functionsCall.getCursorValue(data, "REBATE_AMOUNT");
        String TAX_AMOUNT = functionsCall.getCursorValue(data, "TAX_AMOUNT");
        String BMD_PENALTY = functionsCall.getCursorValue(data, "BMD_PENALTY");
        String PF_PENALTY = functionsCall.getCursorValue(data, "PF_PENALTY");
        String PAYABLE = functionsCall.getCursorValue(data, "PAYABLE");
        String BILLDATE = functionsCall.getCursorValue(data, "BILLDATE");
        String BILLTIME = functionsCall.getCursorValue(data, "BILLTIME");
        String TOD_CURRENT1 = functionsCall.getCursorValue(data, "TOD_CURRENT1");
        String TOD_CURRENT3 = functionsCall.getCursorValue(data, "TOD_CURRENT3");
        String GOK_SUBSIDY = functionsCall.getCursorValue(data, "GOK_SUBSIDY");
        String DEM_REVENUE = functionsCall.getCursorValue(data, "DEM_REVENUE");
        String GPS_LAT = functionsCall.getCursorValue(data, "GPS_LAT");
        String GPS_LONG = functionsCall.getCursorValue(data, "GPS_LONG");
        String IMGADD;
        try {
            IMGADD = functionsCall.getCursorValue(data, "IMGADD");
        } catch (NullPointerException e) {
            e.printStackTrace();
            IMGADD = "noimage";
        }
        String PAYABLE_REAL = functionsCall.getCursorValue(data, "PAYABLE_REAL");
        String PAYABLE_PROFIT = functionsCall.getCursorValue(data, "PAYABLE_PROFIT");
        String PAYABLE_LOSS = functionsCall.getCursorValue(data, "PAYABLE_LOSS");
        String BILL_PRINTED = functionsCall.getCursorValue(data, "BILL_PRINTED");
        String FSLAB1 = functionsCall.getCursorValue(data, "FSLAB1");
        String FSLAB2 = functionsCall.getCursorValue(data, "FSLAB2");
        String FSLAB3 = functionsCall.getCursorValue(data, "FSLAB3");
        String FSLAB4 = functionsCall.getCursorValue(data, "FSLAB4");
        String FSLAB5 = functionsCall.getCursorValue(data, "FSLAB5");
        String ESLAB1 = functionsCall.getCursorValue(data, "ESLAB1");
        String ESLAB2 = functionsCall.getCursorValue(data, "ESLAB2");
        String ESLAB3 = functionsCall.getCursorValue(data, "ESLAB3");
        String ESLAB4 = functionsCall.getCursorValue(data, "ESLAB4");
        String ESLAB5 = functionsCall.getCursorValue(data, "ESLAB5");
        String ESLAB6 = functionsCall.getCursorValue(data, "ESLAB6");
        String CHARITABLE_RBT_AMT = functionsCall.getCursorValue(data, "CHARITABLE_RBT_AMT");
        String SOLAR_RBT_AMT = functionsCall.getCursorValue(data, "SOLAR_RBT_AMT");
        String FL_RBT_AMT = functionsCall.getCursorValue(data, "FL_RBT_AMT");
        String HANDICAP_RBT_AMT = functionsCall.getCursorValue(data, "HANDICAP_RBT_AMT");
        String PL_RBT_AMT = functionsCall.getCursorValue(data, "PL_RBT_AMT");
        String IPSET_RBT_AMT = functionsCall.getCursorValue(data, "IPSET_RBT_AMT");
        String REBATEFROMCCB_AMT = functionsCall.getCursorValue(data, "REBATEFROMCCB_AMT");
        String TOD_CHARGES = functionsCall.getCursorValue(data, "TOD_CHARGES");
        String PF_PENALITY_AMT = functionsCall.getCursorValue(data, "PF_PENALITY_AMT");
        String EXLOAD_MDPENALITY = functionsCall.getCursorValue(data, "EXLOAD_MDPENALITY");
        String CURR_BILL_AMOUNT = functionsCall.getCursorValue(data, "CURR_BILL_AMOUNT");
        String ROUNDING_AMOUNT = functionsCall.getCursorValue(data, "ROUNDING_AMOUNT");
        String DUE_DATE = functionsCall.getCursorValue(data, "DUE_DATE");
        String DISCONN_DATE = functionsCall.getCursorValue(data, "DISCONN_DATE");
        String CREADJ = functionsCall.getCursorValue(data, "CREADJ");
        String PREADKVAH = functionsCall.getCursorValue(data, "PREADKVAH");
        data.close();

        ContentValues cv = new ContentValues();
        cv.put("MONTH", MONTH);
        cv.put("READDATE", READDATE);
        cv.put("RRNO", RRNO);
        cv.put("NAME", NAME);
        cv.put("ADD1", ADD1);
        cv.put("TARIFF", TARIFF);
        cv.put("MF", MF);
        cv.put("PREVSTAT", PREVSTAT);
        cv.put("AVGCON", AVGCON);
        cv.put("LINEMIN", LINEMIN);
        cv.put("SANCHP", SANCHP);
        cv.put("SANCKW", SANCKW);
        cv.put("PRVRED", PRVRED);
        cv.put("FR", FR);
        cv.put("IR", IR);
        cv.put("DLCOUNT", DLCOUNT);
        cv.put("ARREARS", ARREARS);
        cv.put("PF_FLAG", PF_FLAG);
        cv.put("BILLFOR", BILLFOR);
        cv.put("MRCODE", MRCODE);
        cv.put("LEGFOL", LEGFOL);
        cv.put("ODDEVEN", ODDEVEN);
        cv.put("SSNO", SSNO);
        cv.put("CONSNO", CONSNO);
        cv.put("REBATE_FLAG", REBATE_FLAG);
        cv.put("RREBATE", RREBATE);
        cv.put("EXTRA1", EXTRA1);
        cv.put("DATA1", DATA1);
        cv.put("EXTRA2", EXTRA2);
        cv.put("DATA2", DATA2);
        cv.put("PH_NO", PH_NO);
        cv.put("DEPOSIT", DEPOSIT);
        cv.put("MTRDIGIT", MTRDIGIT);
        cv.put("ASDAMT", ASDAMT);
        cv.put("IODAMT", IODAMT);
        cv.put("PFVAL", PFVAL);
        cv.put("BMDVAL", BMDVAL);
        cv.put("BILL_NO", BILL_NO);
        cv.put("INTEREST_AMT", INTEREST_AMT);
        cv.put("CAP_FLAG", CAP_FLAG);
        cv.put("TOD_FLAG", TOD_FLAG);
        cv.put("TOD_PREVIOUS1", TOD_PREVIOUS1);
        cv.put("TOD_PREVIOUS3", TOD_PREVIOUS3);
        cv.put("INT_ON_DEP", INT_ON_DEP);
        cv.put("SO_FEEDER_TC_POLE", SO_FEEDER_TC_POLE);
        cv.put("TARIFF_NAME", TARIFF_NAME);
        cv.put("PREV_READ_DATE", PREV_READ_DATE);
        cv.put("BILL_DAYS", BILL_DAYS);
        cv.put("MTR_SERIAL_NO", MTR_SERIAL_NO);
        cv.put("FDRNAME", FDRNAME);
        cv.put("TCCODE", TCCODE);
        cv.put("MTR_FLAG", MTR_FLAG);
        cv.put("PRES_RDG", PRES_RDG);
        cv.put("PRES_STS", PRES_STS);
        cv.put("UNITS", UNITS);
        cv.put("FIX", FIX);
        cv.put("ENGCHG", ENGCHG);
        cv.put("REBATE_AMOUNT", REBATE_AMOUNT);
        cv.put("TAX_AMOUNT", TAX_AMOUNT);
        cv.put("BMD_PENALTY", BMD_PENALTY);
        cv.put("PF_PENALTY", PF_PENALTY);
        cv.put("PAYABLE", PAYABLE);
        cv.put("BILLDATE", BILLDATE);
        cv.put("BILLTIME", BILLTIME);
        cv.put("TOD_CURRENT1", TOD_CURRENT1);
        cv.put("TOD_CURRENT3", TOD_CURRENT3);
        cv.put("GOK_SUBSIDY", GOK_SUBSIDY);
        cv.put("DEM_REVENUE", DEM_REVENUE);
        cv.put("GPS_LAT", GPS_LAT);
        cv.put("GPS_LONG", GPS_LONG);
        cv.put("IMGADD", IMGADD);
        cv.put("PAYABLE_REAL", PAYABLE_REAL);
        cv.put("PAYABLE_PROFIT", PAYABLE_PROFIT);
        cv.put("PAYABLE_LOSS", PAYABLE_LOSS);
        cv.put("BILL_PRINTED", BILL_PRINTED);
        cv.put("FSLAB1", FSLAB1);
        cv.put("FSLAB2", FSLAB2);
        cv.put("FSLAB3", FSLAB3);
        cv.put("FSLAB4", FSLAB4);
        cv.put("FSLAB5", FSLAB5);
        cv.put("ESLAB1", ESLAB1);
        cv.put("ESLAB2", ESLAB2);
        cv.put("ESLAB3", ESLAB3);
        cv.put("ESLAB4", ESLAB4);
        cv.put("ESLAB5", ESLAB5);
        cv.put("ESLAB6", ESLAB6);
        cv.put("CHARITABLE_RBT_AMT", CHARITABLE_RBT_AMT);
        cv.put("SOLAR_RBT_AMT", SOLAR_RBT_AMT);
        cv.put("FL_RBT_AMT", FL_RBT_AMT);
        cv.put("HANDICAP_RBT_AMT", HANDICAP_RBT_AMT);
        cv.put("PL_RBT_AMT", PL_RBT_AMT);
        cv.put("IPSET_RBT_AMT", IPSET_RBT_AMT);
        cv.put("REBATEFROMCCB_AMT", REBATEFROMCCB_AMT);
        cv.put("TOD_CHARGES", TOD_CHARGES);
        cv.put("PF_PENALITY_AMT", PF_PENALITY_AMT);
        cv.put("EXLOAD_MDPENALITY", EXLOAD_MDPENALITY);
        cv.put("CURR_BILL_AMOUNT", CURR_BILL_AMOUNT);
        cv.put("ROUNDING_AMOUNT", ROUNDING_AMOUNT);
        cv.put("DUE_DATE", DUE_DATE);
        cv.put("DISCONN_DATE", DISCONN_DATE);
        cv.put("CREADJ", CREADJ);
        cv.put("PREADKVAH", PREADKVAH);

        Cursor subdiv_data = databasehelper.subdivdetails();
        subdiv_data.moveToNext();
        String SUBDIVCODE = subdiv_data.getString(subdiv_data.getColumnIndex("SUBDIV_CODE"));
        subdiv_data.close();

        ContentValues collection_values = new ContentValues();
        collection_values.put("RRNO", RRNO);
        collection_values.put("CONSUMER_ID", CONSNO);
        collection_values.put("LF_NO", LEGFOL);
        collection_values.put("NAME", NAME);
        collection_values.put("TARIFF_NAME", TARIFF_NAME);
        collection_values.put("CHQ_DISSHONOUR_FLAG", "N");
        collection_values.put("CHQ_DISSHONOUR_DATE", "01/01/1990");
        collection_values.put("SUB_DIVISION", SUBDIVCODE);
        collection_values.put("MRCODE", MRCODE);
        collection_values.put("PAYABLE_AMOUNT", PAYABLE);
        databasehelper.insertInCollectionInput(collection_values);

        String path = functionsCall.filepath("Textfile");
        String filename = "TextReport.txt";
        File log = new File(path + File.separator + filename);
        try {
            if (!log.exists()) {
                log.createNewFile();
            }
            PrintWriter out = new PrintWriter(new FileWriter(log, true));
            out.append("insert into MAST_OUT(MONTH,READDATE,RRNO,NAME,ADD1,TARIFF,MF,PREVSTAT,AVGCON,LINEMIN,"
                    + "SANCHP,SANCKW,PRVRED,FR,IR,DLCOUNT,ARREARS,PF_FLAG,BILLFOR,MRCODE,LEGFOL,ODDEVEN,SSNO,CONSNO,"
                    + "REBATE_FLAG,RREBATE,EXTRA1,DATA1,EXTRA2,DATA2,DEPOSIT,MTRDIGIT,ASDAMT,IODAMT,PFVAL,BMDVAL,"
                    + "INTEREST_AMT,CAP_FLAG,TOD_FLAG,TOD_PREVIOUS1,TOD_PREVIOUS3,INT_ON_DEP,"
                    + "SO_FEEDER_TC_POLE,TARIFF_NAME,PREV_READ_DATE,BILL_DAYS,MTR_SERIAL_NO,"
                    + "FDRNAME,TCCODE,MTR_FLAG,PRES_RDG,PRES_STS,UNITS,FIX,ENGCHG,REBATE_AMOUNT,TAX_AMOUNT,"
                    + "BMD_PENALTY,PF_PENALTY,PAYABLE,BILLDATE,BILLTIME,TOD_CURRENT1,TOD_CURRENT3,GOK_SUBSIDY,"
                    + "DEM_REVENUE,GPS_LAT,GPS_LONG,PAYABLE_REAL,PAYABLE_PROFIT,PAYABLE_LOSS,BILL_PRINTED,FSLAB1,FSLAB2,FSLAB3,FSLAB4,FSLAB5,"
                    + "ESLAB1,ESLAB2,ESLAB3,ESLAB4,ESLAB5,ESLAB6,CHARITABLE_RBT_AMT,SOLAR_RBT_AMT,FL_RBT_AMT,HANDICAP_RBT_AMT,PL_RBT_AMT,"
                    + "IPSET_RBT_AMT,REBATEFROMCCB_AMT,TOD_CHARGES,PF_PENALITY_AMT,EXLOAD_MDPENALITY,CURR_BILL_AMOUNT,ROUNDING_AMOUNT,"
                    + "DUE_DATE,DISCONN_DATE,CREADJ, PREADKVAH)"
                    + "values(" + "'")
                    .append(MONTH).append("'").append(",")
                    .append("'").append(READDATE).append("'").append(",")
                    .append("'").append(RRNO).append("'").append(",")
                    .append("'").append(NAME).append("'").append(",")
                    .append("'").append(ADD1).append("'").append(",")
                    .append("'").append(TARIFF).append("'").append(",")
                    .append("'").append(MF).append("'").append(",")
                    .append("'").append(PREVSTAT).append("'").append(",")
                    .append("'").append(AVGCON).append("'").append(",")
                    .append("'").append(LINEMIN).append("'").append(",")
                    .append("'").append(SANCHP).append("'").append(",")
                    .append("'").append(SANCKW).append("'").append(",")
                    .append("'").append(PRVRED).append("'").append(",")
                    .append("'").append(FR).append("'").append(",")
                    .append("'").append(IR).append("'").append(",")
                    .append("'").append(DLCOUNT).append("'").append(",")
                    .append("'").append(ARREARS).append("'").append(",")
                    .append("'").append(PF_FLAG).append("'").append(",")
                    .append("'").append(BILLFOR).append("'").append(",")
                    .append("'").append(MRCODE).append("'").append(",")
                    .append("'").append(LEGFOL).append("'").append(",")
                    .append("'").append(ODDEVEN).append("'").append(",")
                    .append("'").append(SSNO).append("'").append(",")
                    .append("'").append(CONSNO).append("'").append(",")
                    .append("'").append(REBATE_FLAG).append("'").append(",")
                    .append("'").append(RREBATE).append("'").append(",")
                    .append("'").append(EXTRA1).append("'").append(",")
                    .append("'").append(DATA1).append("'").append(",")
                    .append("'").append(EXTRA2).append("'").append(",")
                    .append("'").append(DATA2).append("'").append(",")
                    .append("'").append(PH_NO).append("'").append(",")
                    .append("'").append(DEPOSIT).append("'").append(",")
                    .append("'").append(MTRDIGIT).append("'").append(",")
                    .append("'").append(ASDAMT).append("'").append(",")
                    .append("'").append(IODAMT).append("'").append(",")
                    .append("'").append(PFVAL).append("'").append(",")
                    .append("'").append(BMDVAL).append("'").append(",")
                    .append("'").append(BILL_NO).append("'").append(",")
                    .append("'").append(INTEREST_AMT).append("'").append(",")
                    .append("'").append(CAP_FLAG).append("'").append(",")
                    .append("'").append(TOD_FLAG).append("'").append(",")
                    .append("'").append(TOD_PREVIOUS1).append("'").append(",")
                    .append("'").append(TOD_PREVIOUS3).append("'").append(",")
                    .append("'").append(INT_ON_DEP).append("'").append(",")
                    .append("'").append(SO_FEEDER_TC_POLE).append("'").append(",")
                    .append("'").append(TARIFF_NAME).append("'").append(",")
                    .append("'").append(PREV_READ_DATE).append("'").append(",")
                    .append("'").append(BILL_DAYS).append("'").append(",")
                    .append("'").append(MTR_SERIAL_NO).append("'").append(",")
                    .append("'").append(FDRNAME).append("'").append(",")
                    .append("'").append(TCCODE).append("'").append(",")
                    .append("'").append(MTR_FLAG).append("'").append(",")
                    .append("'").append(PRES_RDG).append("'").append(",")
                    .append("'").append(PRES_STS).append("'").append(",")
                    .append("'").append(UNITS).append("'").append(",")
                    .append("'").append(FIX).append("'").append(",")
                    .append("'").append(ENGCHG).append("'").append(",")
                    .append("'").append(REBATE_AMOUNT).append("'").append(",")
                    .append("'").append(TAX_AMOUNT).append("'").append(",")
                    .append("'").append(BMD_PENALTY).append("'").append(",")
                    .append("'").append(PF_PENALTY).append("'").append(",")
                    .append("'").append(PAYABLE).append("'").append(",")
                    .append("'").append(BILLDATE).append("'").append(",")
                    .append("'").append(BILLTIME).append("'").append(",")
                    .append("'").append(TOD_CURRENT1).append("'").append(",")
                    .append("'").append(TOD_CURRENT3).append("'").append(",")
                    .append("'").append(GOK_SUBSIDY).append("'").append(",")
                    .append("'").append(DEM_REVENUE).append("'").append(",")
                    .append("'").append(GPS_LAT).append("'").append(",")
                    .append("'").append(GPS_LONG).append("'").append(",")
                    .append("'").append(IMGADD).append("'").append(",")
                    .append("'").append(PAYABLE_REAL).append("'").append(",")
                    .append("'").append(PAYABLE_PROFIT).append("'").append(",")
                    .append("'").append(PAYABLE_LOSS).append("'").append(",")
                    .append("'").append(BILL_PRINTED).append("'").append(",")
                    .append("'").append(FSLAB1).append("'").append(",")
                    .append("'").append(FSLAB2).append("'").append(",")
                    .append("'").append(FSLAB3).append("'").append(",")
                    .append("'").append(FSLAB4).append("'").append(",")
                    .append("'").append(FSLAB5).append("'").append(",")
                    .append("'").append(ESLAB1).append("'").append(",")
                    .append("'").append(ESLAB2).append("'").append(",")
                    .append("'").append(ESLAB3).append("'").append(",")
                    .append("'").append(ESLAB4).append("'").append(",")
                    .append("'").append(ESLAB5).append("'").append(",")
                    .append("'").append(ESLAB6).append("'").append(",")
                    .append("'").append(CHARITABLE_RBT_AMT).append("'").append(",")
                    .append("'").append(SOLAR_RBT_AMT).append("'").append(",")
                    .append("'").append(FL_RBT_AMT).append("'").append(",")
                    .append("'").append(HANDICAP_RBT_AMT).append("'").append(",")
                    .append("'").append(PL_RBT_AMT).append("'").append(",")
                    .append("'").append(IPSET_RBT_AMT).append("'").append(",")
                    .append("'").append(REBATEFROMCCB_AMT).append("'").append(",")
                    .append("'").append(TOD_CHARGES).append("'").append(",")
                    .append("'").append(PF_PENALITY_AMT).append("'").append(",")
                    .append("'").append(EXLOAD_MDPENALITY).append("'").append(",")
                    .append("'").append(CURR_BILL_AMOUNT).append("'").append(",")
                    .append("'").append(ROUNDING_AMOUNT).append("'").append(",")
                    .append("'").append(DUE_DATE).append("'").append(",")
                    .append("'").append(DISCONN_DATE).append("'").append(",")
                    .append("'").append(CREADJ).append("'").append(",")
                    .append("'").append(PREADKVAH).append("'")
                    .append(")");
            out.append("\r\n");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
