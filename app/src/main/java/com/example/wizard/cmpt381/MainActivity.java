package com.example.wizard.cmpt381;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Creates our Main Activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user clicks the Send button */
    public void addIdeaVisualization(View view) {
     //create an Intent to start an activity called DisplayMessageActivity
        Intent intent = new Intent(this, DrawIdeaVisualizationActivity.class);
        startActivity(intent);
    }

}
