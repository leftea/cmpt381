package com.example.wizard.cmpt381.DrawingTools;

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

public class EraserCreator extends ACreator {


    private int eraserColour;
    private Painter fPainter;
    private LineOperation fCurrentOperation = null;
    private float fX;
    private float fY;


    public EraserCreator(DrawManager aManager, View aView, int eraserColour) {
        super(aManager, aView);
        fPainter = new Painter();
        this.eraserColour = eraserColour;
    }

    //    @Override
    public LineOperation startDrawingOperation(float x, float y) {

        fPainter.start(x, y);

        Paint p = getPaint();
        p.setStyle(Paint.Style.STROKE);
        float eraserWidth = 400f;
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

}
