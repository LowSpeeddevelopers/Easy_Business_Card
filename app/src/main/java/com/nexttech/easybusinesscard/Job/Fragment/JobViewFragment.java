package com.nexttech.easybusinesscard.Job.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nexttech.easybusinesscard.Job.Adapter.ViewJobListAdapter;
import com.nexttech.easybusinesscard.Job.Model.PostModel;
import com.nexttech.easybusinesscard.R;

import java.util.ArrayList;
import java.util.List;


public class JobViewFragment extends Fragment {

    TextView tvAllJobs, tvAppliedJobs, tvSavedJobs, tvLastSearchJobs;
    RecyclerView rvJobList;

    List<PostModel> jobPostList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_job_view, container, false);

        tvAllJobs = view.findViewById(R.id.tv_all_jobs);
        tvAppliedJobs = view.findViewById(R.id.tv_applied_jobs);
        tvSavedJobs = view.findViewById(R.id.tv_saved_jobs);
        tvLastSearchJobs = view.findViewById(R.id.tv_last_viewed_jobs);

        jobPostList = new ArrayList<>();

        rvJobList = view.findViewById(R.id.rv_job_list);
        rvJobList.setLayoutManager(new LinearLayoutManager(getContext()));

        getJobsFromFirebase();

        return view;
    }

    private void getJobsFromFirebase() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Job Posts");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                jobPostList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    PostModel model = snapshot.getValue(PostModel.class);

                    jobPostList.add(model);
                }

                ViewJobListAdapter adapter = new ViewJobListAdapter(getContext(), jobPostList);

                rvJobList.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
