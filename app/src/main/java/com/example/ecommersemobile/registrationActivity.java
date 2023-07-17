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

public class registrationActivity extends AppCompatActivity {
    EditText name,email,password;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getSupportActionBar().hide();

        auth =FirebaseAuth.getInstance();

        if(auth.getCurrentUser() !=null){
            startActivity(new Intent(registrationActivity.this,MainActivity.class));
            finish();
        }

        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);

    }

    public void signup(View view) {
        String userName=name.getText().toString();
        String userEmail=email.getText().toString();
        String userPassword=password.getText().toString();


        if(TextUtils.isEmpty(userName)){

            Toast.makeText(this,"Enter Name!",Toast.LENGTH_SHORT).show();
            return;
        }
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

        auth.createUserWithEmailAndPassword(userEmail,userPassword)
                .addOnCompleteListener(registrationActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(registrationActivity.this, "successfully Registr", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(registrationActivity.this,MainActivity.class));
                        }else{
                            Toast.makeText(registrationActivity.this, "Registration Failed"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
//        startActivity(new Intent(registrationActivity.this,MainActivity.class));

    }

    public void signin(View view) {
        startActivity(new Intent(registrationActivity.this,loginActivity.class));

    }
}