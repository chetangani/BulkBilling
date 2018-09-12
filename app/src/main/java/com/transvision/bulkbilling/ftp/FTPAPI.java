package com.transvision.bulkbilling.ftp;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Handler;

import com.transvision.bulkbilling.extra.FunctionsCall;
import com.transvision.bulkbilling.values.GetSetValues;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.commons.net.ftp.FTPFile;

import java.io.IOException;

import static com.transvision.bulkbilling.extra.Constants.DIR_FTP_DOWNLOAD;
import static com.transvision.bulkbilling.extra.Constants.DIR_FTP_UPLOAD;
import static com.transvision.bulkbilling.extra.Constants.FTP_HOST;
import static com.transvision.bulkbilling.extra.Constants.FTP_PASS;
import static com.transvision.bulkbilling.extra.Constants.FTP_PORT;
import static com.transvision.bulkbilling.extra.Constants.FTP_USER;
import static com.transvision.bulkbilling.extra.Constants.UPLOAD_BILLED_FILE_FOUND;
import static com.transvision.bulkbilling.extra.Constants.UPLOAD_BILLED_FILE_NOT_FOUND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FTPAPI {
    private FunctionsCall functionsCall = new FunctionsCall();

    public class Check_available_file implements Runnable {
        String serverpath, mrcode, file_date;
        Handler handler;
        GetSetValues getSetValues;
        boolean checkfile = false, filefound = false;

        public Check_available_file(String mrcode, String file_date, Handler handler, GetSetValues getSetValues) {
            this.mrcode = mrcode;
            this.file_date = file_date;
            this.handler = handler;
            this.getSetValues = getSetValues;
        }

        @Override
        public void run() {
            serverpath = DIR_FTP_UPLOAD + mrcode.substring(0, 6) + "/" + file_date + "/";
            functionsCall.logStatus("Server Path: "+serverpath);
            functionsCall.logStatus("Main_Download"+" 1");
            FTPClient ftp_1 = new FTPClient();
            functionsCall.logStatus("Main_Download"+" 2");
            try {
                functionsCall.logStatus("Main_Download"+" 3");
                ftp_1.connect(FTP_HOST, FTP_PORT);
                functionsCall.logStatus("Main_Download"+" 4");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                functionsCall.logStatus("Main_Download"+" 5");
                ftp_1.login(FTP_USER, FTP_PASS);
                functionsCall.logStatus("FTP Login Status: "+ftp_1.login(FTP_USER, FTP_PASS));
                checkfile = ftp_1.login(FTP_USER, FTP_PASS);
                functionsCall.logStatus("Main_Download"+" 6");
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
                    functionsCall.logStatus("Main_Download"+" 7");
                    ftp_1.setFileType(FTP.BINARY_FILE_TYPE);
                    ftp_1.enterLocalPassiveMode();
                    functionsCall.logStatus("Main_Download"+" 8");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    functionsCall.logStatus("Main_Download"+" 9");
                    ftp_1.changeWorkingDirectory(serverpath);
                    functionsCall.logStatus("Main_Download"+" 10");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    functionsCall.logStatus("Main_Download"+" 11");
                    functionsCall.logStatus("serverDwnloadFilePath: "+serverpath);
                    FTPFile[] ftpFiles = ftp_1.listFiles(serverpath);
                    functionsCall.logStatus("Main_Download"+" 12");
                    int length = ftpFiles.length;
                    functionsCall.logStatus("Main_Download"+" 13");
                    functionsCall.logStatus("Main_Download_length"+"length = " + length);
                    for (int i = 0; i < length; i++) {
                        String namefile = ftpFiles[i].getName();
                        functionsCall.logStatus("Main_Download["+(i+1)+"]"+" : "+namefile);
                        boolean isFile = ftpFiles[i].isFile();
                        if (isFile) {
                            functionsCall.logStatus("Main_Download_file "+"Download_file: " + find_regex(namefile));
                            if (find_regex(namefile).equals(mrcode)) {
                                filefound = true;
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
                    handler.sendEmptyMessage(UPLOAD_BILLED_FILE_FOUND);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    ftp_1.logout();
                    handler.sendEmptyMessage(UPLOAD_BILLED_FILE_NOT_FOUND);
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

    @SuppressLint("StaticFieldLeak")
    public class Download_Cust_file extends AsyncTask<String, String, String> {
        String server_path, file_name, mrcode, file_date;
        Handler handler;
        boolean file_download = false, billing_file = false;

        public Download_Cust_file(String file_name, String mrcode, String file_date, Handler handler, boolean billing_file) {
            this.file_name = file_name;
            this.mrcode = mrcode;
            this.file_date = file_date;
            this.handler = handler;
            this.billing_file = billing_file;
        }

        @Override
        protected String doInBackground(String... strings) {
            if (billing_file)
                server_path = DIR_FTP_DOWNLOAD + mrcode.substring(0, 6) + "/" + file_date + "/";
            else server_path = DIR_FTP_UPLOAD + mrcode.substring(0, 6) + "/" + file_date + "/";
            return null;
        }
    }
}
