package com.example.wizard.cmpt381;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * IdeaCanvasView holds reference to the IdeaCanvas model,
 * is the drawable area for the DrawIdeaCanvasActivity.
 * TODO: IMPLEMENT
 */
public class IdeaCanvasView extends View {
//    private IdeaCanvas model;  TODO: Turn stuff in here into IC Model

    private IdeaCanvas model;
    public int width;
    public  int height;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Path mPath;
    private Paint mBitmapPaint;
    Context context;
    private Paint circlePaint;
    private Path circlePath;
    private Paint mPaint;

    public IdeaCanvasView(Context c) {
        super(c);
        context=c;
        model = new IdeaCanvas();
    }
    public IdeaCanvasView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        this.context = context;
        model = new IdeaCanvas();
    }
    public IdeaCanvasView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr, 0);
        this.context=context;
        model = new IdeaCanvas();
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        model.setmBitmap(Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888));
        model.setmCanvas(new Canvas(model.getmBitmap()));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
            canvas.drawBitmap(model.getmBitmap(), 0, 0, model.getmBitmapPaint());
            canvas.drawPath(model.getmPath(), model.getmPaint());
            canvas.drawPath(model.getCirclePath(), model.getCirclePaint());
    }

    private float mX, mY;
    private static final float TOUCH_TOLERANCE = 4;

    private void touch_start(float x, float y) {
        model.getmPath().reset();
        model.getmPath().moveTo(x, y);
        mX = x;
        mY = y;
    }

    private void touch_move(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            model.getmPath().quadTo(mX, mY, (x + mX)/2, (y + mY)/2);
            mX = x;
            mY = y;

            model.getCirclePath().reset();
            model.getCirclePath().addCircle(mX, mY, 30, Path.Direction.CW);
        }
    }

    private void touch_up() {
        model.getmPath().lineTo(mX, mY);
        model.getCirclePath().reset();
        // commit the path to our offscreen
        model.getmCanvas().drawPath(model.getmPath(),  model.getmPaint());
        // kill this so we don't double draw
        model.getmPath().reset();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touch_start(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                touch_move(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touch_up();
                invalidate();
                break;
        }
        return true;
    }
}