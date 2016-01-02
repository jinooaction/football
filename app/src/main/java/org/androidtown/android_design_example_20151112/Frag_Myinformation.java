package org.androidtown.android_design_example_20151112;

import android.app.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.hardware.input.InputManager;
import android.inputmethodservice.InputMethodService;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.zip.Inflater;



public class Frag_Myinformation extends Fragment implements View.OnClickListener{

    boolean isResidencePageOpen = false;
    boolean isPositionPageOpen = false;

    MainActivity hostActivity;

    ViewGroup dialog_name;
    EditText dialogNameBox;
    AlertDialog alertDialog_name = null;
    AlertDialog alertDialog_cache = null;

    TextView nameView; //이름 Text
    TextView pointView; //Point Text
    Button positionView;
    Button residenceView;

    RelativeLayout residencePage;
    RelativeLayout positionPage;

    Animation translateUpAnim;
    Animation translateDownAnim;

    private ArrayList<String> mGroupList=null;
    private ArrayList<ArrayList<String>> mChildList = null;
    private ArrayList<String> mSeoulChildList = null;
    private ArrayList<String> mIncheonChildList=null;
    private ArrayList<String> mGangWonChildList=null;

    private ExpandableListView mListView;

    private ArrayList<String> mPositionGroupList=null;
    private ArrayList<ArrayList<String>> mPositionChildList = null;
    private ArrayList<String> mPositionFWChildList = null;
    private ArrayList<String> mPositionMFChildList=null;
    private ArrayList<String> mPositionDFChildList=null;
    private ArrayList<String> mPositionGKChildList= null;

    private ExpandableListView mPositionListView;



    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.nameText:
                generateNameDialog();
                break;
            case R.id.pointView:
                generateCacheDialog();
                break;

            case R.id.positionText:
                if(isPositionPageOpen){
                    positionPage.startAnimation(translateDownAnim);
                }
                else
                {
                    positionPage.setVisibility(View.VISIBLE);
                    positionPage.startAnimation(translateUpAnim);
                }
                break;

            case R.id.residenceText:
                if(isResidencePageOpen){
                    residencePage.startAnimation(translateDownAnim);
                }
                else
                {
                    residencePage.setVisibility(View.VISIBLE);
                    residencePage.startAnimation(translateUpAnim);
                }
                break;

            default:
                Log.d("MyActivity", "default is clicked");
                if(isResidencePageOpen){
                    residencePage.startAnimation(translateDownAnim);
                }
                else if(isPositionPageOpen){
                    positionPage.startAnimation(translateDownAnim);
                }

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final ViewGroup viewGroupList = (ViewGroup)inflater.inflate(R.layout.fragment_frag__myinformation, container, false);

        initViews(viewGroupList);
        initLists(viewGroupList);
        setOnClickListeners(viewGroupList);
        setAnimations();

        //Dialog 설정 (이름 수정)
        setDialog(inflater, container);

        viewGroupList.setFocusableInTouchMode(true); //이게 머하는 코드여?
        viewGroupList.requestFocus(); //이게 뭐하는 코드여?

