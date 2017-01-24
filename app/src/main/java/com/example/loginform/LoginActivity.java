package com.example.loginform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_login;
    Button btn_register;
    EditText et_name ;
    EditText et_pass;
    DataBaseHelper dataBaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_register = (Button) findViewById(R.id.btn_Register);
        et_name = (EditText) findViewById(R.id.et_Name);
        et_pass = (EditText) findViewById(R.id.et_Password);

        btn_register.setOnClickListener(this);
        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_login:
                String name = et_name.getText().toString();
                String pass = et_pass.getText().toString();

                if(name == null || pass == null){
                    Toast.makeText(getApplicationContext(),"Please enter your information",Toast.LENGTH_LONG).show();
                }
                else{
                   dataBaseHelper = new DataBaseHelper(this);
                   String method = "Login";
                   dataBaseHelper.execute(method,name,pass);
                   Intent i = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(i);
                }



                break;

            case  R.id.btn_Register:
                Intent i = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i);
                break;
        }
    }
}
