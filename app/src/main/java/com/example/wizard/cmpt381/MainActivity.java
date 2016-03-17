package com.example.wizard.cmpt381;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Creates our Main Activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.imageButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DrawIdeaCanvasActivity.class));
            }
        });
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

}