package com.nexttech.easybusinesscard.BusinessCard.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewpagerAdapter extends FragmentStatePagerAdapter {
    public static int pos = 0;

    private ArrayList<Fragment> myFragments;

    public ViewpagerAdapter(FragmentManager fm, ArrayList<Fragment> myFrags) {
        super(fm);
        myFragments = myFrags;
    }

    @Override
    public Fragment getItem(int position) {

        return myFragments.get(position);

    }

    @Override
    public int getCount() {

        return myFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {



        String PageTitle = "";

        switch(pos)
        {
            case 0:
                PageTitle = "Text";
                break;
            case 1:
                PageTitle = "Icon";
                break;
            case 2:
                PageTitle = "Image";
                break;
            case 3:
                PageTitle = "QR Code";
                break;
            case 4:
                PageTitle = "Preview";
                break;
            case 5:
                PageTitle = "Back Side";
                break;

        }
        return PageTitle;
    }
}
