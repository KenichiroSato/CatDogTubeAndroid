package com.capken.catdogtube.function.video.presentation.segmented;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.capken.catdogtube.R;
import com.capken.catdogtubedomain.video.presentation.segmented.SegmentProtocol;
import com.capken.catdogtubedomain.video.presentation.segmented.SegmentedContract;
import com.capken.catdogtubedomain.video.presentation.segmented.SegmentsPresenter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ken on 2017/02/15..
 */

public final class SegmentedFragment extends Fragment implements SegmentedContract.View {

    private List<Fragment> mTabFragments = new ArrayList<>();

    private SegmentsPresenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_segmented, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
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

    public void setPresenter(SegmentsPresenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    //MARK: SegmentedContract.View
    @Override
    public void show(@NotNull List<? extends SegmentProtocol> segments) {
        for (final SegmentProtocol segment : segments) {
            mTabFragments.add((Fragment) segment.view());
        }
        setupTabs();

    }

    @Override
    public void reorder(@NotNull List<? extends SegmentProtocol> segments) {

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
