package com.example.wizard.cmpt381.DrawingTools;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;

/**
 * Created by Wizard on 2016-04-03.
 */
public class TriangleOperation implements IDrawOperation {

    private Paint fPaint;
    private RectF fBounds;
    private Point fPoint1;
    private Point fPoint2;
    private Point fPoint3;
    private Path fPath;

    public TriangleOperation(Paint aPaint, RectF aBounds) {
        fPaint = aPaint;
        fBounds = aBounds;
        fPaint.setStrokeWidth(2);
        fPaint.setColor(Color.WHITE);
        fPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        fPaint.setAntiAlias(true);
        fPoint1 = new Point(0, ((int) aBounds.bottom));
        fPoint2 = new Point((int) aBounds.right, ((int) aBounds.bottom));
        fPoint3 = new Point((int) aBounds.centerX(), ((int) aBounds.top));

        fPath = new Path();
        fPath.setFillType(Path.FillType.EVEN_ODD);
        fPath.moveTo(fPoint1.x, fPoint1.y);
        fPath.lineTo(fPoint2.x, fPoint2.y);
        fPath.lineTo(fPoint3.x, fPoint3.y);
        fPath.lineTo(fPoint1.x, fPoint1.y);
        fPath.close();
    }

    @Override
    public synchronized void draw(Canvas aCanvas) {
        aCanvas.drawPath(fPath, fPaint);
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