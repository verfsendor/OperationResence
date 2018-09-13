package com.operation.resence.operationresence.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.diy.charts.view.SlikLineChart;
import com.operation.resence.operationresence.R;
import com.operation.resence.operationresencer.view.MyTextview;

import java.util.ArrayList;

/**
 * Created by xuzhendong on 2018/9/13.
 */

public class ListViewActivity extends AppCompatActivity{

    ListView listView;
    ArrayList<String> data = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        listView = (ListView)findViewById(R.id.listview);
        for(int i = 0; i < 29; i ++){
            data.add("item " + i);
        }
        listView.setAdapter(new MyAdapter(this));
        findViewById(R.id.label).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListViewActivity.this, SlikLineChartActivity.class));
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListViewActivity.this, data.get(position), Toast.LENGTH_LONG).show();
            }
        });
    }


    public class MyAdapter extends BaseAdapter{
        Context context;
        public MyAdapter(Context context){
            this.context = context;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            viewHolder viewHolder;
            if(convertView == null){
                convertView = LayoutInflater.from(context).inflate(R.layout.item_list,null);
                viewHolder = new viewHolder();
                convertView.setTag(viewHolder);
            }else {
                viewHolder = (ListViewActivity.viewHolder) convertView.getTag();
            }
            viewHolder.textview = (MyTextview)convertView.findViewById(R.id.mytxt);
            viewHolder.textview.setText(data.get(position));
            convertView.setTag(viewHolder);
            return convertView;
        }

    }

    public class viewHolder{
        MyTextview textview;
    }
}
