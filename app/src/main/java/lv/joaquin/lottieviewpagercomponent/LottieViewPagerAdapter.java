package lv.joaquin.lottieviewpagercomponent;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Joaquin on 3/26/18.
 */

class LottieViewPagerAdapter extends PagerAdapter
{
    private Context mContext;
    private double[] lottieTimeArray;

    public LottieViewPagerAdapter(Context context, double[] timeArray)
    {
        mContext = context;
        lottieTimeArray = timeArray;
    }

    @Override
    public int getCount()
    {
        return lottieTimeArray.length;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return "Page " + position;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position)
    {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = (ViewGroup) inflater.inflate(android.R.layout.activity_list_item,
                collection, false);
        collection.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view)
    {
        collection.removeView((View) view);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object)
    {
        return false;
    }

    public double[] getLottieTimeArray()
    {
        return lottieTimeArray;
    }
}


