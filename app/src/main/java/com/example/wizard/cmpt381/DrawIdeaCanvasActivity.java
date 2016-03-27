package com.example.wizard.cmpt381;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.view.View.OnTouchListener;
public class DrawIdeaCanvasActivity extends AppCompatActivity {

/*TODO
    Add IdeaCanvasView (Has reference to the IdeaCanvas model) and controls for drawing on it
    to this activity.

 */
    IdeaCanvasView icv;
    Boolean paintSelected;
    Boolean drawButtonSelected;
    Boolean eraserButtonSelected;
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
        drawButtonSelected = false;
        eraserButtonSelected = false;
        icv = (IdeaCanvasView) findViewById(R.id.ideaCanvasView);
        icv.setClickable(false);
        findViewById(R.id.imageButton3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DrawIdeaCanvasActivity.this, DrawIdeaVisualizationActivity.class));
            }
        });
    }

    public void imageBrushAction(View v){
        ImageButton imageBrushBtn = (ImageButton) v;
        ImageButton eraserBtn = (ImageButton) findViewById(R.id.imageButton5);

        if (!(isDrawButtonSelected() || isEraserButtonSelected())) {
            icv.setClickable(true);

            imageBrushBtn.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);

            setDrawButtonSelected(!isDrawButtonSelected());
            icv.setTouchable(isDrawButtonSelected());

            imageBrushBtn.invalidate();
        } else {
            icv.setClickable(false);

            eraserBtn.clearColorFilter();
            imageBrushBtn.clearColorFilter();

            setDrawButtonSelected(!isDrawButtonSelected());
            icv.setTouchable(isDrawButtonSelected());

            imageBrushBtn.invalidate();
            eraserBtn.invalidate();
        }
    }

    public void eraserAction(View v){
        ImageButton eraserBtn = (ImageButton) v;
        ImageButton imageBrushBtn = (ImageButton) findViewById(R.id.imageButton2);
        if (!(isDrawButtonSelected() || isEraserButtonSelected())) {
            icv.setClickable(true);

            eraserBtn.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);

            setEraserButtonSelected(!isEraserButtonSelected());

            icv.setTouchable(isEraserButtonSelected());

            eraserBtn.invalidate();
        } else {
            icv.setClickable(false);

            eraserBtn.clearColorFilter();
            imageBrushBtn.clearColorFilter();

            setEraserButtonSelected(!isEraserButtonSelected());

            icv.setTouchable(isEraserButtonSelected());
            icv.setTouchable(isDrawButtonSelected() || isEraserButtonSelected());

            imageBrushBtn.invalidate();
            eraserBtn.invalidate();
        }
    }

    public void undoAction(View v){
        ImageButton view = (ImageButton) v;
        icv.undoPaint();
    }

    //Uncomment for Menu
 //   @Override
 //   public boolean onCreateOptionsMenu(Menu menu) {
 //       MenuInflater inflater = getMenuInflater();
 //       inflater.inflate(R.menu.ideacanvas, menu);
 //       return true;
 //   }

    public void setDrawButtonSelected(Boolean b) {
        this.drawButtonSelected = b;
    }
    public void setEraserButtonSelected(Boolean b) { this.eraserButtonSelected = b; }
    public boolean isDrawButtonSelected() {
        return this.drawButtonSelected;
    }
    public boolean isEraserButtonSelected() {
        return this.eraserButtonSelected;
    }
}