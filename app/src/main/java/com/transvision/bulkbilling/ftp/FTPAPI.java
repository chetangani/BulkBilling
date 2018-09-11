package com.transvision.bulkbilling.ftp;

import com.transvision.bulkbilling.extra.FunctionsCall;

import java.util.logging.Handler;

public class FTPAPI {
    private FunctionsCall functionsCall = new FunctionsCall();

    public class Check_available_file implements Runnable {
        String serverpath, mrcode;
        Handler handler;
        boolean checkfile = false;

        public Check_available_file(String serverpath, String mrcode, Handler handler) {
            this.serverpath = serverpath;
            this.mrcode = mrcode;
            this.handler = handler;
        }

        @Override
        public void run() {

        }
    }
}
