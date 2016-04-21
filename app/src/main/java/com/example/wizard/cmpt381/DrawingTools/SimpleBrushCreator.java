package com.example.wizard.cmpt381.DrawingTools;

/**
 * Created by Wizard on 2016-04-03.
 */

import android.graphics.Paint;
import android.view.View;

import com.example.wizard.cmpt381.DrawManager;

import java.util.Random;

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

public class SimpleBrushCreator extends ACreator {

    private final float simpleBrushWidth = 25f; //Todo: Turn into slider
    protected Painter fPainter;
    protected LineOperation fCurrentOperation = null;
    protected float fX;
    protected float fY;

    public SimpleBrushCreator(DrawManager aManager, View aView) {
        super(aManager, aView);
        fPainter = new Painter();
    }

    @Override
    public LineOperation startDrawingOperation(float x, float y) {

        fPainter.start(x, y);

        Paint p = getPaint();
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(simpleBrushWidth);

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

    protected class Painter {
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

}
