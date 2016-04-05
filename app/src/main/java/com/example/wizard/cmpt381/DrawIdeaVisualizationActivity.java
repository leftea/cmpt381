package com.example.wizard.cmpt381;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.wizard.cmpt381.DrawingTools.EraserCreator;
import com.example.wizard.cmpt381.DrawingTools.SimpleBrushCreator;

import java.util.ArrayList;

public class DrawIdeaVisualizationActivity extends AppCompatActivity {

    private static final int PAINTBRUSH = 0;
    private static final int ERASER = 1;

    private PaintState fPaintState;
    private ImageButton paletteBtn;
    private int backgroundColor;
    private int textColor;

    private final String TAG = "DrawIVActivity";
    private DrawManager fManager;
    private IdeaVisualizationView fDrawView;
    private Context fContext;
    FileUtils fUtils;
    private Bundle fExtras;
    private String idea_ID;

    private int w;
    private int h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_draw_idea_visualization);
        fExtras = getIntent().getExtras();
        if (fExtras != null) {
            idea_ID = fExtras.getString("IDEA_VIS");
            w = fExtras.getInt("SHAPE_WIDTH");
            h = fExtras.getInt("SHAPE_HEIGHT");
        }

        fDrawView = (IdeaVisualizationView) findViewById(R.id.baseIdeaView);
        fManager = fDrawView.getCanvasManager();
        fManager.setW(w);
        fManager.setH(h);
        fContext = this;
        final FileUtils fUtils = new FileUtils(fManager, this, idea_ID);
        fPaintState = fManager.getPaintState();

        fManager.addTool(PAINTBRUSH, new SimpleBrushCreator(fManager, fDrawView));
        fManager.setCurrentTool(0);
        ((ImageButton) findViewById(R.id.imageBrushButtonV)).getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);

        TypedArray array = getTheme().obtainStyledAttributes(new int[]{
                android.R.attr.colorBackground,
                android.R.attr.textColorPrimary,
        });

        backgroundColor = array.getColor(0, 0xFF00FF);
        textColor = array.getColor(1, 0xFF00FF);
        array.recycle();

        fManager.addTool(ERASER, new EraserCreator(fManager, fDrawView, backgroundColor));

        fDrawView.setClickable(false);

        addListenerOnPaletteButton();

        findViewById(R.id.saveButtonV).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fUtils.save(fContext);
                startActivity(new Intent(DrawIdeaVisualizationActivity.this, MainActivity.class));
            }
        });

        ArrayList<ItemData> list = new ArrayList<>();
        list.add(new ItemData(R.mipmap.ic_launcher_rectangle));
        list.add(new ItemData(R.mipmap.ic_launcher_circle));
        list.add(new ItemData(R.mipmap.ic_launcher_triangle));
        Spinner sp = (Spinner) findViewById(R.id.spinner);
        SpinnerAdapter adapter = new SpinnerAdapter(this,
                R.layout.spinner_layout, R.id.img, list);

        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "pos, id: " + position + "," + id);
                if (position == 0) { //Draw Rectangle border
                    fManager.setBackgroundRectangle();
                    fDrawView.invalidate();
                } else if (position == 1) { //Draw circle border
                    fManager.setBackgroundCircle();
                    fDrawView.invalidate();
                } else if (position == 2) { // Draw triangle border
                    fManager.setBackgroundTriangle();
                    fDrawView.invalidate();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        findViewById(R.id.imageBrushButtonV).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageButton imageBrushBtn = (ImageButton) v;
                imageBrushBtn.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                ImageButton eraserBtn = (ImageButton) findViewById(R.id.eraserButtonV);
                eraserBtn.getBackground().clearColorFilter();
                fManager.setCurrentTool(PAINTBRUSH);
            }
        });


        findViewById(R.id.eraserButtonV).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageButton eraserBtn = (ImageButton) v;
                eraserBtn.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                ImageButton imageBrushBtn = (ImageButton) findViewById(R.id.imageBrushButtonV);
                imageBrushBtn.getBackground().clearColorFilter();
                fManager.setCurrentTool(ERASER);
            }
        });


    }
    public void addListenerOnPaletteButton() {

        paletteBtn = (ImageButton) findViewById(R.id.paletteButtonV);

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

