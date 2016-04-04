package com.example.wizard.cmpt381.DrawingTools;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * IDrawOperation
 * <p/>
 * Interface for Draw Operations
 * A draw operation is used by a DrawingTool to act on a canvas for various maethods of drawing.
 * E.g. A paintbrush may use a MultiLineDrawOperation
 * A SingleLine may use a SingleLineDrawOperation
 */
public interface IDrawOperation {

    public void draw(Canvas aCanvas);

    public Paint getPaint();

    public void computeBounds(RectF aBoundSFCT);

    public void undo();

    public void redo();

    public void complete();
}
