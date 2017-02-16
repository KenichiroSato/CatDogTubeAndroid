package com.capken.catdogtube.function.video.presentation.segmented;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.capken.catdogtube.R;
import com.capken.catdogtube.function.video.presentation.collection.VideoCollectionFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ken on 2017/02/15..
 */

public final class SegmentedFragment extends Fragment {

    private List<Fragment> mTabFragments = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_segmented, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        Fragment f1 = new VideoCollectionFragment();
        //f1.getView()    .setBackgroundColor(Color.BLUE);
        Fragment f2 = new Fragment();
        //f2.getView().setBackgroundColor(Color.GREEN);
        mTabFragments.add(f1);
        mTabFragments.add(f2);
        setupTabs();
    }

    private void setupTabs() {
        View rootView = getView();
        if (rootView != null) {
            TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
            ViewPager pager = (ViewPager) rootView.findViewById(R.id.pager);
            // 1 dp
            final float scale = getResources().getDisplayMetrics().density;
            pager.setPageMargin((int) (scale * 1));
            FragmentPagerAdapter adapter = new SegmentedPagerAdapter(getChildFragmentManager());
            pager.setAdapter(adapter);
            tabLayout.setupWithViewPager(pager);
        }

    }

    private class SegmentedPagerAdapter extends FragmentPagerAdapter {

        SegmentedPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mTabFragments.get(position);
        }

        @Override
        public int getCount() {
            return mTabFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "title";
        }
    }
}
