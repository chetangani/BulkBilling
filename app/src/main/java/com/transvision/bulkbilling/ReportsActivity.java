package com.transvision.bulkbilling;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.transvision.bulkbilling.database.Databasehelper;
import com.transvision.bulkbilling.fragments.Billed_reports_list;
import com.transvision.bulkbilling.fragments.Report_details_display;

import java.util.Objects;

import static com.transvision.bulkbilling.extra.Constants.SEARCH_ID;

public class ReportsActivity extends AppCompatActivity {

    private Fragment fragment;
    FragmentManager fm;
    Databasehelper databasehelper;

    public enum Steps {
        FORM0(Billed_reports_list.class),
        FORM1(Report_details_display.class);

        private Class clazz;

        Steps(Class clazz) {
            this.clazz = clazz;
        }

        public Class getFragClass() {
            return clazz;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

        databasehelper = new Databasehelper(this);
        if (databasehelper.openDatabase())
            switchContent(Steps.FORM0);
        else finish();
    }

    public void setActionBarTitle(String title) {
        Objects.requireNonNull(getSupportActionBar()).setTitle(title);
    }

    public void switchContent(Steps currentForm) {
        try {
            fragment = (Fragment) currentForm.getFragClass().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.bulk_container, fragment, currentForm.name());
        ft.commit();
    }

    public void switchAddonContent(Steps currentForm, String value) {
        Bundle bundle = new Bundle();
        try {
            fragment = (Fragment) currentForm.getFragClass().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        bundle.putString(SEARCH_ID, value);
        fragment.setArguments(bundle);
        ft.replace(R.id.bulk_container, fragment, currentForm.name());
        ft.addToBackStack(currentForm.name());
        ft.commit();
    }

    public Databasehelper getDatabasehelper() {
        return databasehelper;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        databasehelper.close();
    }
}
