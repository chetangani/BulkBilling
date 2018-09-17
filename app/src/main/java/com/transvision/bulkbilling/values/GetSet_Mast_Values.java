package com.transvision.bulkbilling.values;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.os.Handler;
import android.text.TextUtils;

import com.transvision.bulkbilling.database.Databasehelper;
import com.transvision.bulkbilling.extra.FunctionsCall;

import org.apache.commons.lang3.StringUtils;

import java.text.DecimalFormat;

import static com.transvision.bulkbilling.extra.Constants.COLUMNS_ERROR;
import static com.transvision.bulkbilling.extra.Constants.INSERT_MAST_OLD_OUT_ERROR;
import static com.transvision.bulkbilling.extra.Constants.INSERT_MAST_OUT_ERROR;
import static com.transvision.bulkbilling.extra.Constants.INSERT_SUCCESS;
import static com.transvision.bulkbilling.extra.Constants.NEW_TARRIF_CALCULATION;
import static com.transvision.bulkbilling.extra.Constants.OLD_TARRIF_CALCULATION;
import static com.transvision.bulkbilling.extra.Constants.READ_MAST_CUST_ERROR;
import static com.transvision.bulkbilling.extra.Constants.SPLIT_TARRIF_CALCULATION;

public class GetSet_Mast_Values {
    private FunctionsCall functionsCall = new FunctionsCall();
    private Handler handler;

    public GetSet_Mast_Values(Handler handler) {
        this.handler = handler;
    }

    public void getvalues(GetSet_MastCust getSetMastCust, Databasehelper databasehelper) {
        Cursor data1 = databasehelper.subdivdetails();
        data1.moveToNext();
        try {
            getSetMastCust.setFec(functionsCall.convert_decimal(functionsCall.getCursorValue(data1, "FEC")));
        }  catch (NullPointerException e) {
            e.printStackTrace();
            getSetMastCust.setFec(0);
        }
        data1.close();

        try {
            Cursor check_data = databasehelper.getBilledRecordData();
            if (check_data.getCount() > 0) {
                check_data.moveToNext();
                String pres_date = functionsCall.getCursorValue(check_data, "READDATE");
                String prev_date = functionsCall.getCursorValue(check_data, "PREV_READ_DATE");
                double days_diff = Double.parseDouble(functionsCall.CalculateDays(prev_date, pres_date));
                if (days_diff > 31) {
                    double dl_diff = (days_diff / 30);
                    dl_diff = dl_diff - 1;
                    Cursor updatedl = databasehelper.updateDLrecord(""+dl_diff);
                    updatedl.moveToNext();
                    updatedl.close();
                } else if (days_diff < 28) {
                    double dl_diff = ((days_diff+1) / 30);
                    dl_diff = dl_diff - 1;
                    Cursor updatedl = databasehelper.updateDLrecord(""+dl_diff);
                    updatedl.moveToNext();
                    updatedl.close();
                }
            }
            check_data.close();
            Cursor data = databasehelper.getBilledRecordData();
            data.moveToNext();
            set_Mast_Cust_values(data, getSetMastCust);
            if (check_column(data, "PRES_RDG")) {
                getSetMastCust.setColumn_name("PRES_RDG");
                handler.sendEmptyMessage(COLUMNS_ERROR);
                return;
            } else getSetMastCust.setPRES_RDG(functionsCall.getCursorValue(data, "PRES_RDG"));
            if (check_column(data, "PRES_STS")) {
                getSetMastCust.setColumn_name("PRES_STS");
                handler.sendEmptyMessage(COLUMNS_ERROR);
                return;
            } else getSetMastCust.setPRES_STS(functionsCall.getCursorValue(data, "PRES_STS"));
            if (check_column(data, "TOD_CURRENT1")) {
                getSetMastCust.setColumn_name("TOD_CURRENT1");
                handler.sendEmptyMessage(COLUMNS_ERROR);
                return;
            } else getSetMastCust.setTOD_CURRENT1(functionsCall.getCursorValue(data, "TOD_CURRENT1"));
            if (check_column(data, "TOD_CURRENT3")) {
                getSetMastCust.setColumn_name("TOD_CURRENT3");
                handler.sendEmptyMessage(COLUMNS_ERROR);
                return;
            } else getSetMastCust.setTOD_CURRENT3(functionsCall.getCursorValue(data, "TOD_CURRENT3"));
            Cursor ssno = databasehelper.addingSSNO();
            if (ssno.getCount() > 0) {
                ssno.moveToNext();
                getSetMastCust.setSSNO(ssno.getString(ssno.getColumnIndex("SSNO")));
            } else getSetMastCust.setSSNO("1");
            data.close();
            ssno.close();
            setValues(databasehelper, getSetMastCust);
        } catch (Exception e) {
            e.printStackTrace();
            handler.sendEmptyMessage(READ_MAST_CUST_ERROR);
        }
    }

