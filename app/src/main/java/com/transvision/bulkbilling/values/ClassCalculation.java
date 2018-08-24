package com.transvision.bulkbilling.values;

import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.util.Log;

import com.transvision.bulkbilling.database.Databasehelper;
import com.transvision.bulkbilling.extra.FunctionsCall;

import org.apache.commons.lang3.StringUtils;

import java.text.DecimalFormat;

import static java.lang.String.valueOf;

public class ClassCalculation {
    private String santionHp1 = "";
    private String sanctionKw1 = "";
    private String Readingconsume = "";
    private String pd_penalty = "";
    private String flagRebate = "";
    private String tariff = "";
    private String Rrebate = "";
    private String frate = "";
    private String no_of_days = "";
    private String loadfrate = "";
    private double cal_MF = 0;
    private double tod_ofPeakValue = 0;
    private double tod_onPeakValue = 0;
    private double FC = 0;
    private double EC = 0;
    private double EC1 = 0;
    private double bill_Amount = 0;
    private double bmdPenalty = 0;
    private double finalPfPenalitiy = 0;
    private double EC_old=0;
    private double EC1_old=0;
    private double FC_old=0;
    private double EC_new=0;
    private double FC_new=0;
    private double finalRebate = 0;
    private double CharityRate = 0;
    private double pfValue = 0;
    private double pfFlagValue = 0;
    private double gok_payment = 0;
    private double cal_deposit = 0;
    private double dblTax = 0;
    private double dblIntrTax = 0;
    private double finalTODValue = 0;
    private double int_calArrears = 0;
    private double data1 = 0;
    private double data2 = 0;
    private double credit_adj=0;
    private double prepaid_rent=0;
    private double[] arrFrate = new double[10];
    private double[] arrFslab = new double[10];
    private double[] arrdlFslab = new double[10];
    private double[] arrErate = new double[10];
    private double[] arrEslab = new double[10];
    private double[] arrEC = new double[10]; // Array of Ec of each Slab
    private double[] arrFC = new double[10]; // Array of Fc of each Slab
    private double[] arrEC1 = new double[10];

    private double[] arrFrate_old = new double[10];
    private double[] arrFslab_old = new double[10];
    private double[] arrdlFslab_old = new double[10];
    private double[] arrErate_old = new double[10];
    private double[] arrEslab_old = new double[10];
    private double[] arrEC_old = new double[10]; // Array of Ec of each Slab
    private double[] arrFC_old = new double[10];
    private double[] arrEC1_old = new double[10];

    private Databasehelper dbh;

    public ClassCalculation(Databasehelper databasehelper) {
        this.dbh = databasehelper;
    }

    private FunctionsCall fcall = new FunctionsCall();
    private boolean additionalgok = false;

    // ====================== Setters =========================================

    public void setdays70tariff(String noofdays) {
        this.no_of_days = noofdays;
    }

    public void setCalMF(int cal_MF) {
        this.cal_MF = cal_MF;
    }

    public void setPfValue(double pfValue) {
        this.pfValue = pfValue;
    }

    private double Units;

    public void setconsumtion(String consume) {
        this.Readingconsume = consume;
    }

    public void setSantionHp(String santionHp) {
        this.santionHp1 = santionHp;
    }

    public void setSanctionKw(String sanctionKw) {
        this.sanctionKw1 = sanctionKw;
    }

    public void setCal_deposit(double cal_deposit) {
        this.cal_deposit = cal_deposit;
    }

    public void setFlagRebate(String flagRebate) {
        this.flagRebate = flagRebate;
    }

    public void setPfFlagValue(int pfFlagValue) {
        this.pfFlagValue = pfFlagValue;
    }

    public void setTod_ofPeakValue(int tod_ofPeakValue) {
        this.tod_ofPeakValue = tod_ofPeakValue;
    }

    public void setTod_onPeakValue(int tod_onPeakValue) {
        this.tod_onPeakValue = tod_onPeakValue;
    }

    public void setInt_calArrears(double int_calArrears) {
        this.int_calArrears = int_calArrears;
    }

    public void settariff(String tariff) {
        this.tariff = tariff;
    }

    public void setRrebate(String rrebate) {
        this.Rrebate = rrebate;
    }

    public void setdata1(double data1) {
        this.data1 = data1;
    }

    public void setdata2(double data2) {
        this.data2 = data2;
    }

    public void setIntr_Amt(double Intr_Amt) {
        this.dblIntrTax = Intr_Amt;
    }

    public void setPd_penalty(String pdPenalty) { this.pd_penalty = pdPenalty; }

    public void setCredit_adj(double cred_adj) { this.credit_adj = cred_adj; }

