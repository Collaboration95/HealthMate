package com.example.healthmate;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.healthmate.social_fragments.fragment_following;
import com.example.healthmate.social_fragments.fragment_forYou;

public class Adapter_social_fragment extends FragmentStateAdapter {
    public Adapter_social_fragment(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position){
            case 1:
                return new fragment_following();
            default:
                return new fragment_forYou();

        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
