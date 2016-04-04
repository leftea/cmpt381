package com.example.wizard.cmpt381;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import java.util.ArrayList;

/**
 * IdeaCanvas data model
 * holds information about the fully featured canvas for the idea.
 */
public class IdeaCanvas {
    public int width;
    public int height;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Path mPath;
    private Path circlePath;
    private Paint mBitmapPaint;
    private Paint circlePaint;
    private Paint mPaint;
    private Paint mEraserPaint;
    private ArrayList<Path> paths = new ArrayList<>();
    private ArrayList<Paint> paints = new ArrayList<>();
    public IdeaCanvas() {
        mPath = initPath();
        paths.add(mPath);
        mPaint = initPaint(true, true, Color.BLACK, Paint.Style.STROKE, Paint.Join.ROUND, Paint.Cap.ROUND, 12f);
        paints.add(mPaint);
        circlePath = initPath();
        mBitmapPaint = initPaint(true);
        circlePaint = initPaint(false, true, Color.BLUE, Paint.Style.STROKE, Paint.Join.MITER, 4f);
//            circlePaint.setAntiAlias(true);
//            circlePaint.setColor(Color.BLUE);
//            circlePaint.setStyle(Paint.Style.STROKE);
//            circlePaint.setStrokeJoin(Paint.Join.MITER);
//            circlePaint.setStrokeWidth(4f);

        mEraserPaint = initPaint(true, true, Color.WHITE, Paint.Style.STROKE, Paint.Join.ROUND, Paint.Cap.ROUND, 12f);
//            mPaint = new Paint();
//            mPaint.setAntiAlias(true);
//            mPaint.setDither(true);
//            mPaint.setColor(Color.BLACK);
//            mPaint.setStyle(Paint.Style.STROKE);
//            mPaint.setStrokeJoin(Paint.Join.ROUND);
//            mPaint.setStrokeCap(Paint.Cap.ROUND);
//            mPaint.setStrokeWidth(12);

    }

    public Path initPath() {
         return new Path();
    }

    public Paint initPaint(Boolean dither) {
        if (dither)
            return new Paint(Paint.DITHER_FLAG);
        else
            return new Paint();
    }

    public Paint initPaint(Boolean dither, Boolean antiAlias, int color, Paint.Style style,
                           Paint.Join strokeJoin, Paint.Cap strokeCap, Float strokeWidth) {
        Paint p = initPaint(dither,antiAlias,color,style,strokeJoin,strokeWidth);
        if (strokeCap != null)
            p.setStrokeCap(strokeCap);
        return p;
    }

    public Paint initPaint(Boolean dither, Boolean antiAlias,int color,Paint.Style style,
                           Paint.Join strokeJoin, Float strokeWidth) {
        Paint p = new Paint();
        p.setDither(dither);
        p.setAntiAlias(antiAlias);
        p.setColor(color);
        if (style != null)
            p.setStyle(style);
        if (strokeJoin != null)
            p.setStrokeJoin(strokeJoin);
        p.setStrokeWidth(strokeWidth);
        return p;
    }




    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Bitmap getmBitmap() {
        return mBitmap;
    }

    public void setmBitmap(Bitmap mBitmap) {
        this.mBitmap = mBitmap;
    }

    public Canvas getmCanvas() {
        return mCanvas;
    }

    public void setmCanvas(Canvas mCanvas) {
        this.mCanvas = mCanvas;
    }

    public Path getmPath() {
        return mPath;
    }

    public void setmPath(Path mPath) {
        this.mPath = mPath;
    }

    public Paint getmBitmapPaint() {
        return mBitmapPaint;
    }

    public void setmBitmapPaint(Paint mBitmapPaint) {
        this.mBitmapPaint = mBitmapPaint;
    }

    public Paint getCirclePaint() {
        return circlePaint;
    }

    public void setCirclePaint(Paint circlePaint) {
        this.circlePaint = circlePaint;
    }

    public Path getCirclePath() {
        return circlePath;
    }

    public void setCirclePath(Path circlePath) {
        this.circlePath = circlePath;
    }

    public Paint getmPaint() {
        return mPaint;
    }
    public Paint getmEraserPaint() {
        return mEraserPaint;
    }

    public void setmPaint(Paint mPaint) {
        this.mPaint = mPaint;
    }

    public ArrayList<Path> getPaths() { return paths; }
    public void setPaths(ArrayList<Path> paths) {
        this.paths = paths;
    }

    public boolean canUndoPaint() {
        return (paths.size() > 0) && (paints.size() > 0);
    }
    public void undoPaint(){
        if (canUndoPaint()) {
            paths.remove(paths.size() - 1);
            paints.remove(paints.size() - 1);
        }
    //toast the user
    }
    public ArrayList<Paint> getPaints() {
        return this.paints;
    }
}
