package com.capken.catdogtube.function.video.presentation.segmented;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.capken.catdogtube.R;
import com.capken.catdogtubedomain.video.presentation.segmented.SegmentProtocol;
import com.capken.catdogtubedomain.video.presentation.segmented.SegmentedContract;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ken on 2017/02/15..
 */

public final class SegmentedFragment extends Fragment implements SegmentedContract.View {

    private List<Fragment> mTabFragments = new ArrayList<>();

    private SegmentedContract.Presenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_segmented, container, false);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mPresenter = null;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("tag", "onActivityCreated");
        mPresenter.loadSegments();
    }

    private void setupTabs(@NotNull List<? extends SegmentProtocol> segments) {
        View rootView = getView();
        if (rootView == null) {
            return;
        }
        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        ViewPager pager = (ViewPager) rootView.findViewById(R.id.pager);
        // 1 dp
        final float scale = getResources().getDisplayMetrics().density;
        pager.setPageMargin((int) (scale * 1));
        FragmentPagerAdapter adapter = new SegmentedPagerAdapter(getChildFragmentManager());
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);


        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        int index = 0;
        for (final SegmentProtocol segment : segments) {
            TabLayout.Tab tab = tabLayout.getTabAt(index);
            tab.setText("");

            View tabView = inflater.inflate(R.layout.tab, null);
            tab.setCustomView(tabView);
            ImageView image = (ImageView) tabView.findViewById(R.id.tab_icon);
            image.setImageDrawable(ContextCompat.getDrawable(getContext(), segment.iconResourceId()));
            index++;
        }
    }


    //MARK: SegmentedContract.View
    @Override
    public void setPresenter(@NotNull SegmentedContract.Presenter presenter) {
        mPresenter = presenter;
    }


    @Override
    public void show(@NotNull List<? extends SegmentProtocol> segments) {
        for (final SegmentProtocol segment : segments) {
            mTabFragments.add((Fragment) segment.view());
        }
        setupTabs(segments);
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
            return "";
        }

    }
}
