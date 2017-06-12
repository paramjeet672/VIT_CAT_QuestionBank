package com.example.shefalipc.vitbank;



import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shefalipc.vitbank.util.LoginDataBaseAdapter;

public class MainActivity extends Activity {
    Button signup, login;

    LoginDataBaseAdapter loginDataBaseAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




   /*   RelativeLayout Signin = new RelativeLayout(this);
        Button Login = new Button(this);

        Login.setId(1);
        Login.setText("Log in");

        RelativeLayout.LayoutParams logindetails = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
               RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        logindetails.addRule(RelativeLayout.ALIGN_LEFT);
        logindetails.addRule(RelativeLayout.BELOW);

                Signin.addView(Login, logindetails);
        setContentView(Signin);*/
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        signup = (Button) findViewById(R.id.button2);
        signup.setOnClickListener(new OnClickListener(){
                                      public void onClick(View v){
                                          Intent i=new Intent(MainActivity.this,signup.class);
                                          startActivity(i);
                                      }
                                  }

        );


        login = (Button) findViewById(R.id.button4);


    }

    public void login(View V) {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.activity_login);
        dialog.setTitle("LOGIN");


        final EditText editTextUsername = (EditText) dialog.findViewById(R.id.finaluser);
        final EditText editTextPassword = (EditText) dialog.findViewById(R.id.finalpassword);
        Button signin = (Button) dialog.findViewById(R.id.button8);

        signin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String userName = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();

                String storedPassword = loginDataBaseAdapter.getSinlgeEntry(userName);

                if (password.equals(storedPassword)) {

                    Intent b = new Intent(MainActivity.this,loggedin.class);

                    startActivity(b);


                } else {
                    Toast.makeText(MainActivity.this, "Username or Password does not match", Toast.LENGTH_LONG).show();
                }

            }
        });
        dialog.show();
    }
public void signdown(View V)
{
     Dialog dialog = new Dialog(MainActivity.this);
    dialog.setContentView(R.layout.activity_signup);
    dialog.setTitle("SIGN UP");







}






    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        loginDataBaseAdapter.close();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

