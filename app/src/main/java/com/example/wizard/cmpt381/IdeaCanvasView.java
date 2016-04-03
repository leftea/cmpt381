package com.example.wizard.cmpt381;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Pair;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.example.wizard.cmpt381.FileUtils;
import java.util.ArrayList;


/**
 * IdeaCanvasView holds reference to the IdeaCanvas model,
 * is the drawable area for the DrawIdeaCanvasActivity.
 */
public class IdeaCanvasView extends View implements OnTouchListener {

    private static final String TAG = "IdeaCanvasView";
    private static final float TOUCH_TOLERANCE = 4;
    Context context;
    Boolean touchable;
    Boolean erase;
    private IdeaCanvas model;
    private int w;
    private int h;
    private float mX, mY;
    public IdeaCanvasView(Context c) {
        super(c);
        context=c;
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setOnTouchListener(this);
        model = new IdeaCanvas();
        touchable = false;
        erase = false;

    }
    public IdeaCanvasView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        this.context = context;
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setOnTouchListener(this);
        model = new IdeaCanvas();
        touchable = false;
        erase = false;

    }

    public IdeaCanvasView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr, 0);
        this.context=context;
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setOnTouchListener(this);
        model = new IdeaCanvas();
        touchable = false;
        erase = false;

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.w = w;
        this.h = h;
        model.setmBitmap(Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888));
        model.setmCanvas(new Canvas(model.getmBitmap()));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        assert(model.getPaths().size() == model.getPaints().size());
        for (int i = 0; i < model.getPaths().size(); i++)
            canvas.drawPath(model.getPaths().get(i), model.getPaints().get(i));

        if (erase){
        canvas.drawPath(model.getmPath(), model.getmEraserPaint());
        }
        else {
            canvas.drawPath(model.getmPath(), model.getmPaint());
        }

        canvas.drawPath(model.getCirclePath(), model.getCirclePaint());
        canvas.drawBitmap(model.getmBitmap(), 0, 0, model.getmBitmapPaint());
//        model.setmCanvas(canvas);
    }

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
        Log.d(TAG, "touch_up");
        model.getmPath().lineTo(mX, mY);
        model.getCirclePath().reset();
        // commit the path to our offscreen
        //TEST
//        model.getmCanvas().drawPath(model.getmPath(), model.getmPaint());
        if(erase) {
            model.getmCanvas().drawPath(model.getmPath(), model.getmEraserPaint());
            model.getPaths().add(model.getmPath());
            model.getPaints().add(model.getmEraserPaint());
        }
        else {
            model.getmCanvas().drawPath(model.getmPath(), model.getmPaint());
            model.getPaths().add(model.getmPath());
            model.getPaints().add(model.getmPaint());
        }
        // kill path to not double draw
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
        Log.d(TAG, "onUndo");
        model.undoPaint();
        model.setmBitmap(Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888));

        Log.d(TAG, "onUndoFinished");

        this.invalidate();
        Log.d(TAG, "onUndo invalidate called");


    }
    public void setTouchable(Boolean b) {
        touchable = b;
    }
    public Boolean isTouchable() {
        return touchable;
    }
    public IdeaCanvas getModel() {return model;}

}