    public void set_Mast_Cust_values(Cursor data, GetSet_MastCust getSetMastCust) {
        getSetMastCust.setMONTH(functionsCall.getCursorValue(data, "MONTH"));
        getSetMastCust.setREADDATE(functionsCall.getCursorValue(data, "READDATE"));
        getSetMastCust.setRRNO(functionsCall.getCursorValue(data, "RRNO"));
        getSetMastCust.setNAME(functionsCall.getCursorValue(data, "NAME"));
        getSetMastCust.setADD1(functionsCall.getCursorValue(data, "ADD1"));
        getSetMastCust.setTARIFF(functionsCall.getCursorValue(data, "TARIFF"));
        getSetMastCust.setMF(functionsCall.getCursorValue(data, "MF"));
        getSetMastCust.setPREVSTAT(functionsCall.getCursorValue(data, "PREVSTAT"));
        getSetMastCust.setAVGCON(functionsCall.getCursorValue(data, "AVGCON"));
        getSetMastCust.setLINEMIN(functionsCall.getCursorValue(data, "LINEMIN"));
        getSetMastCust.setSANCHP(functionsCall.getCursorValue(data, "SANCHP"));
        getSetMastCust.setSANCKW(functionsCall.getCursorValue(data, "SANCKW"));
        getSetMastCust.setPRVRED(functionsCall.getCursorValue(data, "PRVRED"));
        getSetMastCust.setFR(functionsCall.getCursorValue(data, "FR"));
        getSetMastCust.setIR(functionsCall.getCursorValue(data, "IR"));
        getSetMastCust.setDLCOUNT(functionsCall.getCursorValue(data, "DLCOUNT"));
        getSetMastCust.setARREARS(functionsCall.getCursorValue(data, "ARREARS"));
        getSetMastCust.setPF_FLAG(functionsCall.getCursorValue(data, "PF_FLAG"));
        getSetMastCust.setMRCODE(functionsCall.getCursorValue(data, "MRCODE"));
        getSetMastCust.setLEGFOL(functionsCall.getCursorValue(data, "LEGFOL"));
        getSetMastCust.setODDEVEN(functionsCall.getCursorValue(data, "ODDEVEN"));
        getSetMastCust.setSSNO(functionsCall.getCursorValue(data, "SSNO"));
        getSetMastCust.setCONSNO(functionsCall.getCursorValue(data, "CONSNO"));
        getSetMastCust.setREBATE_FLAG(functionsCall.getCursorValue(data, "REBATE_FLAG"));
        getSetMastCust.setRREBATE(functionsCall.getCursorValue(data, "RREBATE"));
        getSetMastCust.setEXTRA1(functionsCall.getCursorValue(data, "EXTRA1"));
        getSetMastCust.setDATA1(functionsCall.getCursorValue(data, "DATA1"));
        if (getSetMastCust.getFec() != 0.0) {
            getSetMastCust.setEXTRA2("FAC");
        } else getSetMastCust.setEXTRA2(functionsCall.getCursorValue(data, "EXTRA2"));
        getSetMastCust.setDATA2(functionsCall.getCursorValue(data, "DATA2"));
        getSetMastCust.setDEPOSIT(functionsCall.getCursorValue(data, "DEPOSIT"));
        getSetMastCust.setMTRDIGIT(functionsCall.getCursorValue(data, "MTRDIGIT"));
        getSetMastCust.setPFVAL(functionsCall.getCursorValue(data, "PFVAL"));
        getSetMastCust.setBMDVAL(functionsCall.getCursorValue(data, "BMDVAL"));
        getSetMastCust.setASDAMT(functionsCall.getCursorValue(data, "ASDAMT"));
        getSetMastCust.setIODAMT(functionsCall.getCursorValue(data, "IODAMT"));
        getSetMastCust.setINTEREST_AMT(functionsCall.getCursorValue(data, "INTEREST_AMT"));
        getSetMastCust.setCAP_FLAG(functionsCall.getCursorValue(data, "CAP_FLAG"));
        getSetMastCust.setTOD_FLAG(functionsCall.getCursorValue(data, "TOD_FLAG"));
        getSetMastCust.setTOD_PREVIOUS1(functionsCall.getCursorValue(data, "TOD_PREVIOUS1"));
        getSetMastCust.setTOD_PREVIOUS3(functionsCall.getCursorValue(data, "TOD_PREVIOUS3"));
        getSetMastCust.setINT_ON_DEP(functionsCall.getCursorValue(data, "INT_ON_DEP"));
        getSetMastCust.setSO_FEEDER_TC_POLE(functionsCall.getCursorValue(data, "SO_FEEDER_TC_POLE"));
        getSetMastCust.setTARIFF_NAME(functionsCall.getCursorValue(data, "TARIFF_NAME"));
        getSetMastCust.setPREV_READ_DATE(functionsCall.getCursorValue(data, "PREV_READ_DATE"));
        getSetMastCust.setBILL_DAYS(functionsCall.getCursorValue(data, "BILL_DAYS"));
        getSetMastCust.setMTR_SERIAL_NO(functionsCall.getCursorValue(data, "MTR_SERIAL_NO"));
        getSetMastCust.setCHQ_DISSHONOUR_FLAG(functionsCall.getCursorValue(data, "CHQ_DISSHONOUR_FLAG"));
        getSetMastCust.setCHQ_DISHONOUR_DATE(functionsCall.getCursorValue(data, "CHQ_DISHONOUR_DATE"));
        getSetMastCust.setFDRNAME(functionsCall.getCursorValue(data, "FDRNAME"));
        getSetMastCust.setTCCODE(functionsCall.getCursorValue(data, "TCCODE"));
        getSetMastCust.setMTR_FLAG(functionsCall.getCursorValue(data, "MTR_FLAG"));
        getSetMastCust.setHP(functionsCall.getCursorValue(data, "HP"));
        getSetMastCust.setBMDKW(functionsCall.getCursorValue(data, "BMDKW"));
        getSetMastCust.setCONNLDHP(functionsCall.getCursorValue(data, "CONNLDHP"));
        getSetMastCust.setCONNLDKW(functionsCall.getCursorValue(data, "CONNLDKW"));
        getSetMastCust.setCREADJ(functionsCall.getCursorValue(data, "CREADJ"));
        getSetMastCust.setREADKVAH(functionsCall.getCursorValue(data, "READKVAH"));
        if (!TextUtils.isEmpty(functionsCall.getCursorValue(data, "PH_NO"))) {
            getSetMastCust.setPH_NO(functionsCall.getCursorValue(data, "PH_NO"));
        } else getSetMastCust.setPH_NO("0");
        if (!TextUtils.isEmpty(functionsCall.getCursorValue(data, "AADHAAR"))) {
            getSetMastCust.setAADHAAR(functionsCall.getCursorValue(data, "AADHAAR"));
        } else getSetMastCust.setAADHAAR("0");
        if (!TextUtils.isEmpty(functionsCall.getCursorValue(data, "MAIL"))) {
            getSetMastCust.setMAIL(functionsCall.getCursorValue(data, "MAIL"));
        } else getSetMastCust.setMAIL("0");
        if (!TextUtils.isEmpty(functionsCall.getCursorValue(data, "GPS_LAT"))) {
            getSetMastCust.setGPS_LAT(functionsCall.getCursorValue(data, "GPS_LAT"));
        } else getSetMastCust.setGPS_LAT("0");
        if (!TextUtils.isEmpty(functionsCall.getCursorValue(data, "GPS_LONG"))) {
            getSetMastCust.setGPS_LONG(functionsCall.getCursorValue(data, "GPS_LONG"));
        } else getSetMastCust.setGPS_LONG("0");
        if (!TextUtils.isEmpty(functionsCall.getCursorValue(data, "ELECTION"))) {
            getSetMastCust.setELECTION(functionsCall.getCursorValue(data, "ELECTION"));
        } else getSetMastCust.setELECTION("0");
        if (!TextUtils.isEmpty(functionsCall.getCursorValue(data, "RATION"))) {
            getSetMastCust.setRATION(functionsCall.getCursorValue(data, "RATION"));
        } else getSetMastCust.setRATION("0");
        if (!TextUtils.isEmpty(functionsCall.getCursorValue(data, "WATER"))) {
            getSetMastCust.setWATER(functionsCall.getCursorValue(data, "WATER"));
        } else getSetMastCust.setWATER("0");
        if (!TextUtils.isEmpty(functionsCall.getCursorValue(data, "HOUSE_NO"))) {
            getSetMastCust.setHOUSE_NO(functionsCall.getCursorValue(data, "HOUSE_NO"));
        } else getSetMastCust.setHOUSE_NO("0");
        try {
            if (functionsCall.getCursorValue(data, "INVENTORY_LOAD") == null)
                getSetMastCust.setINVENTORY_LOAD("0");
            else getSetMastCust.setINVENTORY_LOAD(functionsCall.getCursorValue(data, "INVENTORY_LOAD"));
        } catch (CursorIndexOutOfBoundsException | NullPointerException e) {
            e.printStackTrace();
            getSetMastCust.setINVENTORY_LOAD("0");
        }
        getSetMastCust.setFDRCODE(functionsCall.getCursorValue(data, "FDRCODE"));
        getSetMastCust.setTCNAME(functionsCall.getCursorValue(data, "TCNAME"));
    }

