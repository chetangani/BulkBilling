package com.transvision.bulkbilling.extra;

public class Constants {

    public static String FTP_HOST = "ftp2.hescomtrm.com";
    public static String FTP_USER = "TRMFTP01";
    public static String FTP_PASS = "TrmFTp1$";
    public static final int FTP_PORT = 21;

    public static final String DIR_DATABASE = "databases";
    public static final String DIR_TEXT_FILE = "Textfile";
    public static final String FILE_DATABASE = "bulk_test.db";
    public static final String FILE_BULK_DATABASE = "bulk_db.db";
    public static final String FILE_BULK_JOURNAL = "bulk_db.db-journal";
    public static final String DIR_FTP_DOWNLOAD = "/Android/BILLING/DOWNLOAD_DB_FILES/";
    public static final String DIR_FTP_UPLOAD = "/Android/BILLING/UPLOADED_DB_FILES/";
    public static final String SEARCH_ID = "search_content";

    public static final String OLD_TARRIF_CALCULATION = "old";
    public static final String NEW_TARRIF_CALCULATION = "new";
    public static final String SPLIT_TARRIF_CALCULATION = "split";

    public static final int INSERT_SUCCESS = 1;
    public static final int INSERT_MAST_OUT_ERROR = 2;
    public static final int INSERT_MAST_OLD_OUT_ERROR = 3;
    public static final int READ_MAST_CUST_ERROR = 4;
    public static final int COLUMNS_ERROR = 5;
    public static final int ASSETS_DB_COPY_SUCCESS = 6;
    public static final int ASSETS_DB_COPY_ERROR = 7;
    public static final int DB_FILE_DELETE_SUCCESS = 8;
    public static final int UPLOAD_BILLED_FILE_FOUND = 9;
    public static final int UPLOAD_BILLED_FILE_NOT_FOUND = 10;
    public static final int MAST_CUST_FILE_DOWNLOADED = 11;
    public static final int MAST_CUST_FILE_UPDATED = 12;
    public static final int MAST_CUST_FILE_EXTRACTED = 13;
    public static final int MAST_OUT_FILE_DOWNLOADED = 14;
    public static final int MAST_OUT_FILE_UPDATED = 15;
    public static final int MAST_OUT_FILE_EXTRACTED = 16;
    public static final int SUBDIV_DETAILS_INSERT_ERROR = 17;
    public static final int DOWNLOAD_BILLING_FILE_FOUND = 18;
    public static final int CHECK_DOWNLOAD_BILLING_FILE = 19;
}
