package com.wwj.popupwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import android.widget.Toast;

/**
 * 
 */
public class CustomShareBoard extends PopupWindow implements OnClickListener {

//    private UMSocialService mController = UMServiceFactory.getUMSocialService(JNConstants.DESCRIPTOR);
    private Activity mActivity;

    public CustomShareBoard(Activity activity) {
        super(activity);
        this.mActivity = activity;
        initView(activity);
    }

    @SuppressWarnings("deprecation")
    private void initView(Context context) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.custom_board, null);
        rootView.findViewById(R.id.layout_share).setOnClickListener(this);
        rootView.findViewById(R.id.wechat).setOnClickListener(this);
        rootView.findViewById(R.id.wechat_circle).setOnClickListener(this);
//        rootView.findViewById(R.id.qq).setOnClickListener(this);
//        rootView.findViewById(R.id.qzone).setOnClickListener(this);
        setContentView(rootView);
        setWidth(LayoutParams.MATCH_PARENT);
        setHeight(LayoutParams.WRAP_CONTENT);
        setFocusable(true);
        setBackgroundDrawable(new BitmapDrawable());
        setTouchable(true);
        this.setAnimationStyle(R.style.popWindow_animation);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.wechat:
//                performShare(SHARE_MEDIA.WEIXIN);

                Toast.makeText(mActivity, "微信分享", Toast.LENGTH_SHORT).show();
                break;
            case R.id.wechat_circle:
                Toast.makeText(mActivity, "朋友圈分享", Toast.LENGTH_SHORT).show();
//                performShare(SHARE_MEDIA.WEIXIN_CIRCLE);
                break;
//            case R.id.qq:
//                performShare(SHARE_MEDIA.QQ);
//                break;
//            case R.id.qzone:
//                performShare(SHARE_MEDIA.QZONE);
//                break;
            case R.id.layout_share:
                dismiss();
            default:
                break;
        }
    }

//    private void performShare(SHARE_MEDIA platform) {
//        mController.postShare(mActivity, platform, new SnsPostListener() {
//
//            @Override
//            public void onStart() {
//
//            }
//
//            @Override
//            public void onComplete(SHARE_MEDIA platform, int eCode, SocializeEntity entity) {
//                String showText = platform.toString();
//                if (eCode == StatusCode.ST_CODE_SUCCESSED) {
//                    showText += "平台分享成功";
//                } else {
//                    showText += "平台分享失败";
//                }
//
//                Log.e(JNConstants.APP_TAG,showText);
//
//            }
//        });
//    }

}
