package com.example.moniyue.view.adapter;


import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moniyue.R;
import com.example.moniyue.model.bean.FenleifuBean;
import com.example.moniyue.model.util.FrescoUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class FenleifuAdapter extends RecyclerView.Adapter {
    private Context context;
    private View item;
    private OnItemClick setOnItemClick;

    public void setOnItemClick(OnItemClick setOnItemClick) {
        this.setOnItemClick = setOnItemClick;
    }

    public FenleifuAdapter(Context context) {
        this.context = context;
    }

    private List<FenleifuBean.DataBean> list = new ArrayList<>();

    public void setData(List<FenleifuBean.DataBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.fenleifu_item, parent, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;


        FrescoUtil.setJianJin(list.get(position).getIcon(), viewHolder.img);
        viewHolder.tex.setText(list.get(position).getName());

        viewHolder.lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOnItemClick.setOnCl0ick(position, list);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView img;
        private TextView tex;
        LinearLayout lin;

        public MyViewHolder(View itemView) {
            super(itemView);
            item = itemView;
            lin = itemView.findViewById(R.id.lin);
            img = itemView.findViewById(R.id.fenlei_fu_item_img);
            tex = itemView.findViewById(R.id.fenlei_fu_item_tex);
        }

    }

    public interface OnItemClick {
        void setOnCl0ick(int pos, List<FenleifuBean.DataBean> list);
    }


}