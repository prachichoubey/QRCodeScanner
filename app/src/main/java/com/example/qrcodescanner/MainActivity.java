package com.example.qrcodescanner;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    //Initialize variable
    Button btscan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign the value to variable
        btscan= findViewById(R.id.bt_scanner);
        btscan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intialize intent integration

                IntentIntegrator intentIntegrator= new IntentIntegrator(MainActivity.this);

                //set the prompt text

                intentIntegrator.setPrompt("Use volume for flash");

                //set beep
                intentIntegrator.setBeepEnabled(true);

                //locked orientation
                intentIntegrator.setOrientationLocked(true);

                //set capture activity
                intentIntegrator.setCaptureActivity(Capture.class);

                //intialize scan
                intentIntegrator.initiateScan();



            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Initialize the intent result
        IntentResult intentResult = IntentIntegrator.parseActivityResult(
                requestCode,resultCode,data
        );

        //bounding condition

        if(intentResult.getContents() != null){
            //when the result content is not null
            //initialize the alert dialog

            AlertDialog.Builder builder=new AlertDialog.Builder(
                    MainActivity.this
            );

            //set title
            builder.setTitle("Result");

            //set Message
            builder.setMessage(intentResult.getContents());

            //set positive button
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //dismiss dialog
                    dialog.dismiss();
                }
            });

            //show alert dialog

            builder.show();
        }
        else{
            //when reuslt content is null
            //display toast
            Toast.makeText(getApplicationContext(), "Nothing Scan",Toast.LENGTH_SHORT).show();
        }
    }
}
