package org.androidtown.android_design_example_20151112.Subpages.SelectMembers;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.androidtown.android_design_example_20151112.R;

public class Subpage_SelectMember extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subpage__select_member);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab=getSupportActionBar();
        ab.setDisplayShowTitleEnabled(false);

    }

    class ListViewAdapter extends BaseAdapter
    {
        private Context mContext;
        //여기에 데이터 저장


        public ListViewAdapter(Context context)
        {
            mContext=context;
        }
        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
            //여기서 view 연결
        }
    }

}
