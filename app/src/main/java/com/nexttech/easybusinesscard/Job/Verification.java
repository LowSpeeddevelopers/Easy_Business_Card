package com.nexttech.easybusinesscard.Job;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nexttech.easybusinesscard.Job.Activity.MainActivity;
import com.nexttech.easybusinesscard.Job.Fragment.SettingsFragment;
import com.nexttech.easybusinesscard.Job.Fragment.signUp_Type;
import com.nexttech.easybusinesscard.R;

import java.util.concurrent.TimeUnit;

import static com.nexttech.easybusinesscard.Job.Fragment.OTP_verification.editTextCode;

public class Verification {

    Context context;
    FirebaseAuth mAuth;
    String mobile;
    DatabaseReference referenc;



    public Verification(Context context, FirebaseAuth mAuth) {
        this.context = context;
        this.mAuth = mAuth;
    }

    private String mVerificationId;

    public void sendVerificationCode(String mobile) {
        this.mobile = mobile;
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+88" + mobile,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallbacks);

        referenc = FirebaseDatabase.getInstance().getReference();
    }


    //the callback to detect the verification status
    public PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            Toast.makeText(context, "Call", Toast.LENGTH_SHORT).show();

            //Getting the code sent by SMS
            String code = phoneAuthCredential.getSmsCode();

            //sometime the code is not detected automatically
            //in this case the code will be null
            //so user has to manually enter the code
            if (code != null) {
                editTextCode.setText(code);
                //verifying the code
                verifyVerificationCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
            Log.e("Auth_Error", e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            Toast.makeText(context, "Code Sent", Toast.LENGTH_SHORT).show();

            //storing the verification id that is sent to the user
            mVerificationId = s;

        }
    };


    public void verifyVerificationCode(String code) {
        //creating the credential
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);

        Toast.makeText(context, "Verified", Toast.LENGTH_SHORT).show();

        //signing the user
        signInWithPhoneAuthCredential(credential);
    }

    public void signInWithPhoneAuthCredential(final PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(context, "Account Creation Successful", Toast.LENGTH_SHORT).show();
                            Intent i =new Intent(context,MainActivity.class);
                            context.startActivity(i);
                        } else {

                            //verification unsuccessful.. display an error message

                            String message = "Somthing is wrong, we will fix it soon...";

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                message = "Invalid code entered...";
                            }

                            Toast.makeText(context, "" + message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
