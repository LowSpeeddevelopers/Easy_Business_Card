package com.nexttech.easybusinesscard;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class TextpagerAdapter extends FragmentPagerAdapter {

    public static int pos = 0;

    private ArrayList<Fragment> myFragments;

    public TextpagerAdapter(FragmentManager fm, ArrayList<Fragment> myFrags) {
        super(fm);
        myFragments = myFrags;
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
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
                PageTitle = "Text Size";
                break;
            case 2:
                PageTitle = "Text Font";
                break;
            case 3:
                PageTitle = "Text color";
                break;
            case 4:
                PageTitle = "Text Background";
                break;
            case 5:
                PageTitle = "Text Style";
                break;

        }
        return PageTitle;
    }
}
