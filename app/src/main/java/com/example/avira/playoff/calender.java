package com.example.avira.playoff;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class calender extends AppCompatActivity {

    ImageButton home;
    CompactCalendarView compactCalendarView;
    TextView monthname;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM yyyy", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        //home
        home = (ImageButton)findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gohome();
            }
        });

        //reference
        monthname = (TextView)findViewById(R.id.monthname);

        compactCalendarView = (CompactCalendarView)findViewById(R.id.compactcalendar_view);
        compactCalendarView.setUseThreeLetterAbbreviation(true);

        //setting event

        Event ev1 = new Event(Color.YELLOW,1555093800000L,"MODEL COLONY ANNUAL TOURNAMENT");
        compactCalendarView.addEvent(ev1);

        Event ev2 = new Event(Color.YELLOW,1555180200000L,"MODEL COLONY ANNUAL TOURNAMENT");
        compactCalendarView.addEvent(ev2);

        Event ev3 = new Event(Color.GREEN,1555698600000L,"SHIVAJI NAGAR ANNUAL TOURNAMENT");
        compactCalendarView.addEvent(ev3);

        Event ev4 = new Event(Color.GREEN,1555785000000L,"SHIVAJI NAGAR ANNUAL TOURNAMENT");
        compactCalendarView.addEvent(ev4);

        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Context context = getApplicationContext();

                if (dateClicked.toString().compareTo("Sat Apr 13 00:00:00 GMT+05:30 2019") == 0 || dateClicked.toString().compareTo("Sun Apr 14 00:00:00 GMT+05:30 2019") == 0) {
                    Toast.makeText(calender.this, "Model Colony Memorial Tournament ", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(calender.this,modeltour.class);
                    startActivity(i);

                }

                else if (dateClicked.toString().compareTo("Sat Apr 20 00:00:00 GMT+05:30 2019") == 0 || dateClicked.toString().compareTo("Sun Apr 21 00:00:00 GMT+05:30 2019") == 0) {
                    Toast.makeText(calender.this, "Shivaji Housing Society Memorial Tournament ", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(calender.this,shivajitour.class);
                    startActivity(i);
                }

                else {
                    Toast.makeText(calender.this, "No tournaments for that day", Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                monthname.setText(dateFormatMonth.format(firstDayOfNewMonth));

            }
        });


    }

    public void gohome(){
        Intent i = new Intent(calender.this,area.class);
        startActivity(i);
    }

}
