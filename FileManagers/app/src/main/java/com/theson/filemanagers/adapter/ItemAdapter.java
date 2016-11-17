package com.theson.filemanagers.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.theson.filemanagers.App;
import com.theson.filemanagers.R;
import com.theson.filemanagers.manager.FileManager;
import com.theson.filemanagers.model.Items;
import java.io.File;

public class ItemAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    public ItemAdapter() {
        inflater = LayoutInflater.from(App.getContext());
    }

    @Override
    public int getCount() {
        return FileManager.getInstance().getCounts();
    }

    @Override
    public Items getItem(int position) {
        return FileManager.getInstance().getFile(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final Holder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_listview, parent, false);
            holder = new Holder();
            holder.imgFolder = (ImageView) convertView.findViewById(R.id.imgFolder);
            holder.imgFolder1 = (ImageView) convertView.findViewById(R.id.imgFolder1);
            holder.txtFolder = (TextView) convertView.findViewById(R.id.txtFolder);
            holder.txtDate = (TextView) convertView.findViewById(R.id.txtDate);
            holder.txtSize = (TextView) convertView.findViewById(R.id.txtSize);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        Items items = FileManager.getInstance().getFile(position);
        if (items.getPath().endsWith(".jpg") || items.getPath().endsWith(".jpg") || items.getPath().endsWith(".png")
                || items.getPath().endsWith(".jpeg") || items.getPath().endsWith(".gif")) {

            holder.imgFolder1.setVisibility(View.VISIBLE);
            holder.imgFolder.setVisibility(View.GONE);

            Picasso.with(convertView.getContext()).load(new File(items.getPath())).fit().centerCrop().into(holder.imgFolder1);

        } else {
            holder.imgFolder.setVisibility(View.VISIBLE);
            holder.imgFolder1.setVisibility(View.GONE);

            holder.imgFolder.setImageResource(items.getResId());
        }

        holder.txtFolder.setText(items.getName());
        holder.txtDate.setText(items.getDate());
        holder.txtSize.setText(items.getSize());
        return convertView;
    }

    private class Holder {
        ImageView imgFolder;
        ImageView imgFolder1;
        TextView txtFolder;
        TextView txtDate;
        TextView txtSize;
    }
}
