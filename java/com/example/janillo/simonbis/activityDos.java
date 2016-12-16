package com.example.janillo.simonbis;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class activityDos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                //Puesto que ya tenemos una variable global para conocer los fallos
                //verificamos si hay algno
                //dependiendo del resultado mostrará un mensaje u otro.
                if(MainActivity.fallos>0){
                    Toast.makeText(activityDos.this, "Has perdido", Toast.LENGTH_SHORT).show();}
                else{Toast.makeText(activityDos.this, "Has ganado", Toast.LENGTH_SHORT).show();}
            }
        });
    }

}