    private boolean check_column(Cursor data, String column) {
        long result = data.getColumnIndex(column);
        return result == -1;
    }

    private void setValues(Databasehelper databasehelper, GetSet_MastCust getSetMastCust) {
        ClassCalculation classCalculation = new ClassCalculation(databasehelper);
        classCalculation.settariff(getSetMastCust.getTARIFF());
        int Present_STS = Integer.parseInt(getSetMastCust.getPRES_STS());
        int intTodFlag = Integer.parseInt(getSetMastCust.getTOD_FLAG());
        if (intTodFlag != 1) {
            if (Present_STS == 3 || Present_STS == 4 || Present_STS == 6 || Present_STS == 8 ||Present_STS == 9 || Present_STS == 10
                    || Present_STS == 11 || Present_STS == 12 || Present_STS == 13 || Present_STS == 14 || Present_STS == 16
                    || Present_STS == 17 || Present_STS == 18 || Present_STS == 19 || Present_STS == 20 || Present_STS == 21
                    || Present_STS == 22 || Present_STS == 23 || Present_STS == 24 || Present_STS == 25 || Present_STS == 26
                    || Present_STS == 27 || Present_STS == 28 || Present_STS == 29 || Present_STS == 30) {
                int units = functionsCall.convert_int(getSetMastCust.getMF()) *
                        (functionsCall.convert_int(getSetMastCust.getPRES_RDG()) - functionsCall.convert_int(getSetMastCust.getPRVRED()));
                int diff = functionsCall.convert_int(getSetMastCust.getFR()) - functionsCall.convert_int(getSetMastCust.getIR());
                int consume = units + diff;
                getSetMastCust.setUnits(""+consume);
                classCalculation.setconsumtion(getSetMastCust.getUnits());
            } else {
                if (Present_STS == 5) {
                    getSetMastCust.setUnits(dialover(""+functionsCall.convert_int(getSetMastCust.getPRVRED()),
                            ""+functionsCall.convert_int(getSetMastCust.getPRES_RDG())));
                    classCalculation.setconsumtion(getSetMastCust.getUnits());
                } else {
                    Cursor data = databasehelper.subdivdetails();
                    data.moveToNext();
                    String dl_flag = data.getString(data.getColumnIndexOrThrow("DL_FLAG"));
                    if (!TextUtils.isEmpty(dl_flag)) {
                        if (StringUtils.startsWithIgnoreCase(dl_flag, "Y") || StringUtils.startsWithIgnoreCase(dl_flag, "y")) {
                            getSetMastCust.setUnits(""+(functionsCall.convert_int(getSetMastCust.getMF()) *
                                    functionsCall.convert_int(getSetMastCust.getAVGCON())));
                            classCalculation.setconsumtion(getSetMastCust.getUnits());
                        } else {
                            getSetMastCust.setUnits("0");
                            classCalculation.setconsumtion(getSetMastCust.getUnits());
                        }
                    } else {
                        getSetMastCust.setUnits(""+(functionsCall.convert_int(getSetMastCust.getMF()) *
                                functionsCall.convert_int(getSetMastCust.getAVGCON())));
                        classCalculation.setconsumtion(getSetMastCust.getUnits());
                    }
                }
            }
        } else {
            int units = functionsCall.convert_int(getSetMastCust.getPRES_RDG()) - functionsCall.convert_int(getSetMastCust.getPRVRED());
            int totalunits = functionsCall.convert_int(getSetMastCust.getMF()) *
                    (units + functionsCall.convert_int(getSetMastCust.getTOD_CURRENT1()) +
                            functionsCall.convert_int(getSetMastCust.getTOD_CURRENT3()));
            int diff = functionsCall.convert_int(getSetMastCust.getFR()) - functionsCall.convert_int(getSetMastCust.getIR());
            int totalconsume = totalunits + diff;
            getSetMastCust.setUnits(""+totalconsume);
            classCalculation.setconsumtion(""+units);
        }
        if (Present_STS == 1 || Present_STS == 2 || Present_STS == 7 || Present_STS == 15) {
            Cursor updatedl = databasehelper.updateDLrecord(""+0);
            updatedl.moveToNext();
            Cursor data = databasehelper.getBilledRecordData();
            data.moveToNext();
            getSetMastCust.setDLCOUNT(functionsCall.getCursorValue(data, "DLCOUNT"));
        }
        classCalculation.setSanctionKw(getSetMastCust.getSANCKW());
        classCalculation.setSantionHp(getSetMastCust.getSANCHP());
        classCalculation.setFlagRebate(getSetMastCust.getREBATE_FLAG());
        classCalculation.setPfValue(functionsCall.convert_decimal(getSetMastCust.getPFVAL()));
        classCalculation.setPfFlagValue(functionsCall.convert_int(getSetMastCust.getPF_FLAG()));
        classCalculation.setTod_ofPeakValue(functionsCall.convert_int(getSetMastCust.getTOD_CURRENT3()));
        classCalculation.setTod_onPeakValue(functionsCall.convert_int(getSetMastCust.getTOD_CURRENT1()));
        classCalculation.setInt_calArrears(functionsCall.convert_decimal(getSetMastCust.getARREARS()));
        classCalculation.setCal_deposit(functionsCall.convert_decimal(getSetMastCust.getDEPOSIT()));
        classCalculation.setCalMF(functionsCall.convert_int(getSetMastCust.getMF()));
        classCalculation.setRrebate(getSetMastCust.getRREBATE());
        classCalculation.setIntr_Amt(functionsCall.convert_decimal(getSetMastCust.getINTEREST_AMT()));
        classCalculation.setdata1(functionsCall.convert_decimal(getSetMastCust.getDATA1()));
        classCalculation.setCredit_adj(functionsCall.convert_decimal(getSetMastCust.getCREADJ()));
        if (getSetMastCust.getEXTRA2().equals("FAC")) {
            functionsCall.check_fac_status(getSetMastCust, getSetMastCust.getREADDATE(), getSetMastCust.getPREV_READ_DATE(),
                    getSetMastCust.getUnits(), databasehelper);
            getSetMastCust.setDATA2(""+(getSetMastCust.getFec() * getSetMastCust.getFac_days()));
            functionsCall.logStatus("days: "+getSetMastCust.getFac_days());
            functionsCall.logStatus("remaining: "+getSetMastCust.getFac_remaining_days());
        }
        classCalculation.setdata2(functionsCall.convert_decimal(getSetMastCust.getDATA2()));
        if (getSetMastCust.getTARIFF().equals("70")) {
            classCalculation.setdays70tariff(getSetMastCust.getBILL_DAYS());
            pdreading(databasehelper, getSetMastCust);
        }
        start_calculation(databasehelper, getSetMastCust, classCalculation);
    }

