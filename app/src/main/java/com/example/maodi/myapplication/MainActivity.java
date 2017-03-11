package com.example.maodi.myapplication;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {

    ListView listv;
    private String[] name;
    private int[] age;
    private String[] grade;
    Boolean flag;
    int count = 0;
    private MyListViewAdapter adapter;
    ProgressBar progressBar;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            adapter.notifyDataSetChanged();
            progressBar.setVisibility(View.GONE);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listv = (ListView) findViewById(R.id.Lv);
        progressBar = (ProgressBar) findViewById(R.id.progress);

/*
        name = new String[]{"张毛弟","段旭辉","赵志强","曹燕"};
        age = new int[]{23,25,24,16};
        grade = new String[]{"T1","T2","T2","T3"};*/
        name = new String[30];
        age = new int[30];
        grade = new String[30];
        initData();
        adapter = new MyListViewAdapter();
        listv.setAdapter(adapter);
        listv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

                switch (scrollState) {
                    case SCROLL_STATE_IDLE:
                        progressBar.setVisibility(View.VISIBLE);
                        if (flag) {
                            new Thread() {
                                @Override
                                public void run() {
                                    super.run();
                                    try {
                                        sleep(  2000);
                                        initData();
                                        handler.sendEmptyMessage(0);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }

                                }
                            }.start();


                        }
                        break;
                }


            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                if (firstVisibleItem + visibleItemCount == totalItemCount) {
                    flag = true;
                } else {
                    flag = false;
                }


            }
        });
    }

    public void initData() {
        for (int i = 0; i < 30; i++) {
            name[i] = "张毛弟" + count;
            age[i] = 1 + count;
            grade[i] = "T1";
            count++;
        }
    }

    class MyListViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return name.length;
        }

        @Override
        public Object getItem(int position) {
            return name[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewholder;
            if (convertView == null) {
//                  convertView= LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_adapter,null);
                viewholder = new ViewHolder();
                convertView = convertView.inflate(MainActivity.this, R.layout.activity_adapter, null);
                viewholder.nametext = (TextView) convertView.findViewById(R.id.name);
                viewholder.agetext = (TextView) convertView.findViewById(R.id.age);
                viewholder.gradetext = (TextView) convertView.findViewById(R.id.grade);
                convertView.setTag(viewholder);
            } else {
                viewholder = (ViewHolder) convertView.getTag();
            }
            viewholder.nametext.setText(name[position]);
            viewholder.agetext.setText(age[position] + "");
            viewholder.gradetext.setText(grade[position]);


            return convertView;
        }
    }

    class ViewHolder {
        TextView nametext, agetext, gradetext;
    }
}



