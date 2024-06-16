package com.example.psychecare.user;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class UserViewPagerAdapter extends FragmentStatePagerAdapter {
    public UserViewPagerAdapter(@NonNull FragmentManager fm,int behavior) {
        super(fm,behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:  return new FragmentInfoDetail();
            case 1:  return new FragmentManagerPosts();
            case 2:  return new FragmentFavoritePost();
            default:return new FragmentInfoDetail();

        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return "Information Detail";
            case 1: return "Manager Posts";
            case 2: return "Favorite Posts";
            default:return "Information Detail";
        }

    }
}