    private String dialover(String last_reading, String pre_reading) {
        int s5 = Integer.parseInt(pre_reading);
        int s6 = Integer.parseInt(last_reading);
        int s7 = last_reading.length();
        String repeat = new String(new char[s7]).replace("\0", "9");
        int s8 = Integer.parseInt(repeat);
        int s9 = s8 - s6;
        int s10 = s9 + s5 + 1;
        return String.valueOf(s10);
    }

    private void pdreading(Databasehelper databasehelper, GetSet_MastCust getSetMastCust) {
        if (!getSetMastCust.getBMDVAL().equals("")) {
            Cursor c14 = databasehelper.getTarrifDataBJ("0", getSetMastCust.getTARIFF());
            getSetMastCust.setPd_penalty(functionsCall.pdcalculation(getSetMastCust.getBMDVAL(), c14, getSetMastCust.getSANCKW(),
                    getSetMastCust.getBILL_DAYS()));
        } else {
            Cursor c14 = databasehelper.getTarrifDataBJ("0", getSetMastCust.getTARIFF());
            getSetMastCust.setPd_penalty(functionsCall.pdcalculation(getSetMastCust.getBMDVAL(), c14, getSetMastCust.getSANCKW(),
                    getSetMastCust.getBILL_DAYS()));
        }
    }

