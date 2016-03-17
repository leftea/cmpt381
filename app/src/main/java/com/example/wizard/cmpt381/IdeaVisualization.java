package com.example.wizard.cmpt381;

import android.graphics.Bitmap;

/**
 * IdeaVisualization: Data Model for the visualized ideas on MainView. Holds bitmaps and other necessary
 * attributes
 */
public class IdeaVisualization {
    private Bitmap mIdeaBitmap;
    private Bitmap mShapeBitmap;
    private Bitmap mIdeaShapeBitmap;
    private IdeaCanvas pIdeaCanvas;

    public IdeaVisualization(Bitmap mIdeaBitmap, Bitmap mShapeBitmap, Bitmap mIdeaShapeBitmap, IdeaCanvas pIdeaCanvas) {
        this.mIdeaBitmap = mIdeaBitmap;
        this.mShapeBitmap = mShapeBitmap;
        this.mIdeaShapeBitmap = mIdeaShapeBitmap;
        this.pIdeaCanvas = pIdeaCanvas;
    }

    public Bitmap getmIdeaBitmap() {
        return mIdeaBitmap;
    }

    public void setmIdeaBitmap(Bitmap mIdeaBitmap) {
        this.mIdeaBitmap = mIdeaBitmap;
    }

    public Bitmap getmShapeBitmap() {
        return mShapeBitmap;
    }

    public void setmShapeBitmap(Bitmap mShapeBitmap) {
        this.mShapeBitmap = mShapeBitmap;
    }

    public Bitmap getmIdeaShapeBitmap() {
        return mIdeaShapeBitmap;
    }

    public void setmIdeaShapeBitmap(Bitmap mIdeaShapeBitmap) {
        this.mIdeaShapeBitmap = mIdeaShapeBitmap;
    }

    public IdeaCanvas getpIdeaCanvas() {
        return pIdeaCanvas;
    }

    public void setpIdeaCanvas(IdeaCanvas pIdeaCanvas) {
        this.pIdeaCanvas = pIdeaCanvas;
    }
}
