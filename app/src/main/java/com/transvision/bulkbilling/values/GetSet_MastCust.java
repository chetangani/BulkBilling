package com.transvision.bulkbilling.values;

import android.database.Cursor;

public class GetSet_MastCust {
    private String MONTH, READDATE, RRNO, NAME, ADD1, TARIFF, MF, PREVSTAT, AVGCON, LINEMIN, SANCHP, SANCKW, PRVRED, FR, IR, DLCOUNT,
            ARREARS, PF_FLAG, MRCODE, LEGFOL, ODDEVEN, SSNO, CONSNO, REBATE_FLAG, RREBATE, EXTRA1, DATA1, EXTRA2, DATA2, PH_NO,
            DEPOSIT, MTRDIGIT, PFVAL, BMDVAL, ASDAMT, IODAMT, INTEREST_AMT, CAP_FLAG, TOD_FLAG, TOD_PREVIOUS1, TOD_PREVIOUS3,
            INT_ON_DEP, SO_FEEDER_TC_POLE, TARIFF_NAME, PREV_READ_DATE, BILL_DAYS, MTR_SERIAL_NO, CHQ_DISSHONOUR_FLAG, CHQ_DISHONOUR_DATE,
            FDRNAME, TCCODE, MTR_FLAG, INVENTORY_LOAD, HP, BMDKW, CONNLDHP, CONNLDKW, CREADJ, READKVAH, GPS_LAT, TOD_CHARGES,
            GPS_LONG, AADHAAR, MAIL, ELECTION, RATION, WATER, HOUSE_NO, FDRCODE, TCNAME, PRES_RDG, PRES_STS, TOD_CURRENT1, TOD_CURRENT3,
            units, FIX, ENGCHG, REBATE_AMOUNT, TAX_AMOUNT, BMD_PENALTY, PF_PENALTY, PAYABLE, GOK_SUBSIDY, DEM_REVENUE,
            PAYABLE_REAL, PAYABLE_PROFIT, PAYABLE_LOSS, CHARITABLE_RBT_AMT, CURR_BILL_AMOUNT, pd_penalty, column_name;
    private int old_days=0, normal_days=0;
    private double EC, fc_old_value=0, fc_normal_value=0, ec_old_value=0, ec_normal_value=0, fac_days=0, fac_remaining_days=0, fec;
    private String tax_days_new="", tax_days_old="";
    private Cursor current_data=null, old_data=null;

    private double[] ArrFrate = new double[10];
    private double[] ArrFslab = new double[10];
    private double[] ArrErate = new double[10];
    private double[] ArrEslab = new double[10];
    private double[] ArrEc = new double[10];
    private double[] ArrFc = new double[10];
    private double[] ArrFrate_old = new double[10];
    private double[] ArrFslab_old = new double[10];
    private double[] ArrErate_old = new double[10];
    private double[] ArrEslab_old = new double[10];
    private double[] ArrEc_old = new double[10];
    private double[] ArrFc_old = new double[10];
    private double[] ArrdlFslab = new double[10];
    private double[] ArrdlFslab_old = new double[10];

    public GetSet_MastCust() {
    }

    public String getMONTH() {
        return MONTH;
    }

    public void setMONTH(String MONTH) {
        this.MONTH = MONTH;
    }

    public String getREADDATE() {
        return READDATE;
    }

    public void setREADDATE(String READDATE) {
        this.READDATE = READDATE;
    }

    public String getRRNO() {
        return RRNO;
    }

