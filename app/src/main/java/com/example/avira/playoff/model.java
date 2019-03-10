package com.example.avira.playoff;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class model extends AppCompatActivity {


    ImageButton home;
    ImageButton direction;
    ImageButton book_now;
    ImageButton call;
    private static final int request_call = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model);

        //make call
        call = (ImageButton) findViewById(R.id.call);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makephonhecall();

            }
        });

        //home button
        home = (ImageButton)findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gohome();
            }
        });

        //google map
        direction = (ImageButton)findViewById(R.id.direction);
        direction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setAction(Intent.ACTION_VIEW);
                i.setData(Uri.parse("geo:18.528916, 73.838441"));
                startActivity(i);
            }
        });

        //book now
        book_now = (ImageButton)findViewById(R.id.book_now);
        book_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openbooking();
            }
        });


    }


    public void gohome(){
        Intent i = new Intent(model.this,area.class);
        startActivity(i);
    }

    public void openbooking(){
        Intent i = new Intent(model.this,booking.class);
        startActivity(i);
    }

    public void makephonhecall(){
        if (ContextCompat.checkSelfPermission(model.this, android.Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(model.this,new String[]{Manifest.permission.CALL_PHONE},request_call);
        }
        else {
            String dial = "tel:9756209394";
            startActivity(new Intent(Intent.ACTION_CALL,Uri.parse(dial)));
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == request_call){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                makephonhecall();
            }
            else
            {
                Toast.makeText(model.this,"PERMISSION DENIED",Toast.LENGTH_SHORT).show();
            }
        }
    }




}
