package com.example.wizard.cmpt381;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class DrawIdeaCanvasActivity extends AppCompatActivity {

/*TODO
    Add IdeaCanvasView (Has reference to the IdeaCanvas model) and controls for drawing on it
    to this activity.

 */
    IdeaCanvasView icv;
    //Uncomment for original onCreate
    //@Override
    //protected void onCreate(Bundle savedInstanceState) {
    //    super.onCreate(savedInstanceState);
    //    setContentView(R.layout.activity_draw_idea_canvas);
    //    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    //    setSupportActionBar(toolbar);
    // }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_idea_canvas);
    }


    //Uncomment for Menu
 //   @Override
 //   public boolean onCreateOptionsMenu(Menu menu) {
 //       MenuInflater inflater = getMenuInflater();
 //       inflater.inflate(R.menu.ideacanvas, menu);
 //       return true;
 //   }

}