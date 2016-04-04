package com.example.wizard.cmpt381.DrawingTools;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

/**
 * Created by Wizard on 2016-04-03.
 */
public class LineOperation implements IDrawOperation {

    private Path fPath;
    private Paint fPaint;
    private RectF fBounds;

    public LineOperation(float x, float y, Paint aPaint) {
        fPath = new Path();
        fPath.moveTo(x, y);
        fPaint = aPaint;
        fBounds = new RectF(x, y, x, y);
    }

    @Override
    public synchronized void draw(Canvas aCanvas) {
        aCanvas.drawPath(fPath, fPaint);
    }

    public synchronized void addPoint(float x, float y) {
        fPath.lineTo(x, y);
        fBounds.union(x, y);
    }

    @Override
    public Paint getPaint() {
        return fPaint;
    }

    @Override
    public synchronized void computeBounds(RectF aBoundSFCT) {
        aBoundSFCT.set(fBounds);
    }

    public synchronized void addCurve(float x1, float y1, float x2, float y2) {
        fPath.quadTo(x1, y1, x2, y2);
        fBounds.union(x2, y2);
    }

    @Override
    public void undo() {
    }

    @Override
    public void redo() {
    }

    @Override
    public void complete() {
    }
}