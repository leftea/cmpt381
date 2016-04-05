package com.example.wizard.cmpt381;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    private Context fContext;
    private DrawManager fManager = new DrawManager(true);
    private GridLayout fGridLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Creates our Main Activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fGridLayout = (GridLayout)findViewById(R.id.ideaGridLayout);

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


    @Override
    protected void onResume()
    {
        super.onResume();

        initButtons();
        fGridLayout.invalidate();

    }



    public String getVisualizationID(String id) {
        return id+"_vis";
    }

    private void initButtons() {
        ImageButton idea1Btn = (ImageButton) findViewById(R.id.idea1);
        ImageButton idea2Btn = (ImageButton) findViewById(R.id.idea2);
        ImageButton idea3Btn = (ImageButton) findViewById(R.id.idea3);
        ImageButton idea4Btn = (ImageButton) findViewById(R.id.idea4);
        ImageButton idea5Btn = (ImageButton) findViewById(R.id.idea5);
        ImageButton idea6Btn = (ImageButton) findViewById(R.id.idea6);

        for (File f : getFilesDir().listFiles()) {
            if(f.getName().equals("idea1_VIS.png")) {
                try {
                    Bitmap bitmap;
                    bitmap = BitmapFactory.decodeStream(new FileInputStream(f));
                    if (bitmap != null){
                    idea1Btn.setScaleType(ImageButton.ScaleType.FIT_CENTER);
                    idea1Btn.setImageDrawable(new BitmapDrawable(bitmap));
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }

            if(f.getName().equals("idea2_VIS.png")) {
                Bitmap bitmap;
                try {
                    bitmap = BitmapFactory.decodeStream(new FileInputStream(f));
                    if (bitmap != null){
                        idea2Btn.setScaleType(ImageButton.ScaleType.FIT_CENTER);
                        idea2Btn.setImageDrawable(new BitmapDrawable(bitmap));
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }

            if(f.getName().equals("idea3_VIS.png")) {
                Bitmap bitmap;
                try {
                    bitmap = BitmapFactory.decodeStream(new FileInputStream(f));
                    if (bitmap != null){
                        idea3Btn.setScaleType(ImageButton.ScaleType.FIT_CENTER);
                        idea3Btn.setImageDrawable(new BitmapDrawable(bitmap));
                    }                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }

            if(f.getName().equals("idea4_VIS.png.png")) {
                Bitmap bitmap;
                try {
                    bitmap = BitmapFactory.decodeStream(new FileInputStream(f));
                    if (bitmap != null){
                        idea4Btn.setScaleType(ImageButton.ScaleType.FIT_CENTER);
                        idea4Btn.setImageDrawable(new BitmapDrawable(bitmap));
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }

            if(f.getName().equals("idea5_VIS.png")) {
                Bitmap bitmap;
                try {
                    bitmap = BitmapFactory.decodeStream(new FileInputStream(f));
                    if (bitmap != null){
                        idea5Btn.setScaleType(ImageButton.ScaleType.FIT_CENTER);
                        idea5Btn.setImageDrawable(new BitmapDrawable(bitmap));
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }

            if(f.getName().equals("idea6_VIS.png")) {
                Bitmap bitmap;
                try {
                    bitmap = BitmapFactory.decodeStream(new FileInputStream(f));
                    if (bitmap != null){
                        idea6Btn.setScaleType(ImageButton.ScaleType.CENTER_CROP);
                        idea6Btn.setImageDrawable(new BitmapDrawable(bitmap));
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        }
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

