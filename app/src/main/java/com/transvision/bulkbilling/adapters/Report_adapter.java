package com.transvision.bulkbilling.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.transvision.bulkbilling.R;
import com.transvision.bulkbilling.ReportsActivity;
import com.transvision.bulkbilling.extra.FunctionsCall;
import com.transvision.bulkbilling.values.GetSetValues;

import java.util.ArrayList;

public class Report_adapter extends RecyclerView.Adapter<Report_adapter.Report_ViewHolder> {
    private Context context;
    private ArrayList<GetSetValues> arrayList;
    private FunctionsCall functionsCall = new FunctionsCall();

    public Report_adapter(Context context, ArrayList<GetSetValues> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Report_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.report_card_view, parent, false);
        return new Report_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Report_ViewHolder holder, int position) {
        GetSetValues getSetValues = arrayList.get(position);
        holder.tv_slno.setText(getSetValues.getSlno());
        holder.tv_account_id.setText(getSetValues.getBilled_account_id());
        holder.tv_pres_rdg.setText(getSetValues.getBilled_pres_rdg());
        holder.tv_units.setText(getSetValues.getBilled_units());
        holder.tv_payable.setText(getSetValues.getBilled_payable());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Report_ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_account_id, tv_pres_rdg, tv_units, tv_payable, tv_slno;

        Report_ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tv_slno = functionsCall.text_id(itemView, R.id.status_report_slno);
            tv_account_id = functionsCall.text_id(itemView, R.id.status_report_account_id);
            tv_pres_rdg = functionsCall.text_id(itemView, R.id.status_report_pres_rdg);
            tv_units = functionsCall.text_id(itemView, R.id.status_report_units);
            tv_payable = functionsCall.text_id(itemView, R.id.status_report_payable);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            GetSetValues details = arrayList.get(pos);
            ((ReportsActivity) context).switchAddonContent(ReportsActivity.Steps.FORM1, details.getBilled_account_id());
        }
    }
}
