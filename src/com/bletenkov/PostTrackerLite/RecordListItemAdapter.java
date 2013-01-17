package com.bletenkov.PostTrackerLite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RecordListItemAdapter extends ArrayAdapter {

    private ArrayList<TrackNumberInfo> listItems;
    private Context context;

    public RecordListItemAdapter(Context context, int textViewResourceId, ArrayList listItems){
        super(context, textViewResourceId, listItems);
        this.context = context;
        this.listItems = listItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View v = convertView;
        if(v == null){
            LayoutInflater li = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.record, null);
        }
        TrackNumberInfo item = (TrackNumberInfo)listItems.get(position);
        if(item != null){

            //id
            TextView id = (TextView) v.findViewById(R.id.record_id);
            if(id != null){
                id.setText(item.getId());
            }
            //tracknumber
            TextView track = (TextView)v.findViewById(R.id.record_tracknumber);
            if(track != null){
                track.setText(item.getTrackNumber());
            }

            //comment
            TextView comment = (TextView)v.findViewById(R.id.record_comment);
            if(comment != null){
                comment.setText(item.getComment());
            }

            //outcountry
            TextView outc = (TextView)v.findViewById(R.id.record_out_country);
            if(outc != null){
                outc.setText(item.getOutCountry());
            }

            //incountry
            TextView inc = (TextView)v.findViewById(R.id.record_in_country);
            if(inc != null){
                inc.setText(item.getInCountry());
            }

            //diff dates
            TextView days = (TextView)v.findViewById(R.id.record_days_in_way);
            if(days != null){
                days.setText(item.getDatesBetween());
            }
        }
        return v;
    }
}
