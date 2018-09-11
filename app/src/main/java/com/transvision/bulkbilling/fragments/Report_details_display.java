package com.transvision.bulkbilling.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.transvision.bulkbilling.R;
import com.transvision.bulkbilling.ReportsActivity;
import com.transvision.bulkbilling.database.Databasehelper;

import java.util.Objects;

public class Report_details_display extends Fragment {

    View view;

    Databasehelper databasehelper;

    public Report_details_display() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_report_details_display, container, false);

        initialize();

        return view;
    }

    private void initialize() {
        databasehelper = ((ReportsActivity) Objects.requireNonNull(getActivity())).getDatabasehelper();
    }

}
