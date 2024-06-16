package com.example.psychecare.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.psychecare.fragment.FragmentDocument;
import com.example.psychecare.fragment.FragmentHome;
import com.example.psychecare.fragment.FragmentNotification;
import com.example.psychecare.fragment.FragmentSearch;
import com.example.psychecare.fragment.FragmentUser;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {


    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
       switch (position){
           case 0: return new FragmentHome();
           case 1: return new FragmentDocument();
           case 2: return new FragmentNotification();
           case 3: return new FragmentUser();
           case 4: return new FragmentSearch();
           default:return new FragmentHome();


       }

    }

    @Override
    public int getCount() {
        return 5;
    }
}
