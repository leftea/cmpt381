package com.example.wizard.cmpt381.DrawingTools;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * IDrawOperation
 * <p/>
 * Interface for Draw Operations
 * A draw operation is used by a DrawingTool to act on a canvas for various maethods of drawing.
 * E.g. A paintbrush with bristles may use a MultiLineDrawOperation
 * A pencil may use a SingleLineDrawOperation
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

public interface IDrawOperation {

    public void draw(Canvas aCanvas);

    public Paint getPaint();

    public void computeBounds(RectF aBoundSFCT);

    public void undo();

    public void redo();

    public void complete();
}
