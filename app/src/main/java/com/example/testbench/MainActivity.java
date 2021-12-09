package com.example.testbench;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

    }

    public void goToRegister(View v){

        //This is mapped to the Register Button in the Layout
        Intent startReg = new Intent(MainActivity.this, Register.class);
        startActivity(startReg);


    }

    public void goToLogin(View v){


        Intent startReg = new Intent(MainActivity.this, Login.class);
        startActivity(startReg);


    }
}