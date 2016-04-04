package com.example.wizard.cmpt381;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

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
                //Intent myIntent = new Intent(view.getContext(), agones.class);
                //startActivityForResult(myIntent, 0);

                AlertDialog alertDialog = new AlertDialog.Builder(fContext).create();
                alertDialog.setTitle("About");
                alertDialog.setMessage("Eyedeas is a cool drawing app. " +
                        "Tap a + button to create a drawing!");
                alertDialog.show();

            }
        });

        findViewById(R.id.redundantButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DrawIdeaCanvasActivity.class).putExtra("IDEA_ID", "test"));
            }
        });

        findViewById(R.id.idea1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startIdeaByID("idea1");
            }
        });
        findViewById(R.id.idea2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startIdeaByID("idea2");
            }
        });
        findViewById(R.id.idea3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startIdeaByID("idea3");
            }
        });
        findViewById(R.id.idea4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startIdeaByID("idea4");
            }
        });
        findViewById(R.id.idea5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startIdeaByID("idea5");
            }
        });

        findViewById(R.id.idea6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startIdeaByID("idea6");
            }
        });

    }
    public void startIdeaByID(String id) {
        Intent intent = new Intent(MainActivity.this, DrawIdeaCanvasActivity.class);
        intent.putExtra("IDEA_ID", id);
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

