package com.example.moniyue.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moniyue.R;
import com.example.moniyue.model.bean.CarBean;
import com.example.moniyue.model.bean.PriceAndCountBean;
import com.example.moniyue.model.util.FrescoUtil;
import com.example.moniyue.view.activity.MainActivity;
import com.example.moniyue.view.fragment.Fragmentcar;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class CarAdapter extends BaseExpandableListAdapter {
    //上下文和数据源
    private Context context;
    private List<CarBean.DataBean> group;
    private List<List<CarBean.DataBean.ListBean>> child;
    //private final LayoutInflater inflater;


    public CarAdapter(Context context, List<CarBean.DataBean> group, List<List<CarBean.DataBean.ListBean>> child) {
        this.context = context;
        this.group = group;
        this.child = child;
    }

    @Override
    public int getGroupCount() {
        return group.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return child.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return group.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return child.get(groupPosition).get(childPosition);
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
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        final GroupViewHolder groupViewHolder;
        if (convertView==null){
            //创建一级布局
            View view = View.inflate(context, R.layout.car_group_item,null);
            convertView=view;
            groupViewHolder = new GroupViewHolder();
            //初始化控件
            groupViewHolder.tv = view.findViewById(R.id.car_group_item_tex);
            groupViewHolder.cbGroup = view.findViewById(R.id.car_group_item_check);
            //放到容器中
            view.setTag(groupViewHolder);
        }else {
            //从容器中取出来
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        //赋值
        final CarBean.DataBean dataBean = group.get(groupPosition);
        groupViewHolder.tv.setText(dataBean.getSellerName());
        groupViewHolder.cbGroup.setChecked(dataBean.isChecked());
        //商家的多选框的点击事件
        groupViewHolder.cbGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //需要改变三个checkbox的状态值
                //1.一级列表的checkbox状态值，需要在bean中添加变量并给get，set方法
                dataBean.setChecked(groupViewHolder.cbGroup.isChecked());
                //2.二级列表的checkbox状态值
                setChildrenCb(groupPosition, groupViewHolder.cbGroup.isChecked());
                //3.全选的checkbox状态值，相当于调用了Activity中的全选的方法


           Fragmentcar.setAllChecked(isAllGroupCbChecked());



                //计算钱和数量并显示
                setPriceAndCount();
                //刷新界面
                notifyDataSetChanged();
            }
        });
        return convertView;
    }



    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ChildViewHolder holder;
        if (convertView==null){
            View view = View.inflate(context,R.layout.car_child_item,null);
            convertView=view;
            holder = new ChildViewHolder();
            holder.iv = view.findViewById(R.id.car_child_item_img);
            holder.tvTitle = view.findViewById(R.id.car_child_item_title);
            holder.tvSubhead = view.findViewById(R.id.car_child_item_Subhead);
            holder.tvPrice = view.findViewById(R.id.car_child_item_price);
            holder.cbChild = view.findViewById(R.id.car_child_item_check);
            holder.btDel = view.findViewById(R.id.car_child_item_btndelete);
            holder.tvNum = view.findViewById(R.id.car_child_item_num);
            holder.ivDel = view.findViewById(R.id.car_child_item_delete);
            holder.ivAdd = view.findViewById(R.id.car_child_item_Add);
            view.setTag(holder);
        }else {
            holder = (ChildViewHolder) convertView.getTag();
        }
        //赋值,首先获得二级列表的集合
        final CarBean.DataBean.ListBean listBean = child.get(groupPosition).get(childPosition);

        String images = listBean.getImages();
        String s=images;
        int i = images.indexOf("|");
        if (i!=-1){
            s = images.substring(0, i);
        }
        FrescoUtil.setJianJin(s, holder.iv);


        holder.tvTitle.setText(listBean.getTitle());
        holder.cbChild.setChecked(child.get(groupPosition).get(childPosition).isChecked());
        holder.tvSubhead.setText(listBean.getSubhead());
        holder.tvPrice.setText(listBean.getPrice() + "元");
        holder.tvNum.setText(listBean.getCount() + "");
        //给二级checkbox设置点击事件
        holder.cbChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //需要改变三个checkbox的状态值
                //1.二级列表的checkbox状态值
                listBean.setChecked(holder.cbChild.isChecked());
                //2.一级列表的checkbox状态值
                group.get(groupPosition).setChecked(isAllChildCbChecked(groupPosition));
                //3.全选的checkbox状态值

              Fragmentcar.setAllChecked(isAllGroupCbChecked());

                //计算钱和数量并显示
                setPriceAndCount();
                //刷新界面
                notifyDataSetChanged();
            }
        });
        //加号的点击监听
        holder.ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取目前显示的值
                int count = listBean.getCount();
                count++;
                //改变JavaBean里的状态值
                listBean.setCount(count);
                //计算钱和数量并显示
                setPriceAndCount();
                //刷新列表
                notifyDataSetChanged();
            }
        });
        //减号的点击监听
        holder.ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取目前显示的值
                int count = listBean.getCount();
                if (count <= 1) {
                    count = 1;
                } else {
                    count--;
                }
                //改变JavaBean里的状态值
                listBean.setCount(count);
                //计算钱和数量并显示
                setPriceAndCount();
                //刷新列表
                notifyDataSetChanged();
            }
        });

        //删除按钮的监听
        holder.btDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //其实就是删除集合
                List<CarBean.DataBean.ListBean> listBeans = child.get(groupPosition);
                if (listBeans.size() > 0) {
                    listBeans.remove(childPosition);
                }
                if (listBeans.size() == 0) {
                    child.remove(groupPosition);
                    group.remove(groupPosition);
                }
                //计算钱和数量并显示
                setPriceAndCount();
                //改变全选状态

                Fragmentcar.setAllChecked(isAllGroupCbChecked());

                //刷新列表
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    class GroupViewHolder{
        TextView tv;
        CheckBox cbGroup;
    }

    class ChildViewHolder{
        SimpleDraweeView iv;
        TextView tvTitle;
        TextView tvSubhead;
        TextView tvPrice;
        CheckBox cbChild;
        Button btDel;
        TextView tvNum;
        ImageView ivDel;
        ImageView ivAdd;
    }

    //设置一级列表和二级列表多选框的设置状态
    private void setChildrenCb(int groupPosition, boolean bool) {
        List<CarBean.DataBean.ListBean> listBeans = child.get(groupPosition);
        for (int i = 0; i < listBeans.size(); i++) {
            listBeans.get(i).setChecked(bool);
        }
    }
    //判断一级列表checkbox状态
    private boolean isAllGroupCbChecked() {
        if (group.size() == 0) {
            return false;
        }
        for (int i = 0; i < group.size(); i++) {
            if (!group.get(i).isChecked()) {
                return false;
            }
        }
        return true;
    }
    //判断二级列表checkbox状态
    private boolean isAllChildCbChecked(int groupPosition) {
        List<CarBean.DataBean.ListBean> listBeans = child.get(groupPosition);
        for (int i = 0; i < listBeans.size(); i++) {
            if (!listBeans.get(i).isChecked()) {
                return false;
            }
        }
        return true;
    }

    //设钱和数量
    private void setPriceAndCount() {
        Fragmentcar.setPriceAndCount(compute());
    }

    //计算钱和数量
    private PriceAndCountBean compute() {
        double price = 0;
        int count = 0;
        for (int i = 0; i < group.size(); i++) {
            List<CarBean.DataBean.ListBean> listBeans = child.get(i);
            for (int j = 0; j < listBeans.size(); j++) {
                if (listBeans.get(j).isChecked()) {
                    price += listBeans.get(j).getPrice() * listBeans.get(j).getCount();
                    count += listBeans.get(j).getCount();
                }
            }
        }
        return new PriceAndCountBean(price, count);
    }

    //全选或者全不选的方法
    public void AllOrNone(boolean bool) {
        for (int i = 0; i < group.size(); i++) {
            group.get(i).setChecked(bool);
            setChildrenCb(i, bool);
        }
        setPriceAndCount();
        notifyDataSetChanged();
    }

}