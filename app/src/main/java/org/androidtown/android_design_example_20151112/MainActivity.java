package org.androidtown.android_design_example_20151112;

        import android.os.Bundle;
        import android.support.design.widget.FloatingActionButton;
        import android.support.design.widget.NavigationView;
        import android.support.design.widget.Snackbar;
        import android.support.design.widget.TabLayout;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentManager;
        import android.support.v4.app.FragmentPagerAdapter;
        import android.support.v4.app.FragmentStatePagerAdapter;
        import android.support.v4.view.GravityCompat;
        import android.support.v4.view.ViewPager;
        import android.support.v4.widget.DrawerLayout;
        import android.support.v7.app.ActionBar;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.util.Log;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;

        import java.util.ArrayList;
        import java.util.List;
        import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;

    private DrawerLayout mDrawerLayout;
    private static String TAG = "MyActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // onCreate() has these following steps:
        // 1. Setup Toolbar
        // 2. Setup Drawer
        // 3. Setup View Pager
        // 4. Setup Floating Action Button
        Log.d(TAG, "################################## onCreated is began");
        // STEP1> Setup Toolbar
        {
            setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
            final ActionBar ab = getSupportActionBar();
            ab.setHomeAsUpIndicator(R.drawable.abc_menu_hardkey_panel_mtrl_mult);
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setDisplayShowTitleEnabled(false);
            Log.d(TAG, "################################## STEP 1 is completed");
        }

        // STEP2> Setup Drawer
        {
            mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(
                    new NavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(MenuItem menuItem) {
                            menuItem.setChecked(true);
                            mDrawerLayout.closeDrawers();
                            return true;
                        }
                    });
            Log.d(TAG, "################################## STEP 2 is completed");
        }

        // STEP3> Setup View Pager
        {
            viewPager = (ViewPager) findViewById(R.id.viewpager);
            Adapter adapter = new Adapter(getSupportFragmentManager());

            viewPager.setAdapter(adapter);
            TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(viewPager);
            Log.d(TAG, "################################## STEP 3 is completed");


        }

        // STEP4> Setup Floating Action Button
        {

            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

            fab.setOnClickListener(new View.OnClickListener() {

                @Override

                public void onClick(View view) {

                    Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG).setAction("Action", null).show();

                }

            });
            Log.d(TAG, "################################## STEP 4 is completed");
        }

    }

    class Adapter extends FragmentPagerAdapter
    {

        public Adapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            switch(position) {
                case 0: return new Frag_Myinformation();
                case 1: return new Frag_Myschdule();
                case 2: return new Frag_Newsfeed();
                case 3: return new Frag_Bookmark();
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
                case 0: return "내정보";
                case 1: return "스케쥴";
                case 2:return "뉴스피드";
                case 3:return "찜";
                default:
                    return null;
            }
        }


    }

    @Override

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: // handle click event on home icon(drawer icon)
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setPage(int num)
    {
        viewPager.setCurrentItem(num);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

