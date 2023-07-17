package com.example.ecommersemobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginActivity extends AppCompatActivity {
    EditText email,password;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();


        auth =FirebaseAuth.getInstance();
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);

    }

    public void signIn(View view) {
        String userEmail=email.getText().toString();
        String userPassword=password.getText().toString();
        if(TextUtils.isEmpty(userEmail)){

            Toast.makeText(this,"Enter Email Address!",Toast.LENGTH_SHORT).show();
            return;

        }
        if(TextUtils.isEmpty(userPassword)){

            Toast.makeText(this,"Enter Password!",Toast.LENGTH_SHORT).show();
            return;

        }
        if(userPassword.length()<6){
            Toast.makeText(this," Password Too Short!, Enter Munimum 6 Characters!",Toast.LENGTH_SHORT).show();
            return;

        }

        auth.signInWithEmailAndPassword(userEmail,userPassword)
                .addOnCompleteListener(loginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(loginActivity.this, "Log IN Successful ", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(loginActivity.this,MainActivity.class));
                        }else{
                            Toast.makeText(loginActivity.this, " Error :"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void signUp(View view) {
        startActivity(new Intent(loginActivity.this,registrationActivity.class));

    }
}