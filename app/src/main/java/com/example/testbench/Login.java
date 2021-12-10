package com.example.testbench;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText mEmail,mPassword;
    Button mLoginButton;
 //   TextView mCreateBtn;
    ProgressBar progress;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmail = findViewById(R.id.editTextTextPersonName);
        mPassword = findViewById(R.id.editTextTextPersonName2);
        progress = findViewById(R.id.progressBar2);
        mLoginButton = findViewById(R.id.button4);
        mEmail = findViewById(R.id.editTextTextPersonName);
        fAuth = FirebaseAuth.getInstance();

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = mEmail.getText().toString().trim();
                String Password = mPassword.getText().toString().trim();

                if(TextUtils.isEmpty(Email) || !Email.contains("@") || !Email.contains(".")){
                    //User has not entered any value in Email Field
                    mEmail.setError("Enter Valid Email Address");
                    return;
                }

                if(TextUtils.isEmpty(Password)){
                    //User has not entered any  value in Password Field
                    mPassword.setError("Enter Password");
                    return;
                }


                //Password Validation
                if(Password.length() < 6){
                    mPassword.setError("Password must be atleast 6 characters long\nYours is "+Password.length());
                    return;
                }

                progress.setVisibility(v.VISIBLE);
                fAuth.signInWithEmailAndPassword(Email , Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful() ){
                            Toast.makeText(Login.this , "Signed In" , Toast.LENGTH_SHORT).show();
                            setContentView(R.layout.activity_home);
                        } else{

                            Toast.makeText(Login.this,  task.getException().getMessage() , Toast.LENGTH_SHORT).show();

                        }
                    }
                });

            }





        });
    }
}