package com.example.avira.playoff;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class confirmation extends AppCompatActivity {

    ImageButton home;
    TextView showuid;
    TextView showcourt;
    TextView showdate;
    TextView showtime;
    ImageView qrimage;

    private DatabaseReference g1;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        //request permission to save qr
        ActivityCompat.requestPermissions(confirmation.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);

        //db reference
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser users = mAuth.getCurrentUser();
        String uid = users.getUid();

        //qr code generation
        qrimage = (ImageView)findViewById(R.id.qrimage);

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(uid, BarcodeFormat.QR_CODE,100,100);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            qrimage.setImageBitmap(bitmap);


            //saving QR

            //Bitmap image = BitmapFactory.decodeResource(getResources(),R.drawable.a);

            File path = Environment.getExternalStorageDirectory();
            //File path = Environment.getRootDirectory();
            //CREATE FOLDER
            File dir = new File(path + "//Playoff");
            dir.mkdirs();

            File file = new File(dir,"Booking QR.png");

            OutputStream out;
            try {
                out = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG,100,out);
                out.flush();
                out.close();
                Toast.makeText(confirmation.this,"QR Saved in Internal Storage",Toast.LENGTH_SHORT).show();

            } catch (java.io.IOException e) {
                e.printStackTrace();

            }

            //end of saving QR code




        }
        catch (WriterException e)
        {
            e.printStackTrace();
        }



        //home
        home = (ImageButton)findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gohome();
            }
        });

        //show profile
        showuid = (TextView)findViewById(R.id.showuid);
        showcourt = (TextView)findViewById(R.id.showcourt);
        showdate = (TextView)findViewById(R.id.showdate);
        showtime = (TextView)findViewById(R.id.showtime);



        //display Uid
        showuid.setText(uid);

        //display court
        g1 = FirebaseDatabase.getInstance().getReferenceFromUrl("https://playoff-7e1ac.firebaseio.com/users").child(uid).child("Court Name");
        g1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String uidd = dataSnapshot.getValue().toString();
                showcourt.setText(uidd);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(confirmation.this,"ERROR in connection",Toast.LENGTH_SHORT).show();
            }
        });

        //display date
        g1 = FirebaseDatabase.getInstance().getReferenceFromUrl("https://playoff-7e1ac.firebaseio.com/users").child(uid).child("Date");
        g1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String datee = dataSnapshot.getValue().toString();
                showdate.setText(datee);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(confirmation.this,"ERROR in connection",Toast.LENGTH_SHORT).show();
            }
        });

        //display time
        g1 = FirebaseDatabase.getInstance().getReferenceFromUrl("https://playoff-7e1ac.firebaseio.com/users").child(uid).child("Check-in Time");
        g1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String timee = dataSnapshot.getValue().toString();
                showtime.setText(timee);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(confirmation.this,"ERROR in connection",Toast.LENGTH_SHORT).show();
            }
        });



    }



    public void gohome(){
        Intent i = new Intent(confirmation.this,area.class);
        startActivity(i);
        finish();
    }

}
