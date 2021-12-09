package com.example.testbench;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    FirebaseAuth fAuth;
    EditText mReg_name, mReg_email , mReg_password , mReg_phone;
    ProgressBar progressBar;

    Button mReg_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mReg_name = findViewById(R.id.reg_name);
        mReg_email = findViewById(R.id.reg_email);
        mReg_password = findViewById(R.id.reg_password);

        mReg_btn = findViewById(R.id.reg_btn);
        mReg_phone = findViewById(R.id.reg_phone);



        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);


        mReg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Email = mReg_email.getText().toString().trim();
                String Password = mReg_password.getText().toString().trim();

                if(TextUtils.isEmpty(Email) || !Email.contains("@")){
                    //User has not entered any value in Email Field
                    mReg_email.setError("Enter Valid Email Address");
                    return;
                }

                if(TextUtils.isEmpty(Password)){
                    //User has not entered any value in Password Field
                    mReg_password.setError("Enter Password");
                    return;
                }

                if(Password.length() < 6){
                    mReg_password.setError("Password must be atleast 6 characters long\nYours is "+Password.length());
                    return;
                }

                for(int i = 0; i <Password.length(); i++){
                        int cnt = 0;
                        char c = Password.charAt(i);
                        Boolean isUpper = Character.isUpperCase(c);
                        if (isUpper == true){
                            cnt++ ;
                        }
                            if (cnt < 1){
                                mReg_password.setError("Password must be contain atleast 1 Upper Case Character");
                                return;

                            }

                }

                progressBar.setVisibility(View.VISIBLE);

                //Register user in FireBase

                fAuth.createUserWithEmailAndPassword(Email , Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Register.this , "User Created" , Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), Home.class));

                        } else{
                                Toast.makeText(Register.this, "Error"  + task.getException().getMessage() , Toast.LENGTH_SHORT).show();

                        }
                    }
                });

            }
        });

    }
}