package com.wwj.popupwindow;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
    private Button setButton;
    private Button addButton;
    private Button btnShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);

        setButton = (Button) findViewById(R.id.btnSet);
        addButton = (Button) findViewById(R.id.btnAdd);
        btnShare = (Button) findViewById(R.id.btnShare);
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
                AddPopWindow addPopWindow = new AddPopWindow(MainActivity.this);
                addPopWindow.showPopupWindow(addButton);
                break;

            case R.id.btnShare:
                CustomShareBoard customShareBoard = new CustomShareBoard(this);
                customShareBoard.showAtLocation(this.getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
                break;

            default:
                break;
        }
    }
}
