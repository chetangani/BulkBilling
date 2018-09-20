package com.transvision.bulkbilling.ftp;

import android.os.Handler;

import com.transvision.bulkbilling.extra.FunctionsCall;
import com.transvision.bulkbilling.values.GetSetValues;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.commons.net.ftp.FTPFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.transvision.bulkbilling.extra.Constants.CHECK_DOWNLOAD_BILLING_FILE;
import static com.transvision.bulkbilling.extra.Constants.DIR_DATABASE;
import static com.transvision.bulkbilling.extra.Constants.DIR_FTP_DOWNLOAD;
import static com.transvision.bulkbilling.extra.Constants.DIR_FTP_UPLOAD;
import static com.transvision.bulkbilling.extra.Constants.DOWNLOAD_BILLING_FILE_FOUND;
import static com.transvision.bulkbilling.extra.Constants.FTP_HOST;
import static com.transvision.bulkbilling.extra.Constants.FTP_PASS;
import static com.transvision.bulkbilling.extra.Constants.FTP_PORT;
import static com.transvision.bulkbilling.extra.Constants.FTP_USER;
import static com.transvision.bulkbilling.extra.Constants.MAST_CUST_FILE_DOWNLOADED;
import static com.transvision.bulkbilling.extra.Constants.MAST_OUT_FILE_DOWNLOADED;
import static com.transvision.bulkbilling.extra.Constants.UPLOAD_BILLED_FILE_FOUND;
import static com.transvision.bulkbilling.extra.Constants.UPLOAD_BILLED_FILE_NOT_FOUND;

public class FTPAPI {
    private FunctionsCall functionsCall = new FunctionsCall();

    public class Check_available_file implements Runnable {
        String serverpath, mrcode, file_date, log_name;
        Handler handler;
        GetSetValues getSetValues;
        boolean checkfile = false, filefound = false, billing_file;

        public Check_available_file(String mrcode, String file_date, Handler handler, GetSetValues getSetValues, boolean billing_file) {
            this.mrcode = mrcode;
            this.file_date = file_date;
            this.handler = handler;
            this.getSetValues = getSetValues;
            this.billing_file = billing_file;
        }

