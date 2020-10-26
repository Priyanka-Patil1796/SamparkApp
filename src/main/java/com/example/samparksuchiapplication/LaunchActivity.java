package com.example.samparksuchiapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.samparksuchiapplication.DataBase.DBHelper;
import com.example.samparksuchiapplication.Model.ContactDetailsModel;
import com.example.samparksuchiapplication.Model.DataProccessor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LaunchActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 1000;
    private String mJSONURLString = "http://btwebservices.biyanitechnologies.com/galaxybackupservices/galaxy1.svc/GetBtContactData";
    ContactDetailsModel model;
    List<ContactDetailsModel> list;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.splashscreen);

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                    Intent mainIntent = new Intent(LaunchActivity.this, MainActivity.class);
                    startActivity(mainIntent);
                    finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }


    private void setData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        // Initialize a new JsonArrayRequest instance
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, mJSONURLString,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        list = new ArrayList<>();
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                model = new ContactDetailsModel();
                                JSONObject student = response.getJSONObject(i);
                                String recId = student.getString("RecId");
                                String memberCode = student.getString("MemberCode");
                                String name = student.getString("FullName");
                                String occupation = student.getString("Occupation");
                                String address = student.getString("PostalAddress");
                                String city = student.getString("City");
                                String pincode = student.getString("Pincode");
                                String state = student.getString("State");
                                String emailId = student.getString("EmailId");
                                String birthday = student.getString("Birthday");
                                String weddding = student.getString("Weddding");
                                String photo = student.getString("UploadPhoto");
                                String pNo = student.getString("ContactNo1");
                                String pNo1 = student.getString("ContactNo2");

                                model.setRecordId(Integer.parseInt(recId));
                                model.setMemberCode(memberCode);
                                model.setContactName(name);
                                model.setOccupation(occupation);
                                model.setCity(city);
                                model.setAddress(address);
                                model.setPinCode(pincode);
                                model.setState(state);
                                model.setEmailId(emailId);
                                model.setPhoneNumber(pNo);
                                model.setPhonenNumber1(pNo1);

                                try {
                                    String v = birthday.replaceAll("/","");
                                    String v1 = v.replaceAll("Date","");
                                    String v2 = v1.replace("(","").replace(")","");

                                    String longV = v2;
                                    long millisecond = Long.parseLong(longV);
                                    String dateString = String.valueOf(DateFormat.format("dd/MM/yyyy", new Date(millisecond)));
                                    model.setBirthDate(dateString);

                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }

                                try {
                                    String date = weddding.replaceAll("/","");
                                    String date1 = date.replaceAll("Date","");
                                    String date2 = date1.replace("(","").replace(")","");

                                    String longV1 = date2;
                                    long millisecond1 = Long.parseLong(longV1);
                                    String dateString1 = String.valueOf(DateFormat.format("dd/MM/yyyy", new Date(millisecond1)));
                                    model.setAnniversaryDate(dateString1);
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                                model.setPhotoUrl(photo);
                                list.add(model);
                            }

                            Log.e("List",""+list);
                            DBHelper helper = new DBHelper(getApplicationContext());
                            helper.insertContacts(list);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Do something when error occurred
                        Toast.makeText(getApplicationContext(),""+error.toString(), Toast.LENGTH_SHORT).show();
                        Log.e("error",""+error.toString());
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
}