    // ---------------------------- Function to Calculate Fixed Charges ----------------------------
    public void FcCalculation(Cursor c, String dbtvalue, double dl_count, int a) {
        Double testdouble = Double.parseDouble(sanctionKw1);
        double KW;
        if (testdouble < 1) {
            KW = 1;
        } else {
            if (StringUtils.startsWithIgnoreCase(dbtvalue, "4"))
                KW = testdouble;
            else {
                if (sanctionKw1.contains(".")) {
                    String test1 = sanctionKw1.substring(0, sanctionKw1.lastIndexOf('.'));
                    String test2 = sanctionKw1.substring(sanctionKw1.lastIndexOf('.')+1, sanctionKw1.length());
                    Double test3 = Double.parseDouble(test2);
                    if (test3 > 0) {
                        KW = (Double.parseDouble(test1) + 1);
                    } else KW = testdouble;
                } else KW = testdouble;
            }
        }
        if (!tariff.equals("70")) {
            double HP = Double.parseDouble(santionHp1);
            if (dbtvalue.equals("4")) {
                if (tariff.equals("50") || tariff.equals("51") || tariff.equals("52") || tariff.equals("53")) {
                }
            } else if (tariff.equals("40") || tariff.equals("41") || tariff.equals("42") || tariff.equals("43") || tariff.equals("50")
                    || tariff.equals("51") || tariff.equals("52") || tariff.equals("53") || tariff.equals("60")) {
                if (HP > 0) {
                    String santHp = "" + HP;
                    String santHp1 = santHp.substring(0, santHp.lastIndexOf('.'));
                    String santHp2 = santHp.substring(santHp.lastIndexOf('.') + 1);
                    int santHp3;
                    if (santHp2.length() == 1) {
                        santHp3 = Integer.parseInt(santHp2 + "0");
                    } else santHp3 = Integer.parseInt(santHp2);
                    if (santHp3 >= 0 && santHp3 <= 12) {
                        KW = Double.parseDouble(santHp1 + "." + "00");
                    } else if (santHp3 > 12 && santHp3 <= 37) {
                        KW = Double.parseDouble(santHp1 + "." + "25");
                    } else if (santHp3 > 37 && santHp3 <= 62) {
                        KW = Double.parseDouble(santHp1 + "." + "50");
                    } else if (santHp3 > 62 && santHp3 <= 87) {
                        KW = Double.parseDouble(santHp1 + "." + "75");
                    } else KW = Double.parseDouble(santHp1 + "." + "00") + 1;
                }
            }
            if (KW == 0) {
                if (HP != 0) {
                    KW = HP * 0.746;
                    String loadkw = String.valueOf(KW);
                    if (loadkw.contains(".")) {
                        String loadkw1 = loadkw.substring(0, loadkw.lastIndexOf('.'));
                        String loadkw2 = loadkw.substring(loadkw.lastIndexOf('.')+1, loadkw.length());
                        int loadkw3 = Integer.parseInt(loadkw2);
                        if (loadkw3 >= 0 && loadkw3 <= 12) {
                            KW = Double.parseDouble(loadkw);
                        } else if (loadkw3 > 12 && loadkw3 <= 37) {
                            KW = (Double.parseDouble(loadkw1) + 0.25);
                        } else if (loadkw3 > 37 && loadkw3 <= 62) {
                            KW = (Double.parseDouble(loadkw1) + 0.50);
                        } else if (loadkw3 > 62 && loadkw3 <= 87) {
                            KW = (Double.parseDouble(loadkw1) + 0.75);
                        } else if (loadkw3 > 87) {
                            KW = (Double.parseDouble(loadkw) + 1);
                        }
                    }
                }
            }
            double remKW = 0;
            fcall.logStatus("Reading Cursor");
            fcall.logStatus("Cursor Count: " + c.getCount());
            if (c.getCount() > 0) {
                fcall.logStatus("Cursor Count: " + c.getCount());
                fcall.logStatus("Cursor Count Column: " + c.getColumnCount());
                fcall.logStatus("FSLABS: " + c.getString(c.getColumnIndex("NOF_FSLABS")));
                String noOfSlab = c.getString(c.getColumnIndex("NOF_FSLABS"));
                int intNoOfSlab = Integer.parseInt(noOfSlab);
                loadfrate = c.getString(c.getColumnIndex("FRATE2"));
                for (int i = 1; i <= intNoOfSlab; i++) {
                    if (!tariff.equals("21")) {
                        String fslab = c.getString(c.getColumnIndex("FSLAB" + i));
                        int intFslab = Integer.parseInt(fslab);
                        String frate = c.getString(c.getColumnIndex("FRATE" + i));
                        double intFrate = Double.parseDouble(frate);
                        if (i == 1) {
                            if (KW <= intFslab) {
                                if (a == 0) {
                                    fc_slab_less_calculation(i, dl_count, KW, intFrate);
                                } else fc_slab_less_calculation_old(i, dl_count, KW, intFrate);
                                break;
                            } else if (KW > intFslab) {
                                if (a == 0) {
                                    remKW = fc_slab_more_calculation(i, dl_count, KW, intFrate, intFslab);
                                } else remKW = fc_slab_more_calculation_old(i, dl_count, KW, intFrate, intFslab);
                            }
                        } else {
                            if (remKW <= intFslab) {
                                if (a == 0) {
                                    fc_slab_less_calculation(i, dl_count, remKW, intFrate);
                                } else fc_slab_less_calculation_old(i, dl_count, remKW, intFrate);
                                break;
                            } else if (remKW > intFslab) {
                                if (a == 0) {
                                    remKW = fc_slab_more_calculation(i, dl_count, remKW, intFrate, intFslab);
                                } else remKW = fc_slab_more_calculation_old(i, dl_count, remKW, intFrate, intFslab);
                            }
                        }
                    } else {
                        if (KW == 1) {
                            String fslab = c.getString(c.getColumnIndex("FSLAB" + i));
                            int intFslab = Integer.parseInt(fslab);
                            String frate = c.getString(c.getColumnIndex("FRATE" + i));
                            double intFrate = Double.parseDouble(frate);
                            loadfrate = c.getString(c.getColumnIndex("FRATE2"));
                            if (i == 1) {
                                if (KW <= intFslab) {
                                    if (a == 0) {
                                        fc_slab_less_calculation(i, dl_count, KW, intFrate);
                                    } else fc_slab_less_calculation_old(i, dl_count, KW, intFrate);
                                    break;
                                } else if (KW > intFslab) {
                                    if (a == 0) {
                                        remKW = fc_slab_more_calculation(i, dl_count, KW, intFrate, intFslab);
                                    } else remKW = fc_slab_more_calculation_old(i, dl_count, KW, intFrate, intFslab);
                                }
                            } else {
                                if (remKW <= intFslab) {
                                    if (a == 0) {
                                        fc_slab_less_calculation(i, dl_count, remKW, intFrate);
                                    } else fc_slab_less_calculation_old(i, dl_count, remKW, intFrate);
                                    break;
                                } else if (remKW > intFslab) {
                                    if (a == 0) {
                                        remKW = fc_slab_more_calculation(i, dl_count, remKW, intFrate, intFslab);
                                    } else remKW = fc_slab_more_calculation_old(i, dl_count, remKW, intFrate, intFslab);
                                }
                            }
                        } else {
                            String fslab = c.getString(c.getColumnIndex("FSLAB" + i));
                            int intFslab = Integer.parseInt(fslab);
                            if (Rrebate.equals("0")) {
                                frate = c.getString(c.getColumnIndex("FRATE" + (i + 1)));
                            }
                            if (Rrebate.equals("1")) {
                                frate = c.getString(c.getColumnIndex("FRATE" + (i + 1)));
                            }
                            loadfrate = c.getString(c.getColumnIndex("FRATE2"));
                            double intFrate = Double.parseDouble(frate);
                            if (i == 1) {
                                if (KW <= intFslab) {
                                    if (a == 0) {
                                        fc_slab_less_calculation(i, dl_count, KW, intFrate);
                                    } else fc_slab_less_calculation_old(i, dl_count, KW, intFrate);
                                    break;
                                } else if (KW > intFslab) {
                                    if (a == 0) {
                                        remKW = fc_slab_more_calculation(i, dl_count, KW, intFrate, intFslab);
                                    } else remKW = fc_slab_more_calculation_old(i, dl_count, KW, intFrate, intFslab);
                                }
                            } else {
                                if (remKW <= intFslab) {
                                    if (a == 0) {
                                        fc_slab_less_calculation(i, dl_count, remKW, intFrate);
                                    } else fc_slab_less_calculation_old(i, dl_count, remKW, intFrate);
                                    break;
                                } else if (remKW > intFslab) {
                                    if (a == 0) {
                                        remKW = fc_slab_more_calculation(i, dl_count, remKW, intFrate, intFslab);
                                    } else remKW = fc_slab_more_calculation_old(i, dl_count, remKW, intFrate, intFslab);
                                }
                            }
                        }
                    }
                }
            }
            for (int j = 0; j <= 5; j++) {
                if (a == 0) {
                    FC_new = FC_new + arrFC[j];
                } else FC_old = FC_old + arrFC_old[j];
            }
            FC = FC_new + FC_old;
        } else {
            if (tariff.equals("70")) {
                String frate = c.getString(c.getColumnIndex("FRATE1"));
                double frate1 = Double.parseDouble(frate);
                double cal_days = frate1 / 7;
                double days = Double.parseDouble(no_of_days);
                FC = days * cal_days * KW;
                arrFslab[1] = KW;
                arrFrate[1] = frate1;
                arrFC[1] = days * cal_days * KW;
            }
        }
    }

