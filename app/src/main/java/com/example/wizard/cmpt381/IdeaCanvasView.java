package com.example.wizard.cmpt381;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;


/**
 * IdeaCanvasView holds reference to the IdeaCanvas model,
 * is the drawable area for the DrawIdeaCanvasActivity.
 * TODO: IMPLEMENT
 */
public class IdeaCanvasView extends View implements OnTouchListener {
//    private IdeaCanvas model;  TODO: Turn stuff in here into IC Model

    private static final String TAG = "IdeaCanvasView";
    private IdeaCanvas model;
    Context context;
    Boolean touchable;

    public IdeaCanvasView(Context c) {
        super(c);
        context=c;
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setOnTouchListener(this);
        model = new IdeaCanvas();
        touchable = false;

    }
    public IdeaCanvasView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        this.context = context;
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setOnTouchListener(this);
        model = new IdeaCanvas();
        touchable = false;
    }
    public IdeaCanvasView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr, 0);
        this.context=context;
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setOnTouchListener(this);
        model = new IdeaCanvas();
        touchable = false;
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
        for (Path p : model.getPaths()){
            canvas.drawPath(p, model.getmPaint());
        }

        canvas.drawBitmap(model.getmBitmap(), 0, 0, model.getmBitmapPaint());
        canvas.drawPath(model.getmPath(), model.getmPaint());
        canvas.drawPath(model.getCirclePath(), model.getCirclePaint());
    }

    private float mX, mY;
    private static final float TOUCH_TOLERANCE = 4;
    private void touch_start(float x, float y) {
        model.getUndoPaths().clear();
            model.getmPath().reset();
        model.getmPath().moveTo(x, y);
        mX = x;
        mY = y;
    }

    private void touch_move(float x, float y) {
        Log.d(TAG, "touch_move");
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
        Log.d(TAG, "touch_up");
        model.getmPath().lineTo(mX, mY);
        model.getCirclePath().reset();
        // commit the path to our offscreen
        model.getmCanvas().drawPath(model.getmPath(), model.getmPaint());
        // kill this so we don't double draw
        model.getPaths().add(model.getmPath());

        model.setmPath(new Path());
    }

    public boolean onTouch(View arg0, MotionEvent event) {
        Log.d(TAG, "onTouch");
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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        Boolean wasHandled = false;
        if(isTouchable()) {
//            wasHandled = true;
            //handle finger painting!
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
            return true; //consumed
        }
        return false; //False, not consumed
    }

    public void onUndo() {
        if (model.undoPaint()) {
            invalidate();
        }
        else {

        }
    }

    public void onRedo() {
        if (model.redoPaint()) {
            invalidate();
        }
        else {

        }
    }

    public void setTouchable(Boolean b) {
        touchable = b;
    }

    public Boolean isTouchable() {
        return touchable;
    }
    public IdeaCanvas getModel() {return model;}

}