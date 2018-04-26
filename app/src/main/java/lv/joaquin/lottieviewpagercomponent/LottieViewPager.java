package lv.joaquin.lottieviewpagercomponent;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * Created by Joaquin on 3/26/18.
 */

class LottieViewPager extends ViewPager
{

    private static LottieListener mLottieListener;
    private static double[] lottieTimeArray;


    private static OnPageChangeListener onPageChangeListener = new OnPageChangeListener()
    {
        @Override
        public void onPageScrolled(int position,
                float positionOffset,
                int positionOffsetPixels)
        {

            double progress = 0;
            if (position >= 0 && position < lottieTimeArray.length)
            {
                progress = sumProgress(lottieTimeArray, position);
            } else if (position == lottieTimeArray.length - 1)
            {
                progress = 1;
            }

            mLottieListener.updateLottieAnimation(
                    progress + (getNextStep(position, lottieTimeArray) * positionOffset));
                /*
                Log.d("M - Start", "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                Log.d("M - Position", String.valueOf(position));
                Log.d("M - Position Offset", String.valueOf(positionOffset));
                Log.d("M - Base Progress", String.valueOf(progress));
                Log.d("M - Base Position", String.valueOf(mLottieTimeArray[position]));
                Log.d("M - Progress",
                        String.valueOf(progress + (mLottieTimeArray[position] * positionOffset)));
                Log.d("M - End", "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                */
        }

        @Override
        public void onPageSelected(int position)
        {

        }

        @Override
        public void onPageScrollStateChanged(int state)
        {

        }
    };

    public LottieViewPager(@NonNull Context context)
    {
        super(context);
    }

    public LottieViewPager(@NonNull Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    public void setAdapter(@Nullable PagerAdapter adapter)
    {
        if (adapter instanceof LottieViewPagerAdapter && mLottieListener != null)
        {
            LottieViewPagerAdapter pagerAdapter = (LottieViewPagerAdapter) adapter;
            super.setAdapter(adapter);
            lottieTimeArray = pagerAdapter.getLottieTimeArray();
            LottieViewPager.this.addOnPageChangeListener(onPageChangeListener);
        }
    }

    private static double sumProgress(double array[], int end)
    {
        float result = 0f;
        if (end < array.length)
        {
            for (int i = 0; i <= end; i++)
            {
                result += array[i];
            }
        }
        return result;
    }

    private static double getNextStep(int position, double[] lottieTimeArray)
    {
        return position < lottieTimeArray.length - 1 ? lottieTimeArray[position + 1] : 1;
    }

    public LottieListener setLottieProgressListener(LottieListener listener)
    {
        return mLottieListener = listener;
    }

    public interface LottieListener
    {
        void updateLottieAnimation(double progress);
    }
}
