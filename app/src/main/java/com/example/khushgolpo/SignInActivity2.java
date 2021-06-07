package com.example.khushgolpo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity2 extends AppCompatActivity {
    FirebaseAuth auth;
    EditText email,pass;
    Button signIn,signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in2);

        auth = FirebaseAuth.getInstance();
        email = findViewById(R.id.mail);
        pass = findViewById(R.id.pass);
        signUp = findViewById(R.id.signUp);
        signIn =findViewById(R.id.signIn);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail,passw;
                mail = email.getText().toString();
                passw = pass.getText().toString();
                auth.signInWithEmailAndPassword(mail,passw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){

                            Toast.makeText(SignInActivity2.this,"Account is Created",Toast.LENGTH_SHORT).show();
                        }

                        else{

                            Toast.makeText(SignInActivity2.this,task.getException().getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(SignInActivity2.this,SignUpActivity.class));
            }
        });


    }
}