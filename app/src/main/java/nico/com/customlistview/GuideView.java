package nico.com.customlistview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/1.
 */
public class GuideView extends View {

    List<String> mDataList = new ArrayList<>();

    private int mViewHeight;

    private int mViewWidth;

    private int mCurrentSelect = -1;

    private OnIndexClickListener mListener;

    public GuideView(Context context) {
        super(context);
    }

    public GuideView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public GuideView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(measureRealWidthSize(widthMeasureSpec), heightMeasureSpec);

        setMeasuredDimension(measureRealWidthSize(widthMeasureSpec), measureRealHeightSize(heightMeasureSpec));

    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mDataList == null || mDataList.isEmpty()) {
            return;
        }
        canvas.drawColor(0x77000000);//背景色
        Paint pt = new Paint();
        pt.setColor(Color.WHITE);
        pt.setTextSize(60.0f);
        pt.setAntiAlias(true);//去锯齿
        for (int i = 0; i < mDataList.size(); i++) {
            int x = (int) pt.measureText(mDataList.get(i));
            int startx = (mViewWidth - x);
            canvas.drawText(mDataList.get(i), startx / 2, ((mViewHeight / mDataList.size() ) * (i + 1)), pt);
        }
    }


    public int measureRealWidthSize(int measureSpec) {

        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);


        int result = 0;

        switch (specMode) {
            case MeasureSpec.AT_MOST:
                result = mViewWidth;
                break;
            case MeasureSpec.EXACTLY:
                result = specSize;
                break;
        }

//        Log.e("caodongquan", "width result " + result);
        return result;
    }


    public int measureRealHeightSize(int measureSpec) {

        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);


        switch (specMode) {
            case MeasureSpec.AT_MOST:
                result = mViewHeight;
                break;
            case MeasureSpec.EXACTLY:
                result = specSize;
                break;
        }

        return result;
    }


    private void initView() {

        Paint pt = new Paint();

        pt.setTextSize(60.0f);


        for (String str : mDataList) {

            Rect bound = new Rect();

            pt.getTextBounds(str, 0, 1, bound);


            mViewWidth = (int) (pt.measureText(str)) + 20;


            mViewHeight += (bound.height() + 10);

        }

        invalidate();
    }


    public void setData(List<String> data) {
        mDataList = data;

        initView();
    }

    public void setOnIndexClickListener(OnIndexClickListener listener) {
        mListener = listener;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mCurrentSelect = getSelect(event);


                mListener.onIndexClick(mCurrentSelect);
                break;
            case MotionEvent.ACTION_MOVE:
                int moveSelect = getSelect(event);
                if (moveSelect != mCurrentSelect) {
                    mCurrentSelect = moveSelect;

                    mListener.onIndexClick(mCurrentSelect);
                }

                break;
            case MotionEvent.ACTION_UP:
                mListener.onIndexClick(10086);
                break;
        }

        return true;
    }


    private int getSelect(MotionEvent event) {

        int currentX = (int) event.getX();
        int currentY = (int) event.getY();


        int currentPos = -1;
        if (currentX <= mViewWidth && currentX >= 0) {
            int perheight = mViewHeight / mDataList.size();
            int subresult = currentY % perheight;
            if (subresult > 0) {
                currentPos = currentY / perheight;
            } else if (subresult == 0) {
                currentPos = currentY / perheight - 1;
            } else {
                currentPos = 0;
            }
        }
        if (currentPos == -1) {
            currentPos = 0;
        }
        return currentPos;
    }
}
