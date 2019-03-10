package com.example.avira.playoff;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profile extends AppCompatActivity {

    ImageButton home;
    TextView showuid;
    TextView showemail;
    TextView showname;
    TextView showcourt;
    TextView showdate;
    TextView showtime;
    TextView showphone;
    TextView showgender;
    TextView showaddress;

    private DatabaseReference g1;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);



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
        showemail = (TextView)findViewById(R.id.showemail);
        showname = (TextView)findViewById(R.id.showname);
        showcourt = (TextView)findViewById(R.id.showcourt);
        showdate = (TextView)findViewById(R.id.showdate);
        showtime = (TextView)findViewById(R.id.showtime);
        showphone = (TextView)findViewById(R.id.showphone);
        showgender = (TextView)findViewById(R.id.showgender);
        showaddress = (TextView)findViewById(R.id.showaddress);

        //db reference
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser users = mAuth.getCurrentUser();
        String uid = users.getUid();

        //display Uid
        showuid.setText(uid);

        //display email
        g1 = FirebaseDatabase.getInstance().getReferenceFromUrl("https://playoff-7e1ac.firebaseio.com/users").child(uid).child("Email");
        g1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String emailid = dataSnapshot.getValue().toString();
                showemail.setText(emailid);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(profile.this,"ERROR in connection",Toast.LENGTH_SHORT).show();
            }
        });

        //display name
        g1 = FirebaseDatabase.getInstance().getReferenceFromUrl("https://playoff-7e1ac.firebaseio.com/users").child(uid).child("Name");
        g1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String name = dataSnapshot.getValue().toString();
                showname.setText(name);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(profile.this,"ERROR in connection",Toast.LENGTH_SHORT).show();
            }
        });

        //display phone
        g1 = FirebaseDatabase.getInstance().getReferenceFromUrl("https://playoff-7e1ac.firebaseio.com/users").child(uid).child("Phone Number");
        g1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String name = dataSnapshot.getValue().toString();
                showphone.setText(name);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(profile.this,"ERROR in connection",Toast.LENGTH_SHORT).show();
            }
        });

        //display gender
        g1 = FirebaseDatabase.getInstance().getReferenceFromUrl("https://playoff-7e1ac.firebaseio.com/users").child(uid).child("Gender");
        g1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String name = dataSnapshot.getValue().toString();
                showgender.setText(name);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(profile.this,"ERROR in connection",Toast.LENGTH_SHORT).show();
            }
        });

        //display address
        g1 = FirebaseDatabase.getInstance().getReferenceFromUrl("https://playoff-7e1ac.firebaseio.com/users").child(uid).child("Postal Address");
        g1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String name = dataSnapshot.getValue().toString();
                showaddress.setText(name);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(profile.this,"ERROR in connection",Toast.LENGTH_SHORT).show();
            }
        });

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
                Toast.makeText(profile.this,"ERROR in connection",Toast.LENGTH_SHORT).show();
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
                Toast.makeText(profile.this,"ERROR in connection",Toast.LENGTH_SHORT).show();
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
                Toast.makeText(profile.this,"ERROR in connection",Toast.LENGTH_SHORT).show();
            }
        });




    }

    public void gohome(){
        Intent i = new Intent(profile.this,area.class);
        startActivity(i);
    }
}
