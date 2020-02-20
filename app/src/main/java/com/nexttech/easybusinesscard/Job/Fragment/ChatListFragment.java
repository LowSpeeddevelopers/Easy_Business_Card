package com.nexttech.easybusinesscard.Job.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nexttech.easybusinesscard.R;

public class ChatListFragment extends Fragment {


    public ChatListFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        
        return inflater.inflate(R.layout.fragment_chat_list, container, false);
    }

}
