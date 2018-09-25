package com.operation.resence.operationresence.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.operation.resence.operationresence.R;
import com.operation.resence.operationresencer.view.MyRecyclerView;
import com.operation.resence.operationresencer.view.MyTextview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuzhendong on 2018/9/13.
 */

public class RecyclerViewActivity extends AppCompatActivity{

    MyRecyclerView recyclerView;
    ArrayList<String> data = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        findViewById(R.id.label).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RecyclerViewActivity.this, MainActivity.class));
            }
        });
        recyclerView = (MyRecyclerView) findViewById(R.id.listview);
        for(int i = 0; i < 29; i ++){
            data.add("recyclerView item " + i);
        }
//        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        MyAdapter myAdapter = new MyAdapter(data,this);
        recyclerView.setAdapter(myAdapter);
        final ArrayList<View> views = new ArrayList<>();
        recyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {
                if(!views.contains(view))
                views.add(view);
//                Log.v("verf","%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
                for(int i = 0; i < recyclerView.getChildCount(); i++){
                    MyTextview myTextview = (MyTextview)views.get(i).findViewById(R.id.mytxt) ;
                    if(myTextview != null) {
//                        Log.v("verf", "recyclerview复用 " + myTextview.getText().toString()
//                                + " " + recyclerView.getChildLayoutPosition(recyclerView.getChildAt(i)) + " ");
                    }
                }

            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public static class MyAdapter extends RecyclerView.Adapter<MyViewHolder> implements View.OnClickListener{
        private List<String> list;
        private Context context;
        private onChildListener listener;
        private RecyclerView recyclerView;

        public void setListener(onChildListener listener) {
            this.listener = listener;
        }

        public MyAdapter(List<String> list, Context context) {
            this.list = list;
            this.context = context;
        }


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);
            final MyTextview myTextview = (MyTextview)view.findViewById(R.id.mytxt);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.v("verf","item被点击了现在 " + myTextview.getText().toString());
                }
            });
            return new MyViewHolder(view, viewType);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.billType.setText(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        @Override
        public void onClick(View view) {
//            int position = recyclerView.getChildAdapterPosition(view);
//            if (recyclerView != null && listener != null && !recyclerView.getItemAnimator().isRunning()) {
//                listener.onChildClick(recyclerView, view, position, list.get(position));
//            }
        }

        /**
         * 当连接到RecyclerView后，提供数据的时候调用这个方法
         * @param recyclerView
         */
        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
            this.recyclerView =recyclerView;
        }

        /**
         * 解绑
         * @param recyclerView
         */
        @Override
        public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
            super.onDetachedFromRecyclerView(recyclerView);
            recyclerView = null;
        }

        public interface onChildListener{
            void onChildClick(RecyclerView parent, View view, int position, String data);
        }
        public void addItem(int position,String data){
            list.add(position, data);
            notifyItemInserted(position);
        }
        public void removeItem(int position){
            list.remove(position);
            notifyItemRemoved(position);
        }
        public void changeItem(int position,String data){
            list.remove(position);
            list.add(position,data);
            notifyItemChanged(position);
        }

    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView billType;
        int type;
        public MyViewHolder(View itemView, int holderType) {
            super(itemView);
            type = holderType;
            billType = (TextView) itemView.findViewById(R.id.mytxt);
        }
    }
}