    private void start_calculation(Databasehelper databasehelper, GetSet_MastCust getSetMastCust, ClassCalculation calculation) {
        Calculation_Tariff calculationTariff = new Calculation_Tariff();
        calculationTariff.tariff_calculation(getSetMastCust.getTARIFF(), getSetMastCust.getRREBATE(), getSetMastCust.getREBATE_FLAG(),
                getSetMastCust.getCONSNO(), getSetMastCust.getUnits(), databasehelper, getSetMastCust);
        Cursor current_data = getSetMastCust.getCurrent_data();
        Cursor old_data = getSetMastCust.getOld_data();
        if (current_data.getCount() > 0) {
            current_data.moveToNext();
        }
        if (old_data.getCount() > 0) {
            old_data.moveToNext();
        }
        int cons_Present_STS = functionsCall.convert_int(getSetMastCust.getPRES_STS());
        double cons_dlcount = functionsCall.convert_decimal(getSetMastCust.getDLCOUNT());
        if (cons_Present_STS == 1 || cons_Present_STS == 2 || cons_Present_STS == 7 || cons_Present_STS == 15) {
            calculation.FcCalculation(current_data, getSetMastCust.getREBATE_FLAG(), cons_dlcount, 0);
            calculation.EcCalculation(current_data, Double.parseDouble(getSetMastCust.getUnits()), cons_dlcount, 0);
        } else {
            switch (functionsCall.check_tarrif_rate(getSetMastCust.getREADDATE(), getSetMastCust.getPREV_READ_DATE(), databasehelper)) {
                case SPLIT_TARRIF_CALCULATION:
                    functionsCall.get_fc_tarrif_rate_status(getSetMastCust, getSetMastCust.getREADDATE(), getSetMastCust.getPREV_READ_DATE(),
                            databasehelper);
                    calculation.FcCalculation(current_data, getSetMastCust.getREBATE_FLAG(), getSetMastCust.getFc_normal_value(), 0);
                    calculation.FcCalculation(old_data, getSetMastCust.getREBATE_FLAG(), getSetMastCust.getFc_old_value(), 1);

                    functionsCall.get_ec_tarrif_rate_status(getSetMastCust, getSetMastCust.getREADDATE(), getSetMastCust.getPREV_READ_DATE(),
                            getSetMastCust.getUnits(), databasehelper);
                    calculation.EcCalculation(current_data, getSetMastCust.getEc_normal_value(), getSetMastCust.getFc_normal_value(), 0);
                    calculation.EcCalculation(old_data, getSetMastCust.getEc_old_value(), getSetMastCust.getFc_old_value(), 1);
                    break;

                case OLD_TARRIF_CALCULATION:
                    calculation.FcCalculation(old_data, getSetMastCust.getREBATE_FLAG(), cons_dlcount, 0);
                    calculation.EcCalculation(old_data, Double.parseDouble(getSetMastCust.getUnits()), cons_dlcount, 0);
                    break;

                case NEW_TARRIF_CALCULATION:
                    calculation.FcCalculation(current_data, getSetMastCust.getREBATE_FLAG(), cons_dlcount, 0);
                    calculation.EcCalculation(current_data, Double.parseDouble(getSetMastCust.getUnits()), cons_dlcount, 0);
                    break;
            }
        }
        getSetMastCust.setGOK_SUBSIDY(""+calculation.gokCalculation(current_data, getSetMastCust.getUnits(), cons_dlcount, 0));
        getSetMastCust.setREBATE_AMOUNT(""+calculation.billDeduCtion(current_data, getSetMastCust.getUnits(), cons_dlcount));
        getSetMastCust.setCHARITABLE_RBT_AMT(""+calculation.charityCalculation(current_data, getSetMastCust.getUnits()));
        if (functionsCall.convert_int(getSetMastCust.getPF_FLAG()) == 2 || functionsCall.convert_int(getSetMastCust.getPF_FLAG()) == 1) {
            calculation.billPenalties(current_data, getSetMastCust.getUnits());
        }
        getSetMastCust.setPF_PENALTY(""+calculation.pfPenality());
        calculation.billExtraCharges(getSetMastCust.getREADDATE(), getSetMastCust.getPREV_READ_DATE(), getSetMastCust, databasehelper, current_data);
        getSetMastCust.setBMD_PENALTY(""+calculation.bmdPenalities(functionsCall.convert_decimal(getSetMastCust.getBMDVAL()),
                getSetMastCust.getINVENTORY_LOAD(), getSetMastCust.getPF_FLAG(), getSetMastCust.getBMDKW(), getSetMastCust.getREBATE_FLAG()));
        if (getSetMastCust.getTARIFF().equals("70")) {
            double pdpen = functionsCall.convert_decimal(getSetMastCust.getPd_penalty());
            double amount = calculation.billAmount();
            getSetMastCust.setCURR_BILL_AMOUNT(""+(amount + pdpen));
        } else getSetMastCust.setCURR_BILL_AMOUNT(""+calculation.billAmount());
        getSetMastCust.setTOD_CHARGES(""+calculation.todCalculation(current_data));
        if (getSetMastCust.getTARIFF().equals("70")) {
            double payable = calculation.billFinalPayment();
            double pdpen = functionsCall.convert_decimal(getSetMastCust.getPd_penalty());
            getSetMastCust.setPAYABLE_REAL(""+(payable + pdpen));
        } else getSetMastCust.setPAYABLE_REAL(""+calculation.billFinalPayment());
        getSetMastCust.setPAYABLE(""+Math.round(functionsCall.convert_decimal(getSetMastCust.getPAYABLE_REAL())));
        double paydiff = functionsCall.convert_decimal(getSetMastCust.getPAYABLE()) - functionsCall.convert_decimal(getSetMastCust.getPAYABLE_REAL());
        if (paydiff >= 0) {
            getSetMastCust.setPAYABLE_PROFIT(functionsCall.decimalroundoff(paydiff));
            getSetMastCust.setPAYABLE_LOSS("0");
        }
        if (paydiff < 0) {
            getSetMastCust.setPAYABLE_PROFIT("0");
            getSetMastCust.setPAYABLE_LOSS(functionsCall.decimalroundoff(paydiff));
        }

        getSetMastCust.setArrErate(calculation.erateForTextViews());
        getSetMastCust.setArrFrate(calculation.frateForTextViews());
        getSetMastCust.setArrEslab(calculation.eslabForTextViews());
        getSetMastCust.setArrFslab(calculation.fslabForTextViews());
        getSetMastCust.setArrEc(calculation.ecForTextViews());
        getSetMastCust.setArrFc(calculation.fcForTextViews());
        //For Old Tariff
        getSetMastCust.setArrErate_old(calculation.erateForTextViews_old());
        getSetMastCust.setArrFrate_old(calculation.frateForTextViews_old());
        getSetMastCust.setArrEslab_old(calculation.eslabForTextViews_old());
        getSetMastCust.setArrFslab_old(calculation.fslabForTextViews_old());
        getSetMastCust.setArrEc_old(calculation.ecForTextViews_old());
        getSetMastCust.setArrFc_old(calculation.fcForTextViews_old());
        //For FC DL Values
        getSetMastCust.setArrdlFslab(calculation.dl_fslabForTextViews());
        getSetMastCust.setArrdlFslab_old(calculation.dl_fslabForTextViews_old());

        if (getSetMastCust.getREBATE_FLAG().equals("5")) {
            double totalEC = calculation.totalEC();
            getSetMastCust.setENGCHG(""+(totalEC - functionsCall.convert_decimal(getSetMastCust.getGOK_SUBSIDY())));
        } else getSetMastCust.setENGCHG(""+calculation.totalEC());
        getSetMastCust.setFIX(""+calculation.totalFC());
        getSetMastCust.setTAX_AMOUNT(functionsCall.decimalround(calculation.tax()));
        if (databasehelper.checkinserteddata(getSetMastCust.getCONSNO()))
            return;
        if (Integer.parseInt(getSetMastCust.getUnits()) < 0)
            return;
        if (functionsCall.convert_decimal(getSetMastCust.getCURR_BILL_AMOUNT()) < 0)
            return;
        if (functionsCall.convert_decimal(getSetMastCust.getTAX_AMOUNT()) < 0)
            return;
        if (functionsCall.convert_decimal(getSetMastCust.getENGCHG()) < 0)
            return;
        insertIntoTable(databasehelper, getSetMastCust, calculation);
    }