    private void fc_slab_less_calculation(int i, double dl_count, double remKW, double intFrate) {
        arrFC[i] = ((dl_count + 1) * remKW) * intFrate;
        arrFrate[i] = intFrate;
        arrFslab[i] = ((dl_count + 1) * remKW);
        arrdlFslab[i] = remKW;
    }

    private double fc_slab_more_calculation(int i, double dl_count, double remKW, double intFrate, double intFslab) {
        arrFC[i] = ((dl_count + 1) * intFslab) * intFrate;
        arrFrate[i] = intFrate;
        arrFslab[i] = ((dl_count + 1) * intFslab);
        arrdlFslab[i] = intFslab;
        return remKW - intFslab;
    }

    private void fc_slab_less_calculation_old(int i, double dl_count, double remKW, double intFrate) {
        arrFC_old[i] = ((dl_count + 1) * remKW) * intFrate;
        arrFrate_old[i] = intFrate;
        arrFslab_old[i] = ((dl_count + 1) * remKW);
        arrdlFslab_old[i] = remKW;
    }

    private double fc_slab_more_calculation_old(int i, double dl_count, double remKW, double intFrate, double intFslab) {
        arrFC_old[i] = ((dl_count + 1) * intFslab) * intFrate;
        arrFrate_old[i] = intFrate;
        arrFslab_old[i] = ((dl_count + 1) * intFslab);
        arrdlFslab_old[i] = intFslab;
        return remKW - intFslab;
    }

