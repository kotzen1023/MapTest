package com.macauto.maptest.Data;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
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
        return 0;
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


        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.viewpager_item, container,
                false);

        // Locate the TextViews in viewpager_item.xml
        textViewName = (TextView) itemView.findViewById(R.id.textView1);
        textViewCharge = (TextView) itemView.findViewById(R.id.textView2);
        textViewMaintain = (TextView) itemView.findViewById(R.id.textView3);

        // Capture position and set to the TextViews
        textViewName.setText(rank[position]);
        textViewCharge.setText(country[position]);
        textViewMaintain.setText(population[position]);

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
