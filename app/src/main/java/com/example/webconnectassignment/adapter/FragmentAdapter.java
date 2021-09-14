package com.example.webconnectassignment.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.webconnectassignment.fragments.ActiveFragment;
import com.example.webconnectassignment.fragments.AppliedFragment;
import com.example.webconnectassignment.fragments.ClosedFragment;

public class FragmentAdapter extends FragmentStateAdapter {
    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position)
        {
            case 0 :
                return new AppliedFragment();

            case 1 :
                return new ActiveFragment();

            case 2 :
                return new ClosedFragment();
        }

        return new ActiveFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }

}
