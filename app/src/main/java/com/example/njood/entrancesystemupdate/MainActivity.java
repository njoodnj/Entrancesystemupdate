package com.example.njood.entrancesystemupdate;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends Activity {

    EditText id, password;
    String Id, Password;
    Context ctx=this;
    String ID=null, NAME=null, PASSWORD=null, EMAIL=null, ADDRESS=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        id = (EditText) findViewById(R.id.NationalID);
        password = (EditText) findViewById(R.id.passwordHP);
    }

    public void main_register(View v){
        startActivity(new Intent(this,register.class));
    }

    public void main_login(View v){
        Id = id.getText().toString();
        Password = password.getText().toString();
        BackGround b = new BackGround();
        b.execute(Id, Password);
    }

    class BackGround extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String id = params[0];
            String password = params[1];
            String data="";
            int tmp;

            try {
                URL url = new URL("http://10.0.2.2/ES/login.php");
                String urlParams = "r_id="+id+"&r_password="+password;

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

            try {
                JSONObject root = new JSONObject(s);
                JSONObject user_data = root.getJSONObject("user_data");
                ID = user_data.getString("r_id");
                NAME = user_data.getString("r_name");
                PASSWORD = user_data.getString("r_password");
                EMAIL = user_data.getString("r_email");
                ADDRESS = user_data.getString("r_address");
            } catch (JSONException e) {
                e.printStackTrace();

            }

            Intent i = new Intent(ctx, User_Home.class);
            i.putExtra("r_id", ID);
            i.putExtra("r_name", NAME);
            i.putExtra("r_password", PASSWORD);
            i.putExtra("r_email", EMAIL);
            i.putExtra("r_address", ADDRESS);
            startActivity(i);

        }
    }
}
