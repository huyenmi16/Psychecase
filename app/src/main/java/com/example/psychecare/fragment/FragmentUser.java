package com.example.psychecare.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.psychecare.R;
import com.example.psychecare.user.UserViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class FragmentUser extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;

//    @SuppressLint("WrongViewCast")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user,container,false);
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager= view.findViewById(R.id.viewPager);
        UserViewPagerAdapter adapter = new UserViewPagerAdapter(getChildFragmentManager(),3);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
}
