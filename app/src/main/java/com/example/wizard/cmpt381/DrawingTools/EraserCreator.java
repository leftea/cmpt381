package com.example.wizard.cmpt381.DrawingTools;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;

import com.example.wizard.cmpt381.DrawManager;

import java.util.Random;

public class EraserCreator extends ACreator {

    private final float eraserWidth = 20f; //Todo: Turn into slider
    Painter fPainter;
    private LineOperation fCurrentOperation = null;
    private float fX;
    private float fY;
    private int eraserColour;

    public EraserCreator(DrawManager aManager, View aView, int eraserColour) {
        super(aManager, aView);
        fPainter = new Painter();
        this.eraserColour = eraserColour;
    }

    @Override
    public LineOperation startDrawingOperation(float x, float y) {

        fPainter.start(x, y);

        Paint p = getPaint();
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(eraserWidth);
        p.setColor(eraserColour);

        fCurrentOperation = new LineOperation(x, y, p);
        fX = x;
        fY = y;
        redraw();
        return fCurrentOperation;
    }

    @Override
    public void updateDrawingOperation(float x, float y) {
        fX = x;
        fY = y;
        fPainter.update(x, y);
        fCurrentOperation.addPoint(fPainter.X, fPainter.Y);
        redraw();
    }

    @Override
    public void endDrawingOperation() {
        fPainter.update(fX, fY);
        fCurrentOperation.addPoint(fPainter.X, fPainter.Y);

        fCurrentOperation.addCurve(
                fPainter.X, fPainter.Y, fX, fY);

        fPainter.reset();
        fCurrentOperation = null;
        redraw();
    }

    public void redraw() {
        getView().invalidate();
    }

    private class Painter {
        float X, Y;
        float dX, dY;
        float fEase;

        public Painter() {
            reset();
        }

        public void reset() {
            dX = 0;
            dY = 0;
            fEase = new Random().nextFloat() * .2f + .2f;
        }

        public void start(float x, float y) {
            X = x;
            Y = y;
        }

        public void update(float x, float y) {
            float DIV = 0.85f;
            dX = (dX + (X - x) * DIV) * fEase;
            X = X - (dX);
            dY = (dY + (Y - y) * DIV) * fEase;
            Y = Y - (dY);
        }
    }

    class LineOperation implements IDrawOperation {

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
}
