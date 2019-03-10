package com.example.avira.playoff;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class shivaji extends AppCompatActivity {

    ImageButton home;
    ImageButton direction;
    ImageButton book_now;
    ImageButton call;
    private static final int request_call = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shivaji);

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
                i.setData(Uri.parse("geo:18.531832, 73.827722"));
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
        Intent i = new Intent(shivaji.this,area.class);
        startActivity(i);
    }

    public void openbooking(){
        Intent i = new Intent(shivaji.this,booking.class);
        startActivity(i);
    }

    public void makephonhecall(){
        if (ContextCompat.checkSelfPermission(shivaji.this, android.Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(shivaji.this,new String[]{Manifest.permission.CALL_PHONE},request_call);
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
                Toast.makeText(shivaji.this,"PERMISSION DENIED",Toast.LENGTH_SHORT).show();
            }
        }
    }


}
