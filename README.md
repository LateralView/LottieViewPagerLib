# LottieViewPagerLib

## Steps to add library to your project

* Download and import module
* Add Java 8 copmpatibility to your gradle file
```` 
    ....
        buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
    compileOptions {
        targetCompatibility 1.8
        sourceCompatibility 1.8
    }
    ....
````    
    **Check compile and target SDK versions are the same**

* Add the animation on the assets folder (Create it from the IDE constructor if you want)
* Add LottiePager component in the layout of your activity/fragment 
````
<lv.joaquin.lottieviewpagercomponent.LottiePager
      android:id="@+id/lottie_pager"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      app:lottieFilePath="animations/animation_test.json"
      app:lottieAnimationLength="4010">
````
* Configure steps on the activity 
````    
        LottiePager lottiePager = findViewById(R.id.lottie_pager);
        lottiePager.setUpView(new int[]{0,1000,2000,3000,4000});
````
* Run app
