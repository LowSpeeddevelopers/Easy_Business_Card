package com.nexttech.easybusinesscard.Job.Fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nexttech.easybusinesscard.Job.Model.PostModel;
import com.nexttech.easybusinesscard.R;

import java.util.Calendar;


public class JobPostFragment extends Fragment {


    EditText etJobPosition, etCompany, etAddress, etSalary;
    Button btnPostJob;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_job_post, container, false);

        etJobPosition = view.findViewById(R.id.et_job_position);
        etCompany = view.findViewById(R.id.et_company);
        etAddress = view.findViewById(R.id.et_address);
        etSalary = view.findViewById(R.id.et_salary);
        btnPostJob = view.findViewById(R.id.btn_post_job);

        btnPostJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jobPosition = etJobPosition.getText().toString();
                String companyName = etCompany.getText().toString();
                String companyAddress = etAddress.getText().toString();
                String jobSalary = etSalary.getText().toString();

                String jobPublisher = FirebaseAuth.getInstance().getCurrentUser().getUid();

                if (jobPosition.isEmpty() || companyName.isEmpty() || companyAddress.isEmpty() || jobSalary.isEmpty()){
                    Toast.makeText(getContext(), "Fields must not be empty", Toast.LENGTH_SHORT).show();
                } else {
                    long postId = Calendar.getInstance().getTimeInMillis();
                    PostModel model = new PostModel(postId, jobPosition, companyName, companyAddress, jobSalary, jobPublisher);

                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Job Posts").child(String.valueOf(postId));

                    reference.setValue(model, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                            Toast.makeText(getContext(), "Job Post", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });

        return view;
    }

}
