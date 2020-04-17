package com.example.customtablayoutdemo.customView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/**
 * @author : zhiwen.yang
 * date   : 2020/4/8
 * desc   : 解决ViewPager嵌套RecycleView滑动冲突
 */
public class CustomViewPager extends ViewPager {

    public CustomViewPager(@NonNull Context context) {
        super(context);
    }

    public CustomViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private int startX, startY;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = (int) ev.getX();
                startY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int endX = (int) ev.getX();
                int endY = (int) ev.getY();
                int disX = Math.abs(endX - startX);
                int disY = Math.abs(endY - startY);
                if (disX > disY && disX >= 5) {//表示左右滑动
                    return true;
                }
            default:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item, false);
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }
}
