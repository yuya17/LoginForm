package com.example.loginform;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText et_name;
    EditText et_email;
    EditText et_phone;
    EditText et_pass;
    Button btn_register;

    DataBaseHelper dataBaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        et_name = (EditText) findViewById(R.id.et_Register_Name);
        et_email =(EditText) findViewById(R.id.et_Register_Email);
        et_phone = (EditText) findViewById(R.id.et_Register_Phone);
        et_pass = (EditText) findViewById(R.id.et_Register_Password);
        btn_register =(Button) findViewById(R.id.btn_Register_register);

        btn_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        String name  = et_name.getText().toString();
        String email = et_email.getText().toString();
        String phone = et_phone.getText().toString();
        String pass= et_pass.getText().toString();
        String method = "raj";
        dataBaseHelper = new DataBaseHelper(this);
        dataBaseHelper.execute(method,name,email,phone,pass);
    }
}
