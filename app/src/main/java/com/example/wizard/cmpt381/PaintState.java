package com.example.wizard.cmpt381;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by Wizard on 2016-04-02.
 */

/*
 * This file heavily modified from the PaintState file in Drawchemy project - https://code.google.com/p/drawchemy/
 *
 * Copyright (c) 2014 Pilmeyer Patrick
 *
 * Drawchemy is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Drawchemy is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Drawchemy.  If not, see <http://www.gnu.org/licenses/>.
 */
public class PaintState {

    //Defaults
    private int fColor = Color.BLACK;

    private Paint.Style fStyle = Paint.Style.STROKE;
    private float fStrokeWeight = 1.5f;
    private boolean fDither = false;

    public int getColor() {
        return fColor;
    }

    public void setColor(int aColor) {
        fColor = aColor;
    }

    public Paint getPaint() {
        Paint p = new Paint();
        p.setColor(getColor());
        p.setStrokeWidth(fStrokeWeight);
        p.setStyle(fStyle);
        p.setStrokeJoin(Paint.Join.ROUND);
        p.setStrokeCap(Paint.Cap.ROUND);
        p.setAntiAlias(true);
        return p;
    }

    public Paint.Style getStyle() {
        return fStyle;
    }

    public void setStyle(Paint.Style fStyle) {
        this.fStyle = fStyle;
    }

    @SuppressWarnings("all")
    public float getStrokeWeight() {
        return fStrokeWeight;
    }

    public void setStrokeWeight(float aStrokeWeigth) {
        fStrokeWeight = aStrokeWeigth;
    }

//  private void updateColor(int aNewColor) {
//    Log.i("INFO", "update color");
//  }


}

