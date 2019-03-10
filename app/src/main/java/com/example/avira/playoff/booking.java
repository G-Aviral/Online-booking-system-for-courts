package com.example.avira.playoff;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class booking extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner courtname;
    Spinner time;
    Spinner racket;
    Spinner shuttle;
    Spinner grip;
    ImageButton paynow;
    TextView amountpay;
    TextView textarea2;
    ImageButton home;
    ImageButton cancelnow;

    private DatabaseReference g1;
    private FirebaseAuth mAuth;

    //calender dialog

    private static ImageButton datedialog;
    int year_x,month_x,day_x;
    static final int dialogg_id=0;


    protected DatePickerDialog.OnDateSetListener dpickerlistener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            year_x = i;
            month_x = i1 + 1;
            day_x = i2;
            // get current date
            String date = new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault()).format(new Date());
            //show current date
            // Toast.makeText(booking.this,date,Toast.LENGTH_SHORT).show();
            //show selected date
            // Toast.makeText(booking.this,"The date is - " + month_x + "-" + day_x + "-" + year_x,Toast.LENGTH_LONG).show();
            String selecteddate = month_x + "-" + day_x + "-" + year_x;
            if (date.compareTo(selecteddate) < 10){
                //Toast.makeText(booking.this,selecteddate,Toast.LENGTH_SHORT).show();

                Map<String,Object> taskmap = new HashMap<>();
                taskmap.put("Date",month_x + "-" + day_x + "-" + year_x);
                textarea2 = (TextView)findViewById(R.id.textView2);
                textarea2.setText(month_x + "-" + day_x + "-" + year_x);
                g1.updateChildren(taskmap);
            }
            else {
                Toast.makeText(booking.this,"Please select future dates",Toast.LENGTH_SHORT).show();
                textarea2.setText("");

            }

        }
    };


    @Override
    protected Dialog onCreateDialog(int idd){
        if (idd==dialogg_id)
        {
            return new DatePickerDialog(this,dpickerlistener, year_x, month_x, day_x);
        }
        return null;
    }


    public void showdate(){
        datedialog=(ImageButton)findViewById(R.id.calenderdate);
        datedialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(dialogg_id);
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        //home
        home = (ImageButton)findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gohome();
            }
        });

        //cancel
        cancelnow = (ImageButton)findViewById(R.id.cancelnow);
        cancelnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelkro();
            }
        });

        //calender dialog

        final Calendar cal = Calendar.getInstance();
        year_x=cal.get(Calendar.YEAR);
        month_x=cal.get(Calendar.MONTH);
        day_x=cal.get(Calendar.DAY_OF_MONTH);

        showdate();

        //court
        courtname = (Spinner)findViewById(R.id.courtname);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.court,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courtname.setAdapter(adapter);
        courtname.setOnItemSelectedListener(this);

        //time
        time = (Spinner)findViewById(R.id.time);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.time,android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        time.setAdapter(adapter1);
        time.setOnItemSelectedListener(this);


        //racket

        racket = (Spinner)findViewById(R.id.racket);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.rackets,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        racket.setAdapter(adapter2);
        racket.setOnItemSelectedListener(this);

        //shuttle

        shuttle = (Spinner)findViewById(R.id.shuttle);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,R.array.shuttles,android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        shuttle.setAdapter(adapter3);
        shuttle.setOnItemSelectedListener(this);

        //grip

        grip = (Spinner)findViewById(R.id.grip);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,R.array.grips,android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        grip.setAdapter(adapter4);
        grip.setOnItemSelectedListener(this);


        //PAY NOW BUTTON CLICK
        paynow = (ImageButton)findViewById(R.id.paynow);
        paynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ON BUTTON CLICK EVENT
                Toast.makeText(booking.this,"Payment Successful",Toast.LENGTH_SHORT).show();
                confirmit();

            }
        });


        //db reference
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser users = mAuth.getCurrentUser();
        String uid = users.getUid();
        g1 = FirebaseDatabase.getInstance().getReferenceFromUrl("https://playoff-7e1ac.firebaseio.com/users").child(uid);



    }

    //home button method
    public void gohome(){
        Intent i = new Intent(booking.this,area.class);
        startActivity(i);
    }

    //on pay button
    public void confirmit(){
        Intent i = new Intent(booking.this,confirmation.class);
        startActivity(i);
        finish();
    }

    //adapter method override
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //putting in database
        String court = courtname.getSelectedItem().toString();
        String booktime = time.getSelectedItem().toString();
        String rackets = racket.getSelectedItem().toString();
        String shuttles = shuttle.getSelectedItem().toString();
        String grips = grip.getSelectedItem().toString();

        Integer checkout = Integer.parseInt(booktime)+1;


        //displaying amount
        Integer amt= (Integer.parseInt(rackets)*100)+(Integer.parseInt(grips)*60)+(Integer.parseInt(shuttles)*120)+200;
        amountpay = (TextView)findViewById(R.id.amountpay);
        amountpay.setText(amt.toString());

        //continue putting in database
        Map<String,Object> taskmap = new HashMap<>();
        taskmap.put("Court Name",court);
        taskmap.put("Check-in Time",booktime);
        taskmap.put("Check-out Time",checkout.toString());
        taskmap.put("Rackets",rackets);
        taskmap.put("Shuttles",shuttles);
        taskmap.put("Grips",grips);
        taskmap.put("Amount Paid",amt.toString());

        g1.updateChildren(taskmap);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public void cancelkro(){

        //cancelling values
        Map<String,Object> taskmap = new HashMap<>();
        taskmap.put("Court Name","");
        taskmap.put("Check-in Time","");
        taskmap.put("Check-out Time","");
        taskmap.put("Rackets","");
        taskmap.put("Shuttles","");
        taskmap.put("Grips","");
        taskmap.put("Amount Paid","");
        taskmap.put("Date","");

        g1.updateChildren(taskmap);

        Intent i = new Intent(booking.this,area.class);
        startActivity(i);
        finish();
    }


}
