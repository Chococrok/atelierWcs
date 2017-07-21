package com.ab.nfcatelier;

import android.nfc.NdefRecord;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by apprenti on 26/06/17.
 */

public class NdefRecordAdapter extends BaseAdapter {

    private List<NdefRecord> records;
    private LayoutInflater inflater;

    public NdefRecordAdapter(List<NdefRecord> records, LayoutInflater inflater) {
        this.records = records;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return this.records.size();
    }

    @Override
    public Object getItem(int i) {
        return this.records.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            view = this.inflater.inflate(R.layout.ndef_item, viewGroup, false);
        }

        TextView textViewBind = (TextView) view.findViewById(R.id.textViewBind);
        TextView textViewExtra = view.findViewById(R.id.textViewExtra);
        TextView textViewText = view.findViewById(R.id.textViewText);

        textViewExtra.setText(this.records.get(i).toMimeType());
        textViewText.setText(new String(this.records.get(i).getPayload()));
        return view;
    }
}