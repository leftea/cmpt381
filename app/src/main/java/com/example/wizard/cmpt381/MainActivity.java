package com.example.wizard.cmpt381;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    private Context fContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Creates our Main Activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fContext = this;
        findViewById(R.id.helpButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(fContext).create();
                alertDialog.setTitle("About");
                alertDialog.setMessage("Eyedeas is a cool drawing app. " +
                        "Tap a + button to create a drawing!");
                alertDialog.show();
            }
        });

        findViewById(R.id.idea1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageButton view = (ImageButton) v;
                Log.d(TAG, "Width of button clicked is: " + view.getWidth() + "\n"
                        + "Height of button clicked is: " + view.getHeight());
                startIdeaByID("idea1", view.getWidth(), view.getHeight());
            }
        });
        findViewById(R.id.idea2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageButton view = (ImageButton) v;

                Log.d(TAG, "Width of button clicked is: " + view.getWidth() + "\n"
                        + "Height of button clicked is: " + view.getHeight());
                startIdeaByID("idea2", view.getWidth(), view.getHeight());
            }
        });
        findViewById(R.id.idea3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageButton view = (ImageButton) v;

                Log.d(TAG, "Width of button clicked is: " + view.getWidth() + "\n"
                        + "Height of button clicked is: " + view.getHeight());
                startIdeaByID("idea3", view.getWidth(), view.getHeight());
            }
        });
        findViewById(R.id.idea4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageButton view = (ImageButton) v;

                Log.d(TAG, "Width of button clicked is: " + view.getWidth() + "\n"
                        + "Height of button clicked is: " + view.getHeight());
                startIdeaByID("idea4", view.getWidth(), view.getHeight());
            }
        });
        findViewById(R.id.idea5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageButton view = (ImageButton) v;

                Log.d(TAG, "Width of button clicked is: " + view.getWidth() + "\n"
                        + "Height of button clicked is: " + view.getHeight());
                startIdeaByID("idea5", view.getWidth(), view.getHeight());
            }
        });

        findViewById(R.id.idea6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageButton view = (ImageButton) v;

                Log.d(TAG, "Width of button clicked is: " + view.getWidth() + "\n"
                        + "Height of button clicked is: " + view.getHeight());
                startIdeaByID("idea6", view.getWidth(), view.getHeight());
            }
        });

    }

    public void startIdeaByID(String id, int w, int h) {
        Intent intent = new Intent(MainActivity.this, DrawIdeaCanvasActivity.class);
        intent.putExtra("IDEA_ID", id);
        intent.putExtra("SHAPE_WIDTH", w);
        intent.putExtra("SHAPE_HEIGHT", h);
        startActivity(intent);
    }


//        findViewById(R.id.action_bar_activity_content).setOnClickListener(new View.OnClickListener() {
        //       @Override
        // public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, DrawIdeaVisualizationActivity.class));
        //           }
        //       });
        // }
    }

    //Uncomment to use menu
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.main, menu);
//        return true;
//    }

