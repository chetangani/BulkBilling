package com.transvision.bulkbilling.values;

public class GetSetValues {
    private int old_days=0, normal_days=0;
    private double EC, fc_old_value=0, fc_normal_value=0, ec_old_value=0, ec_normal_value=0, fac_days=0, fac_remaining_days=0;
    private String tax_days_new="", tax_days_old="";

    public GetSetValues() {
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
}
