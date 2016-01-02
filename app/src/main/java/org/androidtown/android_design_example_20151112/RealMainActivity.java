package org.androidtown.android_design_example_20151112;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

public class RealMainActivity extends AppCompatActivity {
    Intent intent;
    ViewPager viewPager;

    RelativeLayout layoutForAnim;
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

        layoutForAnim = (RelativeLayout) findViewById(R.id.frame_for_anim);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: // handle click event on home icon(drawer icon)
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
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
                case 1: return new FragReal_Team();
                case 2: return new FragReal_League();
                case 3: return new FragReal_Event();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch(position) {
                case 0: return "매칭";
               case 1: return "팀";
                case 2:return "리그";
                case 3:return "이벤트";
                default:
                    return null;
            }
        }


    }
}