    public void setRRNO(String RRNO) {
        this.RRNO = RRNO;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getADD1() {
        return ADD1;
    }

    public void setADD1(String ADD1) {
        this.ADD1 = ADD1;
    }

    public String getTARIFF() {
        return TARIFF;
    }

    public void setTARIFF(String TARIFF) {
        this.TARIFF = TARIFF;
    }

    public String getMF() {
        return MF;
    }

    public void setMF(String MF) {
        this.MF = MF;
    }

    public String getPREVSTAT() {
        return PREVSTAT;
    }

    public void setPREVSTAT(String PREVSTAT) {
        this.PREVSTAT = PREVSTAT;
    }

    public String getAVGCON() {
        return AVGCON;
    }

    public void setAVGCON(String AVGCON) {
        this.AVGCON = AVGCON;
    }

    public String getLINEMIN() {
        return LINEMIN;
    }

    public void setLINEMIN(String LINEMIN) {
        this.LINEMIN = LINEMIN;
    }

    public String getSANCHP() {
        return SANCHP;
    }

    public void setSANCHP(String SANCHP) {
        this.SANCHP = SANCHP;
    }

    public String getSANCKW() {
        return SANCKW;
    }

    public void setSANCKW(String SANCKW) {
        this.SANCKW = SANCKW;
    }

    public String getPRVRED() {
        return PRVRED;
    }

    public void setPRVRED(String PRVRED) {
        this.PRVRED = PRVRED;
    }

    public String getFR() {
        return FR;
    }

    public void setFR(String FR) {
        this.FR = FR;
    }

    public String getIR() {
        return IR;
    }

    public void setIR(String IR) {
        this.IR = IR;
    }

    public String getDLCOUNT() {
        return DLCOUNT;
    }

    public void setDLCOUNT(String DLCOUNT) {
        this.DLCOUNT = DLCOUNT;
    }

    public String getARREARS() {
        return ARREARS;
    }

    public void setARREARS(String ARREARS) {
        this.ARREARS = ARREARS;
    }

    public String getPF_FLAG() {
        return PF_FLAG;
    }

    public void setPF_FLAG(String PF_FLAG) {
        this.PF_FLAG = PF_FLAG;
    }

    public String getMRCODE() {
        return MRCODE;
    }

    public void setMRCODE(String MRCODE) {
        this.MRCODE = MRCODE;
    }

    public String getLEGFOL() {
        return LEGFOL;
    }

    public void setLEGFOL(String LEGFOL) {
        this.LEGFOL = LEGFOL;
    }

    public String getODDEVEN() {
        return ODDEVEN;
    }

    public void setODDEVEN(String ODDEVEN) {
        this.ODDEVEN = ODDEVEN;
    }

    public String getSSNO() {
        return SSNO;
    }

    public void setSSNO(String SSNO) {
        this.SSNO = SSNO;
    }

    public String getCONSNO() {
        return CONSNO;
    }

    public void setCONSNO(String CONSNO) {
        this.CONSNO = CONSNO;
    }

    public String getREBATE_FLAG() {
        return REBATE_FLAG;
    }

    public void setREBATE_FLAG(String REBATE_FLAG) {
        this.REBATE_FLAG = REBATE_FLAG;
    }

    public String getRREBATE() {
        return RREBATE;
    }

    public void setRREBATE(String RREBATE) {
        this.RREBATE = RREBATE;
    }

    public String getEXTRA1() {
        return EXTRA1;
    }

    public void setEXTRA1(String EXTRA1) {
        this.EXTRA1 = EXTRA1;
    }

    public String getDATA1() {
        return DATA1;
    }

    public void setDATA1(String DATA1) {
        this.DATA1 = DATA1;
    }

    public String getEXTRA2() {
        return EXTRA2;
    }

    public void setEXTRA2(String EXTRA2) {
        this.EXTRA2 = EXTRA2;
    }

    public String getDATA2() {
        return DATA2;
    }

    public void setDATA2(String DATA2) {
        this.DATA2 = DATA2;
    }

    public String getPH_NO() {
        return PH_NO;
    }

    public void setPH_NO(String PH_NO) {
        this.PH_NO = PH_NO;
    }

    public String getDEPOSIT() {
        return DEPOSIT;
    }

    public void setDEPOSIT(String DEPOSIT) {
        this.DEPOSIT = DEPOSIT;
    }

    public String getMTRDIGIT() {
        return MTRDIGIT;
    }

    public void setMTRDIGIT(String MTRDIGIT) {
        this.MTRDIGIT = MTRDIGIT;
    }

    public String getPFVAL() {
        return PFVAL;
    }

    public void setPFVAL(String PFVAL) {
        this.PFVAL = PFVAL;
    }

    public String getBMDVAL() {
        return BMDVAL;
    }

    public void setBMDVAL(String BMDVAL) {
        this.BMDVAL = BMDVAL;
    }

    public String getASDAMT() {
        return ASDAMT;
    }

    public void setASDAMT(String ASDAMT) {
        this.ASDAMT = ASDAMT;
    }

    public String getIODAMT() {
        return IODAMT;
    }

    public void setIODAMT(String IODAMT) {
        this.IODAMT = IODAMT;
    }

    public String getINTEREST_AMT() {
        return INTEREST_AMT;
    }

    public void setINTEREST_AMT(String INTEREST_AMT) {
        this.INTEREST_AMT = INTEREST_AMT;
    }

    public String getCAP_FLAG() {
        return CAP_FLAG;
    }

    public void setCAP_FLAG(String CAP_FLAG) {
        this.CAP_FLAG = CAP_FLAG;
    }

    public String getTOD_FLAG() {
        return TOD_FLAG;
    }

    public void setTOD_FLAG(String TOD_FLAG) {
        this.TOD_FLAG = TOD_FLAG;
    }

    public String getTOD_PREVIOUS1() {
        return TOD_PREVIOUS1;
    }

    public void setTOD_PREVIOUS1(String TOD_PREVIOUS1) {
        this.TOD_PREVIOUS1 = TOD_PREVIOUS1;
    }

    public String getTOD_PREVIOUS3() {
        return TOD_PREVIOUS3;
    }

    public void setTOD_PREVIOUS3(String TOD_PREVIOUS3) {
        this.TOD_PREVIOUS3 = TOD_PREVIOUS3;
    }

    public String getINT_ON_DEP() {
        return INT_ON_DEP;
    }

    public void setINT_ON_DEP(String INT_ON_DEP) {
        this.INT_ON_DEP = INT_ON_DEP;
    }

    public String getSO_FEEDER_TC_POLE() {
        return SO_FEEDER_TC_POLE;
    }

    public void setSO_FEEDER_TC_POLE(String SO_FEEDER_TC_POLE) {
        this.SO_FEEDER_TC_POLE = SO_FEEDER_TC_POLE;
    }

    public String getTARIFF_NAME() {
        return TARIFF_NAME;
    }

    public void setTARIFF_NAME(String TARIFF_NAME) {
        this.TARIFF_NAME = TARIFF_NAME;
    }

    public String getPREV_READ_DATE() {
        return PREV_READ_DATE;
    }

    public void setPREV_READ_DATE(String PREV_READ_DATE) {
        this.PREV_READ_DATE = PREV_READ_DATE;
    }

    public String getBILL_DAYS() {
        return BILL_DAYS;
    }

    public void setBILL_DAYS(String BILL_DAYS) {
        this.BILL_DAYS = BILL_DAYS;
    }

    public String getMTR_SERIAL_NO() {
        return MTR_SERIAL_NO;
    }

    public void setMTR_SERIAL_NO(String MTR_SERIAL_NO) {
        this.MTR_SERIAL_NO = MTR_SERIAL_NO;
    }

    public String getCHQ_DISSHONOUR_FLAG() {
        return CHQ_DISSHONOUR_FLAG;
    }

    public void setCHQ_DISSHONOUR_FLAG(String CHQ_DISSHONOUR_FLAG) {
        this.CHQ_DISSHONOUR_FLAG = CHQ_DISSHONOUR_FLAG;
    }

    public String getCHQ_DISHONOUR_DATE() {
        return CHQ_DISHONOUR_DATE;
    }

    public void setCHQ_DISHONOUR_DATE(String CHQ_DISHONOUR_DATE) {
        this.CHQ_DISHONOUR_DATE = CHQ_DISHONOUR_DATE;
    }

    public String getFDRNAME() {
        return FDRNAME;
    }

    public void setFDRNAME(String FDRNAME) {
        this.FDRNAME = FDRNAME;
    }

    public String getTCCODE() {
        return TCCODE;
    }

    public void setTCCODE(String TCCODE) {
        this.TCCODE = TCCODE;
    }

    public String getMTR_FLAG() {
        return MTR_FLAG;
    }

    public void setMTR_FLAG(String MTR_FLAG) {
        this.MTR_FLAG = MTR_FLAG;
    }

    public String getINVENTORY_LOAD() {
        return INVENTORY_LOAD;
    }

    public void setINVENTORY_LOAD(String INVENTORY_LOAD) {
        this.INVENTORY_LOAD = INVENTORY_LOAD;
    }

    public String getHP() {
        return HP;
    }

    public void setHP(String HP) {
        this.HP = HP;
    }

    public String getBMDKW() {
        return BMDKW;
    }

    public void setBMDKW(String BMDKW) {
        this.BMDKW = BMDKW;
    }

    public String getCONNLDHP() {
        return CONNLDHP;
    }

    public void setCONNLDHP(String CONNLDHP) {
        this.CONNLDHP = CONNLDHP;
    }

    public String getCONNLDKW() {
        return CONNLDKW;
    }

    public void setCONNLDKW(String CONNLDKW) {
        this.CONNLDKW = CONNLDKW;
    }

    public String getCREADJ() {
        return CREADJ;
    }

    public void setCREADJ(String CREADJ) {
        this.CREADJ = CREADJ;
    }

    public String getREADKVAH() {
        return READKVAH;
    }

    public void setREADKVAH(String READKVAH) {
        this.READKVAH = READKVAH;
    }

    public String getGPS_LAT() {
        return GPS_LAT;
    }

    public void setGPS_LAT(String GPS_LAT) {
        this.GPS_LAT = GPS_LAT;
    }

    public String getGPS_LONG() {
        return GPS_LONG;
    }

    public void setGPS_LONG(String GPS_LONG) {
        this.GPS_LONG = GPS_LONG;
    }

    public String getAADHAAR() {
        return AADHAAR;
    }

    public void setAADHAAR(String AADHAAR) {
        this.AADHAAR = AADHAAR;
    }

    public String getMAIL() {
        return MAIL;
    }

    public void setMAIL(String MAIL) {
        this.MAIL = MAIL;
    }

    public String getELECTION() {
        return ELECTION;
    }

    public void setELECTION(String ELECTION) {
        this.ELECTION = ELECTION;
    }

    public String getRATION() {
        return RATION;
    }

    public void setRATION(String RATION) {
        this.RATION = RATION;
    }

    public String getWATER() {
        return WATER;
    }

    public void setWATER(String WATER) {
        this.WATER = WATER;
    }

    public String getHOUSE_NO() {
        return HOUSE_NO;
    }

    public void setHOUSE_NO(String HOUSE_NO) {
        this.HOUSE_NO = HOUSE_NO;
    }

    public String getFDRCODE() {
        return FDRCODE;
    }

    public void setFDRCODE(String FDRCODE) {
        this.FDRCODE = FDRCODE;
    }

    public String getTCNAME() {
        return TCNAME;
    }

    public void setTCNAME(String TCNAME) {
        this.TCNAME = TCNAME;
    }

    public String getPRES_RDG() {
        return PRES_RDG;
    }

    public void setPRES_RDG(String PRES_RDG) {
        this.PRES_RDG = PRES_RDG;
    }

    public String getPRES_STS() {
        return PRES_STS;
    }

    public void setPRES_STS(String PRES_STS) {
        this.PRES_STS = PRES_STS;
    }

    public String getTOD_CURRENT1() {
        return TOD_CURRENT1;
    }

    public void setTOD_CURRENT1(String TOD_CURRENT1) {
        this.TOD_CURRENT1 = TOD_CURRENT1;
    }

    public String getTOD_CURRENT3() {
        return TOD_CURRENT3;
    }

    public void setTOD_CURRENT3(String TOD_CURRENT3) {
        this.TOD_CURRENT3 = TOD_CURRENT3;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public int getOld_days() {
        return old_days;
    }

    public void setOld_days(int old_days) {
        this.old_days = old_days;
    }

    public int getNormal_days() {
        return normal_days;
    }

    public void setNormal_days(int normal_days) {
        this.normal_days = normal_days;
    }

    public double getEC() {
        return EC;
    }

    public void setEC(double EC) {
        this.EC = EC;
    }

    public double getFc_old_value() {
        return fc_old_value;
    }

    public void setFc_old_value(double fc_old_value) {
        this.fc_old_value = fc_old_value;
    }

    public double getFc_normal_value() {
        return fc_normal_value;
    }

    public void setFc_normal_value(double fc_normal_value) {
        this.fc_normal_value = fc_normal_value;
    }

    public double getEc_old_value() {
        return ec_old_value;
    }

    public void setEc_old_value(double ec_old_value) {
        this.ec_old_value = ec_old_value;
    }

    public double getEc_normal_value() {
        return ec_normal_value;
    }

    public void setEc_normal_value(double ec_normal_value) {
        this.ec_normal_value = ec_normal_value;
    }

    public double getFac_days() {
        return fac_days;
    }

    public void setFac_days(double fac_days) {
        this.fac_days = fac_days;
    }

    public double getFac_remaining_days() {
        return fac_remaining_days;
    }

    public void setFac_remaining_days(double fac_remaining_days) {
        this.fac_remaining_days = fac_remaining_days;
    }

    public String getTax_days_new() {
        return tax_days_new;
    }

    public void setTax_days_new(String tax_days_new) {
        this.tax_days_new = tax_days_new;
    }

    public String getTax_days_old() {
        return tax_days_old;
    }

    public void setTax_days_old(String tax_days_old) {
        this.tax_days_old = tax_days_old;
    }

    public Cursor getCurrent_data() {
        return current_data;
    }

    public void setCurrent_data(Cursor current_data) {
        this.current_data = current_data;
    }

    public Cursor getOld_data() {
        return old_data;
    }

    public void setOld_data(Cursor old_data) {
        this.old_data = old_data;
    }

    public String getFIX() {
        return FIX;
    }

    public void setFIX(String FIX) {
        this.FIX = FIX;
    }

    public String getENGCHG() {
        return ENGCHG;
    }

    public void setENGCHG(String ENGCHG) {
        this.ENGCHG = ENGCHG;
    }

    public String getREBATE_AMOUNT() {
        return REBATE_AMOUNT;
    }

    public void setREBATE_AMOUNT(String REBATE_AMOUNT) {
        this.REBATE_AMOUNT = REBATE_AMOUNT;
    }

    public String getTAX_AMOUNT() {
        return TAX_AMOUNT;
    }

    public void setTAX_AMOUNT(String TAX_AMOUNT) {
        this.TAX_AMOUNT = TAX_AMOUNT;
    }

    public String getBMD_PENALTY() {
        return BMD_PENALTY;
    }

    public void setBMD_PENALTY(String BMD_PENALTY) {
        this.BMD_PENALTY = BMD_PENALTY;
    }

    public String getPF_PENALTY() {
        return PF_PENALTY;
    }

    public void setPF_PENALTY(String PF_PENALTY) {
        this.PF_PENALTY = PF_PENALTY;
    }

    public String getPAYABLE() {
        return PAYABLE;
    }

    public void setPAYABLE(String PAYABLE) {
        this.PAYABLE = PAYABLE;
    }

    public String getGOK_SUBSIDY() {
        return GOK_SUBSIDY;
    }

    public void setGOK_SUBSIDY(String GOK_SUBSIDY) {
        this.GOK_SUBSIDY = GOK_SUBSIDY;
    }

    public String getDEM_REVENUE() {
        return DEM_REVENUE;
    }

    public void setDEM_REVENUE(String DEM_REVENUE) {
        this.DEM_REVENUE = DEM_REVENUE;
    }

    public String getPAYABLE_REAL() {
        return PAYABLE_REAL;
    }

    public void setPAYABLE_REAL(String PAYABLE_REAL) {
        this.PAYABLE_REAL = PAYABLE_REAL;
    }

    public String getPAYABLE_PROFIT() {
        return PAYABLE_PROFIT;
    }

    public void setPAYABLE_PROFIT(String PAYABLE_PROFIT) {
        this.PAYABLE_PROFIT = PAYABLE_PROFIT;
    }

    public String getPAYABLE_LOSS() {
        return PAYABLE_LOSS;
    }

    public void setPAYABLE_LOSS(String PAYABLE_LOSS) {
        this.PAYABLE_LOSS = PAYABLE_LOSS;
    }

    public String getCHARITABLE_RBT_AMT() {
        return CHARITABLE_RBT_AMT;
    }

    public void setCHARITABLE_RBT_AMT(String CHARITABLE_RBT_AMT) {
        this.CHARITABLE_RBT_AMT = CHARITABLE_RBT_AMT;
    }

    public String getTOD_CHARGES() {
        return TOD_CHARGES;
    }

    public void setTOD_CHARGES(String TOD_CHARGES) {
        this.TOD_CHARGES = TOD_CHARGES;
    }

    public String getCURR_BILL_AMOUNT() {
        return CURR_BILL_AMOUNT;
    }

    public void setCURR_BILL_AMOUNT(String CURR_BILL_AMOUNT) {
        this.CURR_BILL_AMOUNT = CURR_BILL_AMOUNT;
    }

    public double[] getArrFrate() {
        return ArrFrate;
    }

    public void setArrFrate(double[] arrFrate) {
        ArrFrate = arrFrate;
    }

    public double[] getArrFslab() {
        return ArrFslab;
    }

    public void setArrFslab(double[] arrFslab) {
        ArrFslab = arrFslab;
    }

    public double[] getArrErate() {
        return ArrErate;
    }

    public void setArrErate(double[] arrErate) {
        ArrErate = arrErate;
    }

    public double[] getArrEslab() {
        return ArrEslab;
    }

    public void setArrEslab(double[] arrEslab) {
        ArrEslab = arrEslab;
    }

    public double[] getArrEc() {
        return ArrEc;
    }

    public void setArrEc(double[] arrEc) {
        ArrEc = arrEc;
    }

    public double[] getArrFc() {
        return ArrFc;
    }

    public void setArrFc(double[] arrFc) {
        ArrFc = arrFc;
    }

    public double[] getArrFrate_old() {
        return ArrFrate_old;
    }

    public void setArrFrate_old(double[] arrFrate_old) {
        ArrFrate_old = arrFrate_old;
    }

    public double[] getArrFslab_old() {
        return ArrFslab_old;
    }

    public void setArrFslab_old(double[] arrFslab_old) {
        ArrFslab_old = arrFslab_old;
    }

    public double[] getArrErate_old() {
        return ArrErate_old;
    }

    public void setArrErate_old(double[] arrErate_old) {
        ArrErate_old = arrErate_old;
    }

    public double[] getArrEslab_old() {
        return ArrEslab_old;
    }

    public void setArrEslab_old(double[] arrEslab_old) {
        ArrEslab_old = arrEslab_old;
    }

    public double[] getArrEc_old() {
        return ArrEc_old;
    }

    public void setArrEc_old(double[] arrEc_old) {
        ArrEc_old = arrEc_old;
    }

    public double[] getArrFc_old() {
        return ArrFc_old;
    }

    public void setArrFc_old(double[] arrFc_old) {
        ArrFc_old = arrFc_old;
    }

    public double[] getArrdlFslab() {
        return ArrdlFslab;
    }

    public void setArrdlFslab(double[] arrdlFslab) {
        ArrdlFslab = arrdlFslab;
    }

    public double[] getArrdlFslab_old() {
        return ArrdlFslab_old;
    }

    public void setArrdlFslab_old(double[] arrdlFslab_old) {
        ArrdlFslab_old = arrdlFslab_old;
    }

    public String getPd_penalty() {
        return pd_penalty;
    }

    public void setPd_penalty(String pd_penalty) {
        this.pd_penalty = pd_penalty;
    }

    public double getFec() {
        return fec;
    }

    public void setFec(double fec) {
        this.fec = fec;
    }

    public String getColumn_name() {
        return column_name;
    }

    public void setColumn_name(String column_name) {
        this.column_name = column_name;
    }
}
