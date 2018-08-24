package com.transvision.bulkbilling;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.transvision.bulkbilling.database.Databasehelper;
import com.transvision.bulkbilling.extra.FunctionsCall;
import com.transvision.bulkbilling.values.GetSet_MastCust;
import com.transvision.bulkbilling.values.GetSet_Mast_Values;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static com.transvision.bulkbilling.extra.Constants.COLUMNS_ERROR;
import static com.transvision.bulkbilling.extra.Constants.INSERT_MAST_OLD_OUT_ERROR;
import static com.transvision.bulkbilling.extra.Constants.INSERT_MAST_OUT_ERROR;
import static com.transvision.bulkbilling.extra.Constants.INSERT_SUCCESS;
import static com.transvision.bulkbilling.extra.Constants.READ_MAST_CUST_ERROR;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final int RequestPermissionCode = 1;
    private static final int DLG_INSERT_OUT_ERROR = 2;
    private static final int DLG_INSERT_OLD_OUT_ERROR = 3;
    private static final int DLG_READ_MAST_ERROR = 4;
    private static final int DLG_INSERT_SUCCESSFULLY = 5;
    private static final int DLG_PROGRESS = 6;
    private static final int DLG_RECORDS_UPDATE = 7;
    private static final int DLG_COLUMNS_ERROR = 8;

    Button start_btn, bill_reports_btn;
    TextView tv_count, tv_to_bill, tv_completed, tv_records;
    GetSet_MastCust getSetMastCust;
    GetSet_Mast_Values getSetMastValues;
    Databasehelper databasehelper;
    FunctionsCall functionsCall;
    AlertDialog progress_dialog;

    public static int read_count=1, max_count=0;

    private Handler handler = new Handler(new Handler.Callback() {
        @SuppressLint("DefaultLocale")
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case INSERT_SUCCESS:
                    tv_to_bill.setText(String.format("%d", (max_count - read_count)));
                    tv_completed.setText(String.format("%d", read_count));
                    read_count++;
                    if (read_count <= max_count) {
                        databasehelper.updatebill(read_count);
                        getSetMastCust = new GetSet_MastCust();
                        update_count(max_count, read_count);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                getSetMastValues.getvalues(getSetMastCust, databasehelper);
                            }
                        }, 200);
                    } else {
                        progress_dialog.dismiss();
                        start_btn.setEnabled(false);
                        showdialog(DLG_INSERT_SUCCESSFULLY);
                    }
                    break;

                case INSERT_MAST_OUT_ERROR:
                    progress_dialog.dismiss();
                    showdialog(DLG_INSERT_OUT_ERROR);
                    break;

                case INSERT_MAST_OLD_OUT_ERROR:
                    progress_dialog.dismiss();
                    showdialog(DLG_INSERT_OLD_OUT_ERROR);
                    break;

                case READ_MAST_CUST_ERROR:
                    progress_dialog.dismiss();
                    showdialog(DLG_READ_MAST_ERROR);
                    break;

                case COLUMNS_ERROR:
                    progress_dialog.dismiss();
                    showdialog(DLG_COLUMNS_ERROR);
                    break;
            }
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        start_btn = findViewById(R.id.start_bulk_billing_btn);
        start_btn.setOnClickListener(this);
        bill_reports_btn = findViewById(R.id.bulk_bill_reports_btn);
        bill_reports_btn.setOnClickListener(this);
        tv_to_bill = findViewById(R.id.txt_to_bill_count);
        tv_to_bill.setText("0");
        tv_completed = findViewById(R.id.txt_completed_count);
        tv_completed.setText("0");
        tv_records = findViewById(R.id.txt_total_records);
        tv_records.setText("0");
        getSetMastValues = new GetSet_Mast_Values(handler);
        getSetMastCust = new GetSet_MastCust();
        functionsCall = new FunctionsCall();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                checkPermissionsMandAbove();
            }
        }, 500);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_bulk_billing_btn:
                showprogress(DLG_PROGRESS);
                getSetMastValues.getvalues(getSetMastCust, databasehelper);
                break;

            case R.id.bulk_bill_reports_btn:
                break;
        }
    }

    @SuppressLint("DefaultLocale")
    private void enable_btn() {
        start_btn.setEnabled(true);
        databasehelper = new Databasehelper(MainActivity.this);
        databasehelper.openDatabase();

        Cursor data = databasehelper.getData();
        max_count = data.getCount();
        data.close();
        tv_to_bill.setText(String.format("%d", max_count));
        tv_records.setText(String.format("%d", max_count));

        Cursor out_data = databasehelper.billed();
        if (out_data.getCount() > 0) {
            tv_to_bill.setText(String.format("%d", (max_count - out_data.getCount())));
            tv_completed.setText(String.format("%d", out_data.getCount()));
            read_count = out_data.getCount() + 1;
            if (out_data.getCount() == max_count || out_data.getCount() > max_count) {
                start_btn.setEnabled(false);
                showdialog(DLG_RECORDS_UPDATE);
            }
        }
        out_data.close();
    }

    @TargetApi(23)
    private void checkPermissionsMandAbove() {
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= 23) {
            if (!checkPermission()) {
                requestPermission();
            } else enable_btn();
        } else enable_btn();
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(MainActivity.this, new String[]
                {
                        WRITE_EXTERNAL_STORAGE,
                        READ_PHONE_STATE,
                        ACCESS_FINE_LOCATION
                }, RequestPermissionCode);
    }

    private boolean checkPermission() {
        int FirstPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int SecondPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), READ_PHONE_STATE);
        int ThirdPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_FINE_LOCATION);
        return FirstPermissionResult == PackageManager.PERMISSION_GRANTED &&
                SecondPermissionResult == PackageManager.PERMISSION_GRANTED &&
                ThirdPermissionResult == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case RequestPermissionCode:
                if (grantResults.length > 0) {
                    boolean ReadStoragePermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean ReadPhoneStatePermission = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean ReadLocationPermission = grantResults[2] == PackageManager.PERMISSION_GRANTED;
                    if (ReadStoragePermission && ReadPhoneStatePermission && ReadLocationPermission)
                        enable_btn();
                    else checkPermissionsMandAbove();
                }
                break;
        }
    }

    @SuppressLint("SetTextI18n")
    private void update_count(int max_count, int read_count) {
        tv_count.setText(""+read_count + " / " + ""+max_count);
    }

    private void showprogress(int id) {
        switch (id) {
            case DLG_PROGRESS:
                AlertDialog.Builder progress = new AlertDialog.Builder(this);
                progress.setTitle(getResources().getString(R.string.dlg_insertion));
                progress.setCancelable(false);
                @SuppressLint("InflateParams")
                LinearLayout dialog_layout = (LinearLayout) getLayoutInflater().inflate(R.layout.dialog_insertion_layout, null);
                TextView tv_msg = functionsCall.text_id(dialog_layout, R.id.txt_insertion_msg);
                tv_count = functionsCall.text_id(dialog_layout, R.id.txt_insertion_count);
                progress.setView(dialog_layout);
                tv_msg.setText(getResources().getString(R.string.dlg_insertion_msg));
                update_count(max_count, read_count);
                progress_dialog = progress.create();
                progress_dialog.show();
                break;
        }
    }

    @SuppressLint("SetTextI18n")
    private void showdialog(int id) {
        final AlertDialog alertDialog;
        @SuppressLint("InflateParams")
        LinearLayout dialog_layout = (LinearLayout) getLayoutInflater().inflate(R.layout.dialog_message_layout, null);
        TextView tv_msg = functionsCall.text_id(dialog_layout, R.id.dialog_message);
        Button btn_positive = functionsCall.btn_id(dialog_layout, R.id.dialog_positive_btn);
        switch (id) {
            case DLG_INSERT_OUT_ERROR:
                AlertDialog.Builder insert_out = new AlertDialog.Builder(this);
                insert_out.setTitle(getResources().getString(R.string.dlg_insert_error_title));
                insert_out.setCancelable(false);
                insert_out.setView(dialog_layout);
                tv_msg.setText(getResources().getString(R.string.dlg_insert_out_error_msg));
                alertDialog = insert_out.create();
                btn_positive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
                break;

            case DLG_INSERT_OLD_OUT_ERROR:
                AlertDialog.Builder insert_old_out = new AlertDialog.Builder(this);
                insert_old_out.setTitle(getResources().getString(R.string.dlg_insert_error_title));
                insert_old_out.setCancelable(false);
                insert_old_out.setView(dialog_layout);
                tv_msg.setText(getResources().getString(R.string.dlg_insert_old_out_error_msg));
                alertDialog = insert_old_out.create();
                btn_positive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
                break;

            case DLG_READ_MAST_ERROR:
                AlertDialog.Builder read_mast = new AlertDialog.Builder(this);
                read_mast.setTitle(getResources().getString(R.string.dlg_read_error_title));
                read_mast.setCancelable(false);
                read_mast.setView(dialog_layout);
                tv_msg.setText(getResources().getString(R.string.dlg_read_error_msg));
                alertDialog = read_mast.create();
                btn_positive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
                break;

            case DLG_INSERT_SUCCESSFULLY:
                AlertDialog.Builder insert_success = new AlertDialog.Builder(this);
                insert_success.setTitle(getResources().getString(R.string.dlg_insert_title));
                insert_success.setCancelable(false);
                insert_success.setView(dialog_layout);
                tv_msg.setText(getResources().getString(R.string.dlg_insert_success));
                alertDialog = insert_success.create();
                btn_positive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
                break;

            case DLG_RECORDS_UPDATE:
                AlertDialog.Builder records_update = new AlertDialog.Builder(this);
                records_update.setTitle(getResources().getString(R.string.dlg_records_update_title));
                records_update.setCancelable(false);
                records_update.setView(dialog_layout);
                tv_msg.setText(getResources().getString(R.string.dlg_records_update_msg));
                alertDialog = records_update.create();
                btn_positive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
                break;

            case DLG_COLUMNS_ERROR:
                AlertDialog.Builder column_error = new AlertDialog.Builder(this);
                column_error.setTitle(getResources().getString(R.string.column_error_title));
                column_error.setCancelable(false);
                column_error.setView(dialog_layout);
                tv_msg.setText(getSetMastCust.getColumn_name() + " " + getResources().getString(R.string.column_error_msg));
                alertDialog = column_error.create();
                btn_positive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        databasehelper.close();
    }
}
