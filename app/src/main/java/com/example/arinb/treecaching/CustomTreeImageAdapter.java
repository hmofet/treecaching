package com.example.arinb.treecaching;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by arinb on 2016-11-13.
 */

public class CustomTreeImageAdapter extends PagerAdapter {

    private  Context context;
    private ArrayList<String> images = new ArrayList<>();
    private ArrayList<String> imgDescs = new ArrayList<>();


    public CustomTreeImageAdapter(Context context, ArrayList<String> images, ArrayList<String> imgDescs) {
        this.images = images;
        this.imgDescs = imgDescs;
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // TODO Auto-generated method stub
        ((ViewPager) container).removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((View) object);
    }




    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.tree_image_item,container, false);
        ImageView img = (ImageView) view.findViewById(R.id.treeImageView);
        TextView txt = (TextView) view.findViewById(R.id.imageDescriptionText);
        Picasso.with(context)
                .load(images.get(position))
                .fit()
                .centerInside()
                .into(img);

        txt.setText(imgDescs.get(position));


        ((ViewPager) container).addView(view);
        return view;
    }
}


