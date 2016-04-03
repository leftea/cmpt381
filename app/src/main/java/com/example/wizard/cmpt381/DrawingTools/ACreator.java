package com.example.wizard.cmpt381.DrawingTools;

import android.graphics.Paint;
import android.view.View;

import com.example.wizard.cmpt381.DrawManager;

/**
 * Created by Wizard on 2016-04-01.
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

    public abstract PaintBrushCreator.MultiLineOperation startDrawingOperation(float x, float y);

    public abstract void updateDrawingOperation(float x, float y);

    public abstract void endDrawingOperation();

    // Clear the creator state (used when clearing the canvas)
    public void clear() {
    }

}
