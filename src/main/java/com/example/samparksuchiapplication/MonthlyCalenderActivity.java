package com.example.samparksuchiapplication;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.samparksuchiapplication.DataBase.DBHelper;
import com.example.samparksuchiapplication.Model.ContactDetailsModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MonthlyCalenderActivity extends AppCompatActivity {
    LinearLayout linearLayout;
    DBHelper helper;
    List<ContactDetailsModel> myList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monthly_calender_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("MonthlyCalenderActivity");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        linearLayout = findViewById(R.id.ll_monthly_calender);
        helper = new DBHelper(getApplicationContext());
        myList = new ArrayList<>();

        getMonthlyCalender();
    }

    private void getMonthlyCalender() {
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String formattedDate = df.format(c);
        String date = "11/05/2005";
        String[] date1 = date.split("/");
        Calendar beginTime = Calendar.getInstance();
        beginTime.set(Integer.parseInt(date1[2]), Integer.parseInt(date1[1]), Integer.parseInt(date1[0]));
        Log.e("Day",""+Integer.parseInt(date1[0]));
        Log.e("Month",""+Integer.parseInt(date1[1]));
        myList = helper.getMonth(Integer.parseInt(date1[1]));

        for(int i=0;i<myList.size();i++){
            if (myList.get(i).getAnniversaryDate().equalsIgnoreCase("null")){
                if (myList.get(i).getAMonth()==Integer.parseInt(date1[1])){
                    myList.remove(i);
                    i--;
                } else {

                }
            }
        }

        LayoutInflater linflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for ( int i=0;i<myList.size();i++) {
            View myView = linflater.inflate(R.layout.search_people_result_list, null); //here item is the the layout you want to inflate
            TextView name = myView.findViewById(R.id.tvName);
            TextView city = myView.findViewById(R.id.tvcity);
            TextView number = myView.findViewById(R.id.tvNumber);
            TextView bDate = myView.findViewById(R.id.tvBDate);
            TextView aDate = myView.findViewById(R.id.tvADate);
            TextView occupation = myView.findViewById(R.id.tvoccupation);

            name.setText(myList.get(i).getContactName());
            bDate.setText(myList.get(i).getBirthDate());
//            number.setText(myList.get(i).getPhoneNumber());
            city.setText(myList.get(i).getCity());
            aDate.setText(myList.get(i).getAnniversaryDate());
            occupation.setText(myList.get(i).getOccupation());

            if (myList.get(i).getAnniversaryDate().equals(null) || myList.get(i).getAnniversaryDate().equalsIgnoreCase("null")){
                aDate.setText("-");
            }
            if (myList.get(i).getPhoneNumber()!=null && myList.get(i).getPhonenNumber1()!=null)
            {
                number.setText(myList.get(i).getPhoneNumber()+"\n"+myList.get(i).getPhonenNumber1());
            }
            else if (myList.get(i).getPhoneNumber()!=null && myList.get(i).getPhonenNumber1()==null)
            {
                number.setText(myList.get(i).getPhoneNumber());
            }
            else if (myList.get(i).getPhoneNumber()==null && myList.get(i).getPhonenNumber1()!=null)
            {
                number.setText(myList.get(i).getPhonenNumber1());
            }
            linearLayout.addView(myView);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
