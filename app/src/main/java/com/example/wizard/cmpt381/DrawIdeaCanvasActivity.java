package com.example.wizard.cmpt381;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.wizard.cmpt381.DrawingTools.EraserCreator;
import com.example.wizard.cmpt381.DrawingTools.SimpleBrushCreator;

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
    private ImageButton paletteBtn;
    private int backgroundColor;
    private int textColor;
    private String ID; //ID of the canvas for saving/loading
    private Bundle fExtras;
    private int w;
    private int h;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.content_draw_idea_canvas);
        fContext = this;
        fExtras = getIntent().getExtras();
        if (fExtras != null) {
            ID = fExtras.getString("IDEA_ID");
            w = fExtras.getInt("SHAPE_WIDTH");
            h = fExtras.getInt("SHAPE_HEIGHT");

        }
        if (ID == null)
            ID = "test";

        icv = (IdeaCanvasView) findViewById(R.id.ideaCanvasView);
        fManager = icv.getCanvasManager();
        fPaintState = fManager.getPaintState();
        final FileUtils fUtils = new FileUtils(fManager, this, ID);

        fManager.addTool(PAINTBRUSH, new SimpleBrushCreator(fManager, icv));
        fManager.setCurrentTool(0);
        ((ImageButton) findViewById(R.id.imageBrushButton)).getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);


        TypedArray array = getTheme().obtainStyledAttributes(new int[]{
                android.R.attr.colorBackground,
                android.R.attr.textColorPrimary,
        });
        backgroundColor = array.getColor(0, 0xFF00FF);
        textColor = array.getColor(1, 0xFF00FF);
        array.recycle();

        fManager.addTool(ERASER, new EraserCreator(fManager, icv, backgroundColor));

        fUtils.load(fContext);

        icv.setClickable(false);

        addListenerOnPaletteButton();


        findViewById(R.id.saveButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//TODO:  Save this somewhere.                fManager.getBitmap()
                fUtils.save(fContext);
                Intent intent = new Intent(DrawIdeaCanvasActivity.this, DrawIdeaVisualizationActivity.class);
                intent.putExtra("IDEA_VIS", ID+"_VIS");

                intent.putExtra("SHAPE_WIDTH",w);
                intent.putExtra("SHAPE_HEIGHT",w);

                startActivity(intent);
            }
        });


        findViewById(R.id.imageBrushButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageButton imageBrushBtn = (ImageButton) v;
                imageBrushBtn.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                ImageButton eraserBtn = (ImageButton) findViewById(R.id.eraserButton);
                eraserBtn.getBackground().clearColorFilter();
                fManager.setCurrentTool(PAINTBRUSH);
            }
        });


        findViewById(R.id.eraserButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageButton eraserBtn = (ImageButton) v;
                eraserBtn.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                ImageButton imageBrushBtn = (ImageButton) findViewById(R.id.imageBrushButton);
                imageBrushBtn.getBackground().clearColorFilter();
                fManager.setCurrentTool(ERASER);
            }
        });
    }



    public void undoAction(View v){

        icv.onUndo();
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
                fManager.getPaintState().setColor(color);
            }

        });

        colorPickerDialog.show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Runtime.getRuntime().gc();
    }
}