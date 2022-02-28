package com.example.fastfood1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private Button buttonotp;
    private EditText editPhonenumber;

    private static final  String TAG="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);

       buttonotp = findViewById(R.id.buttonotp);
       editPhonenumber = findViewById(R.id.editPhonenumber);

       buttonotp.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String Phonenumber=editPhonenumber.getText().toString();
               if (Phonenumber.isEmpty())
                   Toast.makeText( MainActivity.this, "Enter your phone number", Toast.LENGTH_SHORT).show();
               else{
                   Toast.makeText( MainActivity.this, "otp send", Toast.LENGTH_SHORT).show();

                   PhoneAuthOptions options =
                           PhoneAuthOptions.newBuilder(FirebaseAuth.getInstance())
                                   .setPhoneNumber(Phonenumber)       // Phone number to verify
                                   .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                                   .setActivity(MainActivity.this)                 // Activity (for callback binding)
                                   .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                       @Override
                                       public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                           signInUser(phoneAuthCredential);
                                           Log.d(TAG, "onVerificationCompleted:");

                                       }

                                       @Override
                                       public void onVerificationFailed(@NonNull FirebaseException e) {
                                           Log.d(TAG, "onVerificationFailed:" +e.getLocalizedMessage());
                                       }

                                       @Override
                                       public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                           Log.d(TAG, "onCodeSent:");

                                           super.onCodeSent(verificationId, forceResendingToken);

                                           Dialog dialog=new Dialog(MainActivity.this);
                                           dialog.setContentView(R.layout.verify_popup);
                                           EditText verifycode = dialog.findViewById(R.id.verifycode);
                                           Button btnverifyotp = dialog.findViewById(R.id.btnverifyotp);
                                           verifycode.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                   String verificationCode = verifycode.getText().toString();
                                                   if(verificationId.isEmpty()) return;
                                                   //create a credential
                                                   PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId,verificationCode);
                                                   signInUser(credential);
                                               }


                                           });

                                           dialog.show();
                                       }
                                   })          // OnVerificationStateChangedCallbacks
                                   .build();
                   PhoneAuthProvider.verifyPhoneNumber(options);
               }
           }
       });

    }
    private void signInUser(PhoneAuthCredential credential) {
        FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        startActivity(new Intent(MainActivity.this,HomePage.class));
                        finish();

                    } else{
                        Log.d(TAG, "onComplete:"+task.getException().getLocalizedMessage());
                    }
                });
    }
}