    private void insertIntoTable(Databasehelper databasehelper, GetSet_MastCust getSetMastCust, ClassCalculation calculation) {
        DecimalFormat num = new DecimalFormat("##0.00");
        ContentValues cv = new ContentValues();
        cv.put("MONTH", getSetMastCust.getMONTH());
        cv.put("READDATE", functionsCall.changedateformat(getSetMastCust.getREADDATE(), ""));
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
        cv.put("BILLFOR", "30-"+functionsCall.currentDateTimeforBilling()+"-"+"0");
        cv.put("MRCODE", getSetMastCust.getMRCODE());
        cv.put("LEGFOL", getSetMastCust.getLEGFOL());
        cv.put("ODDEVEN", getSetMastCust.getODDEVEN());
        if (TextUtils.isEmpty(getSetMastCust.getSSNO()))
            cv.put("SSNO", "0");
        else cv.put("SSNO", getSetMastCust.getSSNO());
        cv.put("CONSNO", getSetMastCust.getCONSNO());
        cv.put("REBATE_FLAG", getSetMastCust.getREBATE_FLAG());
        cv.put("RREBATE", getSetMastCust.getRREBATE());
        if (TextUtils.isEmpty(getSetMastCust.getEXTRA1()))
            cv.put("EXTRA1", "0");
        else cv.put("EXTRA1", getSetMastCust.getEXTRA1());
        if (getSetMastCust.getDATA1().equals("0"))
            if (!num.format(functionsCall.convert_decimal(getSetMastCust.getDATA1())).equals("0.00"))
                cv.put("DATA1", num.format(functionsCall.convert_decimal(getSetMastCust.getDATA1())));
            else cv.put("DATA1", "0.00");
        else cv.put("DATA1", functionsCall.convert_decimal(getSetMastCust.getDATA1()));
        if (getSetMastCust.getFec() == 0.0) {
            cv.put("EXTRA2", "0");
            cv.put("DATA2", "0.00");
        } else {
            if (!num.format(functionsCall.convert_decimal(getSetMastCust.getDATA2())).equals("0.00")) {
                cv.put("EXTRA2", getSetMastCust.getEXTRA2());
                cv.put("DATA2", num.format(functionsCall.convert_decimal(getSetMastCust.getDATA2())));
            } else {
                cv.put("EXTRA2", "0");
                cv.put("DATA2", "0.00");
            }
        }
        cv.put("PH_NO", getSetMastCust.getPH_NO());
        cv.put("DEPOSIT", getSetMastCust.getDEPOSIT());
        cv.put("MTRDIGIT", getSetMastCust.getMTRDIGIT());
        cv.put("ASDAMT", getSetMastCust.getASDAMT());
        cv.put("IODAMT", getSetMastCust.getTOD_CHARGES());
        cv.put("PFVAL", getSetMastCust.getPFVAL());
        if (getSetMastCust.getTARIFF().equals("70")) {
            cv.put("BMDVAL", getSetMastCust.getBMDVAL());
        } else {
            if (getSetMastCust.getPF_FLAG().equals("0")) {
                if (functionsCall.convert_decimal(getSetMastCust.getINVENTORY_LOAD()) > 0) {
                    cv.put("BMDVAL", getSetMastCust.getINVENTORY_LOAD());
                } else cv.put("BMDVAL", getSetMastCust.getBMDVAL());
            } else cv.put("BMDVAL", getSetMastCust.getBMDVAL());
        }
        cv.put("BILL_NO", getSetMastCust.getCONSNO()+functionsCall.getMonth(getSetMastCust.getREADDATE())+"01");
        if (getSetMastCust.getINTEREST_AMT().equals("0"))
            cv.put("INTEREST_AMT", "0.0");
        else cv.put("INTEREST_AMT", getSetMastCust.getINTEREST_AMT());
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
        cv.put("PRES_RDG", getSetMastCust.getPRES_RDG());
        cv.put("PRES_STS", getSetMastCust.getPRES_STS());
        cv.put("UNITS", getSetMastCust.getUnits());
        cv.put("FIX", getSetMastCust.getFIX());
        cv.put("ENGCHG", getSetMastCust.getENGCHG());
        if (getSetMastCust.getRREBATE().equals("7"))
            cv.put("REBATE_AMOUNT", getSetMastCust.getCHARITABLE_RBT_AMT());
        else cv.put("REBATE_AMOUNT", getSetMastCust.getREBATE_AMOUNT());
        cv.put("TAX_AMOUNT", ""+getSetMastCust.getTAX_AMOUNT());
        if (getSetMastCust.getTARIFF().equals("70")) {
            cv.put("BMD_PENALTY", getSetMastCust.getPd_penalty());
        } else cv.put("BMD_PENALTY", getSetMastCust.getBMD_PENALTY());
        cv.put("PF_PENALTY", getSetMastCust.getPF_PENALTY());
        cv.put("PAYABLE", getSetMastCust.getPAYABLE());
        cv.put("BILLDATE", functionsCall.changedateformat(functionsCall.dateSet(), ""));
        cv.put("BILLTIME", functionsCall.currentDateandTime());
        cv.put("TOD_CURRENT1", getSetMastCust.getTOD_CURRENT1());
        cv.put("TOD_CURRENT3", getSetMastCust.getTOD_CURRENT3());
        cv.put("GOK_SUBSIDY", getSetMastCust.getGOK_SUBSIDY());
        cv.put("DEM_REVENUE", getSetMastCust.getCURR_BILL_AMOUNT());
        cv.put("GPS_LAT", "0.0");
        cv.put("GPS_LONG", "0.0");
        cv.put("ONLINE_FLAG", "N");
        cv.put("IMGADD", "noimage");
        cv.put("PAYABLE_REAL", getSetMastCust.getPAYABLE_REAL());
        cv.put("PAYABLE_PROFIT", getSetMastCust.getPAYABLE_PROFIT());
        cv.put("PAYABLE_LOSS", getSetMastCust.getPAYABLE_LOSS());
        cv.put("BILL_PRINTED", "N");
        cv.put("BATTERY_CHARGE", "0");
        cv.put("SIGNAL_STRENGTH", "0");

        //Inserting totalfs values
        if ((""+num.format(getSetMastCust.getArrFc()[1])).equals("0.00"))
            cv.put("FSLAB1", "0.00 x 0.00 , 0.0");
        else cv.put("FSLAB1", ""+getSetMastCust.getArrFslab()[1]+" x "+""+getSetMastCust.getArrFrate()[1]+" , "+""+getSetMastCust.getArrFc()[1]);
        if ((""+num.format(getSetMastCust.getArrFc()[2])).equals("0.00"))
            cv.put("FSLAB2", "0.00 x 0.00 , 0.0");
        else cv.put("FSLAB2", ""+getSetMastCust.getArrFslab()[2]+" x "+""+getSetMastCust.getArrFrate()[2]+" , "+""+getSetMastCust.getArrFc()[2]);
        if ((""+num.format(getSetMastCust.getArrFc()[3])).equals("0.00"))
            cv.put("FSLAB3", "0.00 x 0.00 , 0.0");
        else cv.put("FSLAB3", ""+getSetMastCust.getArrFslab()[3]+" x "+""+getSetMastCust.getArrFrate()[3]+" , "+""+getSetMastCust.getArrFc()[3]);
        if ((""+num.format(getSetMastCust.getArrFc()[4])).equals("0.00"))
            cv.put("FSLAB4", "0.00 x 0.00 , 0.0");
        else cv.put("FSLAB4", ""+getSetMastCust.getArrFslab()[4]+" x "+""+getSetMastCust.getArrFrate()[4]+" , "+""+getSetMastCust.getArrFc()[4]);
        if ((""+num.format(getSetMastCust.getArrFc()[5])).equals("0.00"))
            cv.put("FSLAB5", "0.00 x 0.00 , 0.0");
        else cv.put("FSLAB5", ""+getSetMastCust.getArrFslab()[5]+" x "+""+getSetMastCust.getArrFrate()[5]+" , "+""+getSetMastCust.getArrFc()[5]);

        //Inserting totalec values
        if ((""+num.format(getSetMastCust.getArrEc()[1])).equals("0.00"))
            cv.put("ESLAB1", "0.000 x 0.00 , 0.00");
        else cv.put("ESLAB1", ""+getSetMastCust.getArrEslab()[1]+" x "+""+getSetMastCust.getArrErate()[1]+" , "+""+getSetMastCust.getArrEc()[1]);
        if ((""+num.format(getSetMastCust.getArrEc()[2])).equals("0.00"))
            cv.put("ESLAB2", "0.000 x 0.00 , 0.00");
        else cv.put("ESLAB2", ""+getSetMastCust.getArrEslab()[2]+" x "+""+getSetMastCust.getArrErate()[2]+" , "+""+getSetMastCust.getArrEc()[2]);
        if ((""+num.format(getSetMastCust.getArrEc()[3])).equals("0.00"))
            cv.put("ESLAB3", "0.000 x 0.00 , 0.00");
        else cv.put("ESLAB3", ""+getSetMastCust.getArrEslab()[3]+" x "+""+getSetMastCust.getArrErate()[3]+" , "+""+getSetMastCust.getArrEc()[3]);
        if ((""+num.format(getSetMastCust.getArrEc()[4])).equals("0.00"))
            cv.put("ESLAB4", "0.000 x 0.00 , 0.00");
        else cv.put("ESLAB4", ""+getSetMastCust.getArrEslab()[4]+" x "+""+getSetMastCust.getArrErate()[4]+" , "+""+getSetMastCust.getArrEc()[4]);
        if ((""+num.format(getSetMastCust.getArrEc()[5])).equals("0.00"))
            cv.put("ESLAB5", "0.000 x 0.00 , 0.00");
        else cv.put("ESLAB5", ""+getSetMastCust.getArrEslab()[5]+" x "+""+getSetMastCust.getArrErate()[5]+" , "+""+getSetMastCust.getArrEc()[5]);
        if ((""+num.format(getSetMastCust.getArrEc()[6])).equals("0.00"))
            cv.put("ESLAB6", "0.000 x 0.00 , 0.00");
        else cv.put("ESLAB6", ""+getSetMastCust.getArrEslab()[6]+" x "+""+getSetMastCust.getArrErate()[6]+" , "+""+getSetMastCust.getArrEc()[6]);

        if (getSetMastCust.getTARIFF().equals("20") || getSetMastCust.getTARIFF().equals("21"))
            cv.put("CHARITABLE_RBT_AMT", functionsCall.decimalroundoff(functionsCall.convert_decimal(getSetMastCust.getCHARITABLE_RBT_AMT())));
        else cv.put("CHARITABLE_RBT_AMT", "0.0");
        if (getSetMastCust.getTARIFF().equals("20") || getSetMastCust.getTARIFF().equals("21") || getSetMastCust.getTARIFF().equals("23")) {
            if (getSetMastCust.getREBATE_FLAG().equals("1")) {
                cv.put("SOLAR_RBT_AMT", functionsCall.decimalroundoff(functionsCall.convert_decimal(getSetMastCust.getREBATE_AMOUNT())));
            } else cv.put("SOLAR_RBT_AMT", "0.0");
        } else cv.put("SOLAR_RBT_AMT", "0.0");
        if (getSetMastCust.getTARIFF().equals("30")) {
            if (getSetMastCust.getREBATE_FLAG().equals("2")) {
                cv.put("HANDICAP_RBT_AMT", functionsCall.decimalroundoff(functionsCall.convert_decimal(getSetMastCust.getREBATE_AMOUNT())));
            } else cv.put("HANDICAP_RBT_AMT", "0.0");
        } else cv.put("HANDICAP_RBT_AMT", "0.0");
        if (getSetMastCust.getTARIFF().equals("50") || getSetMastCust.getTARIFF().equals("51") || getSetMastCust.getTARIFF().equals("52") ||
                getSetMastCust.getTARIFF().equals("53")) {
            if (getSetMastCust.getREBATE_FLAG().equals("5")) {
                cv.put("PL_RBT_AMT", functionsCall.decimalroundoff(functionsCall.convert_decimal(getSetMastCust.getGOK_SUBSIDY())));
            } else cv.put("PL_RBT_AMT", "0.0");
        } else cv.put("PL_RBT_AMT", "0.0");
        if (getSetMastCust.getTARIFF().equals("23")) {
            cv.put("FL_RBT_AMT", functionsCall.decimalroundoff(functionsCall.convert_decimal(getSetMastCust.getREBATE_AMOUNT())));
        } else cv.put("FL_RBT_AMT", "0.0");
        cv.put("IPSET_RBT_AMT", "0.0");
        cv.put("REBATEFROMCCB_AMT", "0.0");
        cv.put("TOD_CHARGES", "0.0");
        cv.put("PF_PENALITY_AMT", "0.0");
        cv.put("EXLOAD_MDPENALITY", "0.0");
        cv.put("CURR_BILL_AMOUNT", "0.0");
        cv.put("ROUNDING_AMOUNT", "0.0");
        cv.put("DUE_DATE", functionsCall.changedateformat(functionsCall.duedate(functionsCall.changedateformat(getSetMastCust.getREADDATE(), "/"), 14), ""));
        cv.put("DISCONN_DATE", functionsCall.changedateformat(functionsCall.duedate(functionsCall.changedateformat(getSetMastCust.getREADDATE(), "/"), 30), ""));
        cv.put("CREADJ", getSetMastCust.getCREADJ());
        cv.put("PREADKVAH", getSetMastCust.getREADKVAH());
        cv.put("AADHAAR", getSetMastCust.getAADHAAR());
        cv.put("MAIL", getSetMastCust.getMAIL());
        cv.put("MTR_DIGIT", getSetMastCust.getMTRDIGIT());
        cv.put("ELECTION", getSetMastCust.getELECTION());
        cv.put("RATION", getSetMastCust.getRATION());
        cv.put("WATER", getSetMastCust.getWATER());
        cv.put("HOUSE_NO", getSetMastCust.getHOUSE_NO());
        cv.put("VERSION", "0");
        cv.put("FDRCODE", getSetMastCust.getFDRCODE());
        cv.put("TCNAME", getSetMastCust.getTCNAME());
        cv.put("RENT", ""+calculation.getPrepaid_rent());
        cv.put("DL_FC", ""+getSetMastCust.getArrdlFslab()[1] + "," + ""+getSetMastCust.getArrdlFslab()[2]);
        if (databasehelper.insertInTable(cv))
            insert_old_slabs(databasehelper, getSetMastCust, calculation);
        else handler.sendEmptyMessage(INSERT_MAST_OUT_ERROR);
    }

