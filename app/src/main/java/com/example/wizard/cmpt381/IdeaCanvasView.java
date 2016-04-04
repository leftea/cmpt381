package com.example.wizard.cmpt381;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;


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
    private DrawManager fManager;
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
        touchable = false;
        erase = false;
        fManager = new DrawManager();
    }
    public IdeaCanvasView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        this.context = context;
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setOnTouchListener(this);
        touchable = false;
        erase = false;
        fManager = new DrawManager();
    }

    public IdeaCanvasView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr, 0);
        this.context=context;
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setOnTouchListener(this);
        touchable = false;
        erase = false;
        fManager = new DrawManager();
    }


    public DrawManager getCanvasManager() {
        return fManager;
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.w = w;
        this.h = h;
        fManager.setup(w, h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        fManager.draw(canvas);
    }

    public boolean onTouch(View arg0, MotionEvent event) {
        Boolean result = fManager.onTouch(arg0, event);
        //        Log.d(TAG, "onTouch");
        return result;
    }

    public void onUndo() {
        fManager.undo();
        invalidate();
        Log.d(TAG, "fManager, onUndo , invalidate called");
    }
}