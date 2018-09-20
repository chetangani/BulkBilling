package com.transvision.bulkbilling.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.transvision.bulkbilling.R;
import com.transvision.bulkbilling.ReportsActivity;
import com.transvision.bulkbilling.adapters.Report_adapter;
import com.transvision.bulkbilling.database.Bulk_Database;
import com.transvision.bulkbilling.extra.FunctionsCall;
import com.transvision.bulkbilling.values.GetSetValues;

import java.util.ArrayList;
import java.util.Objects;

public class Billed_reports_list extends Fragment {

    View view;

    Toolbar toolbar;
    AppBarLayout.LayoutParams params;

    Bulk_Database bulkDatabase;
    FunctionsCall functionsCall;

    RecyclerView status_report_view;
    ArrayList<GetSetValues> status_list;
    Report_adapter status_adapter;

    public Billed_reports_list() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_billed_reports_list, container, false);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        toolbar = view.findViewById(R.id.reports_toolbar);
        if (activity != null) {
            activity.setSupportActionBar(toolbar);
            Objects.requireNonNull(activity.getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
            activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        params = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
        params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
                | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
        ((ReportsActivity) Objects.requireNonNull(getActivity())).setActionBarTitle(getResources().getString(R.string.bill_reports));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Objects.requireNonNull(getActivity()).onBackPressed();
            }
        });

        initialize();

        setValues();

        return view;
    }

    private void initialize() {
        bulkDatabase = ((ReportsActivity) Objects.requireNonNull(getActivity())).getBulkDatabase();

        functionsCall = new FunctionsCall();

        status_report_view = view.findViewById(R.id.status_recycler_view);
        status_list = new ArrayList<>();
        status_adapter = new Report_adapter(getActivity(), status_list);
        status_report_view.setHasFixedSize(true);
        status_report_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        status_report_view.setAdapter(status_adapter);
    }

    private void setValues() {
        int slno = 0;
        status_list.clear();
        Cursor data = bulkDatabase.getbilledvalues();
        if (data.getCount() > 0) {
            while (data.moveToNext()) {
                GetSetValues getSetValues = new GetSetValues();
                slno++;
                getSetValues.setSlno(""+ slno);
                getSetValues.setBilled_account_id(functionsCall.getCursorValue(data, "CONSNO"));
                getSetValues.setBilled_pres_rdg(functionsCall.getCursorValue(data, "PRES_RDG"));
                getSetValues.setBilled_units(functionsCall.getCursorValue(data, "UNITS"));
                getSetValues.setBilled_payable(functionsCall.getCursorValue(data, "PAYABLE"));
                status_list.add(getSetValues);
                status_adapter.notifyDataSetChanged();
            }
        }
        data.close();
    }
}
