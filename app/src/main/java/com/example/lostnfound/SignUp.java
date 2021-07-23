package com.example.lostnfound;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void register(View view) {
        EditText name = (EditText)findViewById(R.id.sign_et1);
        EditText email = (EditText)findViewById(R.id.sign_et2);
        EditText password = (EditText)findViewById(R.id.sign_et3);
        EditText stdid = (EditText)findViewById(R.id.sign_et4);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference ref = database.getReference("Users").child(mAuth.getCurrentUser().getUid());
                            ref.child("Name").setValue(name.getText().toString());
                            ref.child("Email").setValue(email.getText().toString());
                            ref.child("StudentID").setValue(stdid.getText().toString());
                            Toast.makeText(SignUp.this, "Signup Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), Home.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(SignUp.this, task.getException().toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}