    private void ec_slab_less_calculation(int i, double remUnits, double intErate) {
        arrEC[i] = remUnits * intErate;
        arrErate[i] = intErate;
        arrEslab[i] = remUnits;
    }

    private double ec_slab_more_calculation(int i, double remUnits, double intErate, double intEslab) {
        arrEC[i] = intEslab * intErate;
        arrErate[i] = intErate;
        arrEslab[i] = intEslab;
        return remUnits - intEslab;
    }

    private void ec_slab_less_calculation_old(int i, double remUnits, double intErate) {
        arrEC_old[i] = remUnits * intErate;
        arrErate_old[i] = intErate;
        arrEslab_old[i] = remUnits;
    }

    private double ec_slab_more_calculation_old(int i, double remUnits, double intErate, double intEslab) {
        arrEC_old[i] = intEslab * intErate;
        arrErate_old[i] = intErate;
        arrEslab_old[i] = intEslab;
        return remUnits - intEslab;
    }

    private void ec_slab_less_calculation_gok(int i, double remUnits, double intErate) {
        arrEC1[i] = remUnits * intErate;
    }

    private double ec_slab_more_calculation_gok(int i, double remUnits, double intErate, double intEslab) {
        arrEC1[i] = intEslab * intErate;
        return remUnits - intEslab;
    }

    private void ec_slab_less_calculation_old_gok(int i, double remUnits, double intErate) {
        arrEC1_old[i] = remUnits * intErate;
    }

    private double ec_slab_more_calculation_old_gok(int i, double remUnits, double intErate, double intEslab) {
        arrEC1_old[i] = intEslab * intErate;
        return remUnits - intEslab;
    }

    // ---------------------------- Function to Calculate Energy Consumption ----------------------------
    public void EcCalculation(Cursor c, double units, double value, int a) {
        double remUnits = 0;
        if (c.getCount() > 0) {
            String noOfSlab = c.getString(c.getColumnIndex("NOF_ESLABS"));
            int intNoOfSlab = Integer.parseInt(noOfSlab);
            for (int i = 1; i <= intNoOfSlab; i++) {
                String eslab = c.getString(c.getColumnIndex("ESLAB" + i));
                int extraintEslab = Integer.parseInt(eslab);
                double intEslab = (value + 1) * extraintEslab;
                String erate = c.getString(c.getColumnIndex("ERATE" + i));
                double intErate = Double.parseDouble(erate);
                if (i == 1) {
                    if (units <= intEslab) {
                        if (a == 0) {
                            ec_slab_less_calculation(i, units, intErate);
                        } else ec_slab_less_calculation_old(i, units, intErate);
                        break;
                    } else if (units > intEslab) {
                        if (a == 0) {
                            remUnits = ec_slab_more_calculation(i, units, intErate, intEslab);
                        } else remUnits = ec_slab_more_calculation_old(i, units, intErate, intEslab);
                    }
                } else {
                    if (remUnits <= intEslab) {
                        if (a == 0) {
                            ec_slab_less_calculation(i, remUnits, intErate);
                        } else ec_slab_less_calculation_old(i, remUnits, intErate);
                        break;
                    } else if (remUnits > intEslab) {
                        if (a == 0) {
                            remUnits = ec_slab_more_calculation(i, remUnits, intErate, intEslab);
                        } else remUnits = ec_slab_more_calculation_old(i, remUnits, intErate, intEslab);
                    }
                }
            }
        }
        for (int j = 0; j <= 5; j++) {
            if (a == 0) {
                EC_new = EC_new + arrEC[j];
            } else EC_old = EC_old + arrEC_old[j];
        }
        if (tariff.equals("10")) {
            if (a == 0) {
                if (EC_new < 30) {
                    EC_new = 30;
                }
            } else {
                if (EC_old < 30) {
                    EC_old = 30;
                }
            }
        }
        EC = EC_new + EC_old;
    }

