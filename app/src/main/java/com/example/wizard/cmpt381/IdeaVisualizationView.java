package com.example.wizard.cmpt381;

import android.content.Context;
import android.graphics.Bitmap;
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
public class IdeaVisualizationView extends View implements OnTouchListener {

    private static final String TAG = "IdeaCanvasView";
    private static final float TOUCH_TOLERANCE = 4;
    private final int SQUARE = 0;
    private final int CIRCLE = 1;
    private final int TRIANGLE = 2;
    Context context;
    Boolean touchable;
    Boolean erase;
    private DrawManager fManager;
    private Bitmap fShapeBitMap;
    private Canvas fShapeCanvas;
    private int w;
    private int h;

    public IdeaVisualizationView(Context c) {
        super(c);
        context=c;
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setOnTouchListener(this);
        touchable = false;
        erase = false;
        fManager = new DrawManager();
    }

    public IdeaVisualizationView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        this.context = context;
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setOnTouchListener(this);
        touchable = false;
        erase = false;
        fManager = new DrawManager();
    }

    public IdeaVisualizationView(Context context, AttributeSet attrs, int defStyleAttr) {
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

    public void setupShape(int shape) {
        this.fShapeBitMap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        this.fShapeCanvas = new Canvas(fShapeBitMap);
        drawShape(shape);
    }

    //TODO: IMPLEMENT
    //draw a square 0 cirlce 1 or trirangle 2 using the bitmap/canvas fShape
    private void drawShape(int shape) {
        if (shape == SQUARE) {
        }
        if (shape == TRIANGLE) {
        }
        if (shape == CIRCLE) {
        }
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