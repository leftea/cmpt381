package com.example.wizard.cmpt381;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.ArrayList;

public class DrawIdeaVisualizationActivity extends AppCompatActivity {


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

        findViewById(R.id.saveButton).setOnClickListener(new View.OnClickListener() {
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
  }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Runtime.getRuntime().gc();
    }

}
