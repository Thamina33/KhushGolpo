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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignUpActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseFirestore database;
    EditText mail,pass,name;
    Button signUp,signIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        auth = FirebaseAuth.getInstance();
        database = FirebaseFirestore.getInstance();
        name = findViewById(R.id.name);
        mail = findViewById(R.id.mail);
        pass = findViewById(R.id.pass);
        signUp = findViewById(R.id.signUp);
        signIn =findViewById(R.id.signIn);



       signIn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               startActivity(new Intent(SignUpActivity.this,SignInActivity2.class));
           }
       });

       signUp.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               String email,passw,pname;
               email = mail.getText().toString();
               passw = pass.getText().toString();
               pname = name.getText().toString();

              final  User user = new User();
               user.setEmail(email);
               user.setPass(passw);
               user.setName(pname);


               auth.createUserWithEmailAndPassword(email,passw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {

                       if(task.isSuccessful()){

                           database.collection("users")
                                   .document().set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                               @Override
                               public void onSuccess(Void aVoid) {
                                   startActivity(new Intent(SignUpActivity.this,SignInActivity2.class));
                               }
                           });
                           Toast.makeText(SignUpActivity.this,"Account is Created",Toast.LENGTH_SHORT).show();
                       }

                       else{

                           Toast.makeText(SignUpActivity.this,task.getException().getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                       }
                   }
               });
           }
       });

    }
}