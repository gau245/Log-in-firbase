package com.example.mychak.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mychak.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    EditText etEmail,etUsername,tvPassword,etRePassword;
    public static String email_et,username_et,password_tv,RePassword_et;
    Button btLogin;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_register );

        mAuth = FirebaseAuth.getInstance();

        etEmail=findViewById(R.id.etEmail);
        etUsername=findViewById(R.id.etUsername);
        etRePassword=findViewById(R.id.etRePassword);
        btLogin=findViewById(R.id.btLogin);


        btLogin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email_et=etEmail.getText().toString();
                username_et=etUsername.getText().toString();
               // password_tv=tvPassword.getText().toString();
                RePassword_et=etRePassword.getText().toString();
                firbase();
            }
        } );

    }

    private void firbase() {
        mAuth.createUserWithEmailAndPassword(email_et,RePassword_et).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, "Done", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegisterActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}