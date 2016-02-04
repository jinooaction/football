package org.androidtown.android_design_example_20151112.Subpages;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import org.androidtown.android_design_example_20151112.R;

public class Subpage_AddMatch extends AppCompatActivity implements View.OnClickListener{

    Button addMatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subpage__add_match);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab=getSupportActionBar();
        ab.setDisplayShowTitleEnabled(false);

        addMatch = (Button)findViewById(R.id.addMatch);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.addMatch:
                //설정 버튼 눌렀을때 작동
                break;
        }
    }
}
