package com.transvision.bulkbilling.extra;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.transvision.bulkbilling.database.Databasehelper;
import com.transvision.bulkbilling.values.GetSetValues;
import com.transvision.bulkbilling.values.GetSet_MastCust;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.transvision.bulkbilling.extra.Constants.NEW_TARRIF_CALCULATION;
import static com.transvision.bulkbilling.extra.Constants.OLD_TARRIF_CALCULATION;
import static com.transvision.bulkbilling.extra.Constants.SPLIT_TARRIF_CALCULATION;

public class FunctionsCall {

    public TextView text_id(View view, int id) {
        return view.findViewById(id);
    }

    public EditText edittext_id(View view, int id) {
        return (EditText) view.findViewById(id);
    }

    public Button btn_id(View view, int id) {
        return (Button) view.findViewById(id);
    }

    public String getCursorValue(Cursor data, String column_name) {
        return data.getString(data.getColumnIndexOrThrow(column_name));
    }

    public String dateSet() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int mnth2 = month + 1;
        String present_date1 = day + "/" + mnth2 + "/" + "" + year;
        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy", Locale.US).parse(present_date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        c.setTime(date);
        return sdf.format(c.getTime());
    }

    public String updatedate(String pre_date) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int mnth2 = month + 1;
        String present_date1 = year + "-" + mnth2 + "-" + "" +pre_date;
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(present_date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        c.setTime(date);
        return sdf.format(c.getTime());
    }

    public String getMonthYear() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int mnth2 = month + 1;
        String present_date1;
        if (String.valueOf(mnth2).length() == 1)
            present_date1 = "0"+mnth2 + ""+year;
        else present_date1 = mnth2 + ""+year;
        Date date = null;
        try {
            date = new SimpleDateFormat("MMyyyy", Locale.US).parse(present_date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("MMyyyy", Locale.US);
        c.setTime(date);
        return sdf.format(c.getTime());
    }

    public String getYearMonth() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int mnth2 = month + 1;
        String present_date1;
        if (String.valueOf(mnth2).length() == 1)
            present_date1 = ""+year+"0"+mnth2;
        else present_date1 = ""+year+mnth2;
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyyMM", Locale.US).parse(present_date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM", Locale.US);
        c.setTime(date);
        return sdf.format(c.getTime());
    }

    public String getPrevMonthYear() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        month = month + 1;
        String present_date1;
        if (String.valueOf(month).length() == 1)
            present_date1 = "0"+month +"-"+ ""+year;
        else present_date1 = month +"-"+ ""+year;
        Date date = null;
        try {
            date = new SimpleDateFormat("MM-yyyy", Locale.US).parse(present_date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("MM-yyyy", Locale.US);
        c.setTime(date);
        return sdf.format(c.getTime());
    }

    public String getMonth(String readdate) {
        return readdate.substring(3, 5)+readdate.substring(6, 10);
    }

    public String downloaddbdate() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int mnth2 = month + 1;
        String present_date1 = day + mnth2 + "" + year;
        Date date = null;
        try {
            date = new SimpleDateFormat("ddMMyyyy", Locale.US).parse(present_date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy", Locale.US);
        c.setTime(date);
        return sdf.format(c.getTime());
    }

    public String recept_db_date() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int mnth2 = month + 1;
        String present_date1 = day + mnth2 + "" + year;
        Date date = null;
        try {
            date = new SimpleDateFormat("ddMMyy", Locale.US).parse(present_date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy", Locale.US);
        c.setTime(date);
        return sdf.format(c.getTime());
    }

    public String dateSetfordbfile() throws ParseException {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int mnth2 = month + 1;
        String date1 = day + "/" + mnth2 + "/" + "" + year;
        Date date = new SimpleDateFormat("dd/MM/yyyy", Locale.US).parse(date1);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        c.setTime(date);
        String date2 = sdf.format(c.getTime());
        String dd = date2.substring(0, 2);
        String MM = date2.substring(3, 5);
        String yyyy = date2.substring(6, 10);
        return dd + MM + yyyy;
    }

    public String dateSetforupdbfile() throws ParseException {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int min = c.get(Calendar.MINUTE);
        int mnth2 = month + 1;
        String date1 = day + "/" + mnth2 + "/" + "" + year + " " + "" + hour + ":" + "" + min;
        Date date = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.US).parse(date1);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.US);
        c.setTime(date);
        String date2 = sdf.format(c.getTime());
        String dd = date2.substring(0, 2);
        String MM = date2.substring(3, 5);
        String yyyy = date2.substring(6, 10);
        String HH = date2.substring(11, 13);
        String mm = date2.substring(14, 16);
        return dd + MM + yyyy + "_" + HH + mm;
    }

    //Getting the duedate
    public String duedate(String s, int duedate){
        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy", Locale.US).parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, duedate);
        return (sdf.format(c.getTime()));
    }

    public String datepickerdlgformat(String dateformat) {
        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy", Locale.US).parse(dateformat);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return sdf.format(c.getTime());
    }

    public String currentDateandTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.US);
        return sdf.format(new Date());
    }

    public String currentDateTimeforBilling() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy:HH:mm:ss", Locale.US);
        return sdf.format(new Date());
    }

    public String currentRecpttime() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a", Locale.US);
        return sdf.format(new Date());
    }

    /*public String recptDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm a", Locale.US);
        return sdf.format(new Date());
    }

    public String getuploadfilename(String IMEI) {
        String date = null;
        try {
            date = dateSetfordbfile();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return IMEI + "_" + date + "_UP";
    }

    public String getdownloadfilename(String IMEI) {
        String date = null;
        try {
            date = dateSetfordbfile();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return IMEI + "_" + date + "_DWN";
    }*/

    public String Appfoldername() {
        return "TRM_Bulk_Billing" + File.separator + "data";
    }

    public String filepath(String value) {
        File dir = new File(android.os.Environment.getExternalStorageDirectory(), Appfoldername()
                + File.separator + value);
        if (!dir.exists()) {
            //noinspection ResultOfMethodCallIgnored
            dir.mkdirs();
        }
        return dir.toString();
    }

    public File filestore(String value) {
        File dir = new File(android.os.Environment.getExternalStorageDirectory(), Appfoldername()
                + File.separator + value);
        if (!dir.exists()) {
            //noinspection ResultOfMethodCallIgnored
            dir.mkdirs();
        }
        return dir;
    }

    public File filestorepath(String value, String file) {
        File dir = new File(android.os.Environment.getExternalStorageDirectory(), Appfoldername()
                + File.separator + value);
        if (!dir.exists()) {
            //noinspection ResultOfMethodCallIgnored
            dir.mkdirs();
        }
        return new File(dir, File.separator + file);
    }

    private String trm_backup1() {
        return ".TRM_Backup1" + File.separator + "data" + File.separator + "files";
    }

    public String trm_backup1_filepath(String value) {
        File dir = new File(android.os.Environment.getExternalStorageDirectory(), trm_backup1()
                + File.separator + value);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir.toString();
    }

    public File trm_backup1_filestorepath(String value, String file) {
        File dir = new File(android.os.Environment.getExternalStorageDirectory(), trm_backup1()
                + File.separator + value);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return new File(dir, File.separator + file);
    }

    private String trm_backup2() {
        return ".TRM_Backup2" + File.separator + "data" + File.separator + "files";
    }

    public String trm_backup2_filepath(String value) {
        File dir = new File(android.os.Environment.getExternalStorageDirectory(), trm_backup2()
                + File.separator + value);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir.toString();
    }

    public File trm_backup2_filestorepath(String value, String file) {
        File dir = new File(android.os.Environment.getExternalStorageDirectory(), trm_backup2()
                + File.separator + value);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return new File(dir, File.separator + file);
    }

    public String deletepath(String value) {
        File dir = new File(android.os.Environment.getExternalStorageDirectory(), Appfoldername()
                + File.separator + value);
        if (dir.exists()) {
            dir.delete();
        }
        return "" + dir;
    }

    @SuppressWarnings("SpellCheckingInspection")
    public void deletefile(String value) {
        File dir = new File(android.os.Environment.getExternalStorageDirectory(), Appfoldername()
                + File.separator + value);
        if (!dir.exists()) {
            dir.delete();
        }
        Log.d("debug", "File deleting: " + "" + dir);
    }

    public String changedateformat(String datevalue, String changedivider) {
        Date date = null;
        if ((datevalue.substring(datevalue.length() - 5, datevalue.length() - 4)).equals("/")) {
            try {
                date = new SimpleDateFormat("dd/MM/yyyy", Locale.US).parse(datevalue);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            if ((datevalue.substring(datevalue.length() - 5, datevalue.length() - 4)).equals("-")) {
                try {
                    date = new SimpleDateFormat("dd-MM-yyyy", Locale.US).parse(datevalue);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                if (datevalue.length() == 8) {
                    try {
                        date = new SimpleDateFormat("dd-MM-yyyy", Locale.US).parse(datevalue.substring(0, 2) + "-"
                                + datevalue.substring(2, 4) + "-" + datevalue.substring(4));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        String format = sdf.format(c.getTime());
        return format.substring(0, 2) + changedivider + format.substring(3, 5) + changedivider + format.substring(6, 10);
    }

    public void showtoastatcenter(Context context, String Message) {
        Toast toast = Toast.makeText(context, Message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public void showtoastatbottom(Context context, String Message) {
        Toast toast = Toast.makeText(context, Message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.show();
    }

    public void showtoast(Context context, String Message) {
        Toast.makeText(context, Message, Toast.LENGTH_SHORT).show();
    }

    public String inserttextfile() {
        return "TextReport.txt";
    }

    public void checkimage_and_delete(String foldername, String ConsumerID) {
        String folderpath = filepath(foldername);
        int considlength = ConsumerID.length();
        File imagefiledir = new File(folderpath);
        File[] files = imagefiledir.listFiles();
        for (File file1 : files) {
            String filepath = file1.getName();
            String findimage = filepath.substring(0, considlength);
            if (findimage.equals(ConsumerID)) {
                File file = new File(folderpath + File.separator + filepath);
                if (file.isFile()) {
                    file.delete();
                }
            }
        }
    }

    public boolean checkuploadedfile() {
        boolean fileuploaded = false;
        String foldername = "UploadFolder";
        String folderpath = filepath(foldername);
        String currentdate;
        currentdate = dateSet();
        String MM = currentdate.substring(3, 5);
        String yy = currentdate.substring(6, 10);
        String curdate = MM + yy;
        File uploadfiles = new File(folderpath);
        File[] files = uploadfiles.listFiles();
        if (files.length > 0) {
            for (File file : files) {
                String getfilename = file.getName();
                String getfilemonth = getfilename.substring(18, 24);
                if (getfilemonth.equals(curdate)) {
                    fileuploaded = true;
                    break;
                }
            }
        }
        return fileuploaded;
    }

    public String month(String date) {
        String s1 = date.substring(2, 3);
        String[] months = new String[]{"JAN", "FEB", "MAR",
                "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
        String s3;
        if (s1.equals("/") || s1.equals("-")) {
            String s2 = date.substring(3, 5);
            int m1 = Integer.parseInt(s2);
            s3 = months[m1 - 1];
        } else {
            String s2 = date.substring(5, 7);
            int m1 = Integer.parseInt(s2);
            s3 = months[m1 - 1];
        }
        return s3;
    }

    public Date selectiondate(String date) {
        Date date1 = null;
        try {
            date1 = new SimpleDateFormat("dd/MM/yyyy", Locale.US).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date1;
    }

    public String dateformat(String date, String separationformat) {
        String a1 = date.substring(0, 2);
        String a2 = date.substring(3, 5);
        String a3 = date.substring(6, 10);
        return a1 + separationformat + a2 + separationformat + a3;
    }

    public String changedateview(String date) {
        String s1 = date.substring(2, 3);
        String s3;
        if (s1.equals("/") || s1.equals("-")) {
            //noinspection ConstantConditions
            String a1 = s1.substring(0, 2);
            String a2 = s1.substring(3, 5);
            String a3 = s1.substring(6, 10);
            s3 = a3 + "-" + a2 + "-" + a1;
        } else {
            String a1 = s1.substring(0, 4);
            String a2 = s1.substring(5, 7);
            String a3 = s1.substring(8, 10);
            s3 = a3 + "-" + a2 + "-" + a1;
        }
        return s3;
    }

    public String monthview(String date) {
        String s1 = date.substring(3, 5);
        String[] months = new String[]{"JANUARY", "FEBRUARY", "MARCH",
                "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER"};
        int m1 = Integer.parseInt(s1);
        return months[m1 - 1];
    }

    public String dateview(String date) {
        String s1 = date.substring(2, 3);
        String[] months = new String[]{"JAN", "FEB", "MAR",
                "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
        String s3;
        if (s1.equals("/") || s1.equals("-")) {
            String a1 = date.substring(0, 2);
            String s2 = date.substring(3, 5);
            int m1 = Integer.parseInt(s2);
            String a2 = months[m1 - 1];
            String a3 = date.substring(6, 10);
            s3 = a1 + "-" + a2 + "-" + a3;
        } else {
            String a1 = date.substring(0, 4);
            String s2 = date.substring(5, 7);
            int m1 = Integer.parseInt(s2);
            String a2 = months[m1 - 1];
            String a3 = date.substring(8, 10);
            s3 = a3 + "-" + a2 + "-" + a1;
        }
        return s3;
    }

    @SuppressLint("DefaultLocale")
    public String convertdateview(String date, String format, String separation) {
        String s1, s2, s3, s4, s5="";
        if (date.length() == 10) {
            s1 = date.substring(0, 2);
            s2 = date.substring(3, 5);
            s3 = date.substring(6, 10);
            if (format.equals("DD") || format.equals("dd")) {
                s5 = s1 + separation + s2 + separation + s3;
            } else {
                s5 = s3 + separation + s2 + separation + s1;
            }
        } else if (date.length() == 9) {
            s4 = date.substring(1, 2);
            try {
                int i1 = Integer.parseInt(s4);
                s1 = date.substring(0, 2);
                s2 = date.substring(3, 4);
                s3 = date.substring(5, 9);
                if (format.equals("DD") || format.equals("dd")) {
                    s5 = s1 + separation + "0"+s2 + separation + s3;
                } else {
                    s5 = s3 + separation + "0"+s2 + separation + s1;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                s1 = date.substring(0, 1);
                s2 = date.substring(2, 4);
                s3 = date.substring(5, 9);
                if (format.equals("DD") || format.equals("dd")) {
                    s5 = "0"+s1 + separation + s2 + separation + s3;
                } else {
                    s5 = s3 + separation + s2 + separation + "0"+s1;
                }
            }
        } else if (date.length() == 8) {
            s1 = date.substring(0, 1);
            s2 = date.substring(2, 3);
            s3 = date.substring(4, 8);
            if (format.equals("DD") || format.equals("dd")) {
                s5 = "0"+s1 + separation + "0"+s2 + separation + s3;
            } else {
                s5 = s3 + separation + "0"+s2 + separation + "0"+s1;
            }
        }
        return s5;
    }

    public String getConvertedDateformat(String value) {
        String outDate = "";
        SimpleDateFormat inSDF = new SimpleDateFormat("ddMMyyyy", Locale.getDefault());
        SimpleDateFormat outSDF = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        if (value != null) {
            try {
                Date date = inSDF.parse(value);
                outDate = outSDF.format(date);
            } catch (ParseException ex){
                ex.printStackTrace();
            }
        }
        return outDate;
    }

    public String consumernumber(String number) {
        int consnum = number.length();
        String cons1;
        cons1 = number;
        String cons2;
        String cons3 = null;
        for (int i = 0; i < cons1.length(); i++) {
            cons2 = cons1.substring(i, (i + 1));
            if (cons2.equals("0")) {
                cons3 = cons1.substring(i + 1);
            } else {
                cons3 = cons1.substring(i);
                break;
            }
        }
        return cons3;
    }

    public String considappend(String s, int length, String space_value) {
        int temp;
        StringBuilder spaces = new StringBuilder();
        temp = length - s.length();
        for (int i = 0; i < temp; i++) {
            spaces.append(space_value);
        }
        return (spaces + s);
    }

    public void showprogressdialog(String Message, ProgressDialog dialog) {
        dialog.setTitle("Downloading");
        dialog.setMessage(Message);
        dialog.setCancelable(false);
        dialog.setIndeterminate(true);
        dialog.show();
    }

    public void showprogressdialog(String Message, ProgressDialog dialog, String Title) {
        dialog.setTitle(Title);
        dialog.setMessage(Message);
        dialog.setCancelable(false);
        dialog.show();
    }

    public void showprogress(String Message, ProgressDialog dialog, String Title) {
        dialog.setTitle(Title);
        dialog.setMessage(Message);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setCancelable(false);
        dialog.show();
    }

    //Making right alignment for values
    public String rightalign(String s1, int len) {
        StringBuilder s1Builder = new StringBuilder(s1);
        for (int i = 0; i < len - s1Builder.length(); i++) {
            s1Builder.insert(0, " ");
        }
        s1 = s1Builder.toString();
        return (s1);
    }

    public String alignright(String msg, int len) {
        StringBuilder msgBuilder = new StringBuilder(msg);
        for (int i = 0; i < len - msgBuilder.length(); i++) {
            msgBuilder.insert(0, " ");
        }
        msg = msgBuilder.toString();
        msg = String.format("%" + len + "s", msg);
        return msg;
    }

    public String aligncenter(String msg, int len) {
        int count = msg.length();
        int value = len - count;
        int append = (value / 2);
        return space(" ", append) + msg + space(" ", append);
    }

    //Making left alignment for values
    public String leftalign(String s1, int len) {
        StringBuilder s1Builder = new StringBuilder(s1);
        for (int i = 0; i < len - s1Builder.length(); i++) {
            s1Builder.append(" ");
        }
        s1 = s1Builder.toString();
        return (s1);
    }

    public String centeralign(String text, int width) {
        int count = text.length();
        int value = width - count;
        int append = (value / 2);
        return space(" ", append) + text;
    }

    //Dotted line
    public String line(int length) {
        StringBuilder sb5 = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb5.append("-");
        }
        return (sb5.toString());
    }

    public String spaceright(String s, int length) {
        int temp;
        StringBuilder spaces = new StringBuilder();
        temp = length - s.length();
        for (int i = 0; i < temp; i++) {
            spaces.append(" ");
        }
        return (spaces + s);
    }

    public String space(String s, int len) {
        int temp;
        StringBuilder spaces = new StringBuilder();
        temp = len - s.length();
        for (int i = 0; i < temp; i++) {
            spaces.append(" ");
        }
        return (s + spaces);
    }

    public String blank(int length) {
        StringBuilder sb6 = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb6.append(" ");
        }
        return (sb6.toString());
    }

    public Bitmap getImage(String path, Context con) throws IOException {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        int srcWidth = options.outWidth;
        int srcHeight = options.outHeight;
        int[] newWH = new int[2];
        newWH[0] = srcWidth / 2;
        newWH[1] = (newWH[0] * srcHeight) / srcWidth;

        int inSampleSize = 1;
        while (srcWidth / 2 >= newWH[0]) {
            srcWidth /= 2;
            srcHeight /= 2;
            inSampleSize *= 2;
        }

        options.inJustDecodeBounds = false;
        options.inDither = false;
        options.inSampleSize = inSampleSize;
        options.inScaled = false;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap sampledSrcBitmap = BitmapFactory.decodeFile(path, options);
        ExifInterface exif = new ExifInterface(path);
        String s = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
        System.out.println("Orientation>>>>>>>>>>>>>>>>>>>>" + s);
        Matrix matrix = new Matrix();
        float rotation = rotationForImage(con, Uri.fromFile(new File(path)));
        if (rotation != 0f) {
            matrix.preRotate(rotation);
        }
        return Bitmap.createBitmap(
                sampledSrcBitmap, 0, 0, sampledSrcBitmap.getWidth(), sampledSrcBitmap.getHeight(), matrix, true);
    }


    private float rotationForImage(Context context, Uri uri) {
        if (uri.getScheme().equals("content")) {
            String[] projection = {MediaStore.Images.ImageColumns.ORIENTATION};
            @SuppressLint("Recycle") Cursor c = context.getContentResolver().query(
                    uri, projection, null, null, null);
            if (c != null && c.moveToFirst()) {
                return c.getInt(0);
            }
        } else if (uri.getScheme().equals("file")) {
            try {
                ExifInterface exif = new ExifInterface(uri.getPath());
                return (int) exifOrientationToDegrees(
                        exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                                ExifInterface.ORIENTATION_NORMAL));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return 0f;
    }

    private static float exifOrientationToDegrees(int exifOrientation) {
        if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {
            return 90;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {
            return 180;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {
            return 270;
        }
        return 0;
    }

    public String pdcalculation(String pdrecorded, Cursor c, String sanctionload, String days) {
        double pdcal = 0;
        double pd = Double.parseDouble(pdrecorded);
        double load = Double.parseDouble(sanctionload);
        double pddiff = pd - load;
        if (pddiff > 0) {
            c.moveToNext();
            String frate = c.getString(c.getColumnIndex("FRATE1"));
            double fcal = (Double.parseDouble(frate) / 7) * Double.parseDouble(days);
            DecimalFormat num = new DecimalFormat("##.00");
            String pdload = num.format(pddiff);
            String pdload1 = pdload.substring(0, pdload.lastIndexOf('.'));
            if (TextUtils.isEmpty(pdload1))
                pdload1 = "0";
            String pdload2 = pdload.substring(pdload.lastIndexOf('.')+1);
            int pdload7;
            if (pdload2.length() == 1) {
                pdload7 = Integer.parseInt(pdload2+"0");
            } else pdload7 = Integer.parseInt(pdload2);
            if (pdload7 >= 0 && pdload7 <= 12) {
                pdcal = Double.parseDouble(pdload1) * fcal * 2;
            } else if (pdload7 > 12 && pdload7 <= 37) {
                pdcal = (Double.parseDouble(pdload1) + 0.25) * fcal * 2;
            } else if (pdload7 > 37 && pdload7 <= 62) {
                pdcal = (Double.parseDouble(pdload1) + 0.50) * fcal * 2;
            } else if (pdload7 > 62 && pdload7 <= 87) {
                pdcal = (Double.parseDouble(pdload1) + 0.75) * fcal * 2;
            } else pdcal = (Double.parseDouble(pdload1) + 1) * fcal * 2;
        }
        return decimalroundoff(pdcal);
    }

    public String decimalroundoff(double value) {
        BigDecimal a = new BigDecimal(value);
        BigDecimal roundOff = a.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        return "" + roundOff;
    }

    public String decimalround(double value) {
        String val = String.valueOf(value);
        String val1 = val.substring(val.indexOf('.')+1, val.length());
        if (val1.length() > 2) {
            String val3 = val1.substring(2, 3);
            BigDecimal a = new BigDecimal(value);
            BigDecimal roundOff;
            BigDecimal roundOff1;
            BigDecimal a1;
            if (val1.length() > 3) {
                String val4 = val1.substring(3, 4);
                if (Integer.parseInt(val4) < 5) {
                    roundOff1 = a.setScale(3, BigDecimal.ROUND_HALF_EVEN);
                    a1 = new BigDecimal(String.valueOf(roundOff1));
                    roundOff = a1.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                } else {
                    roundOff1 = a.setScale(3, BigDecimal.ROUND_UP);
                    a1 = new BigDecimal(String.valueOf(roundOff1));
                    roundOff = a1.setScale(2, BigDecimal.ROUND_UP);
                }
            } else {
                if (Integer.parseInt(val3) < 5) {
                    roundOff = a.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                } else roundOff = a.setScale(2, BigDecimal.ROUND_UP);
            }
            return String.valueOf(roundOff);
        } else return String.valueOf(value);
    }

    /*public String taxamount(double value) {
        String tax = ""+value;
        String amt = tax.substring(0, tax.lastIndexOf('.'));
        String amt1 = tax.substring(tax.lastIndexOf('.')+1, tax.length());
        if (amt1.length() > 2) {
            amt1 = amt1.substring(0, 2);
            return amt+"."+amt1;
        } else if (amt1.length() == 1) {
            amt1 = amt1 + "0";
            return amt+"."+amt1;
        } else return amt+"."+amt1;
    }

    public String tripleroundoff(double value) {
        BigDecimal a = new BigDecimal(value);
        BigDecimal roundOff = a.setScale(3, BigDecimal.ROUND_HALF_EVEN);
        double againroundoff = Double.parseDouble(roundOff.toString());
        BigDecimal a2 = new BigDecimal(againroundoff);
        BigDecimal roundOff2 = a2.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        return "" + roundOff2;
    }*/

    public String CalculateDays(String Prev_date, String Pres_date) {
        Prev_date = changedateformat(Prev_date, "/");
        Pres_date = changedateformat(Pres_date, "/");
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        Date Date1 = null;
        Date Date2 = null;
        try {
            Date1 = format.parse(Prev_date);
            Date2 = format.parse(Pres_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long result = 0;
        if (Date2 != null)
            result = (Date2.getTime() - Date1.getTime()) / (24 * 60 * 60 * 1000);
        return "" + result;
    }

    public void showtoastnormal(Context context, String Message) {
        Toast toast = Toast.makeText(context, Message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.show();
    }

    public void logStatus(String message) {
        Log.d("debug", message);
    }

    public String monthnumber(String date) {
        return date.substring(3, 5);
    }

    public boolean checkInternetConnection(Context context) {
        CheckInternetConnection cd = new CheckInternetConnection(context.getApplicationContext());
        return cd.isConnectingToInternet();
    }

    public final boolean isInternetOn(Context activity) {
        ConnectivityManager connect = (ConnectivityManager) activity.getSystemService(android.content.Context.CONNECTIVITY_SERVICE);
        if (connect != null) {
            if (connect.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTED ||
                    connect.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTING ||
                    connect.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTING ||
                    connect.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED) {
                return true;
            } else if (connect.getNetworkInfo(0).getState() == NetworkInfo.State.DISCONNECTED ||
                    connect.getNetworkInfo(1).getState() == NetworkInfo.State.DISCONNECTED) {
                return false;
            }
        }
        return false;
    }

    public void updateApp(Context context, File Apkfile) {
        Uri path;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            path = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", Apkfile);
        } else path = Uri.fromFile(Apkfile);
        Intent objIntent = new Intent(Intent.ACTION_VIEW);
        objIntent.setDataAndType(path, "application/vnd.android.package-archive");
        objIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        objIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        context.startActivity(objIntent);
    }

    public void splitString(String msg, int lineSize, ArrayList<String> arrayList) {
        arrayList.clear();
        Pattern p = Pattern.compile("\\b.{0," + (lineSize - 1) + "}\\b\\W?");
        Matcher m = p.matcher(msg);
        while (m.find()) {
            arrayList.add(m.group().trim());
        }
    }

    public String encodeImage(Bitmap bitmap, int quality) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, quality, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    public void showtitleprogressdialog(String Message, ProgressDialog dialog, String Title) {
        dialog.setTitle(Title);
        dialog.setMessage(Message);
        dialog.setCancelable(false);
        dialog.setIndeterminate(true);
        dialog.show();
    }

    public String normalisedVersion(String version) {
        return normalisedVersion(version, ".", 4);
    }

    private String normalisedVersion(String version, String sep, int maxWidth) {
        String[] split = Pattern.compile(sep, Pattern.LITERAL).split(version);
        StringBuilder sb = new StringBuilder();
        for (String s : split) {
            sb.append(String.format("%" + maxWidth + 's', s));
        }
        return sb.toString();
    }

    public String Parse_Date2(String time) {
        String input = "yyyy-MM-d";
        String output = "dd-MM-yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(input, Locale.US);
        SimpleDateFormat outputFormat = new SimpleDateFormat(output, Locale.US);

        Date date = null;
        String str = null;
        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    public String Parse_date1(String time) {
        String inputPattern = "dd-MM-yyyy HH:mm:ss";
        String outputPattern = "dd-MMM-yyyy h:mm a";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern, Locale.US);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern, Locale.US);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    public String convertTo24Hour(String Time) {
        String convert = Time.substring(Time.length()-2);
//        convert = convert.substring(0, 1)+"."+convert.substring(1, 2)+".";
        convert = convert.toUpperCase();
        Time = Time.substring(0, Time.length()-2)+ " " + convert;
        String formattedDate="";
        try {
            SimpleDateFormat inFormat = new SimpleDateFormat("hh:mm aa", Locale.US);
            SimpleDateFormat outFormat = new SimpleDateFormat("HH:mm", Locale.US);
            formattedDate = outFormat.format(inFormat.parse(Time));
        } catch (Exception e) {
            e.printStackTrace();
        }
        logStatus("Converted: "+formattedDate);
        return formattedDate;
    }

    public Date collectiontime(String date) {
        Date date1 = null;
        try {
            date1 = new SimpleDateFormat("HH:mm", Locale.US).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date1;
    }

    public String currentTime() {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        String present_date1 = hour + ":" + minute;
        Date date = null;
        try {
            date = new SimpleDateFormat("HH:mm", Locale.US).parse(present_date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.US);
        c.setTime(date);
        return sdf.format(c.getTime());
    }

    public boolean compare_Times(Date fromdate, Date todate) {
        boolean result = false;
        Date currentime = collectiontime(currentTime());
        if (currentime.after(fromdate)) {
            logStatus("more");
            if (currentime.before(todate)) {
                logStatus("less");
                result = true;
            } else result = false;
        }
        return result;
    }

    public boolean compare_end_billing_time(String end_time) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.US);
        Date todate = null;
        try {
            todate = sdf.parse(end_time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date currentime = collectiontime(currentTime());
        if (currentime.before(todate)) {
            logStatus("less");
            return true;
        } else return false;
    }

    public boolean compare(String v1, String v2) {
        String s1 = normalisedVersion(v1);
        String s2 = normalisedVersion(v2);
        int cmp = s1.compareTo(s2);
        String cmpStr = cmp < 0 ? "<" : cmp > 0 ? ">" : "==";
        return cmpStr.equals("<");
    }

    public String reversedate(String date) {
        String s1 = date.substring(0, 2);
        String s2 = date.substring(3, 5);
        String s3 = date.substring(6);
        return s2+"/"+s1+"/"+s3;
    }

    public String convert_collection_date(String date) {
        String s1 = date.substring(0, 2);
        String s2 = date.substring(3, 5);
        return date.substring(6);
    }

    public String reversedate(String date, String separation) {
        String s1 = date.substring(0, 2);
        String s2 = date.substring(3, 5);
        String s3 = date.substring(6);
        return s2+separation+s1+separation+s3;
    }

    public String rupeeFormat(String value) {
        value = value.replace(",", "");
        char lastDigit = value.charAt(value.length() - 1);
        String result = "";
        int len = value.length() - 1;
        int nDigits = 0;
        for (int i = len - 1; i >= 0; i--) {
            result = value.charAt(i) + result;
            nDigits++;
            if (((nDigits % 2) == 0) && (i > 0)) {
                result = "," + result;
            }
        }
        return (result + lastDigit);
    }

    public int monthdiff(String date) {
        String currdate;
        currdate = dateSet();
        date = changedateformat(date, "/");
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

        Date d1 = null;
        Date d2 = null;
        try {
            d1 = format.parse(date);
            d2 = format.parse(currdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date startDate = d1;
        Date endDate = d2;
        Calendar startCalendar = new GregorianCalendar();
        startCalendar.setTime(startDate);
        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(endDate);

        int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        return diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
    }

    public String getMonthtoint(String readate, String separation) {
        String finaldate="", month="", s1="", s2="";
        s1 = readate.substring(0, 2);
        s2 = readate.substring(7);
        month = readate.substring(3, 6).toUpperCase();
        switch (month) {
            case "JAN":
                finaldate = s1+separation+"01"+separation+s2;
                break;

            case "FEB":
                finaldate = s1+separation+"02"+separation+s2;
                break;

            case "MAR":
                finaldate = s1+separation+"03"+separation+s2;
                break;

            case "APR":
                finaldate = s1+separation+"04"+separation+s2;
                break;

            case "MAY":
                finaldate = s1+separation+"05"+separation+s2;
                break;

            case "JUN":
                finaldate = s1+separation+"06"+separation+s2;
                break;

            case "JUL":
                finaldate = s1+separation+"07"+separation+s2;
                break;

            case "AUG":
                finaldate = s1+separation+"08"+separation+s2;
                break;

            case "SEP":
                finaldate = s1+separation+"09"+separation+s2;
                break;

            case "OCT":
                finaldate = s1+separation+"10"+separation+s2;
                break;

            case "NOV":
                finaldate = s1+separation+"11"+separation+s2;
                break;

            case "DEC":
                finaldate = s1+separation+"12"+separation+s2;
                break;
        }
        return finaldate;
    }

    public String receipt_date() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int mnth2 = month + 1;
        String present_date1 = year + "-" + mnth2 + "-" + "" + day;
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(present_date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        c.setTime(date);
        return sdf.format(c.getTime());
    }

    public String receipt_date_time() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int mnth2 = month + 1;
        String present_date1 = day + "/" + mnth2 + "/" + "" + year + " " + ""+hour + ":"+ ""+minute;
        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.US).parse(present_date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.US);
        c.setTime(date);
        return sdf.format(c.getTime());
    }

    public boolean compare_Times(String start_time, String end_time) {
        boolean result = false;
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.US);
        Date fromdate = null, todate = null;
        try {
            fromdate = sdf.parse(start_time);
            todate = sdf.parse(end_time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date currentime = collectiontime(currentTime());
        if (currentime.after(fromdate)) {
            logStatus("more");
            if (currentime.before(todate)) {
                logStatus("less");
                result = true;
            } else result = false;
        }
        return result;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void copyFile(String inputPath, String inputFile, String outputPath) {
        InputStream in;
        OutputStream out;
        try {
            //create output directory if it doesn't exist
            File dir = new File (outputPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            in = new FileInputStream(inputPath + inputFile);
            out = new FileOutputStream(outputPath + inputFile);
            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            in.close();
            // write the output file (You have now copied the file)
            out.flush();
            out.close();
        } catch (Exception e) {
            Log.e("debug", e.getMessage());
        }
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public String encoded(String filepath) {
        File originalFile = new File(filepath);
        String encodedBase64 = null;
        try {
            FileInputStream fileInputStreamReader = new FileInputStream(originalFile);
            byte[] bytes = new byte[(int)originalFile.length()];
            fileInputStreamReader.read(bytes);
            encodedBase64 = Base64.encodeToString(bytes, Base64.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return encodedBase64;
    }

    public String check_tarrif_rate(String pres_date, String prev_date, Databasehelper databasehelper) {
        String result, tarrif_date_chg;
        Cursor data = databasehelper.subdivdetails();
        data.moveToNext();
        tarrif_date_chg = getCursorValue(data, "NWTRF_DATE");
        Date present_date = null, previous_date = null, tarrif_chg_date = null;
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        try {
            present_date = formatter.parse(changedateformat(pres_date, "-"));
            previous_date = formatter.parse(changedateformat(prev_date, "-"));
            tarrif_chg_date = formatter.parse(get_month_date_decreased(tarrif_date_chg, 0, -1));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assert previous_date != null;
        if (previous_date.before(tarrif_chg_date)) {
            if (present_date.before(tarrif_chg_date)) {
                result = OLD_TARRIF_CALCULATION;
            } else result = SPLIT_TARRIF_CALCULATION;
        } else result = NEW_TARRIF_CALCULATION;
        return result;
    }

    public void get_fc_tarrif_rate_status(GetSet_MastCust getSetValues, String pres_date, String prev_date, Databasehelper databasehelper) {
        String tarrif_date_chg;
        Cursor data = databasehelper.subdivdetails();
        data.moveToNext();
        tarrif_date_chg = getCursorValue(data, "NWTRF_DATE");
        String tarrif_date = get_month_date_decreased(tarrif_date_chg, 0, -1);
        double days_diff;
        int total_days = Integer.parseInt(CalculateDays(prev_date, pres_date));
        int diff_days1 = Integer.parseInt(CalculateDays(get_month_date_decreased(prev_date, 0, 0), tarrif_date));
        getSetValues.setOld_days(diff_days1);
        int diff_days2 = total_days - diff_days1;
        getSetValues.setNormal_days(diff_days2);
        days_diff = (double)diff_days1 / 30;
        getSetValues.setFc_old_value(days_diff - 1);
        days_diff = (double)diff_days2 / 30;
        getSetValues.setFc_normal_value(days_diff - 1);
    }

    public void get_ec_tarrif_rate_status(GetSet_MastCust getSetValues, String pres_date, String prev_date, String units, Databasehelper databasehelper) {
        String tarrif_date_chg;
        Cursor data = databasehelper.subdivdetails();
        data.moveToNext();
        tarrif_date_chg = getCursorValue(data, "NWTRF_DATE");
        String tarrif_date = get_month_date_decreased(tarrif_date_chg, 0, -1);
        double days_diff;
        int total_days = Integer.parseInt(CalculateDays(prev_date, pres_date));
        int diff_days1 = Integer.parseInt(CalculateDays(get_month_date_decreased(prev_date, 0, 0), tarrif_date));
        int diff_days2 = total_days - diff_days1;
        days_diff = (double)diff_days1 / total_days;
        double sanc_load1 = days_diff * Double.parseDouble(units);
        getSetValues.setEc_old_value(sanc_load1);
        days_diff = (double)diff_days2 / total_days;
        double sanc_load2 = days_diff * Double.parseDouble(units);
        getSetValues.setEc_normal_value(sanc_load2);
    }

    private String get_month_date_decreased(String date, int month, int days) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        Date month_date = null;
        try {
            month_date = formatter.parse(changedateformat(date, "-"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(month_date);
        calendar.add(Calendar.MONTH,  month);
        calendar.add(Calendar.DATE,  days);
        return formatter.format(calendar.getTime());
    }

    public double getBigdecimal(double value, int decimal) {
        return new BigDecimal(value).setScale(decimal, RoundingMode.HALF_EVEN).doubleValue();
    }

    public void check_fac_status(GetSet_MastCust getSetValues, String pres_date, String prev_date, String units, Databasehelper databasehelper) {
        String fac_start, fac_end;
        int diff = Integer.parseInt(pres_date.substring(0, 2));
        Cursor data = databasehelper.subdivdetails();
        data.moveToNext();
        fac_start = get_month_date_decreased(getCursorValue(data, "FAC_START"), -1, diff - 1);
        fac_end = get_month_date_decreased(getCursorValue(data, "FAC_END"), -1, diff + 1);
        Date present_date = null, previous_date = null, fac_null_start = null, fac_null_end = null;
        double days_diff;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        try {
            present_date = formatter.parse(changedateformat(pres_date, "-"));
            previous_date = formatter.parse(changedateformat(prev_date, "-"));
            fac_null_start = formatter.parse(fac_start);
            fac_null_end = formatter.parse(fac_end);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        double total_days = Double.parseDouble(CalculateDays(prev_date, pres_date));
        double fac_days_diff = Double.parseDouble(CalculateDays(fac_start, fac_end));
        assert previous_date != null;
        if (previous_date.before(fac_null_start)) {
            if (present_date.before(fac_null_start)) {
                getSetValues.setFac_days(getBigdecimal(Double.parseDouble(units), 1));
                getSetValues.setFac_remaining_days(0.0);
            } else {
                if (present_date.after(fac_null_start) && present_date.before(fac_null_end)) {
                    int diff_days1 = Integer.parseInt(CalculateDays(get_month_date_decreased(prev_date, 0, 0), fac_start));
                    days_diff = (double)diff_days1 / total_days;
                    double fac_units = getBigdecimal(days_diff * Double.parseDouble(units), 1);
                    getSetValues.setFac_days(fac_units);
                    getSetValues.setFac_remaining_days(getBigdecimal(Double.parseDouble(units) - fac_units, 1));
                } else {
                    double fac_days = getBigdecimal(total_days - fac_days_diff, 1);
                    double days_diff1 = fac_days / total_days;
                    days_diff = days_diff1 * Double.parseDouble(units);
                    getSetValues.setFac_days(getBigdecimal(days_diff, 1));
                    getSetValues.setFac_remaining_days(getBigdecimal(Double.parseDouble(units) - days_diff, 1));
                }
            }
        } else {
            if (present_date.before(fac_null_end)) {
                getSetValues.setFac_days(0.0);
                getSetValues.setFac_remaining_days(getBigdecimal(Double.parseDouble(units), 1));
            } else {
                if (previous_date.after(fac_null_end) && present_date.after(fac_null_end)) {
                    getSetValues.setFac_days(getBigdecimal(Double.parseDouble(units), 1));
                    getSetValues.setFac_remaining_days(0.0);
                } else {
                    int diff_days1 = Integer.parseInt(CalculateDays(fac_end, pres_date));
                    days_diff = (double)diff_days1 / total_days;
                    double fac_units = getBigdecimal(days_diff * Double.parseDouble(units), 1);
                    getSetValues.setFac_days(fac_units);
                    getSetValues.setFac_remaining_days(getBigdecimal(Double.parseDouble(units) - fac_units, 1));
                }
            }
        }
    }

    public boolean isMyServiceRunning(Context context, Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (manager != null) {
            for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
                if (serviceClass.getName().equals(service.service.getClassName())) {
                    return false;
                }
            }
        }
        return true;
    }

    public double convert_decimal(String value) {
        return Double.parseDouble(value);
    }

    public int convert_int(String value) {
        return Integer.parseInt(value);
    }

    public String getdecimal(String value) {
        DecimalFormat num = new DecimalFormat("##0.00");
        return String.valueOf(num.format(Double.parseDouble(value)));
    }

    public String getdecimal(double value) {
        DecimalFormat num = new DecimalFormat("##0.00");
        return String.valueOf(num.format(value));
    }

    public String getAccountID(EditText value) {
        return String.format("%10s", value.getText().toString()).replace(' ', '0');
    }

    public double getTaxStatus(String pres_date, String prev_date, GetSet_MastCust getSetValues, Databasehelper databasehelper, Cursor tax,
                               double energy_charges) {
        String tax_date;
        Cursor data = databasehelper.subdivdetails();
        data.moveToNext();
        tax_date = getCursorValue(data, "TAX_NEW_EFFECT");
        data.close();
        if (TextUtils.isEmpty(tax_date)) {
            return energy_charges * Double.parseDouble(getCursorValue(tax, "TAX_PER"));
        } else {
            Date present_date = null, previous_date = null, tax_date_change = null;
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
            try {
                present_date = formatter.parse(changedateformat(pres_date, "-"));
                previous_date = formatter.parse(changedateformat(prev_date, "-"));
                tax_date_change = formatter.parse(tax_date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            double total_days = Double.parseDouble(CalculateDays(prev_date, pres_date));
            double tax_days_diff_old, tax_days_diff_new, new_tax, old_tax, dbl_tax_old, dbl_tax_new;
            assert previous_date != null;
            if (previous_date.before(tax_date_change)) {
                if (present_date.after(tax_date_change)) {
                    tax_days_diff_old = Double.parseDouble(CalculateDays(prev_date, tax_date));
                    tax_days_diff_new = Double.parseDouble(CalculateDays(tax_date, pres_date));
                } else {
                    tax_days_diff_old = total_days;
                    tax_days_diff_new = 0;
                }
            } else {
                tax_days_diff_old = 0;
                tax_days_diff_new = total_days;
            }
            getSetValues.setTax_days_new(String.valueOf(tax_days_diff_new));
            getSetValues.setTax_days_old(String.valueOf(tax_days_diff_old));
            tax_days_diff_new = tax_days_diff_new / total_days;
            tax_days_diff_old = tax_days_diff_old / total_days;
            new_tax = Double.parseDouble(getCursorValue(tax, "TAX_PER"));
            String old_tax_rate = getCursorValue(tax, "TAX_PER_OLD");
            if (TextUtils.isEmpty(old_tax_rate)) {
                old_tax = 0;
            } else old_tax = Double.parseDouble(old_tax_rate);
            dbl_tax_old = energy_charges * tax_days_diff_old * old_tax;
            dbl_tax_new = energy_charges * tax_days_diff_new * new_tax;
            return dbl_tax_old + dbl_tax_new;
        }
    }
}
