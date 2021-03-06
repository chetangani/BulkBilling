package com.transvision.bulkbilling.values;

import android.database.Cursor;

import com.transvision.bulkbilling.database.Bulk_Database;
import com.transvision.bulkbilling.extra.FunctionsCall;

public class MastOut_Values {
    private FunctionsCall functionsCall = new FunctionsCall();

    public void getBilledValues(Bulk_Database bulkDatabase, String account_id, GetSet_Mast_Out getSetMastOut) {
        Cursor data = bulkDatabase.report(account_id);
        if (data.getCount() > 0) {
            getSetMastOut.setMONTH(functionsCall.getCursorValue(data, "MONTH"));
            getSetMastOut.setREADDATE(functionsCall.getCursorValue(data, "READDATE"));
            getSetMastOut.setRRNO(functionsCall.getCursorValue(data, "RRNO"));
            getSetMastOut.setNAME(functionsCall.getCursorValue(data, "NAME"));
            getSetMastOut.setADD1(functionsCall.getCursorValue(data, "ADD1"));
            getSetMastOut.setTARIFF(functionsCall.getCursorValue(data, "TARIFF"));
            getSetMastOut.setMF(functionsCall.getCursorValue(data, "MF"));
            getSetMastOut.setPREVSTAT(functionsCall.getCursorValue(data, "PREVSTAT"));
            getSetMastOut.setAVGCON(functionsCall.getCursorValue(data, "AVGCON"));
            getSetMastOut.setLINEMIN(functionsCall.getCursorValue(data, "LINEMIN"));
            getSetMastOut.setSANCHP(functionsCall.getCursorValue(data, "SANCHP"));
            getSetMastOut.setSANCKW(functionsCall.getCursorValue(data, "SANCKW"));
            getSetMastOut.setPRVRED(functionsCall.getCursorValue(data, "PRVRED"));
            getSetMastOut.setFR(functionsCall.getCursorValue(data, "FR"));
            getSetMastOut.setIR(functionsCall.getCursorValue(data, "IR"));
            getSetMastOut.setDLCOUNT(functionsCall.getCursorValue(data, "DLCOUNT"));
            getSetMastOut.setARREARS(functionsCall.getCursorValue(data, "ARREARS"));
            getSetMastOut.setPF_FLAG(functionsCall.getCursorValue(data, "PF_FLAG"));
            getSetMastOut.setBILLFOR(functionsCall.getCursorValue(data, "BILLFOR"));
            getSetMastOut.setMRCODE(functionsCall.getCursorValue(data, "MRCODE"));
            getSetMastOut.setLEGFOL(functionsCall.getCursorValue(data, "LEGFOL"));
            getSetMastOut.setODDEVEN(functionsCall.getCursorValue(data, "ODDEVEN"));
            getSetMastOut.setSSNO(functionsCall.getCursorValue(data, "SSNO"));
            getSetMastOut.setCONSNO(functionsCall.getCursorValue(data, "CONSNO"));
            getSetMastOut.setPH_NO(functionsCall.getCursorValue(data, "PH_NO"));
            getSetMastOut.setREBATE_FLAG(functionsCall.getCursorValue(data, "REBATE_FLAG"));
            getSetMastOut.setRREBATE(functionsCall.getCursorValue(data, "RREBATE"));
            getSetMastOut.setEXTRA1(functionsCall.getCursorValue(data, "EXTRA1"));
            getSetMastOut.setDATA1(functionsCall.getCursorValue(data, "DATA1"));
            getSetMastOut.setEXTRA2(functionsCall.getCursorValue(data, "EXTRA2"));
            getSetMastOut.setDATA2(functionsCall.getCursorValue(data, "DATA2"));
            getSetMastOut.setDEPOSIT(functionsCall.getCursorValue(data, "DEPOSIT"));
            getSetMastOut.setMTRDIGIT(functionsCall.getCursorValue(data, "MTRDIGIT"));
            getSetMastOut.setASDAMT(functionsCall.getCursorValue(data, "ASDAMT"));
            getSetMastOut.setIODAMT(functionsCall.getCursorValue(data, "IODAMT"));
            getSetMastOut.setPFVAL(functionsCall.getCursorValue(data, "PFVAL"));
            getSetMastOut.setBMDVAL(functionsCall.getCursorValue(data, "BMDVAL"));
            getSetMastOut.setBILL_NO(functionsCall.getCursorValue(data, "BILL_NO"));
            getSetMastOut.setINTEREST_AMT(functionsCall.getCursorValue(data, "INTEREST_AMT"));
            getSetMastOut.setCAP_FLAG(functionsCall.getCursorValue(data, "CAP_FLAG"));
            getSetMastOut.setTOD_FLAG(functionsCall.getCursorValue(data, "TOD_FLAG"));
            getSetMastOut.setTOD_PREVIOUS1(functionsCall.getCursorValue(data, "TOD_PREVIOUS1"));
            getSetMastOut.setTOD_PREVIOUS3(functionsCall.getCursorValue(data, "TOD_PREVIOUS3"));
            getSetMastOut.setINT_ON_DEP(functionsCall.getCursorValue(data, "INT_ON_DEP"));
            getSetMastOut.setSO_FEEDER_TC_POLE(functionsCall.getCursorValue(data, "SO_FEEDER_TC_POLE"));
            getSetMastOut.setTARIFF_NAME(functionsCall.getCursorValue(data, "TARIFF_NAME"));
            getSetMastOut.setPREV_READ_DATE(functionsCall.getCursorValue(data, "PREV_READ_DATE"));
            getSetMastOut.setBILL_DAYS(functionsCall.getCursorValue(data, "BILL_DAYS"));
            getSetMastOut.setMTR_SERIAL_NO(functionsCall.getCursorValue(data, "MTR_SERIAL_NO"));
            getSetMastOut.setCHQ_DISSHONOUR_FLAG(functionsCall.getCursorValue(data, "CHQ_DISSHONOUR_FLAG"));
            getSetMastOut.setCHQ_DISHONOUR_DATE(functionsCall.getCursorValue(data, "CHQ_DISHONOUR_DATE"));
            getSetMastOut.setFDRNAME(functionsCall.getCursorValue(data, "FDRNAME"));
            getSetMastOut.setTCCODE(functionsCall.getCursorValue(data, "TCCODE"));
            getSetMastOut.setMTR_FLAG(functionsCall.getCursorValue(data, "MTR_FLAG"));
            getSetMastOut.setPRES_RDG(functionsCall.getCursorValue(data, "PRES_RDG"));
            getSetMastOut.setPRES_STS(functionsCall.getCursorValue(data, "PRES_STS"));
            getSetMastOut.setUNITS(functionsCall.getCursorValue(data, "UNITS"));
            getSetMastOut.setFIX(functionsCall.getCursorValue(data, "FIX"));
            getSetMastOut.setENGCHG(functionsCall.getCursorValue(data, "ENGCHG"));
            getSetMastOut.setREBATE_AMOUNT(functionsCall.getCursorValue(data, "REBATE_AMOUNT"));
            getSetMastOut.setTAX_AMOUNT(functionsCall.getCursorValue(data, "TAX_AMOUNT"));
            getSetMastOut.setBMD_PENALTY(functionsCall.getCursorValue(data, "BMD_PENALTY"));
            getSetMastOut.setPF_PENALTY(functionsCall.getCursorValue(data, "PF_PENALTY"));
            getSetMastOut.setPAYABLE(functionsCall.getCursorValue(data, "PAYABLE"));
            getSetMastOut.setBILLDATE(functionsCall.getCursorValue(data, "BILLDATE"));
            getSetMastOut.setBILLTIME(functionsCall.getCursorValue(data, "BILLTIME"));
            getSetMastOut.setTOD_CURRENT1(functionsCall.getCursorValue(data, "TOD_CURRENT1"));
            getSetMastOut.setTOD_CURRENT3(functionsCall.getCursorValue(data, "TOD_CURRENT3"));
            getSetMastOut.setGOK_SUBSIDY(functionsCall.getCursorValue(data, "GOK_SUBSIDY"));
            getSetMastOut.setDEM_REVENUE(functionsCall.getCursorValue(data, "DEM_REVENUE"));
            getSetMastOut.setGPS_LAT(functionsCall.getCursorValue(data, "GPS_LAT"));
            getSetMastOut.setGPS_LONG(functionsCall.getCursorValue(data, "GPS_LONG"));
            getSetMastOut.setONLINE_FLAG(functionsCall.getCursorValue(data, "ONLINE_FLAG"));
            getSetMastOut.setBATTERY_CHARGE(functionsCall.getCursorValue(data, "BATTERY_CHARGE"));
            getSetMastOut.setSIGNAL_STRENGTH(functionsCall.getCursorValue(data, "SIGNAL_STRENGTH"));
            getSetMastOut.setIMGADD(functionsCall.getCursorValue(data, "IMGADD"));
            getSetMastOut.setPAYABLE_REAL(functionsCall.getCursorValue(data, "PAYABLE_REAL"));
            getSetMastOut.setPAYABLE_PROFIT(functionsCall.getCursorValue(data, "PAYABLE_PROFIT"));
            getSetMastOut.setPAYABLE_LOSS(functionsCall.getCursorValue(data, "PAYABLE_LOSS"));
            getSetMastOut.setBILL_PRINTED(functionsCall.getCursorValue(data, "BILL_PRINTED"));
            getSetMastOut.setFSLAB1(functionsCall.getCursorValue(data, "FSLAB1"));
            getSetMastOut.setFSLAB2(functionsCall.getCursorValue(data, "FSLAB2"));
            getSetMastOut.setFSLAB3(functionsCall.getCursorValue(data, "FSLAB3"));
            getSetMastOut.setFSLAB4(functionsCall.getCursorValue(data, "FSLAB4"));
            getSetMastOut.setFSLAB5(functionsCall.getCursorValue(data, "FSLAB5"));
            getSetMastOut.setESLAB1(functionsCall.getCursorValue(data, "ESLAB1"));
            getSetMastOut.setESLAB2(functionsCall.getCursorValue(data, "ESLAB2"));
            getSetMastOut.setESLAB3(functionsCall.getCursorValue(data, "ESLAB3"));
            getSetMastOut.setESLAB4(functionsCall.getCursorValue(data, "ESLAB4"));
            getSetMastOut.setESLAB5(functionsCall.getCursorValue(data, "ESLAB5"));
            getSetMastOut.setESLAB6(functionsCall.getCursorValue(data, "ESLAB6"));
            getSetMastOut.setCHARITABLE_RBT_AMT(functionsCall.getCursorValue(data, "CHARITABLE_RBT_AMT"));
            getSetMastOut.setSOLAR_RBT_AMT(functionsCall.getCursorValue(data, "SOLAR_RBT_AMT"));
            getSetMastOut.setFL_RBT_AMT(functionsCall.getCursorValue(data, "FL_RBT_AMT"));
            getSetMastOut.setHANDICAP_RBT_AMT(functionsCall.getCursorValue(data, "HANDICAP_RBT_AMT"));
            getSetMastOut.setPL_RBT_AMT(functionsCall.getCursorValue(data, "PL_RBT_AMT"));
            getSetMastOut.setIPSET_RBT_AMT(functionsCall.getCursorValue(data, "IPSET_RBT_AMT"));
            getSetMastOut.setREBATEFROMCCB_AMT(functionsCall.getCursorValue(data, "REBATEFROMCCB_AMT"));
            getSetMastOut.setTOD_CHARGES(functionsCall.getCursorValue(data, "TOD_CHARGES"));
            getSetMastOut.setPF_PENALITY_AMT(functionsCall.getCursorValue(data, "PF_PENALITY_AMT"));
            getSetMastOut.setEXLOAD_MDPENALITY(functionsCall.getCursorValue(data, "EXLOAD_MDPENALITY"));
            getSetMastOut.setCURR_BILL_AMOUNT(functionsCall.getCursorValue(data, "CURR_BILL_AMOUNT"));
            getSetMastOut.setROUNDING_AMOUNT(functionsCall.getCursorValue(data, "ROUNDING_AMOUNT"));
            getSetMastOut.setDUE_DATE(functionsCall.getCursorValue(data, "DUE_DATE"));
            getSetMastOut.setDISCONN_DATE(functionsCall.getCursorValue(data, "DISCONN_DATE"));
            getSetMastOut.setCREADJ(functionsCall.getCursorValue(data, "CREADJ"));
            getSetMastOut.setPREADKVAH(functionsCall.getCursorValue(data, "PREADKVAH"));
            getSetMastOut.setAADHAAR(functionsCall.getCursorValue(data, "AADHAAR"));
            getSetMastOut.setMAIL(functionsCall.getCursorValue(data, "MAIL"));
            getSetMastOut.setMTR_DIGIT(functionsCall.getCursorValue(data, "MTR_DIGIT"));
            getSetMastOut.setELECTION(functionsCall.getCursorValue(data, "ELECTION"));
            getSetMastOut.setRATION(functionsCall.getCursorValue(data, "RATION"));
            getSetMastOut.setWATER(functionsCall.getCursorValue(data, "WATER"));
            getSetMastOut.setHOUSE_NO(functionsCall.getCursorValue(data, "HOUSE_NO"));
            getSetMastOut.setVERSION(functionsCall.getCursorValue(data, "VERSION"));
            getSetMastOut.setDL_FC(functionsCall.getCursorValue(data, "DL_FC"));
            getSetMastOut.setFDRCODE(functionsCall.getCursorValue(data, "FDRCODE"));
            getSetMastOut.setTCNAME(functionsCall.getCursorValue(data, "TCNAME"));
            getSetMastOut.setRENT(functionsCall.getCursorValue(data, "RENT"));
        }
    }
}