        @Override
        public void run() {
            if (billing_file)
                serverpath = DIR_FTP_DOWNLOAD + mrcode.substring(0, 6) + "/" + file_date + "/";
            else serverpath = DIR_FTP_UPLOAD + mrcode.substring(0, 6) + "/" + file_date + "/";
            if (billing_file)
                log_name = "Download_check";
            else log_name = "Upload_check";
            functionsCall.logStatus("Server Path: "+serverpath);
            functionsCall.logStatus(log_name+" 1");
            FTPClient ftp_1 = new FTPClient();
            functionsCall.logStatus(log_name+" 2");
            try {
                functionsCall.logStatus(log_name+" 3");
                ftp_1.connect(FTP_HOST, FTP_PORT);
                functionsCall.logStatus(log_name+" 4");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                functionsCall.logStatus(log_name+" 5");
                ftp_1.login(FTP_USER, FTP_PASS);
                functionsCall.logStatus("FTP Login Status: "+ftp_1.login(FTP_USER, FTP_PASS));
                checkfile = ftp_1.login(FTP_USER, FTP_PASS);
                functionsCall.logStatus(log_name+" 6");
            } catch (FTPConnectionClosedException e) {
                e.printStackTrace();
                try {
                    checkfile = false;
                    ftp_1.disconnect();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (checkfile) {
                functionsCall.logStatus("Download check billing_file true....");
                try {
                    functionsCall.logStatus(log_name+" 7");
                    ftp_1.setFileType(FTP.BINARY_FILE_TYPE);
                    ftp_1.enterLocalPassiveMode();
                    functionsCall.logStatus(log_name+" 8");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    functionsCall.logStatus(log_name+" 9");
                    ftp_1.changeWorkingDirectory(serverpath);
                    functionsCall.logStatus(log_name+" 10");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    functionsCall.logStatus(log_name+" 11");
                    functionsCall.logStatus(log_name+"serverDwnloadFilePath: "+serverpath);
                    FTPFile[] ftpFiles = ftp_1.listFiles(serverpath);
                    functionsCall.logStatus(log_name+" 12");
                    int length = ftpFiles.length;
                    functionsCall.logStatus(log_name+" 13");
                    functionsCall.logStatus(log_name+"Download_length"+"length = " + length);
                    for (int i = 0; i < length; i++) {
                        String namefile = ftpFiles[i].getName();
                        functionsCall.logStatus(log_name+"["+(i+1)+"]"+" : "+namefile);
                        boolean isFile = ftpFiles[i].isFile();
                        if (isFile) {
                            functionsCall.logStatus(log_name+"_file "+"Download_file: " + find_regex(namefile));
                            if (find_regex(namefile).equals(mrcode)) {
                                filefound = true;
                                getSetValues.setDownload_file_name(namefile);
                                break;
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            checkfile = false;
            if (filefound) {
                try {
                    ftp_1.logout();
                    if (billing_file)
                        handler.sendEmptyMessage(DOWNLOAD_BILLING_FILE_FOUND);
                    else handler.sendEmptyMessage(UPLOAD_BILLED_FILE_FOUND);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    ftp_1.logout();
                    if (billing_file)
                        handler.sendEmptyMessage(UPLOAD_BILLED_FILE_NOT_FOUND);
                    else handler.sendEmptyMessage(CHECK_DOWNLOAD_BILLING_FILE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String find_regex(String otp) {
        Pattern pattern = Pattern.compile("(\\d{"+ 8 +"})");
        Matcher matcher = pattern.matcher(otp);
        String val = "";
        if (matcher.find()) {
            val = matcher.group(1);
        }
        return val;
    }

    public class Download_file implements Runnable {
        String server_path, download_path, file_name, mrcode, file_date, log_name;
        Handler handler;
        boolean file_download = false, billing_file, checkfile = false, filefound = false;
        FileOutputStream fos = null;
        GetSetValues getSetValues;

        public Download_file(String file_name, String mrcode, String file_date, Handler handler, boolean billing_file,
                             GetSetValues getSetValues) {
            this.file_name = file_name;
            this.mrcode = mrcode;
            this.file_date = file_date;
            this.handler = handler;
            this.billing_file = billing_file;
            this.getSetValues = getSetValues;
        }

        @Override
        public void run() {
            if (billing_file)
                server_path = DIR_FTP_DOWNLOAD + mrcode.substring(0, 6) + "/" + file_date + "/";
            else server_path = DIR_FTP_UPLOAD + mrcode.substring(0, 6) + "/" + file_date + "/";
            if (billing_file)
                log_name = "Download_file";
            else log_name = "Upload_file";
            download_path = functionsCall.filepath(DIR_DATABASE) + File.separator;
            FTPClient ftp_1 = new FTPClient();
            functionsCall.logStatus(log_name+" 2");
            try {
                functionsCall.logStatus(log_name+" 3");
                ftp_1.connect(FTP_HOST, FTP_PORT);
                functionsCall.logStatus(log_name+" 4");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                functionsCall.logStatus(log_name+" 5");
                ftp_1.login(FTP_USER, FTP_PASS);
                functionsCall.logStatus("FTP Login Status: "+ftp_1.login(FTP_USER, FTP_PASS));
                checkfile = ftp_1.login(FTP_USER, FTP_PASS);
                functionsCall.logStatus(log_name+" 6");
            } catch (FTPConnectionClosedException e) {
                e.printStackTrace();
                try {
                    checkfile = false;
                    ftp_1.disconnect();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (checkfile) {
                functionsCall.logStatus("Download check billing_file true....");
                try {
                    functionsCall.logStatus(log_name+" 7");
                    ftp_1.setFileType(FTP.BINARY_FILE_TYPE);
                    ftp_1.enterLocalPassiveMode();
                    functionsCall.logStatus(log_name+" 8");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    functionsCall.logStatus(log_name+" 9");
                    ftp_1.changeWorkingDirectory(server_path);
                    functionsCall.logStatus(log_name+" 10");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    functionsCall.logStatus(log_name+" 11");
                    functionsCall.logStatus(log_name+"serverDwnloadFilePath: "+server_path);
                    FTPFile[] ftpFiles = ftp_1.listFiles(server_path);
                    functionsCall.logStatus(log_name+" 12");
                    int length = ftpFiles.length;
                    functionsCall.logStatus(log_name+" 13");
                    functionsCall.logStatus(log_name+"_length"+"length = " + length);
                    for (int i = 0; i < length; i++) {
                        String namefile = ftpFiles[i].getName();
                        functionsCall.logStatus(log_name+"["+(i+1)+"]"+" : "+namefile);
                        boolean isFile = ftpFiles[i].isFile();
                        if (isFile) {
                            functionsCall.logStatus(log_name+"_file "+"Download_file: " + find_regex(namefile));
                            functionsCall.logStatus(log_name+"_file "+"Download_file name: " + namefile);
                            if (find_regex(namefile).equals(mrcode)) {
                                filefound = true;
                                getSetValues.setDownload_file_name(namefile);
                                break;
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (filefound) {
                    try {
                        fos = new FileOutputStream(download_path + getSetValues.getDownload_file_name());
                        functionsCall.logStatus(log_name+" 14");
                    }
                    catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    try {
                        file_download = ftp_1.retrieveFile(server_path + getSetValues.getDownload_file_name(), fos);
                        functionsCall.logStatus(log_name+" 15");
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            functionsCall.logStatus(log_name+" 16");
            checkfile = false;
            filefound = false;
            try {
                ftp_1.logout();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (file_download) {
                getSetValues.setBilling_file(billing_file);
                functionsCall.logStatus(log_name+" 17");
                if (billing_file) {
                    functionsCall.logStatus(log_name+" 18");
                    handler.sendEmptyMessage(MAST_CUST_FILE_DOWNLOADED);
                } else handler.sendEmptyMessage(MAST_OUT_FILE_DOWNLOADED);
            }
            functionsCall.logStatus(log_name+" 19");
        }
    }
}
