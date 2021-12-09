package com.example.testbench;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    FirebaseAuth fAuth;
    EditText mReg_name, mReg_email , mReg_password;
    ProgressBar progressBar;
    TextView mReg_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mReg_name = findViewById(R.id.reg_name);
        mReg_email = findViewById(R.id.reg_email);
        mReg_password = findViewById(R.id.reg_password);

        mReg_btn = findViewById(R.id.reg_btn);


        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);

    }
}