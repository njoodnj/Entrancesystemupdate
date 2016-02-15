package com.example.njood.entrancesystemupdate;

/**
 * Created by Njood on 2/14/2016.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class User_Home extends Activity {

    String id , name, email, address;
    TextView idTV ,nameTV, emailTV , addressTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);

        idTV = (TextView) findViewById(R.id.home_id);
        nameTV = (TextView) findViewById(R.id.home_name);
        emailTV = (TextView) findViewById(R.id.home_email);
        addressTV = (TextView) findViewById(R.id.home_address);

        id = getIntent().getStringExtra("r_id");
        name = getIntent().getStringExtra("r_name");
        email = getIntent().getStringExtra("r_email");
        address = getIntent().getStringExtra("r_address");

        idTV.setText("ID No:"+id);
        nameTV.setText("Name: "+name);
        emailTV.setText("Email:"+email);
        addressTV.setText("Address:"+address);
    }
}
