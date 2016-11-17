package com.theson.filemanagers.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.theson.filemanagers.App;
import com.theson.filemanagers.R;
import com.theson.filemanagers.model.Menus;

import java.util.ArrayList;

public class MenusAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private ArrayList<Menus> menus;

    public MenusAdapter() {
        menus = new ArrayList<>();
        menus.add(new Menus(R.drawable.ic_stogare, "Storage"));
        menus.add(new Menus(R.drawable.ic_sd_card, "SD Card"));
        menus.add(new Menus(R.drawable.ic_folder_menu, "DICM"));
        menus.add(new Menus(R.drawable.ic_folder_menu, "Download"));
        menus.add(new Menus(R.drawable.ic_folder_menu, "Movies"));
        menus.add(new Menus(R.drawable.ic_folder_menu, "Music"));
        menus.add(new Menus(R.drawable.ic_folder_menu, "Picture"));
        menus.add(new Menus(R.drawable.ic_picture, "Images"));
        menus.add(new Menus(R.drawable.ic_video, "Videos"));
        menus.add(new Menus(R.drawable.ic_audio, "Audio"));
        menus.add(new Menus(R.drawable.ic_document, "Documents"));
        inflater = LayoutInflater.from(App.getContext());
    }

    @Override
    public int getCount() {
        return menus.size();
    }

    @Override
    public Menus getItem(int position) {
        return menus.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (position == 0) {
            convertView = inflater.inflate(R.layout.nav_header_main, parent, false);
            ImageView imgHead = (ImageView) convertView.findViewById(R.id.imgHead);
            imgHead.setImageResource(R.drawable.header);
        } else {
            convertView = inflater.inflate(R.layout.item_menu, parent, false);
            ImageView imgProfile = (ImageView) convertView.findViewById(R.id.imgProfile);
            TextView txtProfile = (TextView) convertView.findViewById(R.id.txtProfile);

            imgProfile.setImageResource(menus.get(position).getResId());
            txtProfile.setText(menus.get(position).getName());
        }
        return convertView;
    }
}
