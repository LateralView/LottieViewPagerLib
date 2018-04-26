package lv.joaquin.lottieviewpagercomponent;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joaquin on 3/27/18.
 */

public class LottiePager extends FrameLayout implements LottieViewPager.LottieListener
{

    private View mRootView;
    private LottieViewPager mLottieViewPager;
    private LottieViewPagerAdapter mLottieViewPagerAdapter;
    private LottieAnimationView mLottieAnimationView;

    //Attributes
    String mLottieAnimationPath;
    int mLottieAnimationLength;
    int[] mLottieAnimationSteps;

    public LottiePager(@NonNull Context context)
    {
        super(context);
        init(context);
    }

    public LottiePager(@NonNull Context context,
            @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        init(context);
        //Read attributes
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LottiePager, 0,
                0);
        try
        {
            mLottieAnimationLength = typedArray.getInt(
                    R.styleable.LottiePager_lottieAnimationLength, 0);
            mLottieAnimationPath = typedArray.getString(R.styleable.LottiePager_lottieFilePath);
        } finally
        {
            typedArray.recycle();
        }
    }

    private void init(Context context)
    {
        mRootView = inflate(context, R.layout.lottie_pager, this);
        mLottieViewPager = mRootView.findViewById(R.id.viewpager);
        mLottieAnimationView = mRootView.findViewById(R.id.animation_view);
    }

    /**
     * @param animationPath   The path to the lottie animation file in assets folder
     * @param animationLength The length of the entire animation in ms
     * @param animationSteps  All the steps of the animation in ms
     */
    public void setUpView(@NonNull String animationPath, @NonNull int animationLength,
            @NonNull int[] animationSteps)
    {
        if (animationPath.equals("") || animationSteps.length == 0)
        {
            try
            {
                throw new Exception("Missing parameters");
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        mLottieAnimationPath = animationPath;
        mLottieAnimationLength = animationLength;
        mLottieAnimationSteps = animationSteps;
        double[] timeArray = getTimeArray(animationLength, animationSteps);
        //Set up pager
        mLottieViewPagerAdapter = new LottieViewPagerAdapter(mRootView.getContext(),
                timeArray);
        //Set up lottie animation view
        mLottieAnimationView.setAnimation(
                animationPath);
        // come from attrs or params
        //Set up listeners and adapter in view pager
        mLottieViewPager.setLottieProgressListener(this);
        mLottieViewPager.setAdapter(mLottieViewPagerAdapter);
    }

    /**
     * This method should be used only when the Length and Path are set as attributes of the view.
     * Otherwise the animation won't run.
     *
     * @param animationSteps All the steps of the animation in ms
     */
    public void setUpView(@NonNull int[] animationSteps)
    {
        if (mLottieAnimationPath.equals("") || animationSteps.length == 0)
        {
            try
            {
                throw new Exception("Missing parameters");
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        double[] timeArray = getTimeArray(mLottieAnimationLength, animationSteps);
        //Set up pager
        mLottieViewPagerAdapter = new LottieViewPagerAdapter(mRootView.getContext(),
                timeArray);
        //Set up lottie animation view
        mLottieAnimationView.setAnimation(
                mLottieAnimationPath);
        // come from attrs or params
        //Set up listeners and adapter in view pager
        mLottieViewPager.setLottieProgressListener(this);
        mLottieViewPager.setAdapter(mLottieViewPagerAdapter);
    }

    @Override
    public void updateLottieAnimation(double progress)
    {
        mLottieAnimationView.setProgress((float) progress);
    }

    private double[] getTimeArray(int animationLength, int[] animationSteps)
    {
        List<Double> timeList = new ArrayList<>();
        //For each step we must get the percentage of the animation and create an array with that
        for (int step : animationSteps)
        {
            double perc = (step * 100) / (double) animationLength;
            timeList.add(perc);
        }


        List<Double> percentages = new ArrayList<>();
        for (int i = 0; i < timeList.size(); i++)
        {
            double perc = timeList.get(i);
            if (i > 0)
            {
                perc -= timeList.get(i - 1);
            }
            percentages.add(perc / 100f);
        }

        double[] timeArray = percentages.stream()
                .mapToDouble(f -> f != null ? f : Double.NaN) // Or whatever default you want.
                .toArray();
        return timeArray;
    }
}