    private void insert_old_slabs(Databasehelper databasehelper, GetSet_MastCust getSetMastCust, ClassCalculation calculation) {
        DecimalFormat num = new DecimalFormat("##0.00");
        ContentValues slabs_cv = new ContentValues();
        slabs_cv.put("READDATE", functionsCall.changedateformat(getSetMastCust.getREADDATE(), ""));
        slabs_cv.put("CONSNO", getSetMastCust.getCONSNO());
        slabs_cv.put("FIX", calculation.totalFC_old());
        slabs_cv.put("FIX_NEW", calculation.totalFC_new());
        slabs_cv.put("ENGCHG", calculation.totalEC_old());
        slabs_cv.put("ENGCHG_NEW", calculation.totalEC_new());

        if ((""+num.format(getSetMastCust.getArrFc_old()[1])).equals("0.00"))
            slabs_cv.put("FSLAB1", "0.00 x 0.00 , 0.0");
        else slabs_cv.put("FSLAB1", ""+getSetMastCust.getArrFslab_old()[1]+" x " +""+getSetMastCust.getArrFrate_old()[1]+" , "+""+
                getSetMastCust.getArrFc_old()[1]);
        if ((""+num.format(getSetMastCust.getArrFc_old()[2])).equals("0.00"))
            slabs_cv.put("FSLAB2", "0.00 x 0.00 , 0.0");
        else slabs_cv.put("FSLAB2", ""+getSetMastCust.getArrFslab_old()[2]+" x " +""+getSetMastCust.getArrFrate_old()[2]+" , "+""+
                getSetMastCust.getArrFc_old()[2]);
        if ((""+num.format(getSetMastCust.getArrFc_old()[3])).equals("0.00"))
            slabs_cv.put("FSLAB3", "0.00 x 0.00 , 0.0");
        else slabs_cv.put("FSLAB3", ""+getSetMastCust.getArrFslab_old()[3]+" x " +""+getSetMastCust.getArrFrate_old()[3]+" , "+""+
                getSetMastCust.getArrFc_old()[3]);
        if ((""+num.format(getSetMastCust.getArrFc_old()[4])).equals("0.00"))
            slabs_cv.put("FSLAB4", "0.00 x 0.00 , 0.0");
        else slabs_cv.put("FSLAB4", ""+getSetMastCust.getArrFslab_old()[4]+" x " +""+getSetMastCust.getArrFrate_old()[4]+" , "+""+
                getSetMastCust.getArrFc_old()[4]);
        if ((""+num.format(getSetMastCust.getArrFc_old()[5])).equals("0.00"))
            slabs_cv.put("FSLAB5", "0.00 x 0.00 , 0.0");
        else slabs_cv.put("FSLAB5", ""+getSetMastCust.getArrFslab_old()[5]+" x " +""+getSetMastCust.getArrFrate_old()[5]+" , "+""+
                getSetMastCust.getArrFc_old()[5]);

        if ((""+num.format(getSetMastCust.getArrEc_old()[1])).equals("0.00"))
            slabs_cv.put("ESLAB1", "0.000 x 0.00 , 0.00");
        else slabs_cv.put("ESLAB1", ""+getSetMastCust.getArrEslab_old()[1]+" x "
                +""+getSetMastCust.getArrErate_old()[1]+" , "+""+getSetMastCust.getArrEc_old()[1]);
        if ((""+num.format(getSetMastCust.getArrEc_old()[2])).equals("0.00"))
            slabs_cv.put("ESLAB2", "0.000 x 0.00 , 0.00");
        else slabs_cv.put("ESLAB2", ""+getSetMastCust.getArrEslab_old()[2]+" x "
                +""+getSetMastCust.getArrErate_old()[2]+" , "+""+getSetMastCust.getArrEc_old()[2]);
        if ((""+num.format(getSetMastCust.getArrEc_old()[3])).equals("0.00"))
            slabs_cv.put("ESLAB3", "0.000 x 0.00 , 0.00");
        else slabs_cv.put("ESLAB3", ""+getSetMastCust.getArrEslab_old()[3]+" x "
                +""+getSetMastCust.getArrErate_old()[3]+" , "+""+getSetMastCust.getArrEc_old()[3]);
        if ((""+num.format(getSetMastCust.getArrEc_old()[4])).equals("0.00"))
            slabs_cv.put("ESLAB4", "0.000 x 0.00 , 0.00");
        else slabs_cv.put("ESLAB4", ""+getSetMastCust.getArrEslab_old()[4]+" x "
                +""+getSetMastCust.getArrErate_old()[4]+" , "+""+getSetMastCust.getArrEc_old()[4]);
        if ((""+num.format(getSetMastCust.getArrEc_old()[5])).equals("0.00"))
            slabs_cv.put("ESLAB5", "0.000 x 0.00 , 0.00");
        else slabs_cv.put("ESLAB5", ""+getSetMastCust.getArrEslab_old()[5]+" x "
                +""+getSetMastCust.getArrErate_old()[5]+" , "+""+getSetMastCust.getArrEc_old()[5]);
        if ((""+num.format(getSetMastCust.getArrEc_old()[6])).equals("0.00"))
            slabs_cv.put("ESLAB6", "0.000 x 0.00 , 0.00");
        else slabs_cv.put("ESLAB6", ""+getSetMastCust.getArrEslab_old()[6]+" x "
                +""+getSetMastCust.getArrErate_old()[6]+" , "+""+getSetMastCust.getArrEc_old()[6]);

        slabs_cv.put("BILL_DAYS", getSetMastCust.getOld_days());
        slabs_cv.put("BILL_DAYS_NEW", getSetMastCust.getNormal_days());
        slabs_cv.put("DL_COUNT", getSetMastCust.getFc_old_value() + 1);
        slabs_cv.put("DL_COUNT_NEW", getSetMastCust.getFc_normal_value() + 1);
        slabs_cv.put("UNITS", getSetMastCust.getEc_old_value());
        slabs_cv.put("UNITS_NEW", getSetMastCust.getEc_normal_value());
        slabs_cv.put("FAC", getSetMastCust.getFac_days());
        slabs_cv.put("FAC_NULL", getSetMastCust.getFac_remaining_days());
        slabs_cv.put("DL_FC", ""+getSetMastCust.getArrdlFslab_old()[1] + "," + ""+getSetMastCust.getArrdlFslab_old()[2]);
        //noinspection ConstantConditions
        if (functionsCall.convert_int(getSetMastCust.getPRES_STS()) != 1 || functionsCall.convert_int(getSetMastCust.getPRES_STS()) != 2 ||
                functionsCall.convert_int(getSetMastCust.getPRES_STS()) != 7 || functionsCall.convert_int(getSetMastCust.getPRES_STS()) != 15) {
            if (StringUtils.startsWithIgnoreCase(functionsCall.check_tarrif_rate(getSetMastCust.getREADDATE(),
                    getSetMastCust.getPREV_READ_DATE(), databasehelper), SPLIT_TARRIF_CALCULATION)) {
                if (databasehelper.insertInSlabsTable(slabs_cv))
                    handler.sendEmptyMessage(INSERT_SUCCESS);
                else handler.sendEmptyMessage(INSERT_MAST_OLD_OUT_ERROR);
            } else handler.sendEmptyMessage(INSERT_SUCCESS);
        } else handler.sendEmptyMessage(INSERT_SUCCESS);
    }
}
