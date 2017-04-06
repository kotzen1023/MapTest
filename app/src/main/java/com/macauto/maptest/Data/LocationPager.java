package com.macauto.maptest.Data;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.macauto.maptest.R;

import static com.macauto.maptest.MapsActivity.country;
import static com.macauto.maptest.MapsActivity.population;
import static com.macauto.maptest.MapsActivity.rank;


public class LocationPager extends PagerAdapter {
    private static final String TAG = LocationPager.class.getName();

    Context context;
    LayoutInflater inflater;

    public LocationPager(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return rank.length+2;
    }



    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // Declare Variables
        ImageView imgPic;
        TextView textViewName;
        TextView textViewCharge;
        TextView textViewMaintain;


        Log.i(TAG, "get position = "+position);

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.viewpager_item, container,
                false);

        // Locate the TextViews in viewpager_item.xml
        textViewName = (TextView) itemView.findViewById(R.id.textView1);
        textViewCharge = (TextView) itemView.findViewById(R.id.textView2);
        textViewMaintain = (TextView) itemView.findViewById(R.id.textView3);

        // Capture position and set to the TextViews

        if (position == getCount() - 1) {
            Log.d(TAG, "<last>");
            textViewName.setText(rank[0]);
            textViewCharge.setText(country[0]);
            textViewMaintain.setText(population[0]);
        } else if (position == 0) {
            Log.d(TAG, "<first>");
            textViewName.setText(rank[rank.length-1]);
            textViewCharge.setText(country[country.length-1]);
            textViewMaintain.setText(population[population.length-1]);
        } else {
            Log.d(TAG, "<normal>");
            textViewName.setText(rank[position-1]);
            textViewCharge.setText(country[position-1]);
            textViewMaintain.setText(population[position-1]);
        }



        // Locate the ImageView in viewpager_item.xml
        //imgPic = (ImageView) itemView.findViewById(R.id.flag);
        // Capture position and set to the ImageView
        //imgPic.setImageResource(flag[position]);

        // Add viewpager_item.xml to ViewPager
        ((ViewPager) container).addView(itemView);

        return itemView;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