        return viewGroupList;
    }

    private void initViews(ViewGroup viewGroupList) {
        hostActivity = (MainActivity)getActivity();

        nameView = (TextView)viewGroupList.findViewById(R.id.nameText);
        pointView = (TextView)viewGroupList.findViewById(R.id.pointView);
        positionView = (Button)viewGroupList.findViewById(R.id.positionText);
        residenceView = (Button)viewGroupList.findViewById(R.id.residenceText);

        residencePage = (RelativeLayout)viewGroupList.findViewById(R.id.residencePage);
        positionPage = (RelativeLayout)viewGroupList.findViewById(R.id.positionPage);
    }

    private void initLists(ViewGroup viewGroupList){
        mListView = (ExpandableListView)viewGroupList.findViewById(R.id.residenceListView);
        mGroupList = new ArrayList<String>();
        mChildList = new ArrayList<ArrayList<String>>();
        mSeoulChildList = new ArrayList<String>();
        mIncheonChildList = new ArrayList<String>();
        mGangWonChildList = new ArrayList<String>();

        mPositionListView = (ExpandableListView)viewGroupList.findViewById(R.id.positionListView);
        mPositionGroupList = new ArrayList<String>();
        mPositionChildList = new ArrayList<ArrayList<String>>();
        mPositionFWChildList = new ArrayList<String>();
        mPositionMFChildList =new ArrayList<String>();
        mPositionDFChildList = new ArrayList<String>();
        mPositionGKChildList = new ArrayList<String>();

        mPositionGroupList.add("FW");
        mPositionGroupList.add("MF");
        mPositionGroupList.add("DF");
        mPositionGroupList.add("GK");

        mPositionFWChildList.add("LWF");
        mPositionFWChildList.add("ST");
        mPositionFWChildList.add("RWF");

        mPositionMFChildList.add("LWM");
        mPositionMFChildList.add("CAM");
        mPositionMFChildList.add("RWM");
        mPositionMFChildList.add("LM");
        mPositionMFChildList.add("CM");
        mPositionMFChildList.add("RM");
        mPositionMFChildList.add("CDM");

        mPositionDFChildList.add("LWB");
        mPositionDFChildList.add("RWB");
        mPositionDFChildList.add("LB");
        mPositionDFChildList.add("CB");
        mPositionDFChildList.add("RB");

        mPositionGKChildList.add("GK");

        mPositionChildList.add(mPositionFWChildList);
        mPositionChildList.add(mPositionMFChildList);
        mPositionChildList.add(mPositionDFChildList);
        mPositionChildList.add(mPositionGKChildList);

        mPositionListView.setAdapter(new ExampleExpandableAdapter(getContext(), mPositionGroupList, mPositionChildList));

        mPositionListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                positionView.setText(mPositionListView.getExpandableListAdapter().getChild(groupPosition, childPosition).toString());
                //서버에 변경사항 보내기
                mPositionListView.collapseGroup(groupPosition); //근데 이 효과 좀 거지같에
                positionPage.startAnimation(translateDownAnim);
                return false;
            }
        });


        mGroupList.add("서울");
        mGroupList.add("경기/인천");
        mGroupList.add("강원도");

        mSeoulChildList.add("강남구");
        mSeoulChildList.add("은평구");
        mSeoulChildList.add("마포구");

        mIncheonChildList.add("수원시");
        mIncheonChildList.add("고양시");
        mIncheonChildList.add("구리시");

        mGangWonChildList.add("홍천군");
        mGangWonChildList.add("평창군");
        mGangWonChildList.add("춘천시");

        mChildList.add(mSeoulChildList);
        mChildList.add(mIncheonChildList);
        mChildList.add(mGangWonChildList);

        mListView.setAdapter(new ExampleExpandableAdapter(getContext(), mGroupList, mChildList));

        mListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                residenceView.setText(mListView.getExpandableListAdapter().getChild(groupPosition, childPosition).toString());
                //서버에 변경사항 보내기
                mListView.collapseGroup(groupPosition);
                residencePage.startAnimation(translateDownAnim);
                return false;
            }
        });
    }

    private void setOnClickListeners(ViewGroup viewGroupList) {
        pointView.setOnClickListener(this);
        positionView.setOnClickListener(this);
        residenceView.setOnClickListener(this);
        nameView.setOnClickListener(this);

        viewGroupList.setOnClickListener(this);
    }

    private void setAnimations() {
        SlidingPageAnimationListener animListener = new SlidingPageAnimationListener();

        translateUpAnim = AnimationUtils.loadAnimation(getContext(), R.anim.translate_up);
        translateDownAnim = AnimationUtils.loadAnimation(getContext(), R.anim.translate_down);

        translateUpAnim.setAnimationListener(animListener);
        translateDownAnim.setAnimationListener(animListener);
    }

    private void setDialog(LayoutInflater inflater, ViewGroup container)
    {
        dialog_name = (ViewGroup)inflater.inflate(R.layout.name_dialog, container, false);
        dialogNameBox = (EditText)dialog_name.findViewById(R.id.dialogNameBox);
        dialogNameBox.setHint(nameView.getText().toString());
    }

    private void generateNameDialog()
    {
        if(alertDialog_name==null)
        {
            alertDialog_name = new AlertDialog.Builder(getContext())
                    .setTitle("이름 수정")
                    .setView((View)dialog_name)
                    .setNeutralButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            nameView.setText(dialogNameBox.getText().toString());
                            //서버로 이름 전송하기
                        }
                    })
                    .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getContext(),"이름 변경 취소", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .create();
            alertDialog_name.show();
        }
        else
        {
            alertDialog_name.show();
        }
    }

    private void generateCacheDialog()
    {
        if(alertDialog_cache == null)
        {
            alertDialog_cache = new AlertDialog.Builder(getContext())
                    .setTitle("확인")
                    .setMessage("포인트 샾으로 이동하시겠습니까?")
                    .setNeutralButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //캐시샾 페이지로 이동
                            hostActivity.setPage(3);
                        }
                    })
                    .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //취소
                        }
                    }).create();
            alertDialog_cache.show();
        }
        else
        {
            alertDialog_cache.show();
        }
    }

    private class SlidingPageAnimationListener implements Animation.AnimationListener
    {
        @Override
        public void onAnimationEnd(Animation animation) {
            if(isResidencePageOpen){
                residencePage.setVisibility(View.INVISIBLE);
                isResidencePageOpen=false;
            }
            else if(residencePage.getVisibility() == View.VISIBLE){
                isResidencePageOpen=true;
            }

            if(isPositionPageOpen)
            {
                positionPage.setVisibility(View.INVISIBLE);
                isPositionPageOpen=false;
            }
            else if(positionPage.getVisibility() == View.VISIBLE)
            {
                isPositionPageOpen=true;
            }
        }

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }


    private class ExampleExpandableAdapter extends BaseExpandableListAdapter
    {
        private ArrayList<String> groupList = null;
        private ArrayList<ArrayList<String>> childList =null;
        private LayoutInflater inflater=null;
        ViewHolder viewHolder = null;

        public ExampleExpandableAdapter(Context c, ArrayList<String> groupList, ArrayList<ArrayList<String>> childList )
        {
            this.inflater=LayoutInflater.from(c);
            this.groupList=groupList;
            this.childList = childList;
        }

        @Override
        public String getChild(int groupPosition, int childPosition) {
            return childList.get(groupPosition).get(childPosition);
        }

        @Override
        public int getGroupCount() {
            return groupList.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return childList.get(groupPosition).size();
        }

        @Override
        public String getGroup(int groupPosition) {
            return groupList.get(groupPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }//이건머야

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            View v=convertView;

            if(v==null)
            {
                viewHolder = new ViewHolder();
                v=inflater.inflate(R.layout.listviewitem, parent, false);
                viewHolder.groupName=(TextView)v.findViewById(R.id.groupText);
                v.setTag(viewHolder); //이거 머여?
            }
            else{
                viewHolder = (ViewHolder)v.getTag();
            }
            viewHolder.groupName.setText(getGroup(groupPosition));

            return v;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            View v =convertView;

            if(v==null)
            {
                viewHolder=new ViewHolder();
                v=inflater.inflate(R.layout.listviewitem, null);
                viewHolder.childName = (TextView)v.findViewById(R.id.childText);
                v.setTag(viewHolder);
            }
            else{
                viewHolder = (ViewHolder)v.getTag();
            }
            viewHolder.childName.setText(getChild(groupPosition,childPosition));

            return v;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }//이건머야

        class ViewHolder{
            //public ImageView image;
            public TextView groupName;
            public TextView childName;
        }
    }


}
