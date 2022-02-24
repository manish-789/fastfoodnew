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
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
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

       buttonotp = findViewById(R.id.buttonotp);
       editPhonenumber = findViewById(R.id.editPhonenumber);

       buttonotp.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String Phonenumber=editPhonenumber.getText().toString();
               if (Phonenumber.isEmpty())
                   Toast.makeText( MainActivity.this, "Enter your phone number", Toast.LENGTH_SHORT).show();
               else{
                   //verify phone number
                   PhoneAuthProvider.getInstance().verifyPhoneNumber(
                          "+91"+Phonenumber, 60, TimeUnit.SECONDS, MainActivity.this,
                           new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                               @Override
                               public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                   signInUser(PhoneAuthCredential);
                               }

                               @Override
                               public void onVerificationFailed(@NonNull FirebaseException e) {
                                   Log.d(TAG, "onVerificationFailed:" +e.getLocalizedMessage());
                               }

                               @Override
                               public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                   super.onCodeSent(s, forceResendingToken);

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

                                       private void signInUser(PhoneAuthCredential credential) {
                                           FirebaseAuth.getInstance().signInWithCredential(credential)
                                                   .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                       @Override
                                                       public void onComplete(@NonNull Task<AuthResult> task) {
                                                           if(task.isSuccessful()){
                                                              startActivity(new Intent(MainActivity.this,HomwPage.class));
                                                              finish();

                                                           } else{
                                                               Log.d(TAG, msg:  "onComplete:"+task.getException().getLocalizedMessage());
                                                           }
                                                       }
                                                   });
                                       }
                                   });

                                   dialog.show();
                               }
                           }
                   );
               }
           }
       });
    }
}