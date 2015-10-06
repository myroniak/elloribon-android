package com.google.elloribon.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.elloribon.R;

import java.util.List;

/**
 * Created by Roman on 06.10.2015.
 */
public class DataAdapter extends BaseAdapter {

    private List<Data> items;
    private Context context;

    public DataAdapter(Context context, List<Data> items) {
        super();
        this.items = items;
        this.context = context;
    }

    static class ViewHolder {
        TextView titleClip, titleArtist, countViews;
        ImageView imageView;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Data getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Data data = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.list_item_row, null);
            viewHolder = new ViewHolder();
            viewHolder.titleClip = (TextView) convertView.findViewById(R.id.titleClip);
            viewHolder.titleArtist = (TextView) convertView.findViewById(R.id.titleArtist);
            viewHolder.countViews = (TextView) convertView.findViewById(R.id.countViews);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.titleClip.setText(data.getTitleClip());
        viewHolder.titleArtist.setText(data.getTitleArtist());
        viewHolder.countViews.setText(data.getCountViews());
        viewHolder.imageView.setImageResource(data.getImage());


        return convertView;
    }
}
