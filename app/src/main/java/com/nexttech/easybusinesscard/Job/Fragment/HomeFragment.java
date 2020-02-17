package com.nexttech.easybusinesscard.Job.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.nexttech.easybusinesscard.R;

public class HomeFragment extends Fragment {

    Button btnPostJob, btnViewJob;

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        btnPostJob = view.findViewById(R.id.btn_post_job);
        btnViewJob = view.findViewById(R.id.btn_view_job);

        btnPostJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new JobPostFragment();

                ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, fragment).commit();
            }
        });

        btnViewJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new JobViewFragment();

                ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, fragment).commit();
            }
        });

        return view;
    }

}
