package com.example.healthmate;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.healthmate.social_fragments.Fragment_Following;
import com.example.healthmate.social_fragments.Fragment_ForYou;

/**
 * Adapter_Social_Fragment is a custom adapter for managing the display of social fragments
 * in the ViewPager2 widget. It allows switching between the "For You" and "Following" sections.
 */
public class Adapter_Social_Fragment extends FragmentStateAdapter {

    /**
     * Constructor for the Adapter_Social_Fragment class.
     */
    public Adapter_Social_Fragment(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    /**
     * This method creates a new instance of the Fragment corresponding to the given position.
     */
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new Fragment_Following();
            default:
                return new Fragment_ForYou();
        }
    }

    /**
     * This method returns the total number of fragments managed by the adapter.
     */
    @Override
    public int getItemCount() {
        return 2;
    }
}
