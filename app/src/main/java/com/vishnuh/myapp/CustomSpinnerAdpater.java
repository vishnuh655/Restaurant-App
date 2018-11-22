package com.vishnuh.myapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomSpinnerAdpater extends ArrayAdapter<SpinnerRowItem> {

    public CustomSpinnerAdpater(Context context, ArrayList<SpinnerRowItem> rowItem) {
        super(context, 0, rowItem);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent){
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.city_selector, parent, false
            );
        }

        ImageView imageView = convertView.findViewById(R.id.selectorImage);
        TextView textView = convertView.findViewById(R.id.selectorText);

        SpinnerRowItem currentItem = getItem(position);
//        Glide.with(get).asBitmap().load(mImages.get(i)).into(viewHolder.image);
        Glide.with(convertView).asBitmap().load(currentItem.getImage()).into(imageView);
//        imageView.setImageResource(currentItem.getImage());
        textView.setText(currentItem.getCity());

        return convertView;
    }
}