    // -------------------------- Function to Calculate BMD Penalties --------------------------
    public double bmdPenalities(double bmdValue, String invenload, String pfflag, String bmdkw, String dbtvalue) {
        if (pfflag.equals("0")) {
            double load = Double.parseDouble(invenload);
            if (Double.parseDouble(invenload) > 0) {
                if (tariff.equals("20") || tariff.equals("21") || tariff.equals("23")) {
                    String ad1 = loadfrate;
                    Double ad = Double.parseDouble(ad1);
                    bmdPenalty = ad * load * 2;
                } else bmdPenalty = arrFrate[1] * load * 2;
            }
        } else {
            DecimalFormat num = new DecimalFormat("##.00");
            double load2, load3;
            if (StringUtils.startsWithIgnoreCase(dbtvalue, "4")) {
                load2 = fcall.getBigdecimal(bmdValue * cal_MF, 2);
                load3 = Double.parseDouble(num.format(Double.parseDouble(bmdkw)));
            } else {
                if (tariff.equals("50") || tariff.equals("51") || tariff.equals("52") || tariff.equals("53") || tariff.equals("60")) {
                    load2 = Double.parseDouble(fcall.decimalroundoff((bmdValue / 0.746) * cal_MF));
                    load3 = Double.parseDouble(num.format((Double.parseDouble(bmdkw)) / 0.746));
                } else {
                    load2 = Double.parseDouble(fcall.decimalroundoff(bmdValue * cal_MF));
                    load3 = Double.parseDouble(num.format(Double.parseDouble(bmdkw)));
                }
            }
            double load4 = Double.parseDouble(num.format(load2 - load3));
            if (load4 > 0) {
                String load5 = "" + load4;
                String load6 = load5.substring(0, load5.lastIndexOf('.'));
                String load8 = load5.substring(load5.lastIndexOf('.') + 1);
                int load7;
                if (load8.length() == 1) {
                    load7 = Integer.parseInt(load8 + "0");
                } else load7 = Integer.parseInt(load8);
                double fcdouble;
                if (tariff.equals("20") || tariff.equals("21") || tariff.equals("23"))
                    fcdouble = Double.parseDouble(loadfrate) * 2;
                else fcdouble = arrFrate[1] * 2;
                if (load7 >= 0 && load7 <= 12) {
                    bmdPenalty = Double.parseDouble(load6) * fcdouble;
                } else if (load7 > 12 && load7 <= 37) {
                    bmdPenalty = (Double.parseDouble(load6) + 0.25) * fcdouble;
                } else if (load7 > 37 && load7 <= 62) {
                    bmdPenalty = (Double.parseDouble(load6) + 0.50) * fcdouble;
                } else if (load7 > 62 && load7 <= 87) {
                    bmdPenalty = (Double.parseDouble(load6) + 0.75) * fcdouble;
                } else if (load7 > 87) {
                    bmdPenalty = (Double.parseDouble(load6) + 1) * fcdouble;
                }
            } else {
                bmdPenalty = 0.00;
            }
        }
        return bmdPenalty;
    }

