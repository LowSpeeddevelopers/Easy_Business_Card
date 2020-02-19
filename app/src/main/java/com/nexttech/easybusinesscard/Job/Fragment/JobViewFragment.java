package com.nexttech.easybusinesscard.Job.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
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

    ViewJobListAdapter adapter;

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

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        rvJobList.setLayoutManager(linearLayoutManager);

        adapter = new ViewJobListAdapter(getContext(), jobPostList);

        rvJobList.setAdapter(adapter);

        getAllJobsFromFirebase();

        tvAllJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAllJobsFromFirebase();
            }
        });

        tvAppliedJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Wait", Toast.LENGTH_SHORT).show();
            }
        });

        tvSavedJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Saves").child(userId);

                jobPostList.clear();

                reference.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull final DataSnapshot dataSnapshot, @Nullable String s) {
                        reference.child(dataSnapshot.getKey()).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot snapshot) {
                                String jobId = dataSnapshot.getKey();

                                getSingleJobsFromFirebase(jobId);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        tvLastSearchJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Last Viewed").child(userId);

                jobPostList.clear();

                reference.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull final DataSnapshot dataSnapshot, @Nullable String s) {
                        reference.child(dataSnapshot.getKey()).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot snapshot) {
                                String jobId = dataSnapshot.getKey();

                                getSingleJobsFromFirebase(jobId);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        return view;
    }

    private void getSingleJobsFromFirebase(String jobId) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Job Posts").child(jobId);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                PostModel model = dataSnapshot.getValue(PostModel.class);

                jobPostList.add(model);

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getAllJobsFromFirebase() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Job Posts");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                jobPostList.clear();
                adapter.notifyDataSetChanged();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    PostModel model = snapshot.getValue(PostModel.class);

                    jobPostList.add(model);

                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
