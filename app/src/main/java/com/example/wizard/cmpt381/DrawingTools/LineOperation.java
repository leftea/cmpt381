package com.example.wizard.cmpt381.DrawingTools;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

/**
 * Created by Wizard on 2016-04-03.
 */

/*
 * This file is part of the Eyedeas project - https://github.com/leftea/cmpt381
 * Copyright (c) 2016 Matthew Galbraith & Simone Debrowney
 *
 * Eyedeas is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Eyedeas is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Eyedeas.  If not, see <http://www.gnu.org/licenses/>.
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