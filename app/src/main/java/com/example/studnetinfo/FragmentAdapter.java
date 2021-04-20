package com.example.studnetinfo;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class FragmentAdapter extends FragmentStatePagerAdapter {

    public FragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch(position){
            case 0:
                AcademicInfo ai  = new AcademicInfo();
                return ai;
            case 1:
                privateInfo pi = new privateInfo();
                return pi;
             default:
                 return null;
        }

    }

    @Override
    public int getCount() {
        return 2;
    }
}
