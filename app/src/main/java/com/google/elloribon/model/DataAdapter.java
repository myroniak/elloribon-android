package com.google.elloribon.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.elloribon.R;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

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

    public static class ViewHolder {
        TextView titleClip, titleArtist, countViews;
        public ImageView imageView;
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
    public boolean isEnabled(int position) {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Data data = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.list_item_row, null);

            TextView titleClip = (TextView) convertView.findViewById(R.id.titleClip);
            TextView titleArtist = (TextView) convertView.findViewById(R.id.titleArtist);
            TextView countViews = (TextView) convertView.findViewById(R.id.countViews);
            ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);

            viewHolder = new ViewHolder();
            viewHolder.titleClip = titleClip;
            viewHolder.titleArtist = titleArtist;
            viewHolder.countViews = countViews;
            viewHolder.imageView = imageView;
            convertView.setTag(viewHolder);

        }
        viewHolder = (ViewHolder) convertView.getTag();

        viewHolder.titleClip.setText(data.getTitleClip());
        viewHolder.titleArtist.setText(data.getTitleArtist());
        viewHolder.countViews.setText(data.getCountViews());
        UrlImageViewHelper.setUrlDrawable(viewHolder.imageView, data.getImageUrl());

        return convertView;
    }
}
