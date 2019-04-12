package ru.deledzis.lab6;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import static ru.deledzis.lab6.MainActivity.mColorsList;

public class DrawingView extends View {

    public DrawingView(Context context) {
        super(context);
        init(context);
    }

    public DrawingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DrawingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public DrawingView(
            Context context,
            @Nullable AttributeSet attrs,
            int defStyleAttr,
            int defStyleRes
    ) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        mListener = (OnDrawingViewInteractionListener) context;

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);

        mRectangles = new ArrayList<>();
        mCircles = new ArrayList<>();
    }

    private static final String TAG = "DrawingView";

    private static final int MIN_SHAPE_SIZE = 50;
    private static final int MAX_SHAPE_SIZE = 200;

    private OnDrawingViewInteractionListener mListener;

    private Canvas mCanvas;
    private Paint mPaint;

    private float x;
    private float y;

    private List<Rectangle> mRectangles;
    private List<Circle> mCircles;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mCanvas = canvas;

        drawShapes();
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            performClick();

            x = event.getX();
            y = event.getY();

            int rectSize = getRandomSize(MIN_SHAPE_SIZE, MAX_SHAPE_SIZE);
            createRect(rectSize);
            createCircle(rectSize);

            mListener.onDrawingViewTouched();

            invalidate();
        }
        return false;
    }

    private void drawShapes() {
        Log.d(TAG, "Call drawShapes()");

        for (Rectangle rect : mRectangles) {
            mPaint.setColor(rect.getColor());
            mCanvas.drawRect(rect.getRect(), mPaint);
        }
        for (Circle circle : mCircles) {
            mPaint.setColor(circle.getColor());
            mCanvas.drawCircle(circle.getX(), circle.getY(), circle.getRadius(), mPaint);
        }
    }

    private void createRect(int rectSize) {
        Rect rect = new Rect();
        rect.top = (int) (y - (rectSize / 2));
        rect.bottom = (int) (y + (rectSize / 2));
        rect.right = (int) (x + (rectSize / 2));
        rect.left = (int) (x - (rectSize / 2));
        Log.d(TAG, "Rect sizes: [Top: " + rect.top + "; Bottom: " + rect.bottom +
                "; Left: " + rect.left + "; Right: " + rect.right + "]");

        mRectangles.add(new Rectangle(rect, getRandomColor()));
    }

    private void createCircle(int rectSize) {
        mPaint.setColor(getRandomColor());
        mCircles.add(new Circle(x, y, (float) getRandomSize(MIN_SHAPE_SIZE / 2, rectSize / 2), getRandomColor()));
    }

    private int getRandomColor() {
        Random random = new Random();
        Log.d(TAG, "Size: " + mColorsList.size());
        return mColorsList.get(random.nextInt(mColorsList.size()));
    }

    private int getRandomSize(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    public void clearCanvas() {
        mRectangles.clear();
        mCircles.clear();
        invalidate();
    }

    public interface OnDrawingViewInteractionListener {
        void onDrawingViewTouched();
    }
}
