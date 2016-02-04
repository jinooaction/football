package org.androidtown.android_design_example_20151112.wait_for_delete;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import org.androidtown.android_design_example_20151112.R;
import org.androidtown.android_design_example_20151112.wait_for_delete.FragReal_Matching;

public class RealMainActivity extends AppCompatActivity {
    ViewPager viewPager;

    private static String TAG = "RealActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarForReal);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
            ab.setHomeAsUpIndicator(R.drawable.ic_accessibility_24dp);
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setDisplayShowTitleEnabled(false);

        Log.d(TAG, "############################### STEP 1 is completed");

        viewPager = (ViewPager) findViewById(R.id.viewpagerForReal);
        Adapter adapter = new Adapter(getSupportFragmentManager());

        viewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabsForReal);
        tabLayout.setupWithViewPager(viewPager);
        Log.d(TAG, "################################## STEP 2 is completed");

    }


    class Adapter extends FragmentPagerAdapter
    {

        public Adapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            switch(position) {
                case 0: return new FragReal_Matching();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 1;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch(position) {
                case 0: return "매칭";
                default:
                    return null;
            }
        }


    }
}
