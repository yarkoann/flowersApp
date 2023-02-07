package com.example.testapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

class FlowerAdapter extends ArrayAdapter<Flower> {
    private final static String PHOTO_URL = "http://services.hanselandpetal.com/photos/";
    private List<Flower> mFlowers;
    private Context mContext;

    public FlowerAdapter(Context context, List<Flower> flowerNAmes) {
        super(context, R.layout.item_flower, flowerNAmes);
        this.mFlowers = flowerNAmes;
        this.mContext = context;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_flower, parent, false);

        ImageView flowerimg = (ImageView) view.findViewById(R.id.imageView);
        TextView nameTextView = (TextView) view.findViewById(R.id.flowername);
        TextView descripTextView = (TextView) view.findViewById(R.id.flowerdescrip);

        Log.d("GIT", ""+"https://services.hanselandpetal.com/photos/" + this.mFlowers.get(position).getPhoto());
        Picasso
                .with(view.getContext())
                .load("https://services.hanselandpetal.com/photos/" + this.mFlowers.get(position).getPhoto())
                .into(flowerimg);
        ;

        nameTextView.setText(this.mFlowers.get(position).getName());
        descripTextView.setText(this.mFlowers.get(position).getInstructions());

        return view;
    }

//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//        Flower flower = mFlowers.get(position);
//        holder.nameTextView.setText(flower.getName());
//
////        Picasso.with(mContext)
////                .load(PHOTO_URL + flower.getPhoto())
////                .resize(200, 150)
////                .into(holder.flowerImageView);
//
//    }
//
//    @Override
//    public int getItemCount() {
//        if (mFlowers == null) {
//            return 0;
//        }
//        return mFlowers.size();
//    }

}