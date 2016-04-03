package com.example.wizard.cmpt381.DrawingTools;

import android.graphics.Paint;

import com.example.wizard.cmpt381.DrawManager;

/**
 * Created by Wizard on 2016-04-01.
 */
public abstract class ACreator {
    private final DrawManager fManager;

    public ACreator(DrawManager aManager) {
        fManager = aManager;
    }

    protected final Paint getPaint() {
        return fManager.getPaintState().getPaint();
    }

    public abstract IDrawOperation startDrawingOperation(float x, float y);

    public abstract void updateDrawingOperation(float x, float y);

    public abstract void endDrawingOperation();

    // Clear the creator state (used when clearing the canvas)
    public void clear() {
    }
}
