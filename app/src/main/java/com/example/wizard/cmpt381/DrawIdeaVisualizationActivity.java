package com.example.wizard.cmpt381;

import android.content.Intent;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_draw_idea_visualization);
        fDrawView = (IdeaVisualizationView) findViewById(R.id.baseIdeaView);
        fManager = fDrawView.getCanvasManager();

        findViewById(R.id.saveButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                } else if (position == 1) { //Draw circle border

                } else if (position == 2) { // Draw triangle border

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //       setSupportActionBar(toolbar);
/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/
    }

}
