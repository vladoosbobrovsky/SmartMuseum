package com.example.user.smartmuseum.estimote;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.smartmuseum.GalleryActivity;
import com.example.user.smartmuseum.R;
import com.example.user.smartmuseum.Search;

import java.util.ArrayList;
import java.util.List;

//
// Running into any issues? Drop us an email to: contact@estimote.com
//

public class ProximityContentAdapter extends BaseAdapter {

    private Context context;

    public ProximityContentAdapter(Context context) {
        this.context = context;
    }

    private List<ProximityContent> nearbyContent = new ArrayList<>();

    public void setNearbyContent(List<ProximityContent> nearbyContent) {
        this.nearbyContent = nearbyContent;
    }

    @Override
    public int getCount() {
        return nearbyContent.size();
    }

    @Override
    public Object getItem(int position) {
        return nearbyContent.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("CheckResult")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert inflater != null;

            convertView = inflater.inflate(R.layout.content_view, parent, false);
        }

        ProximityContent content = nearbyContent.get(position);

        TextView myName = convertView.findViewById(R.id.myName);
        ImageView creator =convertView.findViewById(R.id.beaconImage);
        Glide.with(creator).load("https://firebasestorage.googleapis.com/v0/b/myapp-1079a.appspot.com/o/iBeacon%2Fibeaconsing_in_cc_new.png?alt=media&token=a86891d8-6d5e-42ed-ba80-8a955287fb35").into(creator);
        myName.setText("Vladoos' beacon");

        convertView.setBackgroundColor(Utils.getEstimoteColor(content.getTitle()));
        return convertView;
    }
}
