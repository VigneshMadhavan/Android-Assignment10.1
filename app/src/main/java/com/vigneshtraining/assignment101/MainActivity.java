package com.vigneshtraining.assignment101;


import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,MyAlert.Communicator {
    private Button launchBtn;
    private MyAlert alert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        launchBtn=(Button) findViewById(R.id.launchBtn);
        launchBtn.setOnClickListener(MainActivity.this);


    }

    @Override
    public void onClick(View v) {

        alert=new MyAlert();

        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alert.show(getFragmentManager(),MyAlert.TAG);

    }

    @Override
    public void onConfirmed() {


    }
}
