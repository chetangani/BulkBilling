package com.transvision.bulkbilling.extra;

public class Constants {

    public static String FTP_HOST = "ftp2.hescomtrm.com";
    public static String FTP_USER = "TRMFTP01";
    public static String FTP_PASS = "TrmFTp1$";
    public static final int FTP_PORT = 21;

    public static final String DIR_DATABASE = "databases";
    public static final String DIR_APK = "ApkFolder";
    public static final String DIR_DOWNLOAD = "DownloadFolder";
    public static final String DIR_UPLOAD = "UploadFolder";
    public static final String DIR_IMG_COMPRESSED = "Compressed";
    public static final String DIR_UPLOAD_IMAGES = "ImagesUploaded";
    public static final String DIR_TEXT_FILE = "Textfile";
    public static final String FILE_DATABASE = "mydb.db";
    public static final String FILE_BULK_DATABASE = "bulk_db.db";
    public static final String FILE_BULK_JOURNAL = "bulk_db.db-journal";
    public static final String FILE_DATABASE_JOURNAL = "mydb.db-journal";
    public static final String FILE_ZIP_FORMAT = ".zip";
    public static final String FILE_DB_FORMAT = ".db";
    public static final String FILE_TXT_FORMAT = ".txt";
    public static final String DIR_FTP_APK = "/Android/Apk/";
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
}
