package com.example.njood.entrancesystemupdate;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class register extends Activity {

    Button r_submit;
    EditText r_ID, r_FullName, r_Password, r_repassword, r_email, r_reemail , r_address;
    String id, name, pass, repass, email, remail, address;
    Context ctx=this;
    CheckBox r_policy;
    TextView popup_pol;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_register);


        r_ID = (EditText) findViewById(R.id.ID);
        r_FullName = (EditText) findViewById(R.id.FullName);
        r_Password = (EditText) findViewById(R.id.Password);
        r_repassword = (EditText) findViewById(R.id.repassword);
        r_email = (EditText) findViewById(R.id.email);
        r_reemail = (EditText) findViewById(R.id.reemail);
        r_address= (EditText) findViewById(R.id.address);
        r_policy= (CheckBox) findViewById(R.id.policy);


    }

    public void register_register(View v) {


        id = r_ID.getText().toString();
        name = r_FullName.getText().toString();
        pass = r_Password.getText().toString();
        repass = r_repassword.getText().toString();
        email = r_email.getText().toString();
        remail = r_reemail.getText().toString();
        address = r_address.getText().toString();


        BackGround b = new BackGround();
        b.execute(id, name, pass, email, address);
        startActivity(new Intent(this, MainActivity.class));
    }
    class BackGround extends AsyncTask<String, String, String> {


        @Override
        protected String doInBackground(String... params) {

            String id = params[0];
            String name = params[1];
            String pass = params[2];
            String email = params[3];
            String address = params[4];
            String data="";
            int tmp;




            try {
                URL url = new URL("http://10.0.2.2/ES/register.php");

                String urlParams = "id="+id+"&name="+name+"&pass="+pass+"&email="+email+"&address="+address;

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                os.write(urlParams.getBytes());
                os.flush();
                os.close();
                InputStream is = httpURLConnection.getInputStream();
                while((tmp=is.read())!=-1){
                   data+= (char)tmp;
                }
                is.close();
                httpURLConnection.disconnect();

                return data;

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            if(s.equals("")){
                s="Data saved successfully.";
            }
            Toast.makeText(ctx, s, Toast.LENGTH_LONG).show();
        }


    }

}