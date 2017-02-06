package com.wwj.popupwindow;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wwj.popupwindow.entity.ItemBean;

import java.util.List;

/**
 * Created by wangrui on 2017/1/23.
 */

public class BottomSelectPop extends PopupWindow implements View.OnClickListener{

    private List<ItemBean> itemBeans;
    private LayoutInflater inflater;
    private SelectListener listener;

    public BottomSelectPop(Context context, List<ItemBean> itemBeans,SelectListener listener) {
        super(context);
        this.itemBeans = itemBeans;
        inflater = LayoutInflater.from(context);
        init(context);
        this.listener = listener;
    }

    private void init(Context context){

        View view = inflater.inflate(R.layout.bottom_select_layout,null);
        ListView list_item = (ListView) view.findViewById(R.id.list_item);
        RelativeLayout layout_root = (RelativeLayout) view.findViewById(R.id.layout_root);
        ItemAdapter adapter=new ItemAdapter();
        list_item.setAdapter(adapter);

        setContentView(view);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setFocusable(true);
        setBackgroundDrawable(new BitmapDrawable());
        setTouchable(true);
        this.setAnimationStyle(R.style.popWindow_animation);

        layout_root.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.layout_root){
            dismiss();
        }
    }

    class ItemAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return itemBeans.size();
        }

        @Override
        public Object getItem(int position) {
            return itemBeans.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;

            if(convertView==null){
                convertView=inflater.inflate(R.layout.item_select,null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            }else{
                holder=(ViewHolder) convertView.getTag();
            }

            holder.tvName.setText(itemBeans.get(position).getName());

            if(itemBeans.get(position).isSelected()){
                holder.ivSelect.setVisibility(View.VISIBLE);
            }else {
                holder.ivSelect.setVisibility(View.GONE);
            }

            holder.item_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    for(int i=0;i<itemBeans.size();i++){
                        if(i==position){
                            itemBeans.get(position).setSelected(true);
                        }else {
                            itemBeans.get(i).setSelected(false);
                        }
                    }
                    listener.setSelectItem(position);
                    notifyDataSetChanged();

                }
            });

            return convertView;
        }

        class ViewHolder{
            TextView tvName;
            ImageView ivSelect;
            RelativeLayout item_layout;

            public ViewHolder(View view){
                tvName=(TextView)view.findViewById(R.id.item_tv_name);
                ivSelect = (ImageView) view.findViewById(R.id.item_checkbox);
                item_layout = (RelativeLayout)view.findViewById(R.id.item_layout);
            }
        }
    }

    interface SelectListener {
        void setSelectItem(int position);
    }

}
