package com.wwj.popupwindow;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.wwj.popupwindow.entity.ItemBean;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements OnClickListener ,BottomSelectPop.SelectListener{
    private Button setButton;
    private Button addButton;
    private Button btnShare;
    private CommonWidgetButton widgetButton;
    private CommonWidgetButton widgetButton2;
    private CommonWidgetButton widgetButton3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);

        setButton = (Button) findViewById(R.id.btnSet);
        addButton = (Button) findViewById(R.id.btnAdd);
        btnShare = (Button) findViewById(R.id.btnShare);
        widgetButton= (CommonWidgetButton) findViewById(R.id.widgetbutton);
        widgetButton2 = (CommonWidgetButton) findViewById(R.id.widgetbutton2);
        widgetButton3 = (CommonWidgetButton) findViewById(R.id.widgetbutton3);
        setButton.setOnClickListener(this);
        addButton.setOnClickListener(this);
        btnShare.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSet:
                MorePopWindow morePopWindow = new MorePopWindow(MainActivity.this);
                morePopWindow.showPopupWindow(setButton);
                break;
            case R.id.btnAdd:
//                AddPopWindow addPopWindow = new AddPopWindow(MainActivity.this);
//                addPopWindow.showPopupWindow(addButton);

                showSelectItem();

                break;

            case R.id.btnShare:
                CustomShareBoard customShareBoard = new CustomShareBoard(this);
                customShareBoard.showAtLocation(this.getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
                break;


            default:
                break;
        }
    }

    private void showSelectItem() {
        List<ItemBean> itemBeanList=new ArrayList<ItemBean>();
        for(int i=0;i<10;i++){
            ItemBean bean=new ItemBean();
            bean.setSelected(false);
            bean.setId(i+"");
            bean.setName("老王"+i);
            itemBeanList.add(bean);
        }

        BottomSelectPop pop=new BottomSelectPop(this,itemBeanList,this);

        pop.showAtLocation(this.getWindow().getDecorView(), Gravity.BOTTOM,0,0);
    }

    @Override
    public void setSelectItem(int position) {
        Toast.makeText(this, "选中"+position, Toast.LENGTH_SHORT).show();
    }
}
