package com.example.wizard.cmpt381;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class DrawIdeaCanvasActivity extends AppCompatActivity {

/*TODO
    Add IdeaCanvasView (Has reference to the IdeaCanvas model) and controls for drawing on it
    to this activity.

 */
private IdeaCanvasView icv;
    private Boolean drawButtonSelected;
    private Boolean eraserButtonSelected;
    private ImageButton paletteBtn;
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
        findViewById(R.id.saveButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DrawIdeaCanvasActivity.this, DrawIdeaVisualizationActivity.class));
            }
        });
        addListenerOnPaletteButton();
    }

    public void imageBrushAction(View v){
        ImageButton imageBrushBtn = (ImageButton) v;
        ImageButton eraserBtn = (ImageButton) findViewById(R.id.eraserButton);


        if (!isDrawButtonSelected()) {
            icv.setClickable(true);

            eraserBtn.getBackground().clearColorFilter();
            imageBrushBtn.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);

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

        }
    }

    public void eraserAction(View v){
        ImageButton eraserBtn = (ImageButton) v;
        ImageButton imageBrushBtn = (ImageButton) findViewById(R.id.imageBrushButton);
        if (!isEraserButtonSelected()) {
            icv.setClickable(true);

            eraserBtn.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
            imageBrushBtn.getBackground().clearColorFilter();
            setEraserButtonSelected(!isEraserButtonSelected());
            icv.setTouchable(isEraserButtonSelected());

            imageBrushBtn.invalidate();
            eraserBtn.invalidate();
            icv.erase =true;
        } else {

            eraserBtn.getBackground().clearColorFilter();
            imageBrushBtn.getBackground().clearColorFilter();

            setEraserButtonSelected(!isEraserButtonSelected());
            icv.setTouchable(isEraserButtonSelected());

            imageBrushBtn.invalidate();
            icv.erase = false;
            eraserBtn.invalidate();
            icv.setClickable(false);
        }
    }

    public void undoAction(View v){

        IdeaCanvasView iv = (IdeaCanvasView) findViewById(R.id.ideaCanvasView);
        iv.onUndo();
        iv.invalidate();
        LinearLayout ll = (LinearLayout)findViewById(R.id.linearLayout);
        ll.invalidate();
    }

    public void addListenerOnPaletteButton() {

        paletteBtn = (ImageButton) findViewById(R.id.paletteButton);

        paletteBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                showColorPickerDialog();
            }

        });

    }

    private void showColorPickerDialog() {

        int initialColor = Color.BLACK;

        ColorPickerDialog colorPickerDialog = new ColorPickerDialog(this, initialColor, new ColorPickerDialog.OnColorSelectedListener() {

            public void onColorSelected(int color) {
                //this is where you want to change the paintbrush color
            }

        });

        colorPickerDialog.show();
    }

    //Uncomment for Menu
 //   @Override
 //   public boolean onCreateOptionsMenu(Menu menu) {
 //       MenuInflater inflater = getMenuInflater();
 //       inflater.inflate(R.menu.ideacanvas, menu);
 //       return true;
 //   }

    private void setDrawButtonSelected(Boolean b) {
        this.drawButtonSelected = b;
    }
    private void setEraserButtonSelected(Boolean b) { this.eraserButtonSelected = b; }
    private boolean isDrawButtonSelected() {
        return this.drawButtonSelected;
    }
    private boolean isEraserButtonSelected() {
        return this.eraserButtonSelected;
    }
}