    // ---------------------------- Function to Calculate the Rebate for Consumer ----------------------------
    public double billDeduCtion(Cursor c, String consumption, double dl_value) {
        double intSolarRebateRate;
        double intRebateSolarMAX_VAL;
        int flagRebatesolar = Integer.parseInt(this.flagRebate);
        if (flagRebatesolar == 1) {
            if (c.getCount() > 0) {
                double consumereading = Integer.parseInt(consumption);
                String rebateSolarRate = c.getString(c.getColumnIndex("SOLAR_RATE"));
                intSolarRebateRate = Double.parseDouble(rebateSolarRate);
                String rebateSolarMaxValue = c.getString(c.getColumnIndex("SOLAR_MAX_VAL"));
                intRebateSolarMAX_VAL = Double.parseDouble(rebateSolarMaxValue);
                if ((dl_value + 1) > 0)
                    intRebateSolarMAX_VAL = (dl_value + 1) * intRebateSolarMAX_VAL;
                double deductcalculate = consumereading * intSolarRebateRate;
                if (deductcalculate > intRebateSolarMAX_VAL) {
                    finalRebate = intRebateSolarMAX_VAL;
                } else finalRebate = deductcalculate;
            }
        }
        if (tariff.equals("30")) {
            if (flagRebatesolar == 2) {
                if (c.getCount() > 0) {
                    double consumereading = Integer.parseInt(consumption);
                    String hRebate = c.getString(c.getColumnIndex("HREBATE_PER"));
                    double intHRebate = Double.parseDouble(hRebate);
                    finalRebate = consumereading * intHRebate;
                }
            }
        }
        if (tariff.equals("20") || tariff.equals("21")) {
            if (flagRebatesolar == 7) {
                double consumereading = Integer.parseInt(consumption);
                String charityrate = c.getString(c.getColumnIndex("CHARITY_RATE"));
                double charity = Double.parseDouble(charityrate);
                finalRebate = consumereading * charity;
            }
        }
        return finalRebate;
    }

    public double charityCalculation(Cursor c, String consumption) {
        int flagRebatecharity = Integer.parseInt(this.flagRebate);
        if (tariff.equals("20") || tariff.equals("21")) {
            if (flagRebatecharity == 7) {
                double consumereading = Integer.parseInt(consumption);
                String charityrate = c.getString(c.getColumnIndex("CHARITY_RATE"));
                double charity = Double.parseDouble(charityrate);
                CharityRate = consumereading * charity;
            }
        }
        return CharityRate;
    }

    // ---------------------------- Function to Calculate the GOK ----------------------------
    public double gokCalculation(Cursor c, String consumption, double dl_value, int a) {
        if (c.getCount() > 0) {
            double gok_payment_old = 0;
            if (flagRebate.equals("5")) {
                String plConsumer = c.getString(c.getColumnIndex("PL_CONSUMER"));
                double int_plcons_Value = Double.parseDouble(plConsumer);
                Units = Double.parseDouble(consumption);
                if (int_plcons_Value > 0) {
                    double EC_1, gok;
                    if (a == 0) {
                        EC_1 = EC_new;
                        gok = Units * int_plcons_Value;
                        gok_payment = EC_1 - gok;
                    } else {
                        EC_1 = EC_old;
                        gok = Units * int_plcons_Value;
                        gok_payment_old = EC_1 - gok;
                    }
                    gok_payment = gok_payment + gok_payment_old;
                } else gok_payment = 0.00;
            } else {
                if (tariff.equals("10")) {
                    int readUnits = Integer.parseInt(consumption);
                    if (readUnits <= 40) {
                        if (a == 0)
                            gok_payment = EC_new;
                        else gok_payment_old = EC_old;
                        gok_payment = gok_payment + gok_payment_old + data2;
                        additionalgok = true;
                    }
                } else {
                    if (tariff.equals("23")) {
                        Units = Double.parseDouble(consumption);
                        dbh.openDatabase();
                        Cursor c1;
                        if (a == 0)
                            c1 = dbh.flECcount();
                        else c1 = dbh.flECcount_old();
                        if (c1 != null) {
                            c1.moveToNext();
                            String noOfSlab = c1.getString(c1.getColumnIndex("NOF_ESLABS"));
                            int intNoOfSlab = Integer.parseInt(noOfSlab);
                            EC1 = 0;
                            double remUnits = 0;
                            for (int i = 1; i <= intNoOfSlab; i++) {
                                String eslab1 = c1.getString(c1.getColumnIndex("ESLAB" + i));
                                int extraintEslab1 = Integer.parseInt(eslab1);
                                double intEslab = (dl_value + 1) * extraintEslab1;
                                String erate = c1.getString(c1.getColumnIndex("ERATE" + i));
                                double intErate = Double.parseDouble(erate);
                                if (i == 1) {
                                    if (Units <= intEslab) {
                                        if (a == 0)
                                            ec_slab_less_calculation_gok(i, Units, intErate);
                                        else ec_slab_less_calculation_old_gok(i, Units, intErate);
                                        break;
                                    } else if (Units > intEslab) {
                                        if (a == 0)
                                            remUnits = ec_slab_more_calculation_gok(i, Units, intErate, intEslab);
                                        else remUnits = ec_slab_more_calculation_old_gok(i, Units, intErate, intEslab);
                                    }
                                } else {
                                    if (remUnits <= intEslab) {
                                        if (a == 0)
                                            ec_slab_less_calculation_gok(i, remUnits, intErate);
                                        else ec_slab_less_calculation_old_gok(i, remUnits, intErate);
                                        break;
                                    } else if (remUnits > intEslab) {
                                        if (a == 0)
                                            remUnits = ec_slab_more_calculation_gok(i, remUnits, intErate, intEslab);
                                        else remUnits = ec_slab_more_calculation_old_gok(i, remUnits, intErate, intEslab);
                                    }
                                }
                            }
                        }
                        for (int j = 0; j <= 5; j++) {
                            if (a == 0)
                                EC1 = EC1 + arrEC1[j];
                            else EC1_old = EC1_old + arrEC1_old[j];
                        }
                        EC1 = EC1 + EC1_old;
                        int readUnits = Integer.parseInt(consumption);
                        if (readUnits < 200)
                            gok_payment = EC + FC;
                        else gok_payment = (EC - EC1) + FC;
                    } else if (tariff.equals("40")) {
                        gok_payment = EC + data2;
                        additionalgok = true;
                    }
                }
            }
        }
        return gok_payment;
    }

