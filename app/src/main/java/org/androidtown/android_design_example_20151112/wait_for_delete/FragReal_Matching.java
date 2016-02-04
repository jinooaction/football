package org.androidtown.android_design_example_20151112.wait_for_delete;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import org.androidtown.android_design_example_20151112.Item_formatch_Data;
import org.androidtown.android_design_example_20151112.Item_formatch_Viewer;
import  org.androidtown.android_design_example_20151112.Main.*;
import org.androidtown.android_design_example_20151112.R;
import org.androidtown.android_design_example_20151112.Subpage_AddMatch;


public class FragReal_Matching extends Fragment implements View.OnClickListener{

            @Override
            public void onClick(View v) {
                switch (v.getId())
                {
                    case R.id.addMatch:
                        addMatch();
                        break;
                }
            }

            Intent intent;
/*            ListView listView;
            MatchItemAdapter adapter;

            FloatingActionButton addMatchButton;

        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view =inflater.inflate(R.layout.fragment_frag_real__matching, container, false);

            addMatchButton = (FloatingActionButton)view.findViewById(R.id.addMatch);
            listView = (ListView)view.findViewById(R.id.matchListView);
            adapter= new MatchItemAdapter(getContext());

            Resources res = getResources();

            //이 부분은 예시임
            adapter.addItem(new Item_formatch_Data(res.getDrawable(R.drawable.ic_dummy_amblem_1_24dp), res.getDrawable(R.drawable.ic_dummy_amblem_2_24dp)
                    , "아주FC", "2016년 5월 12일", "수원 올림픽경기장"));
            adapter.addItem(new Item_formatch_Data(res.getDrawable(R.drawable.ic_dummy_amblem_1_24dp), res.getDrawable(R.drawable.ic_dummy_amblem_2_24dp)
                    ,  "서울FC", "2016년 5월 16일", "수원 올림픽경기장"));
            //************

            listView.setAdapter(adapter);

            addMatchButton.setOnClickListener(this);
            return view;
        }
*/
        public void addMatch()
        {
            //1. activity를 새롭게 띄운다.
            intent = new Intent(getContext(), Subpage_AddMatch.class);

            //2. 매치 등록에 관한 세부사항을 설정하고.

            //3. 서버에 등록한다.
        }

        class MatchItemAdapter extends BaseAdapter{
            private Context mContext;

            private List<Item_formatch_Data> items = new ArrayList<Item_formatch_Data>();

            public MatchItemAdapter(Context context)
            {
                mContext=context;
            }

            @Override
            public int getCount() {
                return items.size();
            }

            @Override
            public Object getItem(int position) {
                return items.get(position);
            }

            @Override
            public long getItemId(int position) {
                return 0;
                //?
            }
            public void addItem(Item_formatch_Data item)
            {
                items.add(item);
            }
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                Item_formatch_Viewer itemView;
                if(convertView == null)
                {
                    itemView = new Item_formatch_Viewer(mContext, items.get(position));
                }
                else
                {
                    itemView = (Item_formatch_Viewer) convertView;
                }

                itemView.setText(FORMATCH_TEXT.HOSTNAME, items.get(position).getData(FORMATCH_TEXT.HOSTNAME));
                itemView.setText(FORMATCH_TEXT.PLAYDATE, items.get(position).getData(FORMATCH_TEXT.PLAYDATE));
                itemView.setText(FORMATCH_TEXT.GROUNDLOCATION, items.get(position).getData(FORMATCH_TEXT.GROUNDLOCATION));
                itemView.setText(FORMATCH_TEXT.TIME, items.get(position).getData(FORMATCH_TEXT.TIME));

                itemView.setEmblem(FORMATCH_IMAGE.HOST, items.get(position).getDrawable(FORMATCH_IMAGE.HOST));
                itemView.setEmblem(FORMATCH_IMAGE.ANOHTER, items.get(position).getDrawable(FORMATCH_IMAGE.ANOHTER));

            return itemView;
        }
    }

}
