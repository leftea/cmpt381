package com.example.wizard.cmpt381.DrawingTools;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by Wizard on 2016-04-03.
 */
public class SquareOperation implements IDrawOperation {

    private Paint fPaint;
    private RectF fBounds;

    public SquareOperation(Paint aPaint, RectF aBounds) {
        fPaint = aPaint;
        fBounds = aBounds;
    }

    @Override
    public synchronized void draw(Canvas aCanvas) {
        aCanvas.drawRect(fBounds, fPaint);
    }

    @Override
    public Paint getPaint() {
        return fPaint;
    }

    @Override
    public synchronized void computeBounds(RectF aBoundSFCT) {
        aBoundSFCT.set(fBounds);
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