    // ---------------------- Function for Extra Charges in the Bill like Taxes,Penalties ----------------------
    public void billExtraCharges(Cursor c) {
        if (!flagRebate.equals("3")) {
            String taxPer = c.getString(c.getColumnIndex("TAX_PER"));
            double intTaxPer = Double.parseDouble(taxPer);
            if (!tariff.equals("10")) {
                dblTax = EC * intTaxPer;
            } else {
                double readUnits = Double.parseDouble(Readingconsume);
                if (readUnits > 40) {
                    dblTax = EC * intTaxPer;
                } else dblTax = 0.00;
            }
        } else dblTax = 0.00;
        if (tariff.equals("40"))
            dblTax = 0.00;
    }

    /*public String taxrate(Cursor c) {
        String taxPer = c.getString(c.getColumnIndex("TAX_PER"));
        double tax = Double.parseDouble(taxPer) * 100;
        String tax1 = "@" + "" + tax;
        return tax1.substring(0, tax1.indexOf(".")) + "%";
    }

    public String intrrate(Cursor c) {
        String intrPer = c.getString(c.getColumnIndex("INTR_PER"));
        double intr = Double.parseDouble(intrPer) * 100;
        String intr1 = "@" + "" + intr;
        return intr1.substring(0, intr1.indexOf(".")) + "%";
    }*/

    // ---------------------------- Function to calculate the Penalties ----------------------------
    public void billPenalties(Cursor c, String consumption) {
        double consumereading = Integer.parseInt(consumption);
        double refpfValue;
        if (pfValue > 0.85) {
            refpfValue = 0.85;
        } else if (pfValue < 0.70) {
            if (pfValue == 0) {
                refpfValue = 0;
            } else refpfValue = 0.70;
        } else refpfValue = pfValue;
        if (refpfValue < 0.85) {
            if (refpfValue == 0) {
                finalPfPenalitiy = 0;
            } else {
                String pen_charge;
                try {
                    pen_charge = c.getString(c.getColumnIndex("PF_PEN_CHARGE"));
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    pen_charge = "0";
                }
                double intpfPenAmount = Double.parseDouble(pen_charge);
                double pfDiffrence = 0.85 - refpfValue;
                if (pfDiffrence <= 0.15) {
                    finalPfPenalitiy = intpfPenAmount * (pfDiffrence * consumereading);
                } else finalPfPenalitiy = (0.30 * consumereading);
            }
        } else finalPfPenalitiy = 0;
    }

    // ---------------------------- Function for Bill Amount ----------------------------
    public double billAmount() {
        bill_Amount = 0;
        if (!tariff.equals("70")) {
            double solarRebate = 0;
            double handiRebate = 0;
            if (tariff.equals("40") || tariff.equals("10")) {
                if (additionalgok) {
                    bill_Amount = (EC + FC + bmdPenalty + finalPfPenalitiy) - (handiRebate + solarRebate + (gok_payment - data2));
                } else bill_Amount = (EC + FC + bmdPenalty + finalPfPenalitiy) - (handiRebate + solarRebate + gok_payment);
            } else bill_Amount = (EC + FC + bmdPenalty + finalPfPenalitiy) - (handiRebate + solarRebate + gok_payment);
        } else {
            if (flagRebate.equals("13")) {
//                bill_Amount = Double.parseDouble(pd_penalty);
                if ((EC + dblTax + data2) > FC) {
                    bill_Amount = EC;
                } else bill_Amount = FC;
                if (pfFlagValue == 1) {
                    bill_Amount += 375;
                    prepaid_rent = 375;
                }
                if (pfFlagValue == 2) {
                    bill_Amount += 500;
                    prepaid_rent = 500;
                }
            } else {
                if (FC > EC) {
                    bill_Amount = FC;
                } else bill_Amount = EC;
            }
        }
        return bill_Amount;
    }

