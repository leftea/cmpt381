package com.example.wizard.cmpt381.DrawingTools;

import android.graphics.Paint;
import android.view.View;

import com.example.wizard.cmpt381.DrawManager;

/**
 * Created by Wizard on 2016-04-01.
 */

/*
 * This file is part of the Eyedeas project - https://github.com/leftea/cmpt381
 * This file is modified from a file in the Drawchemy project - https://github.com/PPilmeyer/drawchemy
 * Copyright (c) 2016 Matthew Galbraith & Simone Debrowney
 * Copyright (c) 2015 Pilmeyer Patrick
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

public abstract class ACreator {
    private final DrawManager fManager;
    private final View fView;

    public ACreator(DrawManager aManager, View aView) {
        fManager = aManager;
        fView = aView;
    }

    protected final Paint getPaint() {
        return fManager.getPaintState().getPaint();
    }

    protected final View getView() {
        return fView;
    }

    public abstract IDrawOperation startDrawingOperation(float x, float y);

    public abstract void updateDrawingOperation(float x, float y);

    public abstract void endDrawingOperation();

    // Clear the creator state (used when clearing the canvas)
    public void clear() {
    }

}
