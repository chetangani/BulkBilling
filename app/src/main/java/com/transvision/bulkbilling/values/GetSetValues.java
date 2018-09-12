package com.transvision.bulkbilling.values;

public class GetSetValues {
    private String slno="", billed_account_id="", billed_pres_rdg="", billed_units="", billed_payable="", download_file_name="";

    public GetSetValues() {
    }

    public String getSlno() {
        return slno;
    }

    public void setSlno(String slno) {
        this.slno = slno;
    }

    public String getBilled_account_id() {
        return billed_account_id;
    }

    public void setBilled_account_id(String billed_account_id) {
        this.billed_account_id = billed_account_id;
    }

    public String getBilled_pres_rdg() {
        return billed_pres_rdg;
    }

    public void setBilled_pres_rdg(String billed_pres_rdg) {
        this.billed_pres_rdg = billed_pres_rdg;
    }

    public String getBilled_units() {
        return billed_units;
    }

    public void setBilled_units(String billed_units) {
        this.billed_units = billed_units;
    }

    public String getBilled_payable() {
        return billed_payable;
    }

    public void setBilled_payable(String billed_payable) {
        this.billed_payable = billed_payable;
    }

    public String getDownload_file_name() {
        return download_file_name;
    }

    public void setDownload_file_name(String download_file_name) {
        this.download_file_name = download_file_name;
    }
}