    // ---------------------------- Function to Calculate TOD ----------------------------
    public double todCalculation(Cursor c) {
        Log.d("debug", "todcalculation: " + c.getCount());
        try {
            String minValue = c.getString(c.getColumnIndex("TOD_MIN"));
            double todMinRate = Double.parseDouble(minValue);
            String maxValue = c.getString(c.getColumnIndex("TOD_MAX"));
            double todMaxRate = Double.parseDouble(maxValue);
            finalTODValue = (todMinRate * tod_onPeakValue) + (todMaxRate * tod_ofPeakValue);
        } catch (CursorIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return finalTODValue;
    }

    // ---------------------------- Function to Calculate final payment ----------------------------
    public double billFinalPayment() {
        double finalBill;
        if (tariff.equals("40") || tariff.equals("10")) {
            if (additionalgok) {
                finalBill = (bill_Amount + dblTax + dblIntrTax + data1 + finalTODValue + int_calArrears + credit_adj) - cal_deposit - finalRebate - CharityRate;
            } else finalBill = (bill_Amount + dblTax + dblIntrTax + data1 + data2 + finalTODValue + int_calArrears + credit_adj) - cal_deposit - finalRebate - CharityRate;
        } else {
            if (tariff.equals("70")) {
                if (flagRebate.equals("13")) {
                    finalBill = (bill_Amount + dblTax + dblIntrTax + data1 + data2 + int_calArrears + credit_adj) - cal_deposit - finalRebate - CharityRate;
                } else finalBill = (bill_Amount + dblTax + dblIntrTax + data1 + data2 - finalTODValue + int_calArrears + credit_adj) - cal_deposit - finalRebate - CharityRate;
            } else finalBill = (bill_Amount + dblTax + dblIntrTax + data1 + data2 + finalTODValue + int_calArrears + credit_adj) - cal_deposit - finalRebate - CharityRate;
        }
        return finalBill;
    }

    // ------------------ Reset All Values ------------------
    public void resetAllValues() {
        for (int i = 0; i <= 6; i++) {
            arrErate[i] = 0;
            arrFrate[i] = 0;
            arrEslab[i] = 0;
            arrFslab[i] = 0;
            arrdlFslab[i] = 0;
            arrEC[i] = 0;
            arrFC[i] = 0;
            arrErate_old[i] = 0;
            arrFrate_old[i] = 0;
            arrEslab_old[i] = 0;
            arrFslab_old[i] = 0;
            arrdlFslab_old[i] = 0;
            arrEC_old[i] = 0;
            arrFC_old[i] = 0;
        }
        FC = 0;
        FC_old = 0;
        EC = 0;
        EC_old = 0;
        Readingconsume = "0";
    }

    public double[] erateForTextViews() {
        return arrErate;
    }

    public double[] frateForTextViews() {
        return arrFrate;
    }

    public double[] eslabForTextViews() {
        return arrEslab;
    }

    public double[] fslabForTextViews() {
        return arrFslab;
    }

    public double[] dl_fslabForTextViews() {
        return arrdlFslab;
    }

    public double[] ecForTextViews() {
        return arrEC;
    }

    public double[] fcForTextViews() {
        return arrFC;
    }

    public double totalEC() {
        return EC;
    }

    public double totalEC_old() {
        return EC_old;
    }

    public double totalEC_new() {
        return EC_new;
    }

    public double totalFC() {
        return FC;
    }

    public double totalFC_old() {
        return FC_old;
    }

    public double totalFC_new() {
        return FC_new;
    }

    public double tax() {
        return dblTax;
    }

    public double intr() {
        return dblIntrTax;
    }

    public double pfPenality() {
        return finalPfPenalitiy;
    }

    public String consumeUnits() {
        return valueOf(Units);
    }

    public double[] erateForTextViews_old() {
        return arrErate_old;
    }

    public double[] frateForTextViews_old() {
        return arrFrate_old;
    }

    public double[] eslabForTextViews_old() {
        return arrEslab_old;
    }

    public double[] fslabForTextViews_old() {
        return arrFslab_old;
    }

    public double[] dl_fslabForTextViews_old() {
        return arrdlFslab_old;
    }

    public double[] ecForTextViews_old() {
        return arrEC_old;
    }

    public double[] fcForTextViews_old() {
        return arrFC_old;
    }

    public double getPrepaid_rent() {
        return prepaid_rent;
    }
}