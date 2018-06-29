package com.example.moniyue.view.adapter;


import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.example.moniyue.R;
import com.example.moniyue.model.bean.FenleifuBean;
import com.example.moniyue.model.bean.FenleizhiBean;
import com.example.moniyue.model.util.FrescoUtil;
import com.example.moniyue.view.activity.ProductListActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class FenleizhiAdapter extends RecyclerView.Adapter {

    private Context context;

    public FenleizhiAdapter(Context context) {
        this.context = context;
    }

    private List<FenleizhiBean.DataBean> list = new ArrayList<>();

    public void setData(List<FenleizhiBean.DataBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.fenleizhi_item, parent, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;
        viewHolder.tex.setText(list.get(position).getName());



        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
        ClassifyFenItemAdapter classifyFenItemAdapter = new ClassifyFenItemAdapter();
        viewHolder.gridView.setAdapter(classifyFenItemAdapter);
        viewHolder.gridView.setLayoutManager(gridLayoutManager);
        classifyFenItemAdapter.setData(list.get(position).getList());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tex;
        private RecyclerView gridView;

        public MyViewHolder(View itemView) {
            super(itemView);
            tex = itemView.findViewById(R.id.fenlei_zhi_item_tex);
            gridView = itemView.findViewById(R.id.gridrecyView);
        }

    }


    public class ClassifyFenItemAdapter extends RecyclerView.Adapter {
        private List<FenleizhiBean.DataBean.ListBean> listItem =new ArrayList<>();

        public void setData(List<FenleizhiBean.DataBean.ListBean> listaa){
            listItem.clear();
            listItem.addAll(listaa);
            notifyDataSetChanged();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.fenleizhisan_item,parent,false);
            return new MyViewHolder(inflate);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            MyViewHolder viewHolder= (MyViewHolder) holder;

            FrescoUtil.setJianJin(listItem.get(position).getIcon(),viewHolder.fenlei_zhisan_item_img);
            viewHolder.fenlei_zhisan_item_tex.setText(listItem.get(position).getName());

        }

        @Override
        public int getItemCount() {
            return listItem.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder{
            private SimpleDraweeView fenlei_zhisan_item_img;
            private TextView fenlei_zhisan_item_tex;

            public MyViewHolder(View itemView) {
                super(itemView);
                fenlei_zhisan_item_img = itemView.findViewById(R.id.fenlei_zhisan_item_img);
                fenlei_zhisan_item_tex = itemView.findViewById(R.id.fenlei_zhisan_item_tex);
            }
        }
    }
}