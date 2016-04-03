package com.example.wizard.cmpt381;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.wizard.cmpt381.DrawingTools.PaintBrushCreator;

public class DrawIdeaCanvasActivity extends AppCompatActivity {

    private static final int PAINTBRUSH = 0;
    private static final int ERASER = 1;
/*TODO
    Add IdeaCanvasView (Has reference to the IdeaCanvas model) and controls for drawing on it
    to this activity.

 */
private IdeaCanvasView icv;
    private DrawManager fManager;
    private PaintState fPaintState;
    private Context fContext;
    private Boolean drawButtonSelected;
    private Boolean eraserButtonSelected;

    //Uncomment for original onCreate
    //@Override
    //protected void onCreate(Bundle savedInstanceState) {
    //    super.onCreate(savedInstanceState);
    //    setContentView(R.layout.activity_draw_idea_canvas);
    //    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    //    setSupportActionBar(toolbar);
    // }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_draw_idea_canvas);
        fContext = this;
        drawButtonSelected = false;
        eraserButtonSelected = false;
        icv = (IdeaCanvasView) findViewById(R.id.ideaCanvasView);
        fManager = icv.getCanvasManager();
        fPaintState = fManager.getPaintState();
        final FileUtils fUtils = new FileUtils(fManager, this);

        fManager.addTool(PAINTBRUSH, new PaintBrushCreator(fManager, icv));
        //fManager.addTool(PAINTBRUSH, new PaintBrushCreator(fManager, icv));
        fManager.setCurrentTool(0);

        fUtils.load(fContext);

        icv.setClickable(false);
        findViewById(R.id.saveButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//TODO:  Save this somewhere.                fManager.getBitmap()
                fUtils.save(fContext);

                startActivity(new Intent(DrawIdeaCanvasActivity.this, DrawIdeaVisualizationActivity.class));
            }
        });
    }

    public void imageBrushAction(View v){

        ImageButton imageBrushBtn = (ImageButton) v;
        ImageButton eraserBtn = (ImageButton) findViewById(R.id.eraserButton);
        eraserBtn.getBackground().clearColorFilter();
        imageBrushBtn.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
        fManager.setCurrentTool(0);
/*
        if (!isDrawButtonSelected()) {
            icv.setClickable(true);


            setDrawButtonSelected(!isDrawButtonSelected());
            icv.setTouchable(isDrawButtonSelected());

            imageBrushBtn.invalidate();
            eraserBtn.invalidate();
        } else {

            eraserBtn.getBackground().clearColorFilter();
            imageBrushBtn.getBackground().clearColorFilter();

            setDrawButtonSelected(!isDrawButtonSelected());

            icv.setTouchable(isDrawButtonSelected());

            icv.setClickable(false);
*/
    }

    public void eraserAction(View v){
        ImageButton eraserBtn = (ImageButton) v;
        ImageButton imageBrushBtn = (ImageButton) findViewById(R.id.imageBrushButton);
        fManager.getCurrentCreator().clear();
    }

    public void undoAction(View v){

        icv.onUndo();

//        IdeaCanvasView iv = (IdeaCanvasView) findViewById(R.id.ideaCanvasView);
//        iv.onUndo();
//        iv.invalidate();
        LinearLayout ll = (LinearLayout)findViewById(R.id.linearLayout);
        ll.invalidate();
    }

    public enum Tool {
        IMAGEBRUSH,
        ERASER
    }

    //Uncomment for Menu
 //   @Override
 //   public boolean onCreateOptionsMenu(Menu menu) {
 //       MenuInflater inflater = getMenuInflater();
 //       inflater.inflate(R.menu.ideacanvas, menu);
 //       return true;
 //   }

}