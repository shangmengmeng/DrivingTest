package app.my.mylibrary.utils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

/**
 * Created by pig on 2017/5/24.
 */

public class ScaleUtils {
    public static void getScaleAnimator(View view){
        AnimatorSet set = new AnimatorSet();
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view,"ScaleX",1.1f,1.2f,1.1f,1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view,"ScaleY",1.1f,1.2f,1.1f,1f);
        set.playTogether(scaleX,scaleY);
        set.setDuration(100);
        set.start();
    }
    public static void getScaleAnimatorSmall(View view){
        AnimatorSet set = new AnimatorSet();
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view,"ScaleX",0.9f,0.8f,0.9f,1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view,"ScaleY",0.9f,0.8f,0.9f,1f);
        set.playTogether(scaleX,scaleY);
        set.setDuration(100);
        set.start();
    }
    public static void getScaleAlphaAnimatorSmall(View view){
        AnimatorSet set = new AnimatorSet();
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view,"ScaleX",1.6f,1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view,"ScaleY",1.6f,1f);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(view,"Alpha",0.3f,1f);
        set.playTogether(scaleX,scaleY,alpha);
        set.setDuration(800);
        set.start();
    }
    //旋转
    public static void getRotationAnimator(View view){
        ObjectAnimator animator = ObjectAnimator.ofFloat(view,"rotation",0,720);
        animator.setDuration(1000);
        animator.start();
    }
}
