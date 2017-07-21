package com.ab.bluetoothatelier;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by apprenti on 19/06/17.
 */

public class BtDeviceAdapter extends BaseAdapter {

    private List<BluetoothDevice> devices;
    private LayoutInflater inflater;

    public BtDeviceAdapter(List<BluetoothDevice> devices, LayoutInflater inflate) {
        this.devices = devices;
        this.inflater = inflate;
    }

    @Override
    public int getCount() {
        return this.devices.size();
    }

    @Override
    public Object getItem(int position) {
        return this.devices.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            convertView = this.inflater.inflate(R.layout.blue_tooth_item, parent, false);
        }

        TextView textViewName = (TextView) convertView.findViewById(R.id.textViewBtName);
        TextView textViewMac = (TextView) convertView.findViewById(R.id.textViewBtMac);


        textViewName.setText(this.devices.get(position).getName());
        textViewMac.setText(this.devices.get(position).getAddress());

        return convertView;
    }
}
