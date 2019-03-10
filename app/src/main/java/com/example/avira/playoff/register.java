package com.example.avira.playoff;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class register extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference g1;
    String uid;
    EditText phonenum;
    EditText address;
    EditText gender;
    ImageButton registerbtn;
    String phnum, postaladd,gen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //db reference
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser users = mAuth.getCurrentUser();
        uid = users.getUid();
        g1 = FirebaseDatabase.getInstance().getReferenceFromUrl("https://playoff-7e1ac.firebaseio.com/users").child(uid);

        phonenum = (EditText)findViewById(R.id.phonenum);
        address = (EditText)findViewById(R.id.address);
        gender = (EditText)findViewById(R.id.gender);


        registerbtn = (ImageButton)findViewById(R.id.registerbtn);
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                phnum = phonenum.getText().toString();
                postaladd = address.getText().toString();
                gen = gender.getText().toString();


                Map<String,Object> taskmap = new HashMap<>();
                taskmap.put("Phone Number",phnum);
                taskmap.put("Postal Address",postaladd);
                taskmap.put("Gender",gen);
                g1.updateChildren(taskmap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Intent intent = new Intent(register.this, area.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(register.this,"ERROR. Please check your connection",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

    }
}
