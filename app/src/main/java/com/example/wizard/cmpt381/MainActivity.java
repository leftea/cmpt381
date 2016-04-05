package com.example.wizard.cmpt381;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    private Context fContext;
    private DrawManager fManager = new DrawManager(true);
    private GridLayout fGridLayout;

    private boolean delete = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Creates our Main Activity
        super.onCreate(savedInstanceState);
        Log.d(TAG, "ONCREATE");
        setContentView(R.layout.activity_main);
        fGridLayout = (GridLayout)findViewById(R.id.ideaGridLayout);
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
                if (!delete) {
                    ImageButton view = (ImageButton) v;
                    Log.d(TAG, "Width of button clicked is: " + view.getWidth() + "\n"
                            + "Height of button clicked is: " + view.getHeight());
                    startIdeaByID("idea1", view.getWidth(), view.getHeight());
                } else {
//Dialogue for are you sure?
                    deleteIdeaCanvas("idea1");
                    Toast.makeText(fContext, "You've deleted your eyedea", Toast.LENGTH_SHORT).show();

                }


            }
        });
        findViewById(R.id.idea2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!delete) {
                    ImageButton view = (ImageButton) v;

                    Log.d(TAG, "Width of button clicked is: " + view.getWidth() + "\n"
                            + "Height of button clicked is: " + view.getHeight());
                    startIdeaByID("idea2", view.getWidth(), view.getHeight());
                } else {
//Dialogue for are you sure?
                    deleteIdeaCanvas("idea2");
                    Toast.makeText(fContext, "You've deleted your eyedea", Toast.LENGTH_SHORT).show();

                }

            }
        });
        findViewById(R.id.idea3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!delete) {
                    ImageButton view = (ImageButton) v;

                    Log.d(TAG, "Width of button clicked is: " + view.getWidth() + "\n"
                            + "Height of button clicked is: " + view.getHeight());
                    startIdeaByID("idea3", view.getWidth(), view.getHeight());
                } else {
//Dialogue for are you sure?
                    deleteIdeaCanvas("idea3");
                    Toast.makeText(fContext, "You've deleted your eyedea", Toast.LENGTH_SHORT).show();

                }

            }
        });
        findViewById(R.id.idea4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!delete) {
                    ImageButton view = (ImageButton) v;

                    Log.d(TAG, "Width of button clicked is: " + view.getWidth() + "\n"
                            + "Height of button clicked is: " + view.getHeight());
                    startIdeaByID("idea4", view.getWidth(), view.getHeight());
                } else {
//Dialogue for are you sure?
                    deleteIdeaCanvas("idea4");
                    Toast.makeText(fContext, "You've deleted your eyedea", Toast.LENGTH_SHORT).show();

                }

            }
        });
        findViewById(R.id.idea5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!delete) {
                    ImageButton view = (ImageButton) v;

                    Log.d(TAG, "Width of button clicked is: " + view.getWidth() + "\n"
                            + "Height of button clicked is: " + view.getHeight());
                    startIdeaByID("idea5", view.getWidth(), view.getHeight());
                } else {
//Dialogue for are you sure?
                    deleteIdeaCanvas("idea5");
                    Toast.makeText(fContext, "You've deleted your eyedea", Toast.LENGTH_SHORT).show();

                }

            }
        });

        findViewById(R.id.idea6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!delete) {

                    ImageButton view = (ImageButton) v;

                    Log.d(TAG, "Width of button clicked is: " + view.getWidth() + "\n"
                            + "Height of button clicked is: " + view.getHeight());
                    startIdeaByID("idea6", view.getWidth(), view.getHeight());

                } else {
//Dialogue for are you sure?
                    deleteIdeaCanvas("idea6");
                    Toast.makeText(fContext, "You've deleted your eyedea", Toast.LENGTH_SHORT).show();

                }

            }
        });



        /*
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
        */

    }

    private void deleteIdeaCanvas(String id) {

        File dir = getFilesDir();
        File file = new File(dir, id + ".png");
        boolean deleted = file.delete();
        file = new File(dir, id + "_VIS.png");
        deleted = file.delete();
        initButtons();
        Log.d(TAG, "initbuttons");
        this.delete = false;
        ImageView v = (ImageView) findViewById(R.id.trashButton);
        v.getBackground().clearColorFilter();
    }

    @Override
    protected void onResume()
    {

        super.onResume();
        Log.d(TAG, "onResume");
        initButtons();
        findViewById(R.id.relativeLayout).invalidate();
        super.onRestart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");

        super.onStop();
        super.onStart();
        initButtons();
        findViewById(R.id.relativeLayout).invalidate();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState");
        initButtons();
        Log.d(TAG, "initbuttons");

        Log.d(TAG, savedInstanceState.keySet().toString());
    }



    private void initButtons() {
        ImageButton idea1Btn = (ImageButton) findViewById(R.id.idea1);
        ImageButton idea2Btn = (ImageButton) findViewById(R.id.idea2);
        ImageButton idea3Btn = (ImageButton) findViewById(R.id.idea3);
        ImageButton idea4Btn = (ImageButton) findViewById(R.id.idea4);
        ImageButton idea5Btn = (ImageButton) findViewById(R.id.idea5);
        ImageButton idea6Btn = (ImageButton) findViewById(R.id.idea6);

        Boolean i1 = false;
        Boolean i2 = false;
        Boolean i3 = false;
        Boolean i4 = false;
        Boolean i5 = false;
        Boolean i6 = false;

        for (File f : getFilesDir().listFiles()) {
            if (f.getName().equals("idea1_VIS.png")) {
                i1 = true;
                try {
                    Bitmap bitmap;
                    bitmap = BitmapFactory.decodeStream(new FileInputStream(f));
                    if (bitmap != null){
                        idea1Btn.setScaleType(ImageButton.ScaleType.FIT_CENTER);
                        idea1Btn.setImageDrawable(new BitmapDrawable(bitmap));
                    } else {
                        idea1Btn.setImageResource(R.mipmap.ic_launcher_add_idea);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }

            if (f.getName().equals("idea2_VIS.png")) {
                i2 = true;
                Bitmap bitmap;
                try {
                    bitmap = BitmapFactory.decodeStream(new FileInputStream(f));
                    if (bitmap != null){
                        idea2Btn.setScaleType(ImageButton.ScaleType.FIT_CENTER);
                        idea2Btn.setImageDrawable(new BitmapDrawable(bitmap));
                    } else {
                        idea2Btn.setImageResource(R.mipmap.ic_launcher_add_idea);
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }

            if (f.getName().equals("idea3_VIS.png")) {
                i3 = true;
                Bitmap bitmap;
                try {
                    bitmap = BitmapFactory.decodeStream(new FileInputStream(f));
                    if (bitmap != null) {
                        idea3Btn.setScaleType(ImageButton.ScaleType.FIT_CENTER);
                        idea3Btn.setImageDrawable(new BitmapDrawable(bitmap));
                    } else {
                        idea3Btn.setImageResource(R.mipmap.ic_launcher_add_idea);
                    }


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }

            if (f.getName().equals("idea4_VIS.png.png")) {
                i4 = true;
                Bitmap bitmap;
                try {
                    bitmap = BitmapFactory.decodeStream(new FileInputStream(f));
                    if (bitmap != null){
                        idea4Btn.setScaleType(ImageButton.ScaleType.FIT_CENTER);
                        idea4Btn.setImageDrawable(new BitmapDrawable(bitmap));
                    } else {
                        idea4Btn.setImageResource(R.mipmap.ic_launcher_add_idea);
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }

            if (f.getName().equals("idea5_VIS.png")) {
                i5 = true;
                Bitmap bitmap;
                try {
                    bitmap = BitmapFactory.decodeStream(new FileInputStream(f));
                    if (bitmap != null){
                        idea5Btn.setScaleType(ImageButton.ScaleType.FIT_CENTER);
                        idea5Btn.setImageDrawable(new BitmapDrawable(bitmap));
                    } else {
                        idea5Btn.setImageResource(R.mipmap.ic_launcher_add_idea);
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }

            if (f.getName().equals("idea6_VIS.png")) {
                i6 = true;
                Bitmap bitmap;
                try {
                    bitmap = BitmapFactory.decodeStream(new FileInputStream(f));
                    if (bitmap != null){
                        idea6Btn.setScaleType(ImageButton.ScaleType.CENTER_CROP);
                        idea6Btn.setImageDrawable(new BitmapDrawable(bitmap));
                    } else {
                        idea6Btn.setImageResource(R.mipmap.ic_launcher_add_idea);
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        }
        if (!i1)
            idea1Btn.setImageResource(R.mipmap.ic_launcher_add_idea);
        if (!i2)
            idea2Btn.setImageResource(R.mipmap.ic_launcher_add_idea);
        if (!i3)
            idea3Btn.setImageResource(R.mipmap.ic_launcher_add_idea);
        if (!i4)
            idea4Btn.setImageResource(R.mipmap.ic_launcher_add_idea);
        if (!i5)
            idea5Btn.setImageResource(R.mipmap.ic_launcher_add_idea);
        if (!i6)
            idea6Btn.setImageResource(R.mipmap.ic_launcher_add_idea);


        idea1Btn.invalidate();
        idea2Btn.invalidate();
        idea3Btn.invalidate();
        idea4Btn.invalidate();
        idea5Btn.invalidate();
        idea6Btn.invalidate();
    }



    public void startIdeaByID(String id, int w, int h) {
        Intent intent = new Intent(MainActivity.this, DrawIdeaCanvasActivity.class);
        intent.putExtra("IDEA_ID", id);
        intent.putExtra("SHAPE_WIDTH", w);
        intent.putExtra("SHAPE_HEIGHT", h);
        startActivity(intent);
    }

    public void deleteAction(View view) {
        ImageView v = (ImageView) view;
        v.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
        this.delete = true;
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

